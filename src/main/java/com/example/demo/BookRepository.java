package com.example.demo;


import org.springframework.stereotype.Component;
import java.util.*;
@Component
public class BookRepository {

    private final List<Book> book = new ArrayList<>();

    public Book findBook(int id){ 
        Book b = null;  
        for(int i = 0; i < book.size(); i++){
            if(Integer.valueOf(id).equals(book.get(i).getId())){
                b = book.get(i);
                return b;
            }
        }
        return null;
    }

    public void addBook(int id, String title, String author, boolean avaliable){
        if(book.contains(findBook(id)))System.out.println("book is already present");
        Book b = new Book(id, title, author, avaliable);
        book.add(b);
    }

    public void removeBook(int id){
        if(findBook(id).equals(null)) System.out.println("Book of following id is not present");
        book.remove(book.indexOf((findBook(id))));
    }

    public List<Book> findAll(){
        return book;
    }

}
