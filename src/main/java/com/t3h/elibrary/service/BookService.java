package com.t3h.elibrary.service;

import com.t3h.elibrary.model.Books;
import com.t3h.elibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Books> listBook() {
        return bookRepository.findAll();
    }

    public Books getBookById(int bookId) {
        return bookRepository.findById(bookId).get();
    }

    public Books saveBook(Books book) {
        return bookRepository.save(book);
    }

}
