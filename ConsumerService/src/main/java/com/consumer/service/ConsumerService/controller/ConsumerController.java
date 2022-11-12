package com.consumer.service.ConsumerService.controller;


import com.consumer.service.ConsumerService.model.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/get-users")
    List<User> getUsers(){
        System.out.println(adminConsumer.getClass().getSimpleName());
        System.out.println("accessing from admin-service");
        return adminConsumer.getUsers();
    }

    @PostMapping("/signup")
    public String signup(@RequestBody User user) throws Exception{
        return adminConsumer.signup(user);
    }

    @PostMapping("/login")
    String login(@RequestBody Map<String, Object> map){

        String initial_response =  adminConsumer.login(map);

        System.out.println(initial_response);

        // if email not found || password is incorrect
        if(!initial_response.contains("data")) return initial_response;

        // else insert token with the initial response
        int id_index = initial_response.indexOf("id")+5;
        String id = initial_response.substring(  id_index,
                                                    initial_response.indexOf(",", id_index));


        String token = createToken(new ObjectId(id));

        //System.out.println(new ObjectId(id));

        StringBuilder response = new StringBuilder(initial_response);
        int token_index = initial_response.indexOf('}', id_index)-4;
        response.insert(token_index, ",\n"+"       token : "+token);

        return response.toString();

    }
    @GetMapping("/get-token/{id}")
    String createToken(@PathVariable("id") ObjectId id){
        return tokenConsumer.createToken(id);
    }

}
