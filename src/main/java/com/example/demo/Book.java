package com.example.demo;

import lombok.Data;

@Data
public class Book {
    private final int id;
    private final String isbn;
    private final String name;
    private final String author;
}