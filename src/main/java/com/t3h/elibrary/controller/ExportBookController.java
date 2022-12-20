package com.t3h.elibrary.controller;

import com.t3h.elibrary.entity.Book;
import com.t3h.elibrary.service.BookService;
import com.t3h.elibrary.service.ExportBookCsv;
import com.t3h.elibrary.service.ExportBookXlsx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/book")
public class ExportBookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/list")
    public String listBook(@ModelAttribute("books") Book book, Model model) {
        model.addAttribute("books", bookService.listBook());
        return "ListBook";
    }
    @Autowired
    private final ExportBookCsv exportBookCsv;

    public ExportBookController(ExportBookCsv exportBookCsv) {
        this.exportBookCsv = exportBookCsv;
    }

    @GetMapping(path = "/exportbookcsv")//
    public void getAllEmployeesInCsv(HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        exportBookCsv.writeBookToCsv(servletResponse.getWriter());
    }

    @Autowired
    private ExportBookXlsx exportBookXlsx;

    @GetMapping("/exportbookxlsx")//
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");

        List<Book> list = exportBookXlsx.listBook();
        ExportBookXlsx exportBookXlsx = new ExportBookXlsx(list);
        exportBookXlsx.export(response);
    }
}
