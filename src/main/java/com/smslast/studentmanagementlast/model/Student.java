package com.smslast.studentmanagementlast.model;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password", nullable = false)
    private String password;


    @Column(name = "confirm_pwd")
    private String confirm_pwd;



    public Student(String firstName, String lastName, String email, String password, String confirm_pwd) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password=password;
        this.confirm_pwd=confirm_pwd;
    }

    public Student() {

    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confirm_pwd='" + confirm_pwd + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
        try {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            this.password = passwordEncoder.encode(password);
        } catch (NullPointerException | IllegalArgumentException npe) {
            System.out.println(password);
            npe.printStackTrace();
        }
    }

    public String getConfirm_pwd() {
        return confirm_pwd;
    }

    public void setConfirm_pwd(String confirm_pwd) {
    try {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.confirm_pwd = passwordEncoder.encode(confirm_pwd);
    }catch(NullPointerException  | IllegalArgumentException npe){
        System.out.println(confirm_pwd);
        npe.printStackTrace();
    }
    }

    public Student(Long id, String firstName, String lastName, String email, String password, String confirm_pwd) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.confirm_pwd = confirm_pwd;
    }
}
