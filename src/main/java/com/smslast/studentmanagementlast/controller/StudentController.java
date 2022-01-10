package com.smslast.studentmanagementlast.controller;

import com.smslast.studentmanagementlast.model.Student;
import com.smslast.studentmanagementlast.service.StudentService;
import org.hibernate.PropertyValueException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.Objects;


@Controller
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students";
    }


    @GetMapping("/students")
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students";
    }


    @GetMapping("/students/new")
    public String createStudentForm(Model model) {

        Student student = new Student("","","","","");
        model.addAttribute("student", student);
        System.out.println(student.toString());
        return "create_student";
    }

    @PostMapping("/students")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/students/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        return "edit_student";
    }

    @GetMapping("/edit_student")
    public String saveStd(@ModelAttribute("student") Student std) {
        studentService.saveStudent(std);
        return "redirect:/students";
    }

    @PostMapping({"/students/{id}"})
    public String updateStudent(@PathVariable Long id, @ModelAttribute("student") Student student) {
        Student existingStudent = this.studentService.getStudentById(id);
        existingStudent.setId(id);
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());
        this.studentService.updateStudent(existingStudent);
        return "redirect:/students";

    }

    @GetMapping("/students/{id}")
    public String deleteStudent(@PathVariable Long id) {

        try{
            studentService.deleteStudentById(id);
        }catch(PropertyValueException pve){
            pve.printStackTrace();
            System.out.println(id);
        }
        return "redirect:/students";
    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView("login");
        mv.addObject("student", new Student());
        return mv;
    }

    @PostMapping("login")
    public String login(@ModelAttribute("student") Student student) {
        try {

            Student outhUser = studentService.login(student.getEmail(), student.getPassword());
            System.out.println(outhUser);
            if (Objects.nonNull(outhUser)) {
                return "redirect:/students";
            } else {
                return "redirect:/login";

            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "redirect:/login";
        }
    }
    
}
