package com.demo.testGraphql.resolvers.role;

import com.demo.testGraphql.models.dtos.RoleDto;
import com.demo.testGraphql.services.RoleService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleMutationResolver implements GraphQLMutationResolver {

    @Autowired
    private RoleService roleService;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public RoleDto createRole(String name) {
        return roleService.createRole(name);
    }

    public RoleDto updateRole(Long id, String name) {
        return roleService.updateRole(id, name);
    }
}
