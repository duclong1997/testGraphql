package com.demo.testGraphql.services.impl;

import com.demo.testGraphql.mappers.UserMapper;
import com.demo.testGraphql.models.dtos.UserDto;
import com.demo.testGraphql.models.entities.User;
import com.demo.testGraphql.repositories.UserRepository;
import com.demo.testGraphql.security.JwtUserDetailsService;
import com.demo.testGraphql.security.jwt.JwtTokenUtil;
import com.demo.testGraphql.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserMapper userMapper;

    public UserServiceImpl(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserDto authen(String username, String password) {
        // set authen
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = jwtTokenUtil.generateToken(authentication);
        User user = jwtTokenUtil.getUserCurrent();
        UserDto userDto = null;
        if (user != null) {
            userDto = userMapper.entityToDto(user);
            userDto.setToken(jwtToken);
        }
        return userDto;
    }

    @Override
    public UserDto getMe() {
        User user = jwtTokenUtil.getUserCurrent();
        return userMapper.entityToDto(user);
    }
}
