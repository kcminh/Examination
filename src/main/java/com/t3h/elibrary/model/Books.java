package com.t3h.elibrary.model;

import lombok.Data;

import javax.annotation.processing.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Entity
//@Table(name = "books")
public class Books {
    @Id
    @GeneratedValue
    private int bookId;
    private String name;
    private String author;
    private String publisher;
    private LocalDateTime publishDate;
    private int categoryId;
    private String bookCountry;
    private String facebook;
    private String status;
}
