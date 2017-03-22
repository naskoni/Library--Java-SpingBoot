package com.naskoni.library.controller;

import java.util.Set;

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

import com.naskoni.library.constant.ClientConstants;
import com.naskoni.library.constant.CommonConstants;
import com.naskoni.library.entity.Client;
import com.naskoni.library.service.ClientService;
import com.naskoni.library.util.ErrorUtils;
import com.naskoni.library.util.UserUtils;

@Controller
public final class ClientRegisterController {

	private static final Logger logger = LoggerFactory.getLogger(ClientRegisterController.class);

	@Autowired
	private ClientService clientService;

	@RequestMapping(value = ClientConstants.URL_CLIENT_REGISTER, method = RequestMethod.GET)
	public String loadClientRegister(Model model) {
		UserUtils.addUserToModel(model);
		model.addAttribute("clients", clientService.findClients());
		model.addAttribute(CommonConstants.URL_EDIT, ClientConstants.URL_EDIT_CLIENT);
		model.addAttribute(CommonConstants.URL_ADD, ClientConstants.URL_ADD_CLIENT);
		model.addAttribute(CommonConstants.URL_DELETE, ClientConstants.URL_DELETE_CLIENT);
		model.addAttribute(CommonConstants.URL_SEARCH, ClientConstants.URL_SEARCH_CLIENT);

		return ClientConstants.CLIENT_REGISTER;
	}

	@RequestMapping(value = ClientConstants.URL_DELETE_CLIENT, method = RequestMethod.GET)
	public String deleteClient(HttpServletRequest request) {
		Long id = Long.parseLong(request.getParameter(CommonConstants.ID));
		clientService.deleteClient(id);

		return CommonConstants.REDIRECT + ClientConstants.CLIENT_REGISTER;
	}

	@RequestMapping(value = ClientConstants.URL_SEARCH_CLIENT, method = RequestMethod.POST)
	public String searchClient(HttpServletRequest request, RedirectAttributes redir) {
		String searchParam = request.getParameter("searchParam");
		String searchedWord = request.getParameter("searchedWord");
		Set<Client> clients = clientService.findClients(searchedWord, searchParam);
		redir.addFlashAttribute("searchedClients", clients);

		return CommonConstants.REDIRECT + ClientConstants.CLIENT_REGISTER;
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleError(HttpServletRequest request, Exception exception) {
		logger.error("Request: " + request.getRequestURL() + " raised " + exception);

		return ErrorUtils.prepareErrorModelAndView();
	}
}
