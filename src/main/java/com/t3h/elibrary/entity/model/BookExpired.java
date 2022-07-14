package com.t3h.elibrary.entity.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
public class BookExpired {
    private int bookId;
    private String name;
    private long countExpired;

    public BookExpired(int bookId,String name,long countExpired) {
        this.bookId = bookId;
        this.name = name;
        this.countExpired = countExpired;
    }
}
