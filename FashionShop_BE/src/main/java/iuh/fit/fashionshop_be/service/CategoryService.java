/*
 * @ (#) d.java     1.0    17-Oct-25
 *
 * Copyright (c) 2025 IUH. All rights reserved.
 */

package iuh.fit.fashionshop_be.service;

/*
 * @description:
 * @author: Nguyen Quoc Huy
 * @date:17-Oct-25
 * @version: 1.0
 */
import iuh.fit.fashionshop_be.dto.response.CategoryResponse;
import iuh.fit.fashionshop_be.mapper.CategoryMapper;
import iuh.fit.fashionshop_be.model.Category;
import iuh.fit.fashionshop_be.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryResponse> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.toCategoryResponseList(categories);
    }

    public CategoryResponse getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return categoryMapper.toCategoryResponse(category);
    }

    public List<CategoryResponse> getSubCategoriesByParentId(Long parentCategoryID) {
        List<Category> subCategories = categoryRepository.findByParentCategoryID(parentCategoryID);
        return categoryMapper.toCategoryResponseList(subCategories);
    }

    public List<CategoryResponse> searchCategoriesByName(String name) {
        List<Category> categories = categoryRepository.findByNameContainingIgnoreCase(name);
        return categoryMapper.toCategoryResponseList(categories);
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, Category categoryDetails) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        category.setName(categoryDetails.getName());
        category.setParent(categoryDetails.getParent());
        category.setDescription(categoryDetails.getDescription());
        category.setImage(categoryDetails.getImage());
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
