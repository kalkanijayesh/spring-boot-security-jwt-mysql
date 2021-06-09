package spring.mysqljwtauthdemo.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.mysqljwtauthdemo.demo.model.AuthorizationRequest;
import spring.mysqljwtauthdemo.demo.model.AuthorizationResponse;
import spring.mysqljwtauthdemo.demo.model.Student;
import spring.mysqljwtauthdemo.demo.repository.StudentRepository;
import spring.mysqljwtauthdemo.demo.service.DefaultUser;
import spring.mysqljwtauthdemo.demo.service.StudentService;
import spring.mysqljwtauthdemo.demo.utility.JwtUtil;

@RestController
public class StudentController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    StudentService studentService;

    @Autowired
    DefaultUser defaultUser;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/authenticate") // Should be accessible everytime.
    private ResponseEntity<?> authenticate(@RequestBody AuthorizationRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),
                    request.getPassword(), new ArrayList<>()));
        } catch (Exception e) {
            throw new IllegalStateException("Invalid username or password");
        }

        // Create a jwt token based on the user details.
        final UserDetails userDetails = defaultUser.loadUserByUsername(request.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthorizationResponse(jwt));
    }

    @GetMapping
    private List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // Get - Get all the students [done]
    // Post - Save the student details [done]
    // Put - Update the student details (Email, Name) [Done]
    // Del - Delete the student from email id, id.

    @PostMapping
    private void addStudent(@RequestBody Student student) {
        // Save into the database.
        // Add validation here
        studentService.addStudent(student);
    }

    @PutMapping("/{id}")
    private void updateStudent(@PathVariable("id") Long id, @RequestBody Student student) {
        studentService.updateStudent(student, id);
    }

    @DeleteMapping("/{id}")
    private void deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
    }

}

// Model
// Controller (Collection of endpoints)
// Service (Business Logic)
// Repository (JPA)

// @@Annotations@@
// GetMapping
// PostMapping
// PutMapping
// DeleteMapping
// Transactional
// Service
// RestController
// Reporsitory
// Component (We haven't used this annotation in this project yet) Don't worry

// Done endpoints related tasks
// Let's go for security [Done]
