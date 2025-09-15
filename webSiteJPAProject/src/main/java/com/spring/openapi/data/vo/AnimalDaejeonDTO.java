package com.spring.openapi.data.vo;

import lombok.Data;

@Data
public class AnimalDaejeonDTO {
    private String animalSeq;
    private String searchCondition;  /* 유기동물구분 */
    private String searchCondition3; /* 입양상태 */
}