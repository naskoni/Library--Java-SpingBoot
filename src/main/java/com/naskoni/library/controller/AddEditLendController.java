package com.naskoni.library.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.naskoni.library.constant.BookConstants;
import com.naskoni.library.constant.ClientConstants;
import com.naskoni.library.constant.CommonConstants;
import com.naskoni.library.constant.LendConstants;
import com.naskoni.library.entity.Book;
import com.naskoni.library.entity.Client;
import com.naskoni.library.entity.Lend;
import com.naskoni.library.service.BookService;
import com.naskoni.library.service.ClientService;
import com.naskoni.library.service.LendService;
import com.naskoni.library.util.ErrorUtils;
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

	@RequestMapping(value = LendConstants.URL_ADD_LEND, method = RequestMethod.GET)
	public String addLend(Model model) {

		UserUtils.addUserToModel(model);
		model.addAttribute(CommonConstants.URL_REGISTER, LendConstants.URL_LEND_REGISTER);
		model.addAttribute(CommonConstants.URL_POST, LendConstants.URL_ADD_LEND_POST);
		model.addAttribute(BookConstants.BOOKS, bookService.findBooks());
		model.addAttribute("clients", clientService.findClients());

		return "addEditLend";
	}

	@RequestMapping(value = LendConstants.URL_ADD_LEND_POST, method = RequestMethod.POST)
	public String addLendPost(@ModelAttribute("lend") Lend lend, HttpServletRequest request) {
		Long bookId = Long.parseLong(request.getParameter(BookConstants.BOOK_ID));
		Book book = bookService.findBook(bookId);
		Long clientId = Long.parseLong(request.getParameter(ClientConstants.CLIENT_ID));
		Client client = clientService.findClient(clientId);
		lend.setBook(book);
		lend.setClient(client);
		lendService.addLend(lend);

		return CommonConstants.REDIRECT + LendConstants.LEND_REGISTER;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = LendConstants.URL_EDIT_LEND, method = RequestMethod.GET)
	public String editLend(Model model, HttpServletRequest request) {
		UserUtils.addUserToModel(model);
		Long id = Long.parseLong(request.getParameter(CommonConstants.ID));
		model.addAttribute("lend", lendService.findLend(id));
		model.addAttribute(BookConstants.BOOKS, bookService.findBooks());
		model.addAttribute("clients", clientService.findClients());
		model.addAttribute(CommonConstants.URL_REGISTER, LendConstants.URL_LEND_REGISTER);
		model.addAttribute(CommonConstants.URL_POST, LendConstants.URL_EDIT_LEND_POST);
		model.addAttribute(CommonConstants.ID, id);

		return "addEditLend";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = LendConstants.URL_EDIT_LEND_POST, method = RequestMethod.POST)
	public String editLendPost(@ModelAttribute("lend") Lend lend, HttpServletRequest request) {
		Long bookId = Long.parseLong(request.getParameter(BookConstants.BOOK_ID));
		Book book = bookService.findBook(bookId);
		Long clientId = Long.parseLong(request.getParameter(ClientConstants.CLIENT_ID));
		Client client = clientService.findClient(clientId);
		lend.setBook(book);
		lend.setClient(client);

		lendService.updateLend(lend);

		return CommonConstants.REDIRECT + LendConstants.LEND_REGISTER;
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleError(HttpServletRequest request, Exception exception) {
		logger.error("Request: " + request.getRequestURL() + " raised " + exception);

		return ErrorUtils.prepareErrorModelAndView();
	}
}
