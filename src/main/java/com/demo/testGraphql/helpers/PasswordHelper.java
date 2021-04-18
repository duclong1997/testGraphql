package com.demo.testGraphql.helpers;

import com.demo.testGraphql.utils.HashUtil;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordHelper {

    @Autowired
    private final BCryptPasswordEncoder passwordEncoder;

    public PasswordHelper(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public Pair<String, String> createPassword(String password) {
        final var salt = HashUtil.generateSalt();
        final var saltPassword = createPassword(password, salt);
        return Pair.of(salt, saltPassword);
    }

    public boolean matchPassword(String presentedPassword, String salt, String matchPassword) {
        final var saltPassword = salt + presentedPassword;
        return this.passwordEncoder.matches(saltPassword, matchPassword);
    }

    private String createPassword(String password, String salt) {
        return passwordEncoder.encode(salt + password);
    }
}