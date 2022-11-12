package com.consumer.service.ConsumerService.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private ObjectId id;

    private String username;

    private String password;

    private String password_confirm;

    private String email;
}
