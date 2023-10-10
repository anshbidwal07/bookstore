package com.bookStore.bookStore.controller;

import com.bookStore.bookStore.entities.Book;
import com.bookStore.bookStore.entities.MyBookList;
import com.bookStore.bookStore.service.BookService;
import com.bookStore.bookStore.service.MyBookListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
public class BookController {
  @Autowired
  private BookService bookService;
  @Autowired
  private MyBookListService myBookListService;
    @GetMapping("/")
    public String home(){
      return "Home";
    }
   @GetMapping("/bookregister")
   public String bookRegister(){
      return "bookRegister";
  }
  @GetMapping("/availablebooks")
  public ModelAndView getAllBooks(){
    List<Book> list= bookService.getAllBook();
//    ModelAndView m =new ModelAndView();
//    m.setViewName("booklist");
//    m.addObject("book",list);
    return new ModelAndView("booklist","book",list);
  }
  @PostMapping("/save")
      public String addBook(@ModelAttribute Book b){
      bookService.save(b);
      return "redirect:/availablebooks";
  }

  @GetMapping("/mybooks")
  public String getMyBooks(Model model){
    List<MyBookList> list= myBookListService.getAllMyBooks();
    model.addAttribute("book",list);
      return "myBooks";
  }
  @RequestMapping("/mylist/{id}")
  public String getMyList(@PathVariable("id")int id){
      Book b= bookService.getBookById(id);
      MyBookList mb=new MyBookList(b.getId(),b.getName(),b.getAuthor(),b.getPrice());
      myBookListService.saveMyBooks(mb);
      return "redirect:/mybooks";
  }
  @RequestMapping("/editBook/{id}")
  public String editBook(@PathVariable("id")int id,Model model){
      Book b = bookService.getBookById(id);
      model.addAttribute("book",b);
      return "bookEdit";
  }

  @RequestMapping("/deleteBook/{id}")
  public String deleteBook(@PathVariable("id")int id){
      bookService.deleteById(id);
      return "redirect:/availablebooks";
  }
}
