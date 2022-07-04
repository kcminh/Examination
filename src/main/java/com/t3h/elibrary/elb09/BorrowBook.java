package com.t3h.elibrary.elb09;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Getter
@Setter
@Entity
@Table(name = "borrow_books")
@IdClass(BorrowBook.class)
public class BorrowBook implements Serializable {
    @Id
    @Column(name = "book_id")
    private int bookId;
    @Id
    @Column(name = "student_id")
    private int studentId;
    @Column(name = "borrow_date")
    private Timestamp borrowDate;
    @Column(name = "expire_date")
    private Timestamp expiredDate;
    @Column(name = "status")
    private String status;
    @Column(name = "comment")
    private String comment;

}
