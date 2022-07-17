package com.t3h.elibrary.entity.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class BorrowForm {
    int bookId;
    int studentId;
    int quantityBorrow;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate expiredDate;
}
