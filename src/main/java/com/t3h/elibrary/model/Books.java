package com.t3h.elibrary.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.sql.Timestamp;
import java.util.Date;

@Data
@Entity
@Table(name = "books")
public class Books {
    @Id
    @Column(name = "book_id")
    private int bookId;
    @Column(name = "name")
    private String name;
    @Column(name = "author")
    private String author;
    @Column(name = "publisher")
    private String publisher;
    @Column(name = "publish_date")
    private Timestamp publishDate;
    @Column(name = "category_id")
    private int categoryId;
    @Column(name = "book_country")
    private String bookCountry;
    @Column(name = "facebook")
    private String faceBook;
    @Column(name = "status")
    private String status;
}
