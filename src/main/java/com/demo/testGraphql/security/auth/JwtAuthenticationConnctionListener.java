package com.demo.testGraphql.security.auth;

import com.demo.testGraphql.security.jwt.JwtTokenUtil;
import com.demo.testGraphql.security.services.JwtUserDetailsService;
import graphql.kickstart.execution.subscriptions.SubscriptionSession;
import graphql.kickstart.execution.subscriptions.apollo.ApolloSubscriptionConnectionListener;
import graphql.kickstart.execution.subscriptions.apollo.OperationMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class JwtAuthenticationConnctionListener implements ApolloSubscriptionConnectionListener {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    private final String AUTHENTICATION = "AUTHENTICATION";

    @Override
    public void onConnect(SubscriptionSession session, OperationMessage message) {

        log.info("onConnect: {}", message.getPayload());
        var payload = (Map<String, String>) message.getPayload();

        var jwtAuthToken = payload.get("Authorization");

        if (jwtAuthToken != null) {
            UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(jwtTokenUtil.getUsernameFromToken(jwtAuthToken));
            if (userDetails != null && jwtTokenUtil.isTokenValid(jwtAuthToken, userDetails)) {
                var authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                session.getUserProperties().put(AUTHENTICATION, authentication);
            } else {
                throw new AuthenticationCredentialsNotFoundException("Access deny");
            }
        } else {
            if (SecurityContextHolder.getContext().getAuthentication() != null) {
                session.getUserProperties().put(AUTHENTICATION, SecurityContextHolder.getContext().getAuthentication());
            }
        }
    }

    @Override
    public void onStart(SubscriptionSession session, OperationMessage message) {
        log.info("onStart: {}", message.getPayload());
        var authentication = (Authentication) session.getUserProperties().get(AUTHENTICATION);
        if (authentication != null) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }

    @Override
    public void onStop(SubscriptionSession session, OperationMessage message) {
        log.info("onStop: {}", message.getPayload());
    }

    @Override
    public void onTerminate(SubscriptionSession session, OperationMessage message) {
        log.info("onTerminate: {}", message.getPayload());
    }
}
