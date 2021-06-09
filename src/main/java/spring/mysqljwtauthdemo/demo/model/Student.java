package spring.mysqljwtauthdemo.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity // This annotation will be used for Hibernate
@Table
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    private String address;
    private String email;
    private String password;
    private String user_full_name;

    public Student() {
    }

    public Student(String address, String email, String password, String user_full_name) {
        this.address = address;
        this.email = email;
        this.password = password;
        this.user_full_name = user_full_name;
    }

    public Student(Long id, String address, String email, String password, String user_full_name) {
        this.id = id;
        this.address = address;
        this.email = email;
        this.password = password;
        this.user_full_name = user_full_name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_full_name() {
        return user_full_name;
    }

    public void setUser_full_name(String user_full_name) {
        this.user_full_name = user_full_name;
    }

}
