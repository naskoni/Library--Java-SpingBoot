package com.naskoni.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

  private static final String LOGIN = "login";

  @GetMapping(value = "/login")
  public String loadLoginPage() {
    return LOGIN;
  }
}
