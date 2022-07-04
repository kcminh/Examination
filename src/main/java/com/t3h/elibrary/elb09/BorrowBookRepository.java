package com.t3h.elibrary.elb09;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BorrowBookRepository extends JpaRepository<BorrowBook,Integer> {
    @Query("SELECT new com.t3h.elibrary.elb09.BookExpired(bb.bookId,count(bb))" +
            "FROM BorrowBook bb \n" +
            "WHERE bb.status = 'out of date'\n" +
            "GROUP BY bb.bookId")
    public List<BookExpired> findBookExpiredCount();
}
