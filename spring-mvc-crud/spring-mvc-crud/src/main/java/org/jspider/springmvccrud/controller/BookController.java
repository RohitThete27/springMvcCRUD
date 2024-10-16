package org.jspider.springmvccrud.controller;

import org.jspider.springmvccrud.domain.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
@Controller
public class BookController {

    List<Book> bookList = new ArrayList<>();

    {
        bookList.add(new Book(1, "SPRING", 1800.00, "EDUCATION"));
        bookList.add(new Book(2, "JAVA", 1999.00, "EDUCATION"));
        bookList.add(new Book(3, "J2EE", 1599.00, "EDUCATION"));
        bookList.add(new Book(4, "HTML", 1500.00, "EDUCATION"));
        bookList.add(new Book(5, "SQL", 1000.00, "EDUCATION"));
        bookList.add(new Book(6, "CSS", 3000.00, "EDUCATION"));
    }

    @GetMapping("/books")
    public String getBook(Model model) {
        model.addAttribute("books", bookList);
        return "books";
    }

    @GetMapping("/bookform")
    public String bookForm(Model model) {
        model.addAttribute("book", new Book());
        return "bookform.html";
    }

    @PostMapping("/savebook")
    public String addBook(Book b) {
        bookList.add(b);
        return "redirect:/books";
    }

    @GetMapping("/updatebook/{id}")
    public String updateBook(@PathVariable int id, Model model) {

        for (Book b : bookList) {
            if (b.getBookId() == id) {
                model.addAttribute("book", b);
            }
        }
        return "updatebook";
    }

    @PostMapping("/modifybook")
    public String modifyBook(Book b) {
        for (int a = 0; a < bookList.size(); a++) {
            Book b1 = bookList.get(a);

            if (b1.getBookId() == b.getBookId()) {
                bookList.set(a, b);
            }
        }
        return "redirect:/books";
    }

    @GetMapping("/deletebook/{id}")
    public String deleteBook(@PathVariable int id,Model model) {
        for (int a = 0; a < bookList.size(); a++) {
            if (bookList.get(a).getBookId()== id){
                model.addAttribute("book", bookList.remove(a));
            }
        }
        return "redirect:/books";
    }

    @GetMapping("sortname")
    public String sortByName(Model model){
        Collections.sort(bookList, Comparator.comparing(Book::getBookName));
        return "redirect:/books";
    }
    @GetMapping("sortprice")
    public String sortByPrice(Model model){
        Collections.sort(bookList, Comparator.comparingDouble(Book::getBookPrice));
        return "redirect:/books";
    }
}
