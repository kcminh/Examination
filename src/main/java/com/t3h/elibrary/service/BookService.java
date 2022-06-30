package com.t3h.elibrary.service;

import com.t3h.elibrary.model.Books;
//import com.t3h.elibrary.model.BorrowBook;
import com.t3h.elibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Books> listBook() {
        return bookRepository.findAll();
    }

    public Books getBookById(int bookId) {
        Optional<Books> booksOptional = bookRepository.findById(bookId);
        Books book;
        if(booksOptional.isPresent()) {
            book = booksOptional.get();
        } else {
            throw new RuntimeException("Book not found with id: " + bookId);
        }
        return book;
    }

    public Books saveBook(Books book) {
        return bookRepository.save(book);
    }



}
