package com.example.springvue.Service;

import com.example.springvue.Model.Book;

import java.util.List;

public interface ServiceBookImpentance {
    Book select(Integer id);

    List<Book> fandallbook(Integer page);

    List<Book> AllBook();

    int add(Book book);

    int change(Book book);

    int delete(Integer id);

    Book serach1(String name);
}
