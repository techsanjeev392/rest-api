package com.springboot3.restapis.service.student.executors;

import com.springboot3.restapis.dto.Response;
import com.springboot3.restapis.model.Library;
import com.springboot3.restapis.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.Callable;

public class StudentExecutor implements Callable<Response> {

    private LibraryRepository libraryRepository;
    private Library libraryDetails;

    private final Callable<Response> task;

    public StudentExecutor(Callable<Response> task, LibraryRepository libraryRepository,Library libraryDetails) {
        this.task = task;
        this.libraryRepository = libraryRepository;
        this.libraryDetails = libraryDetails;
    }

    @Override
    public Response call() throws Exception {
        // basic validation
        if (libraryRepository == null) {
            return Response.error("LibraryRepository not initialized", 500);
        }
        if (libraryDetails == null) {
            return Response.error("Library details are required", 400);
        }

        try {
            // persist the library record
            Library saved = libraryRepository.save(libraryDetails);

            // run optional post-processing task (side-effects). Don't let its failure hide the saved result.
            if (task != null) {
                try {
                    task.call();
                } catch (Exception ex) {
                    // return success but include a warning message about post-processing failure
                    return Response.ok(saved, "Saved but post-processing task failed: " + ex.getMessage());
                }
            }

            return Response.created(saved);
        } catch (org.springframework.dao.DataIntegrityViolationException dive) {
            String msg = dive.getMostSpecificCause() != null ? dive.getMostSpecificCause().getMessage() : dive.getMessage();
            return Response.error("Constraint violation: " + msg, 409);
        } catch (Exception e) {
            return Response.error("Failed to save library: " + e.getMessage(), 500);
        }

    }


}
