package com.naskoni.library.controller;

import static com.naskoni.library.constant.BookConstants.BOOKS;
import static com.naskoni.library.constant.BookConstants.BOOK_ID;
import static com.naskoni.library.constant.ClientConstants.CLIENT_ID;
import static com.naskoni.library.constant.CommonConstants.ID;
import static com.naskoni.library.constant.CommonConstants.REDIRECT;
import static com.naskoni.library.constant.CommonConstants.URL_POST;
import static com.naskoni.library.constant.CommonConstants.URL_REGISTER;
import static com.naskoni.library.constant.LendConstants.LEND_REGISTER;
import static com.naskoni.library.constant.LendConstants.URL_ADD_LEND;
import static com.naskoni.library.constant.LendConstants.URL_ADD_LEND_POST;
import static com.naskoni.library.constant.LendConstants.URL_EDIT_LEND;
import static com.naskoni.library.constant.LendConstants.URL_EDIT_LEND_POST;
import static com.naskoni.library.constant.LendConstants.URL_LEND_REGISTER;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.naskoni.library.entity.Book;
import com.naskoni.library.entity.Client;
import com.naskoni.library.entity.Lend;
import com.naskoni.library.service.BookService;
import com.naskoni.library.service.ClientService;
import com.naskoni.library.service.LendService;
import com.naskoni.library.util.UserUtils;

@Controller
public class AddEditLendController {

  private static final Logger logger = LoggerFactory.getLogger(AddEditLendController.class);

  @Autowired
  private LendService lendService;

  @Autowired
  private BookService bookService;

  @Autowired
  private ClientService clientService;

  @GetMapping(value = URL_ADD_LEND)
  public String addLend(Model model) {

    UserUtils.addUserToModel(model);
    model.addAttribute(URL_REGISTER, URL_LEND_REGISTER);
    model.addAttribute(URL_POST, URL_ADD_LEND_POST);
    model.addAttribute(BOOKS, bookService.findBooks());
    model.addAttribute("clients", clientService.findClients());

    return "addEditLend";
  }

  @PostMapping(value = URL_ADD_LEND_POST)
  public String addLendPost(@ModelAttribute("lend") Lend lend, HttpServletRequest request) {
    Long bookId = Long.parseLong(request.getParameter(BOOK_ID));
    Book book = bookService.findBook(bookId);
    Long clientId = Long.parseLong(request.getParameter(CLIENT_ID));
    Client client = clientService.findClient(clientId);
    lend.setBook(book);
    lend.setClient(client);
    lendService.addLend(lend);

    return REDIRECT + LEND_REGISTER;
  }

  @Secured("ROLE_ADMIN")
  @GetMapping(value = URL_EDIT_LEND)
  public String editLend(Model model, HttpServletRequest request) {
    UserUtils.addUserToModel(model);
    Long id = Long.parseLong(request.getParameter(ID));
    model.addAttribute("lend", lendService.findLend(id));
    model.addAttribute(BOOKS, bookService.findBooks());
    model.addAttribute("clients", clientService.findClients());
    model.addAttribute(URL_REGISTER, URL_LEND_REGISTER);
    model.addAttribute(URL_POST, URL_EDIT_LEND_POST);
    model.addAttribute(ID, id);

    return "addEditLend";
  }

  @Secured("ROLE_ADMIN")
  @PostMapping(value = URL_EDIT_LEND_POST)
  public String editLendPost(@ModelAttribute("lend") Lend lend, HttpServletRequest request) {
    Long bookId = Long.parseLong(request.getParameter(BOOK_ID));
    Book book = bookService.findBook(bookId);
    Long clientId = Long.parseLong(request.getParameter(CLIENT_ID));
    Client client = clientService.findClient(clientId);
    lend.setBook(book);
    lend.setClient(client);

    lendService.updateLend(lend);

    return REDIRECT + LEND_REGISTER;
  }

}
