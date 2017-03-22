package com.naskoni.library.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.naskoni.library.constant.BookConstants;
import com.naskoni.library.constant.CommonConstants;
import com.naskoni.library.entity.Book;
import com.naskoni.library.service.BookService;
import com.naskoni.library.util.ErrorUtils;
import com.naskoni.library.util.UserUtils;

@Controller
public final class AddEditBookController {

  private static final Logger logger = LoggerFactory.getLogger(AddEditBookController.class);

  @Autowired
  private BookService bookService;

  @RequestMapping(value = BookConstants.URL_ADD_BOOK, method = RequestMethod.GET)
  public String addBook(Model model) {

    UserUtils.addUserToModel(model);
    model.addAttribute(CommonConstants.URL_REGISTER, BookConstants.URL_BOOK_REGISTER);
    model.addAttribute(CommonConstants.URL_POST, BookConstants.URL_ADD_BOOK_POST);

    return "addEditBook";
  }

  @RequestMapping(value = BookConstants.URL_ADD_BOOK_POST, method = RequestMethod.POST)
  public String addBookPost(@ModelAttribute("book") Book book) {
    bookService.addBook(book);

    return CommonConstants.REDIRECT + BookConstants.BOOK_REGISTER;
  }

  @RequestMapping(value = BookConstants.URL_EDIT_BOOK, method = RequestMethod.GET)
  public String editBook(Model model, HttpServletRequest request) {
    UserUtils.addUserToModel(model);
    Long id = Long.parseLong(request.getParameter(CommonConstants.ID));
    model.addAttribute("book", bookService.findBook(id));
    model.addAttribute(CommonConstants.URL_REGISTER, BookConstants.URL_BOOK_REGISTER);
    model.addAttribute(CommonConstants.URL_POST, BookConstants.URL_EDIT_BOOK_POST);
    model.addAttribute(CommonConstants.ID, id);

    return "addEditBook";
  }

  @RequestMapping(value = BookConstants.URL_EDIT_BOOK_POST, method = RequestMethod.POST)
  public String editBookPost(@ModelAttribute("book") Book book) {
    bookService.updateBook(book);

    return CommonConstants.REDIRECT + BookConstants.BOOK_REGISTER;
  }

  @ExceptionHandler(Exception.class)
  public ModelAndView handleError(HttpServletRequest request, Exception exception) {

    logger.error("Request: " + request.getRequestURL() + " raised " + exception, exception);

    return ErrorUtils.prepareErrorModelAndView();
  }
}
