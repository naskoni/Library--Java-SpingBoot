package com.naskoni.library.controller;

import static com.naskoni.library.constant.ClientConstants.CLIENT_REGISTER;
import static com.naskoni.library.constant.ClientConstants.URL_ADD_CLIENT;
import static com.naskoni.library.constant.ClientConstants.URL_ADD_CLIENT_POST;
import static com.naskoni.library.constant.ClientConstants.URL_CLIENT_REGISTER;
import static com.naskoni.library.constant.ClientConstants.URL_EDIT_CLIENT;
import static com.naskoni.library.constant.ClientConstants.URL_EDIT_CLIENT_POST;
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

import com.naskoni.library.entity.Client;
import com.naskoni.library.service.ClientService;
import com.naskoni.library.util.UserUtils;

@Controller
public final class AddEditClientController {

  private static final Logger logger = LoggerFactory.getLogger(AddEditClientController.class);

  @Autowired
  private ClientService clientService;

  @GetMapping(value = URL_ADD_CLIENT)
  public String addClient(Model model) {
    UserUtils.addUserToModel(model);
    model.addAttribute(URL_REGISTER, URL_CLIENT_REGISTER);
    model.addAttribute(URL_POST, URL_ADD_CLIENT_POST);

    return "addEditClient";
  }

  @PostMapping(value = URL_ADD_CLIENT_POST)
  public String addClientPost(@ModelAttribute("client") Client client) {
    clientService.addClient(client);

    return REDIRECT + CLIENT_REGISTER;
  }

  @GetMapping(value = URL_EDIT_CLIENT)
  public String editClient(Model model, HttpServletRequest request) {

    Long id = Long.parseLong(request.getParameter(ID));

    UserUtils.addUserToModel(model);
    model.addAttribute("client", clientService.findClient(id));
    model.addAttribute(URL_REGISTER, URL_CLIENT_REGISTER);
    model.addAttribute(URL_POST, URL_EDIT_CLIENT_POST);
    model.addAttribute(ID, id);

    return "addEditClient";
  }

  @PostMapping(value = URL_EDIT_CLIENT_POST)
  public String editClientPost(@ModelAttribute("client") Client client) {
    clientService.update(client);

    return REDIRECT + CLIENT_REGISTER;
  }

}
