package com.springboot3.restapis.controller;

import com.springboot3.restapis.dto.Response;
import com.springboot3.restapis.dto.StudentDto;
import com.springboot3.restapis.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class Controller {

    @Autowired
    StudentService services;

    @PostMapping
    public ResponseEntity<Response<StudentDto>> create(@RequestBody StudentDto student) {
        Response<StudentDto> resp = services.create(student);
        return ResponseEntity.status(resp.getStatus()).body(resp);
    }

    @GetMapping
    public ResponseEntity<Response<List<StudentDto>>> getAll() {
        Response<List<StudentDto>> resp = services.findAll();
        return ResponseEntity.status(resp.getStatus()).body(resp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<StudentDto>> getById(@PathVariable Long id) {
        Response<StudentDto> resp = services.findById(id);
        return ResponseEntity.status(resp.getStatus()).body(resp);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<StudentDto>> update(@PathVariable Long id, @RequestBody StudentDto student) {
        Response<StudentDto> resp = services.update(id, student);
        return ResponseEntity.status(resp.getStatus()).body(resp);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Void>> delete(@PathVariable Long id) {
        Response<Void> resp = services.deleteById(id);
        return ResponseEntity.status(resp.getStatus()).body(resp);
    }

    @GetMapping("/{id}/exists")
    public ResponseEntity<Response<Boolean>> exists(@PathVariable Long id) {
        Response<Boolean> resp = services.existsById(id);
        return ResponseEntity.status(resp.getStatus()).body(resp);
    }

}
