package com.bookStore.bookStore.controller;

import com.bookStore.bookStore.entities.Book;
import com.bookStore.bookStore.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/books")
public class BookController {

  private final BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping
  public List<Book> getAllBooks() {
    return bookService.getAllBooks();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> getBookById(@PathVariable Long id) {
    Book book = bookService.getBookById(id);
    if (book != null) {
      return ResponseEntity.ok(book);
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book with ID " + id + " not found.");
    }
  }
  @PostMapping("/{id}")
  public ResponseEntity<Object> createBook(@RequestBody Book book) {
   Book createdBook = bookService.createBook(book);
    return ResponseEntity.ok(createdBook);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
    Book book = bookService.updatedBook(id,updatedBook);
    if(book != null){
      return ResponseEntity.ok(book);
    }else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book with ID " + id + " not found.");
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteBook(@PathVariable Long id) {

    if (bookService.deleteBook(id)) {
      return ResponseEntity.ok("Book with ID " + id + " is deleted.");
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book with ID " + id + " not found.");
    }
  }
}

