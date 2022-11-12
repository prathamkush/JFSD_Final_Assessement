package com.consumer.service.ConsumerService.controller;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("token-service/auth")
public interface TokenConsumer {

    @GetMapping("/get-token/{id}")
    String createToken(@PathVariable("id") int id);

}