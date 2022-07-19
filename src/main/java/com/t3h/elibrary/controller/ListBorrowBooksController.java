package com.t3h.elibrary.controller;

import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import com.t3h.elibrary.entity.BorrowBook;
import com.t3h.elibrary.service.BorrowBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;



@Controller
public class ListBorrowBooksController {
    @Autowired
    private BorrowBookService bookService;


    @GetMapping("/report/borrowBook")
    public String  reportBorrowBooks() {
        return "listBorrowBooks/listBorrowBook";
    }

    @GetMapping("/report/borrowBook/api/week")
    @ResponseBody
    public ResponseEntity<?> borrowByWeek () throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/elibrary", "root", "1");
        int currentWeekOfYear = -1 + Calendar.getInstance().get(Calendar.WEEK_OF_YEAR) ;
        String select = "SELECT count(borrow_id) AS count " +
                "FROM  borrow_books bb " +
                "WHERE status IN ('BORROWING', 'EXPIRED')  AND  weekofyear(borrow_date) = " + currentWeekOfYear;
        Statement statement = connection.createStatement();
        ResultSet resultSet;
        int count;

        statement.execute(select);
        resultSet = statement.getResultSet();
        count = resultSet.next()
                ? resultSet.getInt("count")
                : 0;
        JSONWrappedObject jsonWrappedObject = new JSONWrappedObject("{\"count\":","}",count);

        return new ResponseEntity<>(jsonWrappedObject,HttpStatus.OK);
    }

    @GetMapping("/report/borrowBook/api/month")
    @ResponseBody
    public ResponseEntity<?> borrowByMonth () throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/elibrary", "root", "1");
        int currentMonth = LocalDateTime.now().getMonthValue();
        String select = "SELECT count(borrow_id) AS 'count'  " +
                "FROM borrow_books bb   " +
                "WHERE   status IN ('BORROWING', 'EXPIRED') AND MONTH(borrow_date) = " + currentMonth;
        Statement statement = connection.createStatement();
        ResultSet resultSet;
        int count;

        statement.execute(select);
        resultSet = statement.getResultSet();
        count = resultSet.next()
                ? resultSet.getInt("count")
                : 0;

        return  new ResponseEntity<>(new JSONWrappedObject("{\"count\":","}", count),HttpStatus.OK);
    }
}
