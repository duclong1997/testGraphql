package com.demo.testGraphql.resolvers.user;

import com.demo.testGraphql.models.dtos.UserDto;
import com.demo.testGraphql.services.UserService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private UserService userService;

    @PreAuthorize("isAuthenticated()")
    public UserDto getMe() {
        return userService.getMe();
    }
}
