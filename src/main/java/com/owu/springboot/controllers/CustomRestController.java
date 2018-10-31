package com.owu.springboot.controllers;

import com.owu.springboot.dao.ContactDAO;
import com.owu.springboot.models.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class CustomRestController {
    @Autowired
    private ContactDAO contactDAO;


    @PostMapping("/save-ajax")
    public /*@ResponseBody*/ List<Contact> saveAjax(
            @RequestBody Contact contact
    ) {
        contactDAO.save(contact);
        System.out.println(contact);
        return contactDAO.findAll();
    }

    @PostMapping("/upload")
    public void upload(
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam String phone,
            @RequestParam MultipartFile image
                         ) throws IOException {
        Contact contact = new Contact(name, surname, phone);


        System.out.println(contact);
        contactDAO.save(contact);
        System.out.println(contact);
    }
    
}
