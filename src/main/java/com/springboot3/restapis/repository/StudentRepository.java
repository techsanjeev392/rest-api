package com.springboot3.restapis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.springboot3.restapis.model.Student;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmailId(String emailId);

    @Query(value = "SELECT * FROM student WHERE name = :name", nativeQuery = true)
    Optional<Student> findByName(String name);
}
