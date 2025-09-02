package com.boot.example.mapper;

import com.boot.example.domain.Subject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SubjectMapper { //이 방식대로 하면 xml 따로 작성할 필요가 없다.
    @Results(id = "subjectResult", value = {
            @Result(column = "no", property = "no"),
            @Result(column = "s_num", property = "subjectNumber"),
            @Result(column = "s_name", property = "subjectName"),
    })
    @Select("SELECT no, s_num, s_name FROM subject ORDER BY no")
    public List<Subject> subjectList();
}
