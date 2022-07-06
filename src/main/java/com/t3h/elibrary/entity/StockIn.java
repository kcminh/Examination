package com.t3h.elibrary.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stock_in")
public class StockIn {
    @Id
    @Column(name = "stock_id")
    private int stockId;
    @Column(name = "book_id")
    private int bookId;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "added_at")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate addedAt;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "supplyer")
    private String supplier;

    public void borrowedOne(int number) {
        this.quantity -= number;
    }

    public void returnedOne() {
        this.quantity++;
    }
}
