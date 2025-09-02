package com.boot.example.service;

import com.boot.example.domain.Student;

import java.util.List;

public interface StudentService {
    public List<Student> studentList();
    public String studentAutoNumber(String subjectNumber);
    public String studentIdCheck(String studentId);
    public void studentInsert(Student student);
    public int studentDelete(Student student);
}