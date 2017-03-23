package com.naskoni.library.controller;

import static com.naskoni.library.constant.ClientConstants.CLIENT_REGISTER;
import static com.naskoni.library.constant.ClientConstants.URL_ADD_CLIENT;
import static com.naskoni.library.constant.ClientConstants.URL_CLIENT_REGISTER;
import static com.naskoni.library.constant.ClientConstants.URL_DELETE_CLIENT;
import static com.naskoni.library.constant.ClientConstants.URL_EDIT_CLIENT;
import static com.naskoni.library.constant.ClientConstants.URL_SEARCH_CLIENT;
import static com.naskoni.library.constant.CommonConstants.ID;
import static com.naskoni.library.constant.CommonConstants.REDIRECT;
import static com.naskoni.library.constant.CommonConstants.URL_ADD;
import static com.naskoni.library.constant.CommonConstants.URL_DELETE;
import static com.naskoni.library.constant.CommonConstants.URL_EDIT;
import static com.naskoni.library.constant.CommonConstants.URL_SEARCH;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.naskoni.library.entity.Client;
import com.naskoni.library.service.ClientService;
import com.naskoni.library.util.UserUtils;

@Controller
public final class ClientRegisterController {

  private static final Logger logger = LoggerFactory.getLogger(ClientRegisterController.class);

  @Autowired
  private ClientService clientService;

  @RequestMapping(value = URL_CLIENT_REGISTER, method = RequestMethod.GET)
  public String loadClientRegister(Model model) {
    UserUtils.addUserToModel(model);
    model.addAttribute("clients", clientService.findClients());
    model.addAttribute(URL_EDIT, URL_EDIT_CLIENT);
    model.addAttribute(URL_ADD, URL_ADD_CLIENT);
    model.addAttribute(URL_DELETE, URL_DELETE_CLIENT);
    model.addAttribute(URL_SEARCH, URL_SEARCH_CLIENT);

    return CLIENT_REGISTER;
  }

  @RequestMapping(value = URL_DELETE_CLIENT, method = RequestMethod.GET)
  public String deleteClient(HttpServletRequest request) {
    Long id = Long.parseLong(request.getParameter(ID));
    clientService.deleteClient(id);

    return REDIRECT + CLIENT_REGISTER;
  }

  @RequestMapping(value = URL_SEARCH_CLIENT, method = RequestMethod.POST)
  public String searchClient(HttpServletRequest request, RedirectAttributes redir) {
    String searchParam = request.getParameter("searchParam");
    String searchedWord = request.getParameter("searchedWord");
    Set<Client> clients = clientService.findClients(searchedWord, searchParam);
    redir.addFlashAttribute("searchedClients", clients);

    return REDIRECT + CLIENT_REGISTER;
  }

}
