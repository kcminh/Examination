package com.t3h.elibrary.controller;

import com.t3h.elibrary.model.Books;
import com.t3h.elibrary.service.BookService;
import com.t3h.elibrary.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public String listBook(@ModelAttribute("books") Books book, Model model)  {
        model.addAttribute("books", bookService.listBook());
        return "book/list-book";
    }

    @GetMapping("/add")
    public String addBook(Model model) {
        model.addAttribute("books", new Books());
        return "book/form";
    }

    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable(value = "id") int id, Model model) {
        Books book = bookService.getBookById(id);
        if (book != null) {
            model.addAttribute("books", book);
            return "book/form";
        } else {
            return "redirect:/book/add";
        }
    }
    @PostMapping("/save")
    public String saveBook(@ModelAttribute("books") Books book, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "book/form";
        }
        bookService.saveBook(book);
        return "redirect:/book/list";
    }
}

