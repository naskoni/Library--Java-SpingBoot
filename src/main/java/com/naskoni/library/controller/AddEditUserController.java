package com.naskoni.library.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.naskoni.library.constant.CommonConstants;
import com.naskoni.library.constant.UserConstants;
import com.naskoni.library.entity.LibraryUser;
import com.naskoni.library.service.UserService;
import com.naskoni.library.util.ErrorUtils;
import com.naskoni.library.util.UserUtils;

@Controller
public class AddEditUserController {

	private static final Logger logger = LoggerFactory.getLogger(AddEditUserController.class);

	@Autowired
	private UserService userService;

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = UserConstants.URL_ADD_USER, method = RequestMethod.GET)
	public String addUser(Model model) {

		UserUtils.addUserToModel(model);
		model.addAttribute(CommonConstants.URL_REGISTER, UserConstants.URL_USER_REGISTER);
		model.addAttribute(CommonConstants.URL_POST, UserConstants.URL_ADD_USER_POST);

		return "addUser";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = UserConstants.URL_ADD_USER_POST, method = RequestMethod.POST)
	public String addUserPost(@ModelAttribute("user") LibraryUser user) {
		userService.addUser(user);

		return CommonConstants.REDIRECT + UserConstants.USER_REGISTER;
	}

	@RequestMapping(value = UserConstants.URL_EDIT_USER, method = RequestMethod.GET)
	public String editUser(Model model) {
		UserDetails loggedUser = UserUtils.getUser();
		LibraryUser user = userService.findUser(loggedUser.getUsername());
		model.addAttribute(UserConstants.USER, user);
		model.addAttribute(CommonConstants.URL_POST, UserConstants.URL_EDIT_USER_POST);

		return "editUser";
	}

	@RequestMapping(value = UserConstants.URL_EDIT_USER_POST, method = RequestMethod.POST)
	public String editUserPost(@ModelAttribute("user") LibraryUser user) {
		userService.updateUser(user);

		return "home";
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleError(HttpServletRequest request, Exception exception) {
		logger.error("Request: " + request.getRequestURL() + " raised " + exception);

		return ErrorUtils.prepareErrorModelAndView();
	}
}
