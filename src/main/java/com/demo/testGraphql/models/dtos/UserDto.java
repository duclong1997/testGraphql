package com.demo.testGraphql.models.dtos;

import com.demo.testGraphql.models.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {
    private static final long serialVersionUID = 1466956427135915190L;

    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private String token;
    private Boolean enabled;
    private Role role;

}
