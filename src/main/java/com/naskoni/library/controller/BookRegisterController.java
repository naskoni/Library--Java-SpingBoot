package com.naskoni.library.controller;

import static com.naskoni.library.constant.BookConstants.BOOKS;
import static com.naskoni.library.constant.BookConstants.BOOK_REGISTER;
import static com.naskoni.library.constant.BookConstants.URL_ADD_BOOK;
import static com.naskoni.library.constant.BookConstants.URL_BOOK_REGISTER;
import static com.naskoni.library.constant.BookConstants.URL_DELETE_BOOK;
import static com.naskoni.library.constant.BookConstants.URL_EDIT_BOOK;
import static com.naskoni.library.constant.BookConstants.URL_SEARCH_BOOK;
import static com.naskoni.library.constant.CommonConstants.ID;
import static com.naskoni.library.constant.CommonConstants.REDIRECT;
import static com.naskoni.library.constant.CommonConstants.URL_ADD;
import static com.naskoni.library.constant.CommonConstants.URL_DELETE;
import static com.naskoni.library.constant.CommonConstants.URL_EDIT;
import static com.naskoni.library.constant.CommonConstants.URL_SEARCH;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.naskoni.library.service.BookService;
import com.naskoni.library.util.UserUtils;

@Controller
public final class BookRegisterController {

  private static final Logger logger = LoggerFactory.getLogger(BookRegisterController.class);

  @Autowired
  private BookService bookService;

  @RequestMapping(value = URL_BOOK_REGISTER, method = RequestMethod.GET)
  public String loadBookRegister(Model model) {
    UserUtils.addUserToModel(model);
    model.addAttribute(BOOKS, bookService.findBooks());
    model.addAttribute(URL_EDIT, URL_EDIT_BOOK);
    model.addAttribute(URL_ADD, URL_ADD_BOOK);
    model.addAttribute(URL_DELETE, URL_DELETE_BOOK);
    model.addAttribute(URL_SEARCH, URL_SEARCH_BOOK);

    return BOOK_REGISTER;
  }

  @RequestMapping(value = URL_DELETE_BOOK, method = RequestMethod.GET)
  public String deleteBook(HttpServletRequest request) {
    Long id = Long.parseLong(request.getParameter(ID));
    bookService.deleteBook(id);

    return REDIRECT + BOOK_REGISTER;
  }

  @RequestMapping(value = URL_SEARCH_BOOK, method = RequestMethod.POST)
  public String searchBook(HttpServletRequest request, RedirectAttributes redir) {
    String searchParam = request.getParameter("searchParam");
    String searchedWord = request.getParameter("searchedWord");
    redir.addFlashAttribute("searchedBooks", bookService.findBooks(searchedWord, searchParam));

    return REDIRECT + BOOK_REGISTER;
  }

}
