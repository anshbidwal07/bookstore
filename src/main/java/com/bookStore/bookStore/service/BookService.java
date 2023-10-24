package com.bookStore.bookStore.service;

import com.bookStore.bookStore.entities.Book;

import java.util.List;

public interface BookService {
  List<Book> getAllBooks();
  Book getBookById(Long id);
  Book createBook(Book book);
  Book updatedBook (Long id, Book updatedBook);
  boolean deleteBook(Long id);
}
