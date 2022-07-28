package com.t3h.elibrary.entity;

import lombok.*;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "books")
@FieldNameConstants
public class Book {
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

