package com.example.reminder.controller;

import com.example.reminder.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.reminder.domain.Product;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("product/new")
	public String newProduct(Model model) {
		model.addAttribute("product", new Product());
		return "productform";
	}

	@PostMapping("product")
	public String saveProduct(Product product) {
		productService.save(product);
		return "redirect:/product/" + product.getId();
	}

	@GetMapping("product/{id}")
	public String showProduct(@PathVariable Integer id, Model model) {
		model.addAttribute("product", productService.getById(id));
		return "productshow";
	}
	
	@GetMapping("/products")
	public String list(Model model) {
		model.addAttribute("products",productService.listAll());
		return "products";
	}
	
	@GetMapping("product/edit/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		model.addAttribute("product", productService.getById(id));
		return "productform";
	}
	
	@GetMapping("product/delete/{id}")
	public String delete(@PathVariable Integer id) {
		productService.delete(id);
		return "redirect:/products";
	}

}
