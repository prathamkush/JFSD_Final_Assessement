package com.shop.management.service.shopManagementService.controller;


import com.shop.management.service.shopManagementService.exceptionHandling.BadRequestException;
import com.shop.management.service.shopManagementService.exceptionHandling.CategoryException;
import com.shop.management.service.shopManagementService.model.Category;
import com.shop.management.service.shopManagementService.model.CategoryPayloadValidation;
import com.shop.management.service.shopManagementService.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService service;



    @RequestMapping(value = "/add-category", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Category addCategory(@RequestBody Category category) throws BadRequestException {

        if(!CategoryPayloadValidation.createdPayloadIdValidation(category)) throw new BadRequestException("PAYLOAD MALFORMED. CATEGORY ID MUST NOT BE DEFINED !!!");

        if(!service.getCategoryByName(category.getName()).isEmpty()) throw new BadRequestException("Category with this name is already present!!!");

        return service.addCategory(category);
    }


    @RequestMapping(value = "/get-categories", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Category> getCategories(){
        return service.getCategories();
    }


    @RequestMapping(value = "/update-description-by-id/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateCategoryById(@PathVariable("id") int category_id, @RequestBody Map<String, Object> map) throws CategoryException, BadRequestException{

        if(!CategoryPayloadValidation.createdPayloadDescriptionField(map)) throw new BadRequestException("PAYLOAD MALFORMED. Description MUST be PROVIDED !!!");

        if(!service.checkCategoryExistsById(category_id)) throw new CategoryException("Category with this id NOT FOUND");

        return service.updateCategoryById(category_id,map.get("description").toString());

    }


    @RequestMapping(value = "/delete-category-by-id/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteCategoryById(@PathVariable("id") int category_id) throws CategoryException{

        if(!service.checkCategoryExistsById(category_id)) throw new CategoryException("Category with this id NOT FOUND");

        return service.deleteCategoryById(category_id);
    }





}
