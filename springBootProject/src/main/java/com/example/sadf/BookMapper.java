package com.example.sadf;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface BookMapper {
    @Results(id = "bookList", value = {
            @Result(column = "book_no", property = "bookNo"),
            @Result(column = "title", property = "bookTitle"),
            @Result(column = "writer", property = "bookWriter"),
            @Result(column = "price", property = "bookPrice"),
    })
    @Select("SELECT book_no, title, writer, price FROM book ORDER BY book_no")
    public List<Book> bookList();
}
