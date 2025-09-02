package com.boot.example.service;

import com.boot.example.domain.Subject;
import com.boot.example.mapper.SubjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService{
    private final SubjectMapper subjectMapper;

    @Override
    public List<Subject> subjectList() {
        List<Subject> subjectList = subjectMapper.subjectList();
        return subjectList;
    }
}