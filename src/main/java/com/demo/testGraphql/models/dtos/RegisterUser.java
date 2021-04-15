package com.demo.testGraphql.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUser implements Serializable {
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private Long roleId;
}
