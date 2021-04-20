package com.demo.testGraphql.services;

import com.demo.testGraphql.models.dtos.RoleDto;

import java.util.List;

public interface RoleService {
    RoleDto createRole(String name);

    RoleDto updateRole(Long id, String name);

    List<RoleDto> getRoles(Integer page, Integer size);

    RoleDto getDetailRole(Long id);
}
