package com.example.demo.controller;

import com.example.demo.dao.EntryRepository;
import com.example.demo.dao.ListImageRepository;
import com.example.demo.dao.MainInfoRepository;
import com.example.demo.dao.ProductRepository;
import com.example.demo.model.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
public class AdminController{


    @Autowired
    private UserService service;

    @Autowired
    private MetaFileDbService offerService;

    @Autowired
    private FileStorageService storeService;

    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private MainInfoRepository infoRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ListImageRepository productImageRepository;


    @RequestMapping("admin")
    public ModelAndView adminPage() {
        List<User> users = service.getAllUser();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Role> rolesUser =(List<Role>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        List<GeneralEntry> listBanner = entryRepository.getEntryByCategory("banner");
        List<GeneralEntry> listAdvantage = entryRepository.getEntryByCategory("advantage");
        List<GeneralEntry> listOffer_c = entryRepository.getEntryByCategory("offer_c");
        List<GeneralEntry> listFaq = entryRepository.getEntryByCategory("faq");
        List<MainInfo> mainInfo = infoRepository.findAll();
        user.setRoles(rolesUser);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin");
        modelAndView.addObject("user", user);
        modelAndView.addObject("mainInfo", mainInfo);
        modelAndView.addObject("listBanner", listBanner);
        modelAndView.addObject("listAdvantage", listAdvantage);
        modelAndView.addObject("listOffer", listOffer_c);
        modelAndView.addObject("listFaq", listFaq);
        return modelAndView;
    }

    @RequestMapping(value="admin/edit", method = RequestMethod.GET)
    public ModelAndView editPage() {
        List<Role> listRoles = service.getAllRole();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("listRoles", listRoles);
        modelAndView.setViewName("admin");
        return modelAndView;
    }

    @RequestMapping(value = "admin/edit", method = RequestMethod.POST)
    public ModelAndView editUser(@ModelAttribute("user") User user, @RequestParam("checkboxRole") String[] checkboxRoles) {
        List<Role> listRoles = service.getAllRole();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin");
        List<Role> oldRoles = new ArrayList<>();
        for (int i = 0; i < checkboxRoles.length; i++) {
            oldRoles.add(new Role(checkboxRoles[i]));
        }
        user.setRoles(oldRoles);
        service.edit(user, checkboxRoles);
        return modelAndView;
    }

    @RequestMapping(value="adminset", method = RequestMethod.GET)
    public ModelAndView settingsPage() {
        List<Role> listRoles = service.getAllRole();

        List<User> users = service.getAllUser();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Role> rolesUser =(List<Role>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        user.setRoles(rolesUser);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin-settings");
        modelAndView.addObject("user", user);
        modelAndView.addObject("userList", users);
        modelAndView.addObject("listRoles", listRoles);

        return modelAndView;
    }

    @RequestMapping(value="product", method = RequestMethod.GET)
    public ModelAndView productPage() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ModelAndView modelAndView = new ModelAndView();
        List<Product> productList = productRepository.findAll();
        List<ListImage> listImages = productImageRepository.findAll();
        modelAndView.setViewName("product");
        modelAndView.addObject("user", user);
        modelAndView.addObject("productList", productList);
        modelAndView.addObject("listImages", listImages);

        return modelAndView;
    }

    @RequestMapping(value="schoolForAdmin", method = RequestMethod.GET)
    public ModelAndView schoolForAdminPage() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin-offer2-page");
        modelAndView.addObject("user", user);

        return modelAndView;
    }

    @RequestMapping(value="grownForAdmin", method = RequestMethod.GET)
    public ModelAndView grownForAdminPage() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin-offer3-page");
        modelAndView.addObject("user", user);

        return modelAndView;
    }

    @RequestMapping(value="timbildingForAdmin", method = RequestMethod.GET)
    public ModelAndView timbildingForAdminPage() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin-offer4-page");
        modelAndView.addObject("user", user);
        return modelAndView;
    }






}
