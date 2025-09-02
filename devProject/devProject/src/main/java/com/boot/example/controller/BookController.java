package com.boot.example.controller;

import com.boot.example.domain.Book;
import com.boot.example.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/bookList")
    public String bookList(Model model, Book book){
        List<Book> bookList = bookService.bookList();
        model.addAttribute("bookList", bookList);
        model.addAttribute("book", book);
        return "/book/bookList";
    }

    @PostMapping("/bookInsert")
    public String bookInsert(@ModelAttribute Book book){
        bookService.bookInsert(book);
        return "redirect:/book/bookList";
    }

    @PostMapping("/bookUpdate")
    public String bookUpdate(@ModelAttribute Book book){
        bookService.bookUpdate(book);
        return "redirect:/book/bookList";
    }

    @GetMapping("/bookDelete")
    public String bookDelete(@ModelAttribute Book book){
        bookService.bookDelete(book);
        return "redirect:/book/bookList";
    }
}