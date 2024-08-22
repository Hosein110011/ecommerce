package com.ecommerce.demo.controller;


import com.ecommerce.demo.model.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api2")
public class StudentController {

    private List<Student> students = new ArrayList<>(List.of(
       new Student(1, "farid", 22),
       new Student(2, "george", 33)
    ));

    @GetMapping("/students")
    public List<Student> getStudents() {
        return students;
    }

    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @PostMapping("/students")
    public String addStudent(@RequestBody Student student) {
        students.add(student);
        return "success";
    }
}
