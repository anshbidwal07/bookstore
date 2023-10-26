package com.bookStore.bookStore.service;

import com.bookStore.bookStore.dao.BookRepository;
import com.bookStore.bookStore.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookServiceImpl implements BookService{
  @Autowired
  private BookRepository bookRepository;

//  public BookServiceImpl(BookRepository bookRepository) {
//    this.bookRepository = bookRepository;
//  }

  @Override
  public List<Book> getAllBooks() {
    return bookRepository.findAll();
  }

  @Override
  public Book getBookById(Long id) {
    return bookRepository.findById(id).orElse(null);
  }

  @Override
  public Book createBook(Book book) {
    return bookRepository.save(book);
  }

  @Override
  public Book updatedBook(Long id, Book updatedBook) {
    Book book = bookRepository.findById(id).orElse(null);
    if (book != null) {
      book.setId(updatedBook.getId());
      book.setName(updatedBook.getName());
      book.setAuthor(updatedBook.getAuthor());
      book.setPrice(updatedBook.getPrice());
      return bookRepository.save(book);
    }
    return null;
  }

  @Override
  public boolean deleteBook(Long id) {
    if (bookRepository.existsById(id)) {
      bookRepository.deleteById(id);
      return true;
    }
    return false;
  }
}
