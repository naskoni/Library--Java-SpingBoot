package com.naskoni.library.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Exception handler component that processes the exceptions thrown by all controllers
 * 
 * @author Atanas Atanasov
 * @version 1.0.0
 */
@ControllerAdvice
public class ErrorHandler {

  private static final Logger logger = LoggerFactory.getLogger(ErrorHandler.class);

  @ExceptionHandler(Exception.class)
  public ModelAndView handleError(HttpServletRequest request, Exception exception) {
    logger.error("Request: " + request.getRequestURL() + " raised " + exception, exception);

    ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject("errorMessage", "Something wrong has happened.");
    modelAndView.setViewName("error");

    return modelAndView;
  }

}
