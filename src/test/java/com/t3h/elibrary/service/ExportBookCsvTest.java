package com.t3h.elibrary.service;


import com.t3h.elibrary.entity.Book;
import com.t3h.elibrary.repository.BookRepository;
import lombok.Getter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

@Getter
class InMemoryWriter extends Writer{

    List<Character> characters = new ArrayList<>();
    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        if(characters == null)
            throw new EOFException();
        if(off >= cbuf.length)
            throw new ArrayIndexOutOfBoundsException();
        for(int i=0; i<cbuf.length; i++){
            characters.add(cbuf[i]);
        }
    }

    @Override
    public void flush() throws IOException {

    }

    @Override
    public void close() throws IOException {

    }
}


@SpringBootTest
public class ExportBookCsvTest {

    @Mock
    private BookRepository bookRepository;

    private Random random;
    private ExportBookCsv exportBookCsvNeedTest;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this.getClass());
        exportBookCsvNeedTest = new ExportBookCsv(bookRepository);
        random = new Random();
    }

    @Test
    public void itWriteBookToCsvSuccess() {
        InMemoryWriter inMemoryWriter = new InMemoryWriter();
        Writer writer = new BufferedWriter(inMemoryWriter);

        Book book1 = new Book();
        book1.setBookId(random.nextInt());
        book1.setBookCountry(UUID.randomUUID().toString());
        book1.setName(UUID.randomUUID().toString());
        book1.setPublisher(UUID.randomUUID().toString());
        book1.setAuthor(UUID.randomUUID().toString());
        book1.setFaceBook(UUID.randomUUID().toString());
        book1.setCategoryId(random.nextInt());

        Book book2 = new Book();
        book2.setBookId(random.nextInt());
        book2.setBookCountry(UUID.randomUUID().toString());
        book2.setName(UUID.randomUUID().toString());
        book2.setPublisher(UUID.randomUUID().toString());
        book2.setAuthor(UUID.randomUUID().toString());
        book2.setFaceBook(UUID.randomUUID().toString());
        book2.setCategoryId(random.nextInt());

        List<Book> expect = List.of(book1, book2);

        given(bookRepository.findAll())
                .willReturn(expect);

        exportBookCsvNeedTest.writeBookToCsv(inMemoryWriter);

        assertTrue(!inMemoryWriter.getCharacters().isEmpty());
    }
}
