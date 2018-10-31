package com.owu.springboot.controllers;

import com.owu.springboot.dao.ProductDAO;
import com.owu.springboot.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductDAO productDAO;

    @GetMapping("/products")
    public String products() {
        return "products";
    }

    @GetMapping("/show-products-all")
    public String showAllProducts(
            Model model) {
        List<Product> products = productDAO.findAll();
        model.addAttribute("products", products);
        return "product-list";
    }

    @GetMapping("/show-products-last-{amount}")
    public String showProducts(
            @PathVariable int amount,
            Model model) {
        List<Product> products = productDAO.findFirst10ByOrderByIdDesc();
        model.addAttribute("products", products);
        return "product-list";
    }

    @GetMapping("/show-products-by-price-greater-than-{price}")
    public String showProductsMoreExpensiveThan(
            @PathVariable int price,
            Model model) {
        List<Product> products = productDAO.findByPriceGreaterThan(price);
        System.out.println(products);
        model.addAttribute("products",products);
        return "product-list";
    }

    @GetMapping("/show-products-sorted-by-alph")
    public String showProductsSortedByAlph(Model model) {
        List<Product> products = productDAO.findAllByOrderByNameAsc();
        System.out.println(products);
        model.addAttribute("products", products);
        return "product-list";
    }

    @PostMapping("/save-product")
    public String saveProducts(
            Product product
    ) {
        System.out.println(product);
        productDAO.save(product);
        System.out.println(product);
        return "redirect:/products";
    }
}
