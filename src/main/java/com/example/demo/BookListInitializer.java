package com.example.demo;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component 
public class BookListInitializer {
    private final BookRepository bookRepository;
    BookListInitializer(BookRepository bookReposit){
        this.bookRepository = bookReposit;
    }
    @PostConstruct
    public void init(){
        bookRepository.addBook( 1, "The Pragmatic Programmer", "Hunt & Thomas", true);
        bookRepository.addBook(2, "Clean Code", "Robert C. Martin", true);
        bookRepository.addBook(3, "Refactoring", "Martin Fowler", true);
        bookRepository.addBook(4, "Domain-Driven Design", "Eric Evans", true);
        bookRepository.addBook( 5, "Design Patterns", "Gang of Four", true);
    }
    
    
}
