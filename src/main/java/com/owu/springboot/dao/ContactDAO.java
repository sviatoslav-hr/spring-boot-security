package com.owu.springboot.dao;

import com.owu.springboot.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactDAO extends JpaRepository<Contact, Integer> {
}
