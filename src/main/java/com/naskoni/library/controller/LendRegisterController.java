package com.naskoni.library.controller;

import static com.naskoni.library.constant.CommonConstants.REDIRECT;
import static com.naskoni.library.constant.CommonConstants.URL_ADD;
import static com.naskoni.library.constant.CommonConstants.URL_EDIT;
import static com.naskoni.library.constant.CommonConstants.URL_SEARCH;
import static com.naskoni.library.constant.LendConstants.LEND_REGISTER;
import static com.naskoni.library.constant.LendConstants.URL_ADD_LEND;
import static com.naskoni.library.constant.LendConstants.URL_EDIT_LEND;
import static com.naskoni.library.constant.LendConstants.URL_LEND_REGISTER;
import static com.naskoni.library.constant.LendConstants.URL_SEARCH_LEND;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.naskoni.library.service.LendService;
import com.naskoni.library.util.UserUtils;

@Controller
public final class LendRegisterController {

  private static final Logger logger = LoggerFactory.getLogger(LendRegisterController.class);

  @Autowired
  private LendService lendService;

  @GetMapping(value = URL_LEND_REGISTER)
  public String loadLendRegister(Model model) {
    UserUtils.addUserToModel(model);
    model.addAttribute("lends", lendService.findLends());
    model.addAttribute(URL_EDIT, URL_EDIT_LEND);
    model.addAttribute(URL_ADD, URL_ADD_LEND);
    model.addAttribute(URL_SEARCH, URL_SEARCH_LEND);

    return LEND_REGISTER;
  }

  @PostMapping(value = URL_SEARCH_LEND)
  public String searchLend(HttpServletRequest request, RedirectAttributes redir) {
    String searchParam = request.getParameter("searchParam");
    String searchedWord = request.getParameter("searchedWord");
    redir.addFlashAttribute("searchedLends", lendService.findLends(searchedWord, searchParam));

    return REDIRECT + LEND_REGISTER;
  }

}
