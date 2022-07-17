package com.t3h.elibrary.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "borrow_books")
public class BorrowBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "borrow_id")
    private int borrowId;
    @Column(name = "book_id")
    private int bookId;
    @Column(name = "student_id")
    private int studentId;
    @Column(name = "borrow_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate borrowDate;
    @Column(name = "expire_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate expiredDate;
    @Column(name = "status")
    private String status;
    @Column(name = "comment")
    private String comment;
    @Column(name = "quantity_borrow")
    private int quantityBorrow;
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "book_id", referencedColumnName = "book_id")
//    private Book book;

}
