package com.springboot3.restapis.controller;

import com.springboot3.restapis.dto.Response;
import com.springboot3.restapis.model.Library;
import com.springboot3.restapis.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/libarary")
public class LibraryController {

    @Autowired
    private LibraryRepository libraryRepository;

    // GET /api/libarary - return all library records
    @GetMapping
    public ResponseEntity<Response<List<Library>>> getAll() {
        List<Library> list = libraryRepository.findAll();
        Response<List<Library>> resp = Response.ok(list, "Fetched " + list.size() + " library records");
        return ResponseEntity.status(resp.getStatus()).body(resp);
    }

    // GET /api/libarary/{id} - return a single library record by id
    @GetMapping("/{id}")
    public ResponseEntity<Response<Library>> getById(@PathVariable Long id) {
        return libraryRepository.findById(id)
                .map(lib -> ResponseEntity.status(200).body(Response.ok(lib)))
                .orElseGet(() -> ResponseEntity.status(404).body(Response.error("Library not found", 404)));
    }

    // GET /api/libarary/student/{studentId} - return all library records for a given student id
    @GetMapping("/student/{studentId}")
    public ResponseEntity<Response<List<Library>>> getByStudentId(@PathVariable Long studentId) {
        List<Library> list = libraryRepository.findAll().stream()
                .filter(lib -> lib.getStudent() != null && Objects.equals(lib.getStudent().getId(), studentId))
                .collect(Collectors.toList());

        Response<List<Library>> resp = Response.ok(list, "Fetched " + list.size() + " library records for student " + studentId);
        return ResponseEntity.status(resp.getStatus()).body(resp);
    }

}
