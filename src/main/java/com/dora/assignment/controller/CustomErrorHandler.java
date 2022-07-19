package com.dora.assignment.controller;

import com.dora.assignment.exception.NotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.ui.Model;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class CustomErrorHandler {

  @ExceptionHandler(value = ConstraintViolationException.class)
  public String constraintViolationHandler(Model theModel, ConstraintViolationException e) {
    theModel.addAttribute("message", e.getMessage());
    return "error";
  }

  @ExceptionHandler(value = NotFoundException.class)
  public String nullPointerHandler(Model theModel, NotFoundException e) {
    theModel.addAttribute("message", e.getMessage());
    return "error";
  }

  @ExceptionHandler(value = Exception.class)
  public String generalExceptionHandler(Model theModel, Exception e) {
    theModel.addAttribute("message", "Something went wrong");
    return "error";
  }
}
