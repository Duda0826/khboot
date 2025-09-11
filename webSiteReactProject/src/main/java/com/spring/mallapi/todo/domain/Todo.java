package com.spring.mallapi.todo.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_todo")
@SequenceGenerator(name = "tbl_todo_generator",
        sequenceName = "tbl_todo_seq",
        initialValue = 1,
        allocationSize = 1)
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tbl_todo_generator")
    private Long tno;
    private String title;
    private String writer;
    private boolean complete;
    private LocalDate dueDate;

    // Todo 엔티티 클래스에서는 수정이 가능한 부분에 대해서 변경 가능하게 수정한다.
    // Todo의 제목(title), 완료여부(complete), 만료일(dueDate)은 수정할 수 있다.
    public void changeTitle(String title) {
        this.title = title;
    }

    public  void changeComplete(boolean complete) {
        this.complete = complete;
    }

    public void changeDueDate(LocalDate dueDate){
        this.dueDate = dueDate;
    }
}
