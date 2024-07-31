package org.example.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.example.dao.BookDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jws.WebParam;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookDAO bookDAO;

    public BookController(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }

    @GetMapping("{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("book", bookDAO.show(id));
        return "books/show";
    }
}
