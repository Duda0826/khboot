package com.boot.example.mapper;

import com.boot.example.domain.Student;
import com.boot.example.domain.Subject;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class StudentMapperTests {
    @Autowired
    private StudentMapper studentMapper;

    @Test
    public void studentListTest(){
        studentMapper.studentList().stream().forEach(student -> log.info(student.toString()));
    }

    @Test
    public void studentAutoNumberTest() {
        String studentNumber = studentMapper.studentAutoNumber("01");
        log.info("자동으로 구한 학번(컴퓨터학과 선택시): {}", studentNumber);
    }

    @Test
    public void studentIdCheckTest() {
        int result = studentMapper.studentIdCheck("javajsp");
        log.info("아이디 중복여부(1/0): {}", result);
    }

    @Test
    public void studentInsertTest() {
        log.info("------------------------------");
        log.info("studentInsert() 메서드 실행");
        Student student = Student.builder()
                .studentNumber(studentMapper.studentAutoNumber("01"))
                .studentName("김지수")
                .studentId("springuser")
                .studentPasswd("spring1234")
                .studentBirth("20000615")
                .studentPhone("01052058320")
                .studentAddress("(06181) 서울 강남구 테헤란로98길 12")
                .studentEmail("springuser@naver.com")
                .subject(Subject.builder().subjectNumber("01").build()).build();

        int result = studentMapper.studentInsert(student);
        log.info("입력된 행의 수: {}", result);
    }
}