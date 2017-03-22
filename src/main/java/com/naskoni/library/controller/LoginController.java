package com.naskoni.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	private static final String LOGIN = "login";

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loadLoginPage() {
		return LOGIN;
	}
}
