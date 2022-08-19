package com.t3h.elibrary.service;

import com.t3h.elibrary.entity.Book;
import com.t3h.elibrary.repository.BookRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.util.List;

@Service
public class ExportBookCsv {

    private final BookRepository bookRepository;

    public ExportBookCsv(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public void writeBookToCsv(Writer writer) {

        List<Book> books = bookRepository.findAll();
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("Total: " + books.size());
            csvPrinter.printRecord("Date: " + LocalDate.now());
            csvPrinter.printRecord("");
            csvPrinter.printRecord("");
            csvPrinter.printRecord(Book.Fields.bookId.toUpperCase(),
                    Book.Fields.name.toUpperCase(),
                    Book.Fields.author.toUpperCase(),
                    Book.Fields.publisher.toUpperCase(),
                    Book.Fields.categoryId.toUpperCase(),
                    Book.Fields.bookCountry.toUpperCase(),
                    Book.Fields.faceBook.toUpperCase(),
                    Book.Fields.status.toUpperCase()
            );
            for (Book book : books) {
                csvPrinter.printRecord(book.getBookId(), book.getName(), book.getAuthor(), book.getPublisher(),
                        book.getCategoryId(), book.getBookCountry(), book.getFaceBook(),
                        book.getStatus());
            }
        } catch (IOException ignored) {
        }
    }
}
