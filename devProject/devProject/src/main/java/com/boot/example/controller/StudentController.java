package com.boot.example.controller;

import com.boot.example.domain.Student;
import com.boot.example.domain.Subject;
import com.boot.example.service.StudentService;
import com.boot.example.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    private final SubjectService subjectService;

    @GetMapping("/studentList")
    public String studentList(Model model, Student student) {
        List<Student> studentList = studentService.studentList();
        model.addAttribute("studentList", studentList);
        model.addAttribute("student", student);

        List<Subject> subjectList = subjectService.subjectList();
        model.addAttribute("subjectList", subjectList);
        return "/student/studentList";
    }

    @GetMapping("/studentAutoNumber")
    @ResponseBody
    public String studentAutoNumber(String subjectNumber){
        String studentNumber = studentService.studentAutoNumber(subjectNumber);
        return studentNumber;
    }

    @PostMapping("/studentIdCheck")
    @ResponseBody
    public String studentIdCheck(String studentId){
        String result = studentService.studentIdCheck(studentId);
        return result;
    }

    @PostMapping("/studentInsert")
    public String studentInsert(Student student){
        studentService.studentInsert(student);
        return "redirect:/student/studentList";
    }
}