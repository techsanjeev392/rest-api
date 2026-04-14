package com.springboot3.restapis.repository;

import com.springboot3.restapis.model.Library;
import com.springboot3.restapis.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {

}
