package com.naskoni.library.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.naskoni.library.constant.CommonConstants;
import com.naskoni.library.constant.UserConstants;
import com.naskoni.library.service.UserService;
import com.naskoni.library.util.ErrorUtils;
import com.naskoni.library.util.UserUtils;

@Controller
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private UserService userService;

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = UserConstants.USER_REGISTER, method = RequestMethod.GET)
	public String loadAdminPage(Model model) {
		UserUtils.addUserToModel(model);
		model.addAttribute("users", userService.findUsers());
		model.addAttribute(CommonConstants.URL_ADD, UserConstants.URL_ADD_USER);
		model.addAttribute("url_deactivate", UserConstants.URL_DEACTIVATE_USER);
		model.addAttribute("url_search", UserConstants.URL_SEARCH_USER);

		return "admin";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = UserConstants.URL_DEACTIVATE_USER, method = RequestMethod.GET)
	public String deleteBook(HttpServletRequest request) {
		Long id = Long.parseLong(request.getParameter(CommonConstants.ID));
		userService.deactivateUser(id);

		return CommonConstants.REDIRECT + UserConstants.USER_REGISTER;
	}

	@RequestMapping(value = UserConstants.URL_SEARCH_USER, method = RequestMethod.POST)
	public String searchBook(HttpServletRequest request, RedirectAttributes redir) {
		String searchParam = request.getParameter("searchParam");
		String searchedWord = request.getParameter("searchedWord");
		redir.addFlashAttribute("searchedUsers", userService.findUsers(searchedWord, searchParam));

		return CommonConstants.REDIRECT + UserConstants.USER_REGISTER;
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleError(HttpServletRequest request, Exception exception) {
		logger.error("Request: " + request.getRequestURL() + " raised " + exception);

		return ErrorUtils.prepareErrorModelAndView();
	}
}
