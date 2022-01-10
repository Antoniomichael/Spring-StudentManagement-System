package com.smslast.studentmanagementlast.service;
import com.smslast.studentmanagementlast.model.Student;
import com.smslast.studentmanagementlast.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;


@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepository;



    public StudentServiceImpl(StudentRepository studentRepository) {

        this.studentRepository = studentRepository;
    }


    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public  Student saveStudent(Student student) {
        return studentRepository.save(student);
    }


    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student login(String email, String password )throws SQLException {
        
         try {
             
             //created a new encoder and encode password received from UI
             //Then pass the new variable with the encrypted password to
             // the findemailandpassword method
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
           String encrypted_Password = passwordEncoder.encode(password);
             Student student= studentRepository.findByEmailAndPassword(email,encrypted_Password);
             
        } catch (NullPointerException | IllegalArgumentException npe) {
            System.out.println(password);
            npe.printStackTrace();
        }
        
        return student;
    }

    @Override
    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }


}
