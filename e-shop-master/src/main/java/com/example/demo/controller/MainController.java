package com.example.demo.controller;

import com.example.demo.dao.EntryRepository;
import com.example.demo.model.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class MainController {

    @Autowired
    private EntryRepository entryRepository;

    @RequestMapping(value={"/index","/"}, method = RequestMethod.GET)
    public ModelAndView indexPage() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("banner", entryRepository.findAll());
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping("/user")
    public String user() {
        return "/user";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/forget")
    public String forget() {
        return "/forget";
    }

    @GetMapping("/403")
    public String error403() {
        return "/403";
    }

    @Autowired
    private EmailService emailService;

    @PostMapping("/sendEmail")
    public String sendEmail(@RequestParam String emailTo) {
        emailService.sendEmail(emailTo);
        return "message send !";

    }
}
