package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Category;
import com.springboot.blog.exception.ResourceNotFound;
import com.springboot.blog.payload.CategoryDto;
import com.springboot.blog.repository.CategoryRepository;
import com.springboot.blog.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
private CategoryRepository categoryRepository;
private ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDto addCategory(CategoryDto categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);
        Category savedCategory = categoryRepository.save(category);
        return modelMapper.map(savedCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto getCategory(Long categoryId) {
      Category category =  categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFound("Post","id",categoryId));
      return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
       List<Category> categories = categoryRepository.findAll();
       return categories.stream().map((category) -> modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto,Long categoryId) {
        Category category =  categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFound("Post","id",categoryId));
        category.setDescription(categoryDto.getDescription());
        category.setName(categoryDto.getName());
        Category savedCategory = categoryRepository.save(category);

        return modelMapper.map(savedCategory, CategoryDto.class);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category =  categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFound("Post","id",categoryId));
        categoryRepository.delete(category);
        return "Category deleted successfully" ;
    }


}
