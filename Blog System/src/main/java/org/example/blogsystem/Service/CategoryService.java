package org.example.blogsystem.Service;

import lombok.RequiredArgsConstructor;
import org.example.blogsystem.ApiResponse.ApiException;
import org.example.blogsystem.Model.Category;
import org.example.blogsystem.Repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public void addCategory(Category category){
        categoryRepository.save(category);
    }

    public void updateCategory(Integer id,Category category){
        Category oldCategory = categoryRepository.findCategoryByCategoryId(id);

        if (oldCategory==null)
            throw new ApiException("Category not found");

        oldCategory.setName(category.getName());

        categoryRepository.save(oldCategory);
    }

    public void deleteCategory(Integer id){
        Category category = categoryRepository.findCategoryByCategoryId(id);

        if (category==null)
            throw new ApiException("Category not found");

        categoryRepository.delete(category);
    }

    public Category getCategoryById(Integer id){

        Category category =categoryRepository.findCategoryByCategoryId(id);

        if (category==null)
            throw new ApiException("Category not found");

        return category;
    }

    public Category getCategoryByName(String name){
        Category category=categoryRepository.giveMeCategoryByName(name);

        if (category==null)
            throw new ApiException("Category not found");

        return category;
    }
}
