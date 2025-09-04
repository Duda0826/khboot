package com.example.sadf;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    private int bookNo;
    private String bookTitle;
    private String bookWriter;
    private int bookPrice;
}