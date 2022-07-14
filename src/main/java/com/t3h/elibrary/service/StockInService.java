package com.t3h.elibrary.service;

import com.t3h.elibrary.entity.StockIn;
import com.t3h.elibrary.repository.StockInRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockInService {
    @Autowired
    private StockInRepository stockInRepository;

}
