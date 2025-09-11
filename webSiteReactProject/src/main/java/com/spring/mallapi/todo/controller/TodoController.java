package com.spring.mallapi.todo.controller;

import com.spring.common.dto.PageRequestDTO;
import com.spring.common.dto.PageResponseDTO;
import com.spring.mallapi.todo.dto.TodoDTO;
import com.spring.mallapi.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @GetMapping("/{tno}")
    public TodoDTO get(@PathVariable(name = "tno") Long tno){
        return todoService.get(tno);
    }

    @GetMapping("/list")
    public PageResponseDTO<TodoDTO> list(PageRequestDTO pageRequestDTO){
        return todoService.list(pageRequestDTO);
    }
}
