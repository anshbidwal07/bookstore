package com.bookStore.bookStore.controller;

import com.bookStore.bookStore.entities.Book;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/books")
public class BookController {

  private List<Book> books = new ArrayList<>();

  @GetMapping
  public List<Book> getAllBooks() {
    return books;
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> getBookById(@PathVariable int id) {
    for (Book book : books) {
      if (Objects.equals(book.getId(), id)) {
        return ResponseEntity.ok(book);
      }
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book with ID " + id + " not found.");
  }

  @PostMapping
  public ResponseEntity<Object> createBook(@RequestBody Book book) {
    books.add(book);
    return ResponseEntity.ok(book);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> updateBook(@PathVariable int id, @RequestBody Book updatedBook) {
    for (Book book : books) {
      if (Objects.equals(book.getId(), id)) {
        book.setId(updatedBook.getId());
        book.setPrice(updatedBook.getPrice());
        book.setAuthor(updatedBook.getAuthor());
        return ResponseEntity.ok(book);
      }
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book with ID " + id + " not found.");
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteBook(@PathVariable int id) {
    for (Book book : books) {
      if (Objects.equals(book.getId(), id)) {
        books.remove(book);
        return ResponseEntity.ok("Book with ID " + id + " is deleted.");
      }
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book with ID " + id + " not found.");
  }

}

