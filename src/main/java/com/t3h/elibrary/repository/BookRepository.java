package com.t3h.elibrary.repository;

import com.t3h.elibrary.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Books, Integer> {
    @Query(value = "SELECT b.* FROM books b LEFT JOIN stock_in si ON b.book_id = si.book_id  WHERE b.status NOT LIKE 'ARCHIVE' AND si.quantity > 0", nativeQuery = true)
    List<Books> listBookIssued();
}
