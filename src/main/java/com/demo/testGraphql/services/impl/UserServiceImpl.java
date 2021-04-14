package com.demo.testGraphql.services.impl;

import com.demo.testGraphql.helpers.PasswordHelper;
import com.demo.testGraphql.mappers.UserMapper;
import com.demo.testGraphql.models.dtos.RegisterUser;
import com.demo.testGraphql.models.dtos.UserDto;
import com.demo.testGraphql.models.entities.User;
import com.demo.testGraphql.repositories.UserRepository;
import com.demo.testGraphql.security.services.JwtUserDetailsService;
import com.demo.testGraphql.security.jwt.JwtTokenUtil;
import com.demo.testGraphql.services.UserService;
import com.demo.testGraphql.utils.UserStatus;
import graphql.GraphQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

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

    @Autowired
    private PasswordHelper passwordHelper;

    public UserServiceImpl(AuthenticationManager authenticationManager, PasswordHelper passwordHelper) {
        this.authenticationManager = authenticationManager;
        this.passwordHelper = passwordHelper;
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

    @Override
    public UserDto register(RegisterUser userRegister) {
        if (userRegister.getUsername() == null) {
            throw new GraphQLException("username is not null");
        }
        Optional<User> userOp = userRepository.findTopByUsername(userRegister.getUsername().toLowerCase());
        if (userOp.isPresent()) {
            throw new GraphQLException("user is existed");
        }

        User user = new User();
        user.setEnabled(UserStatus.ACTIVE);
        user.setEmail(userRegister.getEmail());
        user.setUsername(userRegister.getUsername().toLowerCase());
        user.setFirstname(userRegister.getFirstname());
        user.setLastname(userRegister.getLastname());
        // salt and password
        final var saltPasswordPair = passwordHelper.createPassword(userRegister.getPassword());
        user.setSalt(saltPasswordPair.getLeft());
        user.setPassword(saltPasswordPair.getRight());
        user = userRepository.save(user);

        return userMapper.entityToDto(user);
    }
}
