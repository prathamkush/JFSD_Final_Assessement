package com.shop.management.service.shopManagementService.service;


import com.shop.management.service.shopManagementService.model.Category;
import com.shop.management.service.shopManagementService.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {


    @Autowired
    private CategoryRepository repository;


    public Category addCategory(Category category){
        return repository.save(category);
    }


    //--------------------------------------------------------------//
                    // Short useful services //

    public List<Category> getCategories(){
        return repository.findAll();
    }
    public boolean checkCategoryExistsById(int id){
        return repository.existsById(id);
    }
    @Transactional
    public List<Category> getCategoryByName(String name){
        return repository.getCategoriesByName(name);
    }

    //--------------------------------------------------------------//


    @Transactional
    public String updateCategoryById(int category_id, String description){
        repository.updateCategoryById(category_id, description);

        return "{\"message\" : \"Successfully UPDATED Description !!\"}";
    }

    public String deleteCategoryById(int category_id){
        repository.deleteById(category_id);

        return "{\"message\" : \"Successfully DELETED this category !!\"}";
    }





}
