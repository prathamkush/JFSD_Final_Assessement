package com.signup.service.signupService.controller;

import com.signup.service.signupService.model.User;
import com.signup.service.signupService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {
    private UserService service;
    @Autowired
    public UserController(UserService service){
        this.service = service;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String signup(@RequestBody User user){
        return service.signupService(user);
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String login(@RequestBody Map<String, Object> map){
        return service.loginService(map.get("username").toString(), map.get("password").toString());
    }

    @RequestMapping(value = "/get-users", method = RequestMethod.GET)
    public List<User> getUsers(){
        return service.getUsers();
    }

}
