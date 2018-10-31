package com.owu.springboot.controllers;

import com.owu.springboot.dao.ContactDAO;
import com.owu.springboot.models.Contact;
import com.owu.springboot.models.User;
import com.owu.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    ContactDAO contactDAO;
    @Autowired
    private UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "hello");
        return "index";
    }

    @PostMapping("/save")
    public String save(
        Contact contact,
        @RequestParam MultipartFile image
    ) throws IOException {
        String path = System.getProperty("user.home")+
                File.separator+
                "images"+
                File.separator+
                image.getOriginalFilename();
        image.transferTo(new File(path));
        contact.setAvatar(image.getOriginalFilename());
        System.out.println(contact);
        contactDAO.save(contact);
        System.out.println(contact);
        return "redirect:/";
    }

    @GetMapping("/show-all-contacts")
    public String showAllContacts(
            Model model
    ) {
        List<Contact> contacts = contactDAO.findAll();
        model.addAttribute("contacts", contacts);
        return "contact-list";
    }

    @GetMapping("/contact-details/{id}")
    public String contactDetails(
            @PathVariable int id,
            Model model
    ) {
        Contact contact = contactDAO.findById(id).get();
        model.addAttribute("contact", contact);
        return "single-contact";
    }
    
    @PostMapping("/update-contact")
    public String updateContact(Contact contact) {
        contactDAO.save(contact);
        return "redirect:/show-all-contacts";
    }

    @PostMapping("/successURL")
    public String successURL() {
        return "redirect:/show-all-contacts";
    }


    @PostMapping("/saveUser")
    public String saveUser(User user) {
        String encode = passwordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        userService.save(user);
        return "redirect:/";
    }
}
