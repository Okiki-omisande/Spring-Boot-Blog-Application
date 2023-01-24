package com.springboot.blog.service;

import com.springboot.blog.payload.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    CategoryDto addCategory(CategoryDto categoryDTO);
    CategoryDto getCategory(Long categoryId);
    List<CategoryDto> getAllCategory();
    CategoryDto updateCategory(CategoryDto categoryDto,Long categoryId);
    String deleteCategory(Long CategoryId);
}
