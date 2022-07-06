package com.t3h.elibrary.service;

import com.t3h.elibrary.common.Constants;
import com.t3h.elibrary.entity.Books;
import com.t3h.elibrary.entity.BorrowBook;
import com.t3h.elibrary.entity.StockIn;
import com.t3h.elibrary.repository.BookRepository;
import com.t3h.elibrary.repository.BorrowBookRepository;
import com.t3h.elibrary.repository.StockInRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BorrowBookService {
    @Autowired
    private BorrowBookRepository borrowBookRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private StockInRepository stockInRepository;

    public List<Books> listIssuedBook() {
        return bookRepository.listBookIssued();
    }

    public Books getBorrowBookById(int bookId) {
        Optional<Books> optional = bookRepository.findById(bookId);
        Books book;
        if (optional.isPresent()) {
            book = optional.get();
        } else {
            throw new RuntimeException("Can not found for borrow id: " + bookId);
        }
        return book;
    }

    public BorrowBook addBorrow(BorrowBook borrowBook) throws RuntimeException {
        StockIn stockIn = stockInRepository.findById(borrowBook.getBookId()).get();

        if(borrowBookRepository.borrowBookPerWeek(borrowBook.getStudentId()) < 4 && borrowBookRepository.borrowBookByStatus(borrowBook.getStudentId()) < 7) {
            if (stockIn.getQuantity() != 0) {
                LocalDate date = java.time.LocalDate.now();
                borrowBook.setBorrowDate(date);
                borrowBook.setStatus(Constants.BORROW_STATUS_BORROWING);

                stockIn.borrowedOne(borrowBook.getQuantityBorrow());
                borrowBookRepository.save(borrowBook);
            } else {
                throw new RuntimeException("Book out of stock!");
            }
            return borrowBook;
        } else {
            return null;
        }
    }
}
