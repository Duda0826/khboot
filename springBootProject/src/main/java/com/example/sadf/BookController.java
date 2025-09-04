package com.example.sadf;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("book")
public class BookController {
    private final BookService service;

    @GetMapping("selectAllList")
    @ResponseBody
    public List<Book> selectAllList() {
        List<Book> selectAllList = service.selectAllList();
        return selectAllList;
    }
}
