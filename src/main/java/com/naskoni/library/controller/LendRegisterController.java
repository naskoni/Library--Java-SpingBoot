package com.naskoni.library.controller;

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

import com.naskoni.library.constant.CommonConstants;
import com.naskoni.library.constant.LendConstants;
import com.naskoni.library.service.LendService;
import com.naskoni.library.util.ErrorUtils;
import com.naskoni.library.util.UserUtils;

@Controller
public final class LendRegisterController {

	private static final Logger logger = LoggerFactory.getLogger(LendRegisterController.class);

	@Autowired
	private LendService lendService;

	@RequestMapping(value = LendConstants.URL_LEND_REGISTER, method = RequestMethod.GET)
	public String loadLendRegister(Model model) {
		UserUtils.addUserToModel(model);
		model.addAttribute("lends", lendService.findLends());
		model.addAttribute(CommonConstants.URL_EDIT, LendConstants.URL_EDIT_LEND);
		model.addAttribute(CommonConstants.URL_ADD, LendConstants.URL_ADD_LEND);
		model.addAttribute(CommonConstants.URL_SEARCH, LendConstants.URL_SEARCH_LEND);

		return LendConstants.LEND_REGISTER;
	}

	@RequestMapping(value = LendConstants.URL_SEARCH_LEND, method = RequestMethod.POST)
	public String searchLend(HttpServletRequest request, RedirectAttributes redir) {
		String searchParam = request.getParameter("searchParam");
		String searchedWord = request.getParameter("searchedWord");
		redir.addFlashAttribute("searchedLends", lendService.findLends(searchedWord, searchParam));

		return CommonConstants.REDIRECT + LendConstants.LEND_REGISTER;
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleError(HttpServletRequest request, Exception exception) {
		logger.error("Request: " + request.getRequestURL() + " raised " + exception);

		return ErrorUtils.prepareErrorModelAndView();
	}
}
