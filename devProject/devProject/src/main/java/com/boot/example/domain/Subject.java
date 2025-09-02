package com.boot.example.domain;

import lombok.*;
import org.apache.ibatis.annotations.Select;

@Data //설정자 접근자 한번에 줄 수 있다.
@AllArgsConstructor //매개변수 가지는 생성자
@NoArgsConstructor //디폴트 생성자
@Builder
public class Subject {
    private int no;
    private String subjectNumber;
    private String subjectName;
}
