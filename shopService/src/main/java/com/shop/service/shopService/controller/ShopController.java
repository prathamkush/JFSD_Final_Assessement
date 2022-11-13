package com.shop.service.shopService.controller;


import com.shop.service.shopService.model.Category;
import com.shop.service.shopService.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("shop")
public class ShopController {

    @Autowired
    CategoryConsumer categoryConsumer;

    @Autowired
    ProductConsumer productConsumer;


    @GetMapping("/check")
    public String check(){
        return categoryConsumer.check();
    }


    @GetMapping("/products")
    public List<Product> getProducts(){
        return productConsumer.getProducts();
    }

    @GetMapping("/categories")
    public List<Category> getCategories(){
        return categoryConsumer.getCategories();
    }


    @RequestMapping(value = "/products", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getProductByField(@RequestBody Map<String, Object> map){
        return productConsumer.getProductByField(map);
    }

    @RequestMapping(value = "/categories", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    List<Category> getCategoriesByField(@RequestBody Map<String, Object> map){
        return categoryConsumer.getCategoriesByField(map);
    }

}
