package com.demo.testGraphql.resolvers.user;

import com.demo.testGraphql.models.dtos.UserDto;
import com.demo.testGraphql.services.UserService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMutationResolver implements GraphQLMutationResolver {

    @Autowired
    private UserService userService;

    // not Authorization
    @PreAuthorize("isAnonymous()")
    public UserDto login(String username, String password) {
        return userService.authen(username, password);
    }
}
