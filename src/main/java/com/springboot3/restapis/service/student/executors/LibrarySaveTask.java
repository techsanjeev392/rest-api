package com.springboot3.restapis.service.student.executors;

import com.springboot3.restapis.dto.Response;
import com.springboot3.restapis.model.Library;
import com.springboot3.restapis.repository.LibraryRepository;

import java.util.concurrent.Callable;

public class LibrarySaveTask implements Callable<Response> {

    private final LibraryRepository libraryRepository;
    private final Library libraryDetails;

    public LibrarySaveTask(LibraryRepository libraryRepository, Library libraryDetails) {
        this.libraryRepository = libraryRepository;
        this.libraryDetails = libraryDetails;
    }

    @Override
    public Response call() {
        try {


            if (libraryRepository == null) {
                return Response.error("LibraryRepository not initialized", 500);
            }

            if (libraryDetails == null) {
                return Response.error("Library details are required", 400);
            }
            Library saved = libraryRepository.save(libraryDetails);
            return Response.created(saved);


        } catch (Exception e) {
            return Response.error("Execution failed: " + e.getMessage(), 500);
        }
    }
}