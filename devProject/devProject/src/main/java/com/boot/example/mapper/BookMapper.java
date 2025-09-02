package com.boot.example.mapper;

import com.boot.example.domain.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {
    public List<Book> bookList();
    public int bookInsert(Book book);
    public int bookUpdate(Book book);
    public int bookDelete(Book book);
}
