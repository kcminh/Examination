package com.t3h.elibrary.service;

import com.t3h.elibrary.entity.BorrowBook;
import com.t3h.elibrary.repository.BorrowBookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
public class BorrowBookServiceTest {
//    junit + mockito

    /**
     * tạo  real Object cho service
     * trong class Test chỉ duy nhất InjectMocks
     * InjectMock
     */
    @InjectMocks
    private BorrowBookService borrowBookService;

    /* tạo properties tương ứng trong service UserService */
    @Mock
    private BorrowBookRepository borrowBookRepository;

    /* tạo case test tương ứng cho functions */
    @Test
    @DisplayName("test List borrowBook success")
    public void testListBorrowBookSuccess() {
        // setup
        when(borrowBookRepository.findAll()).thenReturn(List.of(new BorrowBook()));

        // execute
    }

}
