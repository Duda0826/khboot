package com.boot.example.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {
    private int no;
    private String cNum;
    private String cName;
    private int cCredit;
    private String cSection;
}
