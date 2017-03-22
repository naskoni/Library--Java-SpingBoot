package com.naskoni.library.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.naskoni.library.constant.BookConstants;
import com.naskoni.library.constant.CommonConstants;
import com.naskoni.library.service.BookService;
import com.naskoni.library.util.ErrorUtils;
import com.naskoni.library.util.UserUtils;

@Controller
public final class BookRegisterController {

  private static final Logger logger = LoggerFactory.getLogger(BookRegisterController.class);

  @Autowired
  private BookService bookService;

  @RequestMapping(value = BookConstants.URL_BOOK_REGISTER, method = RequestMethod.GET)
  public String loadBookRegister(Model model) {
    UserUtils.addUserToModel(model);
    model.addAttribute(BookConstants.BOOKS, bookService.findBooks());
    model.addAttribute(CommonConstants.URL_EDIT, BookConstants.URL_EDIT_BOOK);
    model.addAttribute(CommonConstants.URL_ADD, BookConstants.URL_ADD_BOOK);
    model.addAttribute(CommonConstants.URL_DELETE, BookConstants.URL_DELETE_BOOK);
    model.addAttribute(CommonConstants.URL_SEARCH, BookConstants.URL_SEARCH_BOOK);

    return BookConstants.BOOK_REGISTER;
  }

  @RequestMapping(value = BookConstants.URL_DELETE_BOOK, method = RequestMethod.GET)
  public String deleteBook(HttpServletRequest request) {
    Long id = Long.parseLong(request.getParameter(CommonConstants.ID));
    bookService.deleteBook(id);

    return CommonConstants.REDIRECT + BookConstants.BOOK_REGISTER;
  }

  @RequestMapping(value = BookConstants.URL_SEARCH_BOOK, method = RequestMethod.POST)
  public String searchBook(HttpServletRequest request, RedirectAttributes redir) {
    String searchParam = request.getParameter("searchParam");
    String searchedWord = request.getParameter("searchedWord");
    redir.addFlashAttribute("searchedBooks", bookService.findBooks(searchedWord, searchParam));

    return CommonConstants.REDIRECT + BookConstants.BOOK_REGISTER;
  }

  @ExceptionHandler(Exception.class)
  public ModelAndView handleError(HttpServletRequest request, Exception exception) {
    logger.error("Request: " + request.getRequestURL() + " raised " + exception);

    return ErrorUtils.prepareErrorModelAndView();
  }
}
