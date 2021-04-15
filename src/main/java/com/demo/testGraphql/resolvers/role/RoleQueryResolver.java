package com.demo.testGraphql.resolvers.role;

import com.demo.testGraphql.models.dtos.RoleDto;
import com.demo.testGraphql.services.RoleService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RoleQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private RoleService roleService;

    public List<RoleDto> getRoles(Integer page, Integer size){
        return roleService.getRoles(page, size);
    }

    public RoleDto getDetailRole(Long id){
        return roleService.getDetailRole(id);
    }
}
