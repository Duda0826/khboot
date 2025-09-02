package com.boot.example.service;

import com.boot.example.domain.Book;
import com.boot.example.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{
    private final BookMapper bookMapper;

    @Override
    public List<Book> bookList() {
        return bookMapper.bookList();
    }

    @Override
    public int bookInsert(Book book){
        int result = bookMapper.bookInsert(book);
        return result;
    }

    @Override
    public int bookUpdate(Book book){
        int result = bookMapper.bookUpdate(book);
        return result;
    }

    @Override
    public int bookDelete(Book book){
        int result = bookMapper.bookDelete(book);
        return result;
    }
}
