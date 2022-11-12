package com.shop.management.service.shopManagementService.controller;


import com.shop.management.service.shopManagementService.exceptionHandling.BadRequestException;
import com.shop.management.service.shopManagementService.exceptionHandling.EntityNotFoundException;
import com.shop.management.service.shopManagementService.model.Category;
import com.shop.management.service.shopManagementService.model.PayloadValidation;
import com.shop.management.service.shopManagementService.model.Product;
import com.shop.management.service.shopManagementService.service.CategoryService;
import com.shop.management.service.shopManagementService.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;


    @RequestMapping(value = "/get-products", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getProduct(){
        return productService.getProducts();
    }


    @RequestMapping(value = "/add-product", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Product addProduct(@RequestBody Product product) throws BadRequestException {

        if(!PayloadValidation.createdPayloadIdValidation(product)) throw new BadRequestException("PAYLOAD MALFORMED. PRODUCT ID MUST NOT BE DEFINED !!!");

        if(!productService.getProductByName(product.getName()).isEmpty()) throw new BadRequestException("Product with this name is already present!!!");

        return productService.addProduct(product);
    }


    @RequestMapping(value = "/product-with-category/{product_id}/{category_id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Product assignDetails(@PathVariable("product_id") int product_id, @PathVariable("category_id") int category_id) throws  EntityNotFoundException{

        if(!productService.checkProductExistsById(product_id)) throw new EntityNotFoundException("Product with this ID Doesn't Exists !!");

        Category category = categoryService.getCategoryById(category_id);
        if(category==null) throw new EntityNotFoundException("Category with this Id Doesn't Exist!! Cannot Assign to Product!!");

        //System.out.println(category);

        return productService.assignCategory(product_id, category);
    }



    @RequestMapping(value = "/update-description-by-id/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateProductById(@PathVariable("id") int product_id, @RequestBody Map<String, Object> map) throws EntityNotFoundException, BadRequestException{

        if(!PayloadValidation.createdPayloadDescriptionField(map)) throw new BadRequestException("PAYLOAD MALFORMED. Description MUST be PROVIDED !!!");

        if(!productService.checkProductExistsById(product_id)) throw new EntityNotFoundException("Product with this id NOT FOUND");

        return productService.updateDescriptionById(product_id,map.get("description").toString());

    }


    @RequestMapping(value = "/delete-product-by-id/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteProductById(@PathVariable("id") int product_id) throws EntityNotFoundException {

        if(!productService.checkProductExistsById(product_id)) throw new EntityNotFoundException("Product with this id NOT FOUND");

        return productService.deleteProductById(product_id);
    }




}
