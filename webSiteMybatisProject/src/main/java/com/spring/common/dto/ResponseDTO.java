package com.spring.common.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResponseDTO<E> {
    private List<E> list;
    private RequestDTO requestDTO;

    public ResponseDTO(List<E> list, RequestDTO requestDTO) {
        this.list = list;
        this.requestDTO = requestDTO;
    }
}
