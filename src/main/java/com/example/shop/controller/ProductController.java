package com.example.shop.controller;

import com.example.shop.model.Product;
import com.example.shop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;

@Controller
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String showProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("product", new Product());
        return "products";
    }

    @PostMapping("/products")
    public String addProduct(@ModelAttribute Product product) {
        // Basic server-side validation for this learning project.
        if (product.getName() != null
                && !product.getName().isBlank()
                && product.getPrice() != null
                && product.getPrice().compareTo(BigDecimal.ZERO) >= 0
                && product.getStock() >= 0) {
            productService.addProduct(product);
        }
        return "redirect:/";
    }
}
