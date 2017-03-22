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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.naskoni.library.entity.Client;
import com.naskoni.library.service.ClientService;
import com.naskoni.library.util.ErrorUtils;
import com.naskoni.library.util.UserUtils;

@Controller
public final class AddEditClientController {

  private static final Logger logger = LoggerFactory.getLogger(AddEditClientController.class);

  @Autowired
  private ClientService clientService;

  @RequestMapping(value = URL_ADD_CLIENT, method = RequestMethod.GET)
  public String addClient(Model model) {
    UserUtils.addUserToModel(model);
    model.addAttribute(URL_REGISTER, URL_CLIENT_REGISTER);
    model.addAttribute(URL_POST, URL_ADD_CLIENT_POST);

    return "addEditClient";
  }

  @RequestMapping(value = URL_ADD_CLIENT_POST, method = RequestMethod.POST)
  public String addClientPost(@ModelAttribute("client") Client client) {
    clientService.addClient(client);

    return REDIRECT + CLIENT_REGISTER;
  }

  @RequestMapping(value = URL_EDIT_CLIENT, method = RequestMethod.GET)
  public String editClient(Model model, HttpServletRequest request) {

    Long id = Long.parseLong(request.getParameter(ID));

    UserUtils.addUserToModel(model);
    model.addAttribute("client", clientService.findClient(id));
    model.addAttribute(URL_REGISTER, URL_CLIENT_REGISTER);
    model.addAttribute(URL_POST, URL_EDIT_CLIENT_POST);
    model.addAttribute(ID, id);

    return "addEditClient";
  }

  @RequestMapping(value = URL_EDIT_CLIENT_POST, method = RequestMethod.POST)
  public String editClientPost(@ModelAttribute("client") Client client) {
    clientService.update(client);

    return REDIRECT + CLIENT_REGISTER;
  }

  @ExceptionHandler(Exception.class)
  public ModelAndView handleError(HttpServletRequest request, Exception exception) {
    logger.error("Request: " + request.getRequestURL() + " raised " + exception);

    return ErrorUtils.prepareErrorModelAndView();
  }
}
