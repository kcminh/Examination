package com.t3h.elibrary.service;

import com.t3h.elibrary.common.Constants;
import com.t3h.elibrary.entity.Book;
import com.t3h.elibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> listBook(String status) {
        if (status != null) {
            if (status.equals(Constants.BOOK_STATUS_NEW)) {
                return bookRepository.listBookByStatus(Constants.BOOK_STATUS_NEW);
            } else if (status.equals(Constants.BOOK_STATUS_ISSUED)) {
                return bookRepository.listBookByStatus(Constants.BOOK_STATUS_ISSUED);
            } else {
                return bookRepository.listBookByStatus(Constants.BOOK_STATUS_ARCHIVE);
            }
        } else {
            return bookRepository.findAll();
        }
    }

    public Book getBookById(int bookId) {
        Optional<Book> booksOptional = bookRepository.findById(bookId);
        Book book;
        if(booksOptional.isPresent()) {
            book = booksOptional.get();
        } else {
            throw new RuntimeException("Book not found with id: " + bookId);
        }
        return book;
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }



}
