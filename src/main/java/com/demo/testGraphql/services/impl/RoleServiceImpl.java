package com.demo.testGraphql.services.impl;

import com.demo.testGraphql.mappers.RoleMapper;
import com.demo.testGraphql.models.dtos.RoleDto;
import com.demo.testGraphql.models.entities.Role;
import com.demo.testGraphql.repositories.RoleRepository;
import com.demo.testGraphql.services.RoleService;
import graphql.GraphQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public RoleDto createRole(String name) {
        Role role = new Role();
        role.setName(name);
        role = roleRepository.save(role);
        return roleMapper.entityToDto(role);
    }

    @Override
    public RoleDto updateRole(Long id, String name) {
        Optional<Role> roleOp = roleRepository.findById(id);
        if (roleOp.isPresent()) {
            Role role = roleOp.get();
            role.setName(name);
            role = roleRepository.save(role);
            return roleMapper.entityToDto(role);
        }
        throw new GraphQLException("Role not existed");
    }

    @Override
    public List<RoleDto> getRoles(Integer page, Integer size) {
        List<Role> roles = roleRepository.findAll();
        return roleMapper.entitiesToDtos(roles);
    }

    @Override
    public RoleDto getDetailRole(Long id) {
        Optional<Role> roleOp = roleRepository.findById(id);
        if(roleOp.isPresent()){
            Role role = roleOp.get();
            RoleDto roleDto =roleMapper.entityToDto(role);
            return roleDto;
        }
        throw new GraphQLException("Role not existed");
    }
}
