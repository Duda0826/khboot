package com.example.sadf;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookMapper bookMapper;


    @Override
    public List<Book> selectAllList() {
        List<Book> selectAllList = bookMapper.bookList();
        return selectAllList;
    }
}
