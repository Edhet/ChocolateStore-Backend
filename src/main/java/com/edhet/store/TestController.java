package com.edhet.store;

import com.edhet.store.category.Category;
import com.edhet.store.category.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
@AllArgsConstructor
public class TestController {
    private final CategoryService categoryService;
    @GetMapping
    public List<Category> getCat() {
        return categoryService.getAllCategories();
    }
}
