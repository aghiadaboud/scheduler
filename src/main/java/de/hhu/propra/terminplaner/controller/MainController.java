package de.hhu.propra.terminplaner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {


  @GetMapping("/")
  public String homePage() {

    return "test";
  }


}