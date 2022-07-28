package com.t3h.elibrary.service;

import com.t3h.elibrary.entity.Book;
import com.t3h.elibrary.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class BookServiceTest {
    @InjectMocks
    private BookService bookService;


    @Mock
    private BookRepository bookRepository;

    private Random random;


    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this.getClass());
        random = new Random();
    }

    @Test
    @DisplayName("it get list book success")
    public void testListBookSuccess(){
        Book book = new Book();
        book.setBookId(random.nextInt());
        book.setBookCountry(UUID.randomUUID().toString());
        book.setName(UUID.randomUUID().toString());
        book.setPublisher(UUID.randomUUID().toString());
        book.setAuthor(UUID.randomUUID().toString());
        book.setFaceBook(UUID.randomUUID().toString());
        book.setCategoryId(random.nextInt());

        List<Book> expect = List.of(book);

        given(bookRepository.findAll())
                .willReturn(expect);

        List<Book> actual = bookService.listBook();

        assertEquals(actual.size(), expect.size());
        assertEquals(actual.get(0).getBookId(), book.getBookId());
        assertTrue(!actual.isEmpty());
    }
}


