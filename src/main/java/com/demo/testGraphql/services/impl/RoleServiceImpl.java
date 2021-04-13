package com.demo.testGraphql.services.impl;

import com.demo.testGraphql.repositories.RoleRepository;
import com.demo.testGraphql.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
}
