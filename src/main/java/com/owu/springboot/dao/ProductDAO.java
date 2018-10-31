package com.owu.springboot.dao;

import com.owu.springboot.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDAO extends JpaRepository<Product, Integer> {
     List<Product> findAll();

     List<Product> findByPriceGreaterThan(int price);

//     List<Product> findByNumber(int number);

//    List<Product> findFirstNumberByOrderByIdDesc(int number);

    List<Product> findFirst10ByOrderByIdDesc();

    List<Product> findAllByOrderByNameAsc();
}
