package com.t3h.elibrary.elb09;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Getter
@Setter
@Entity
@Table(name = "borrow_books")
public class BorrowBook {
    @Id
    @Column(name = "borrow_id")
    private int borrowId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    private Book book;
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
