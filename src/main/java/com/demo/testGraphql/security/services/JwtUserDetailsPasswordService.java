package com.demo.testGraphql.security.services;

import com.demo.testGraphql.models.entities.User;
import com.demo.testGraphql.repositories.UserRepository;
import com.demo.testGraphql.security.models.JwtUser;
import com.demo.testGraphql.utils.RoleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class JwtUserDetailsPasswordService implements UserDetailsPasswordService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails updatePassword(UserDetails userDetails, String newPassword) {
        var userOp = userRepository.findTopByUsername(userDetails.getUsername());
        if (userOp.isPresent()) {
            User user = userOp.get();
            user.setPassword(newPassword);
            return new JwtUser(user.getId(),
                    user.getUsername(),
                    user.getFirstname(),
                    user.getLastname(),
                    user.getEmail(),
                    user.getPassword(),
                    List.of(new SimpleGrantedAuthority(user.getRole() != null
                            ? user.getRole().getName().name()
                            : RoleName.ROLE_USER.toString())),
                    user.getEnabled(),
                    null,
                    user.getSalt(),
                    user.getPassword()
            );
        }
        throw new UsernameNotFoundException("User not found");
    }
}
