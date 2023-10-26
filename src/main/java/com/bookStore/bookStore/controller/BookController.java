package com.bookStore.bookStore.controller;

import com.bookStore.bookStore.entities.Book;
import com.bookStore.bookStore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/books")
public class BookController {
  @Autowired
  private BookService bookService;


//  public BookController(BookService bookService) {
//    this.bookService = bookService;
//  }

  @GetMapping
  public List<Book> getAllBooks() {
    return bookService.getAllBooks();
  }

  @GetMapping("/{id}")
  public Book getBookById(@PathVariable Long id) {
   return bookService.getBookById(id);
  }
  @PostMapping("/{id}")
  public String createBook(@RequestBody Book book) {
    Book createBook = bookService.createBook(book);
    if (createBook != null){
      return "Book Created with ID: " + createBook.getId();
    }else {
      return "Falied to create book.";
    }
  }

  @PutMapping("/{id}")
  public String updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
    Book book = bookService.updatedBook(id,updatedBook);
    if(book!=null){
      return "Book with ID " + id + " is updated.";
    }else {
      return "Book with ID " + id + " not found.";
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteBook(@PathVariable Long id) {

    if (bookService.deleteBook(id)) {
      return ResponseEntity.ok("Book with ID " + id + " is deleted.");
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book with ID " + id + " not found.");
    }
  }
}

