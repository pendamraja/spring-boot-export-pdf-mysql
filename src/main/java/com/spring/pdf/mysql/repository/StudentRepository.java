package com.spring.pdf.mysql.repository;

import com.spring.pdf.mysql.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
