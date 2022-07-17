package com.t3h.elibrary.controller;

import com.t3h.elibrary.entity.model.BookExpired;
import com.t3h.elibrary.repository.BorrowBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BookExpiredController {

    @Autowired
    BorrowBookRepository repo;

    @GetMapping("/rpt-book-expired")
    public String reportBookExpired(ModelMap modelMap, @RequestParam(name = "chart", required = false) String chart) {
        List<BookExpired> bookExpireds = repo.findBookExpiredCount();

        String[] bookNames = new String[bookExpireds.size()];
        Long[] countExpired = new Long[bookExpireds.size()];
        int i = 0, j = 0;
        for (BookExpired bookExpired : bookExpireds) {
            bookNames[i++] = bookExpired.getName();
            countExpired[j++] = bookExpired.getCountExpired();
        }
        modelMap.addAttribute("bookNames", bookNames);
        modelMap.addAttribute("countExpired", countExpired);
        modelMap.addAttribute("chartType", (chart == null || chart.isEmpty() ? "line" : chart));

        return "rptBookExpired";
    }
}
