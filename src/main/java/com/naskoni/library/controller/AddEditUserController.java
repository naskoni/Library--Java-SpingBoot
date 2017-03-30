package com.naskoni.library.controller;

import static com.naskoni.library.constant.CommonConstants.REDIRECT;
import static com.naskoni.library.constant.CommonConstants.URL_POST;
import static com.naskoni.library.constant.CommonConstants.URL_REGISTER;
import static com.naskoni.library.constant.UserConstants.URL_ADD_USER;
import static com.naskoni.library.constant.UserConstants.URL_ADD_USER_POST;
import static com.naskoni.library.constant.UserConstants.URL_EDIT_USER;
import static com.naskoni.library.constant.UserConstants.URL_EDIT_USER_POST;
import static com.naskoni.library.constant.UserConstants.URL_USER_REGISTER;
import static com.naskoni.library.constant.UserConstants.USER;
import static com.naskoni.library.constant.UserConstants.USER_REGISTER;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.naskoni.library.entity.LibraryUser;
import com.naskoni.library.service.UserService;
import com.naskoni.library.util.UserUtils;

@Controller
public class AddEditUserController {

  private static final Logger logger = LoggerFactory.getLogger(AddEditUserController.class);

  @Autowired
  private UserService userService;

  @Secured("ROLE_ADMIN")
  @GetMapping(value = URL_ADD_USER)
  public String addUser(Model model) {
    UserUtils.addUserToModel(model);
    model.addAttribute(URL_REGISTER, URL_USER_REGISTER);
    model.addAttribute(URL_POST, URL_ADD_USER_POST);

    return "addUser";
  }

  @Secured("ROLE_ADMIN")
  @PostMapping(value = URL_ADD_USER_POST)
  public String addUserPost(@ModelAttribute("user") LibraryUser user) {
    userService.addUser(user);

    return REDIRECT + USER_REGISTER;
  }

  @GetMapping(value = URL_EDIT_USER)
  public String editUser(Model model) {
    UserDetails loggedUser = UserUtils.getUser();
    LibraryUser user = userService.findUser(loggedUser.getUsername());
    model.addAttribute(USER, user);
    model.addAttribute(URL_POST, URL_EDIT_USER_POST);

    return "editUser";
  }

  @PostMapping(value = URL_EDIT_USER_POST)
  public String editUserPost(@ModelAttribute("user") LibraryUser user) {
    userService.updateUser(user);

    return "home";
  }

}
