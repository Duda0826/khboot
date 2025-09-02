package com.example.demo.vo;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

//@Getter //해당 클래스에 정의된 모든 필드에 Getter와 Settr가 생성된다
//@Setter //해당 클래스에 정의된 모든 필드에 Getter와 Settr가 생성된다
//@ToString //toString() 메서드를 만들어준다. 객체를 로그로 출력할 때 유용하다
//AllArgsConstructor //클래스에 사용하며 모든 필드를 매개변수로 받는 생성자를 만들어 준다
//@NoArgsConstructor //클래스에 사용하며 아무런 매개변수도 갖지 않는 생성자를 만들어 준다
//@RequiredArgsConstructor
//클래스에 사용하며 final로 선언된 필드들을 매개변수로 받는 생성자를 만들어 준다
//@Builder //클래스에 사용하며 객체를 생성을 위해 빌더 패턴을 사용할 수 있도록 해준다.
//@Slf4j //클래스에 사용하며, 자동으로 클래스에 log라는 로깅 객체를 생성해 준다.
@Data //클래스에서 사용하며 @Getter, @Setter, @ToString 등을 하나의 애노테이션으로 대체할 수 있다.
public class Member {
    private Long id;
    private String name;
    private String email;
    private int age;
}