package com.t3h.elibrary.service;

import com.t3h.elibrary.entity.Book;
import com.t3h.elibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> listBook(){
        return bookRepository.findAll();
    }
}
