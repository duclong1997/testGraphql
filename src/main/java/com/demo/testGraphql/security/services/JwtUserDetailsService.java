package com.demo.testGraphql.security.services;

import com.demo.testGraphql.models.entities.User;
import com.demo.testGraphql.repositories.UserRepository;
import com.demo.testGraphql.security.models.JwtUser;
import com.demo.testGraphql.utils.RoleName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        logger.info("load user...");
        Optional<User> user = userRepository.findTopByUsername(username);
        if (user.isPresent()) {
            logger.info("user:: {}", user.get().getUsername());
            return getJwtUser(user.get());
        } else {
            logger.info("user not found");
            //throw new UsernameNotFoundException(String.format("User not found with username '%s'.", username));
            return null;
        }
    }

    public JwtUser getJwtUser(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(user.getRole() != null
                        ? user.getRole().getName().name()
                        : RoleName.ROLE_USER.name())),
                user.getEnabled(),
                null,
                user.getSalt(),
                user.getPassword()
        );
    }
}
