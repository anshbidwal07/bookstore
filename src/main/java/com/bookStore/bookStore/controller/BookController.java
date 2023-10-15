package com.bookStore.bookStore.controller;

import com.bookStore.bookStore.entities.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController("/api")
public class BookController {
  @PersistenceContext
  private EntityManager entityManager; // An API that manages the lifecycle of entity instance.
  @GetMapping("/books")
  public List<Book> getAllBooks(){
    TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b", Book.class);
    return query.getResultList();
  }
  @GetMapping("/books/{id}")
  public Book getBookById(@PathVariable Long id){
    return entityManager.find(Book.class,id);
  }

  @PostMapping
  @Transactional
  public Book createBook(@RequestBody Book book){
    entityManager.persist(book);
    return book;
  }

  @PutMapping("/{id}")
  @Transactional
  public ResponseEntity<Object> updateBookById(@PathVariable Long id, @RequestBody Book updatedBook){
    Book exixtingBook = entityManager.find(Book.class,id);
    if (exixtingBook != null){
      exixtingBook.setId(updatedBook.getId());
      exixtingBook.setName(updatedBook.getName());
      exixtingBook.setAuthor(updatedBook.getAuthor());
      exixtingBook.setPrice(updatedBook.getPrice());
      return ResponseEntity.ok(exixtingBook);
    }else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book with ID " + id + " not found.");
    }
  }

  @DeleteMapping("/{id}")
  @Transactional
    public ResponseEntity<Object> deleteBook(@PathVariable Long id){
      Book book = entityManager.find(Book.class,id);
      if (book != null){
        entityManager.remove(book);
        return ResponseEntity.ok("Book with ID " + id + " is deleted." );
      }else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book with ID " + id + " not found.");
      }
    }
}

