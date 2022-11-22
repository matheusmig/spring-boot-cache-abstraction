package com.example.demo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookLibrary {
    private static Logger LOG = LoggerFactory.getLogger(BookLibrary.class);
    private List<Book> bookList;

    public BookLibrary(){
        bookList = TestBookGenerator.getAllBooks();
    }

    @Cacheable("books")
    public Book getById(int id) {
        LOG.info("Cache miss");

        return this.bookList
                .stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Cacheable(value= "books")
    public List<Book> getByAuthor(String author) {
        LOG.info("Cache miss");

        return this.bookList
                .stream()
                .filter(x -> x.getAuthor().equals(author))
                .toList();
    }

    @CachePut(value= "books", key = "#book.id")
    public Book update(Book book) {
        LOG.info("Cache updated");

        this.bookList.set(book.getId()-1, book);
        return book;
    }

    @CacheEvict(value= "books", allEntries = true)
    public void cleanCacheAllEntries(){ }

    public void printAllBooks(){
        this.bookList.forEach(x -> LOG.info(x.toString()));
    }

}