package com.equipo4.nocturnemusic.controller.admin;

import com.equipo4.nocturnemusic.model.Category;
import com.equipo4.nocturnemusic.model.Product;
import com.equipo4.nocturnemusic.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "category/list";
    }

    @GetMapping("/add")
    public String showAddCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "category/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditCategoryForm(@PathVariable("id") Long id, Model model) {
        Optional<Category> category = categoryService.findById(id);
        category.ifPresent(c -> model.addAttribute("category", c));
        return category.isPresent() ? "category/form" : "redirect:/admin/categories";
    }

    @PostMapping
    public String saveCategory(@ModelAttribute("category") Category category) {
        categoryService.save(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteById(id);
        return "redirect:/admin/categories";
    }

}
