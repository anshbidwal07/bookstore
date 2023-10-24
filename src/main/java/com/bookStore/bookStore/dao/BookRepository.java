package com.bookStore.bookStore.dao;

import com.bookStore.bookStore.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findById(String id);
  List<Book> findByname(String name);
  List<Book> findByauthor(String author);
  List<Book> findByPrice(String price);
}
