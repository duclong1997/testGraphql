package com.demo.testGraphql.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUser implements Serializable {

    @NotBlank
    private String username;
    private String password;
    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
    private String email;
    private Long roleId;
}
