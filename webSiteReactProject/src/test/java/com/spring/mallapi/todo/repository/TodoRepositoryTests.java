package com.spring.mallapi.todo.repository;

import com.spring.mallapi.todo.domain.Todo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
@Slf4j
public class TodoRepositoryTests {
    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void testInsert(){
        for(int i = 1; i <= 100; i++){
            Todo todo = Todo.builder()
                    .title("title..." + i)
                    .dueDate(LocalDate.of(2025, 9, 5))
                    .writer("user00").build();
            todoRepository.save(todo);
        }
    }

    @Test
    public void testRead() {
        // 존재하는 번호로 확인
        Long tno = 33L;
        java.util.Optional<Todo> result = todoRepository.findById(tno);
        Todo todo = result.orElseThrow();
        log.info("데이터 조회(33L); {}", todo);
    }

    @Test
    public void testModify() {
        Long tno = 33L;

        Optional<Todo> result = todoRepository.findById(tno);

        Todo todo = result.orElseThrow();
        todo.changeTitle("Modified 33...");
        todo.changeComplete(true);
        todo.changeDueDate(LocalDate.of(2025, 9, 20));

        Todo todoResult = todoRepository.save(todo);
        log.info("수정된 데이터: {}", todoResult);
    }

    @Test
    public void testDelete() {
        Long tno = 11L;

        todoRepository.deleteById(tno);
    }

    @Test
    public void testPaging(){
        Pageable pageable = PageRequest.of(0,10, Sort.by("tno").descending());
        Page<Todo> resullt = todoRepository.findAll(pageable);
        log.info("총 데이터 수: {}", resullt.getTotalElements());
        resullt.getContent().stream().forEach(todo -> log.info(todo.toString()));
    }
}
