package com.boot.example.mapper;

import com.boot.example.domain.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper {
    @Results(id = "studentResult", value = {
            @Result(property = "no", column = "no"),
            @Result(property = "studentNumber", column = "sd_num"),
            @Result(property = "studentName", column = "sd_name"),
            @Result(property = "studentId", column = "sd_id"),
            @Result(property = "studentBirth", column = "sd_birth"),
            @Result(property = "studentPhone", column = "sd_phone"),
            @Result(property = "studentAddress", column = "sd_address"),
            @Result(property = "studentEmail", column = "sd_email"),
            @Result(property = "regDate", column = "sd_date"),
            @Result(property = "subject.subjectNumber", column = "s_num"),
            @Result(property = "subject.subjectName", column = "s_name")
    })
    @Select("""
            SELECT st.no as no, sd_num, sd_name, sd_id, sd_birth, sd_phone, sd_address, sd_email, sd_date, su.s_num as s_num, s_name
            FROM student st INNER JOIN subject su
            ON st.s_num = su.s_num
            ORDER BY st.no
    """)
    public List<Student> studentList();

    @Select("""
            SELECT TO_CHAR(SYSDATE, 'YY') || s.s_num ||
                   LPAD(NVL(MAX(TO_NUMBER(SUBSTR(st.sd_num, 5))), 0) + 1, 4, '0') AS student_number
            FROM subject s LEFT JOIN student st
            ON s.s_num = st.s_num
            WHERE s.s_num = #{subjectNumber}
            GROUP BY s.s_num
    """)
    public String studentAutoNumber(@Param("subjectNumber") String subjectNumber);

    @Select("""
            SELECT CASE
                     WHEN EXISTS (SELECT 1 FROM student WHERE sd_id = #{studentId})
                     THEN 1
                     ELSE 0
                   END AS result
            FROM dual
    """)
    public int studentIdCheck(@Param("studentId") String studentId);

    @Insert("""
            INSERT INTO student(no, sd_num, sd_name, sd_id, sd_passwd, s_num, sd_birth, sd_phone, sd_address, sd_email)
            VALUES(student_seq.nextval, #{student.studentNumber}, #{student.studentName}, #{student.studentId},#{student.studentPasswd},
                   #{student.subject.subjectNumber}, #{student.studentBirth}, #{student.studentPhone}, #{student.studentAddress}, #{student.studentEmail})
    """)
    public int studentInsert(@Param("student") Student student);
}