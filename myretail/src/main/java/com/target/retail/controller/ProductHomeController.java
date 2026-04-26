package com.target.retail.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductHomeController {

  @GetMapping("/")
  public ModelAndView document() {
    return new ModelAndView("redirect:/swagger-ui.html");
  }
}
