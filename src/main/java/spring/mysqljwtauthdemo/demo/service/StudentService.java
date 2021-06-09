package spring.mysqljwtauthdemo.demo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.mysqljwtauthdemo.demo.model.Student;
import spring.mysqljwtauthdemo.demo.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Transactional
    public void updateStudent(Student student, Long id) {
        Student studentObj = studentRepository.findStudentById(id)
                .orElseThrow(() -> new IllegalStateException("Id not exist !!"));

        studentObj.setUser_full_name(student.getUser_full_name());
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void addStudent(Student student) {
        studentRepository.save(student);
    }

}
