package com.boot.example.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class SubjectMapperTest {
    @Autowired
    private SubjectMapper subjectMapper;

    @Test
    public void subjectListTest() {
        subjectMapper.subjectList().stream().forEach(subject -> log.info(subject.toString()));
    }
}
