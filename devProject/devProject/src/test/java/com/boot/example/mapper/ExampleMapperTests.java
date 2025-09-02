package com.boot.example.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ExampleMapperTests {
    @Autowired
    private ExampleMapper exampleMapper;

    @Test
    public void testGetDate(){
        log.info(exampleMapper.getClass().getName());
        log.info("getDate() 메서드 실행");
        log.info(exampleMapper.getDate());
    }

    @Test
    public void testGetTime() {
        log.info(exampleMapper.getClass().getName());
        log.info("getTime() 메서드 실행");
        log.info(exampleMapper.getTime());
    }
}
