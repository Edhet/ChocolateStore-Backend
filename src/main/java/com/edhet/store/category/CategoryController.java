package com.edhet.store.category;

import com.edhet.store.util.DtoMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final DtoMapper dtoMapper;

    @GetMapping("/all")
    public List<CategoryDTO> getAllCategories() {
        return categoryService
                .getAllCategories()
                .stream().map(dtoMapper::categoryToDto)
                .toList();
    }

    @GetMapping("/names")
    public List<String> getAllCategoryNames() {
        return categoryService.getAllCategoryNames();
    }

    @GetMapping("{id}")
    public CategoryDTO getCategory(@PathVariable String id) {
        return dtoMapper.categoryToDto(categoryService.getCategoryFromRequest(id));
    }
}
