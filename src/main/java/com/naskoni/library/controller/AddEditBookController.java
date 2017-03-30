package com.naskoni.library.controller;

import static com.naskoni.library.constant.BookConstants.BOOK_REGISTER;
import static com.naskoni.library.constant.BookConstants.URL_ADD_BOOK;
import static com.naskoni.library.constant.BookConstants.URL_ADD_BOOK_POST;
import static com.naskoni.library.constant.BookConstants.URL_BOOK_REGISTER;
import static com.naskoni.library.constant.BookConstants.URL_EDIT_BOOK;
import static com.naskoni.library.constant.BookConstants.URL_EDIT_BOOK_POST;
import static com.naskoni.library.constant.CommonConstants.ID;
import static com.naskoni.library.constant.CommonConstants.REDIRECT;
import static com.naskoni.library.constant.CommonConstants.URL_POST;
import static com.naskoni.library.constant.CommonConstants.URL_REGISTER;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.naskoni.library.entity.Book;
import com.naskoni.library.service.BookService;
import com.naskoni.library.util.UserUtils;

@Controller
public final class AddEditBookController {

  private static final Logger logger = LoggerFactory.getLogger(AddEditBookController.class);

  @Autowired
  private BookService bookService;

  @GetMapping(value = URL_ADD_BOOK)
  public String addBook(Model model) {
    UserUtils.addUserToModel(model);
    model.addAttribute(URL_REGISTER, URL_BOOK_REGISTER);
    model.addAttribute(URL_POST, URL_ADD_BOOK_POST);

    return "addEditBook";
  }

  @PostMapping(value = URL_ADD_BOOK_POST)
  public String addBookPost(@ModelAttribute("book") Book book) {
    bookService.addBook(book);

    return REDIRECT + BOOK_REGISTER;
  }

  @GetMapping(value = URL_EDIT_BOOK)
  public String editBook(Model model, HttpServletRequest request) {
    UserUtils.addUserToModel(model);
    Long id = Long.parseLong(request.getParameter(ID));
    model.addAttribute("book", bookService.findBook(id));
    model.addAttribute(URL_REGISTER, URL_BOOK_REGISTER);
    model.addAttribute(URL_POST, URL_EDIT_BOOK_POST);
    model.addAttribute(ID, id);

    return "addEditBook";
  }

  @PostMapping(value = URL_EDIT_BOOK_POST)
  public String editBookPost(@ModelAttribute("book") Book book) {
    bookService.updateBook(book);

    return REDIRECT + BOOK_REGISTER;
  }

}
