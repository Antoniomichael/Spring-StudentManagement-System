package com.smslast.studentmanagementlast.service;
import com.smslast.studentmanagementlast.model.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();

    Student saveStudent(Student student);


    Student getStudentById(Long id);

    Student updateStudent(Student student);

    Student login(String email,String password)throws SQLException;

    void deleteStudentById(Long id);


}
