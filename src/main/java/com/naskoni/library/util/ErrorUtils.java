package com.naskoni.library.util;

import org.springframework.web.servlet.ModelAndView;

public class ErrorUtils {

	private ErrorUtils() {
	}

	public static ModelAndView prepareErrorModelAndView() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("errorMessage", "Something wrong has happened.");
		modelAndView.setViewName("error");

		return modelAndView;
	}
}
