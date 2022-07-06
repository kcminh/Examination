package com.t3h.elibrary.repository;

import com.t3h.elibrary.entity.StockIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StockInRepository extends JpaRepository<StockIn, Integer> {

}
