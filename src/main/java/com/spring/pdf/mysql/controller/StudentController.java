package com.spring.pdf.mysql.controller;

import com.spring.pdf.mysql.entity.Student;
import com.spring.pdf.mysql.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@RequestMapping("/student")
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/insertData")
    public Student insertStudentData(@RequestBody Student student) {
        return studentService.insertData(student);
    }
    @GetMapping("/getData")
    public List<Student> getStudentData() {
        return studentService.getData();
    }
    @PutMapping("/update")
    public Student updateStudent(@RequestBody Student student) {
        return studentService.updateData(student);
    }

    @GetMapping(value = "/getStudentPdf")
    public ResponseEntity<InputStreamResource> getReportPdf() throws IOException {
    List<Student> studentList = studentService.findAll();
        ByteArrayInputStream byteArrayInputStream = studentService.studentPDFReport(studentList);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "inline: filename=employees.pdf");
        return ResponseEntity.ok().headers(httpHeaders).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(byteArrayInputStream));
    }
}
