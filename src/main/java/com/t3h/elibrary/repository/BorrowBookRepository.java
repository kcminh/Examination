package com.t3h.elibrary.repository;

import com.t3h.elibrary.entity.BorrowBook;
import com.t3h.elibrary.entity.model.BookExpired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface BorrowBookRepository extends JpaRepository<BorrowBook, Integer> {
    @Query(value = "SELECT count(bb.borrow_id) FROM borrow_books bb WHERE date_sub(current_timestamp, INTERVAL 1 week) < bb.borrow_date AND bb.student_id LIKE ?1", nativeQuery = true)
    public int borrowBookPerWeek(int studentId);

    @Query(value = "SELECT count(*) FROM borrow_books bb WHERE bb.status NOT LIKE 'RETURNED' AND bb.student_id LIKE ?1", nativeQuery = true)
    public int borrowBookByStatus(int studentId);

    @Query("SELECT new com.t3h.elibrary.entity.model.BookExpired(bb.bookId,b.name,count(bb))" +
            "FROM BorrowBook bb  " +
            "left join Book b" +
            " on b.bookId = bb.bookId" +
            " where bb.status = 'out of date' " +
            "GROUP BY bb.bookId")
    public List<BookExpired> findBookExpiredCount();
}
