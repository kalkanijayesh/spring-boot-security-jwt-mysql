package spring.mysqljwtauthdemo.demo.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import spring.mysqljwtauthdemo.demo.model.Student;
import spring.mysqljwtauthdemo.demo.repository.StudentRepository;

@Service
public class DefaultUser implements UserDetailsService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Business logic.
        // return new User("foo", "foo", new ArrayList<>());

        Optional studentOptional = studentRepository.findByEmail(username);

        if (studentOptional.isPresent()) {
            Student studentObj = (Student) studentOptional.get();
            return new User(studentObj.getEmail(), studentObj.getPassword(), new ArrayList<>());
        } else {
            throw new IllegalStateException("Email id not exist !!");
        }

    }

}
