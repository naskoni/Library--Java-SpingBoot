package com.naskoni.library.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.naskoni.library.util.UserUtils;

@Controller
public final class HomeController {

  private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

  @GetMapping(value = {"", "/", "/home"})
  public String home(Locale locale, Model model) {
    logger.info("Welcome home! The client locale is {}.", locale);

    UserUtils.addUserToModel(model);

    return "home";
  }
}
