package com.boot.example.domain;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExampleDTO {
    private String name;
    private int age;
    private String email;
}
