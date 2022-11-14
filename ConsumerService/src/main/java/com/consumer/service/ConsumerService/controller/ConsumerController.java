package com.consumer.service.ConsumerService.controller;


import com.consumer.service.ConsumerService.model.Category;
import com.consumer.service.ConsumerService.model.Product;
import com.consumer.service.ConsumerService.model.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    AdminConsumer adminConsumer;

    @Autowired
    TokenConsumer tokenConsumer;

    @Autowired
    ProductConsumer productConsumer;

    @Autowired
    CategoryConsumer categoryConsumer;




    //-----------------------------------TOKEN-SERVICE CONSUMER--------------------------------------//

    @GetMapping("/get-token/{id}")
    String createToken(@PathVariable("id") ObjectId id){
        return tokenConsumer.createToken(id);
    }





    //-----------------------------------ADMIN-SERVICE CONSUMER--------------------------------------//

    @GetMapping("/get-users")
    List<User> getUsers(){
        System.out.println(adminConsumer.getClass().getSimpleName());
        System.out.println("accessing from admin-service");
        return adminConsumer.getUsers();
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String signup(@RequestBody User user) throws Exception{
        try{
            return adminConsumer.signup(user);
        }
        catch (Exception e){
            String errorResponse = e.getMessage();
            int index = errorResponse.indexOf("{\"errorCode");
            System.out.println(e.getMessage());
            return errorResponse.substring(index, errorResponse.length()-1);
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String login(@RequestBody Map<String, Object> map){

        try {

            String initial_response = adminConsumer.login(map);

            // if username not found || password is incorrect
            if (!initial_response.contains("data")) return initial_response;

            // else insert token with the initial response
            int id_index = initial_response.indexOf("id") + 5;
            String id = initial_response.substring(id_index,
                    initial_response.indexOf(",", id_index));


            String token = createToken(new ObjectId(id));

            //System.out.println(new ObjectId(id));

            StringBuilder response = new StringBuilder(initial_response);
            int token_index = initial_response.indexOf('}', id_index) - 4;
            response.insert(token_index, ",\n" + "       token : " + token);

            return response.toString();
        }
        catch (Exception e){
            String errorResponse = e.getMessage();
            int index = errorResponse.indexOf("{\"errorCode");
            return errorResponse.substring(index, errorResponse.length()-1);
        }

    }


    //-----------------------------------ShopManagement-SERVICE (Category) CONSUMER--------------------------------------//

    @GetMapping("/shop-management/categories")
    public List<Category> getCategories(){
        return categoryConsumer.getCategories();
    }


    @RequestMapping(value = "/shop-management/add-category", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String addCategory(@RequestBody Category category){

        try{
            return categoryConsumer.addCategory(category).toString();
        }
        catch (Exception e){
            String errorResponse = e.getMessage();
            int index = errorResponse.indexOf("{\"errorCode");
            return errorResponse.substring(index, errorResponse.length()-1);
        }

    }

    @RequestMapping(value = "/shop-management/update-category-by-id/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateCategoryById(@PathVariable("id") int category_id, @RequestBody Map<String, Object> map){

        try{
            return categoryConsumer.updateCategoryById(category_id,map);
        }

        catch (Exception e){
            String errorResponse = e.getMessage();
            System.out.println(e+"\n"+"---------------------------------------------------");
            int index = errorResponse.indexOf("{\"errorCode");
            return errorResponse.substring(index, errorResponse.length()-1);
        }

    }

    @RequestMapping(value = "/shop-management/delete-category-by-id/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteCategoryById(@PathVariable("id") int category_id){

        try{
            return categoryConsumer.deleteCategoryById(category_id);
        }

        catch (Exception e){
            String errorResponse = e.getMessage();
            int index = errorResponse.indexOf("{\"errorCode");
            return errorResponse.substring(index, errorResponse.length()-1);
        }
    }

    //-----------------------------------ShopManagement-SERVICE (Product) CONSUMER--------------------------------------//

    @GetMapping("/shop-management/products")
    public List<Product> getProducts(){
        return productConsumer.getProducts();
    }


    @RequestMapping(value = "/shop-management/add-products", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String addProducts(@RequestBody List<Product> products){
        try {
            return productConsumer.addProducts(products).toString();
        }
        catch (Exception e){
            String errorResponse = e.getMessage();
            int index = errorResponse.indexOf("{\"errorCode");
            return errorResponse.substring(index, errorResponse.length()-1);
        }
    }

    @RequestMapping(value = "/shop-management/add-product", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String addProduct(@RequestBody Product product){
        try {
            return productConsumer.addProduct(product).toString();
        }
        catch (Exception e){
            String errorResponse = e.getMessage();
            int index = errorResponse.indexOf("{\"errorCode");
            return errorResponse.substring(index, errorResponse.length()-1);
        }

    }

    @RequestMapping(value = "/shop-management/product-with-category/{product_id}/{category_id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String assignDetails(@PathVariable("product_id") int product_id, @PathVariable("category_id") int category_id){
        try{
            return productConsumer.assignDetails(product_id, category_id).toString();
        }
        catch (Exception e){
            String errorResponse = e.getMessage();
            int index = errorResponse.indexOf("{\"errorCode");
            return errorResponse.substring(index, errorResponse.length()-1);
        }
    }

    @RequestMapping(value = "/shop-management/update-product-by-id/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateProductById(@PathVariable("id") int product_id, @RequestBody Map<String, Object> map){
        try{
            return productConsumer.updateProductById(product_id, map);
        }
        catch (Exception e){
            String errorResponse = e.getMessage();
            int index = errorResponse.indexOf("{\"errorCode");
            return errorResponse.substring(index, errorResponse.length()-1);
        }
    }

    @RequestMapping(value = "/shop-management/delete-product-by-id/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteProductById(@PathVariable("id") int product_id){
        try{
            return productConsumer.deleteProductById(product_id);
        }
        catch (Exception e){
            String errorResponse = e.getMessage();
            int index = errorResponse.indexOf("{\"errorCode");
            return errorResponse.substring(index, errorResponse.length()-1);
        }
    }

}
