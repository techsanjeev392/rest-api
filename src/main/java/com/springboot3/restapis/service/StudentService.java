package com.springboot3.restapis.service;

import com.springboot3.restapis.config.BookConfig;
import com.springboot3.restapis.dto.Response;
import com.springboot3.restapis.dto.StudentDto;
import com.springboot3.restapis.interfaces.CurdInterface;
import com.springboot3.restapis.model.Library;
import com.springboot3.restapis.model.Student;
import com.springboot3.restapis.repository.LibraryRepository;
import com.springboot3.restapis.repository.StudentRepository;
import com.springboot3.restapis.service.student.executors.LibrarySaveTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class StudentService implements CurdInterface<StudentDto, Long> {

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository repository;
    private final LibraryRepository libraryRepository;

    @Autowired
    private BookConfig bookConfig;


    @Autowired
    public StudentService(StudentRepository repository, LibraryRepository libraryRepository) {
        this.repository = repository;
        this.libraryRepository = libraryRepository;
    }

    private StudentDto toDto(Student s) {
        if (s == null) return null;
        return new StudentDto(s.getId(), s.getName(), s.getEmailId());
    }

    private Student toEntity(StudentDto d) {
        if (d == null) return null;
        return new Student(d.getId(), d.getName(), d.getEmailId());
    }

    @Override
    public Response<StudentDto> create(StudentDto entity) {
        // check unique email
        if (entity == null) return Response.error("Student data is required", HttpStatus.BAD_REQUEST.value());
        Optional<Student> exists = repository.findByEmailId(entity.getEmailId());
        if (exists.isPresent()) {
            return Response.error("Email already exists", HttpStatus.CONFLICT.value());
        }
        Student saved = repository.save(toEntity(entity));

        // After successfully saving student, insert common books associated with this student
        List<Response> libraryResponses = new ArrayList<>();
        bookConfig.getNames().forEach(title ->
                {
                    String isbn = "ISBN-" + (1000 + new Random().nextInt(9000));
                    Library lib = new Library(title, null, isbn, saved);
                    libraryResponses.add(persistLibrary(lib));
                }

        );


        // Log all responses at once
        logger.info("Inserted {} library records for student {}: {}", libraryResponses.size(), saved.getId(), libraryResponses.toString());

        return Response.created(toDto(saved));
    }

    private Response<?> persistLibrary(Library lib) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        try {
            LibrarySaveTask exec = new LibrarySaveTask(libraryRepository, lib);
            Future<Response> future = executor.submit(exec);

            return future.get(); // wait for result
        } catch (Exception e) {
            logger.error("Library insert task failed for title '{}': {}", lib.getTitle(), e.getMessage(), e);
            return Response.error("Executor failed: " + e.getMessage(), 500);
        } finally {
            executor.shutdown();
        }
    }

    @Override
    public Response<StudentDto> update(Long id, StudentDto entity) {
        if (entity == null) return Response.error("Student data is required", HttpStatus.BAD_REQUEST.value());
        return repository.findById(id)
                .map(existing -> {
                    // if email changed, ensure uniqueness
                    if (!existing.getEmailId().equals(entity.getEmailId())) {
                        Optional<Student> byEmail = repository.findByEmailId(entity.getEmailId());
                        if (byEmail.isPresent() && !byEmail.get().getId().equals(id)) {
                            return Response.<StudentDto>error("Email already in use", HttpStatus.CONFLICT.value());
                        }
                    }
                    existing.setName(entity.getName());
                    existing.setEmailId(entity.getEmailId());
                    Student updated = repository.save(existing);
                    return Response.ok(toDto(updated), "Updated successfully");
                })
                .orElseGet(() -> Response.error("Student not found", HttpStatus.NOT_FOUND.value()));
    }

    @Override
    public Response<StudentDto> findById(Long id) {
        return repository.findById(id)
                .map(s -> Response.ok(toDto(s)))
                .orElseGet(() -> Response.error("Student not found", HttpStatus.NOT_FOUND.value()));
    }

    @Override
    public Response<List<StudentDto>> findAll() {
        List<StudentDto> list = repository.findAll().stream().map(this::toDto).collect(Collectors.toList());
        return Response.ok(list, "Fetched " + list.size() + " students");
    }

    @Override
    public Response<Void> deleteById(Long id) {
        if (!repository.existsById(id)) {
            return Response.error("Student not found", HttpStatus.NOT_FOUND.value());
        }
        repository.deleteById(id);
        return Response.ok(null, "Deleted");
    }

    @Override
    public Response<Boolean> existsById(Long id) {
        boolean exists = repository.existsById(id);
        return Response.ok(exists, exists ? "Exists" : "Does not exist");
    }
}
