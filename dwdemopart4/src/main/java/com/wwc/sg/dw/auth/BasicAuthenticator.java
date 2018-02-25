package com.wwc.sg.dw.auth;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

import java.util.Optional;

public class BasicAuthenticator
        implements Authenticator<BasicCredentials, User> {
    @Override
    public Optional<User> authenticate(BasicCredentials credentials)
            throws AuthenticationException {
        if ("pwd".equals(credentials.getPassword())) {
            return Optional.of(new User(credentials.getUsername()));
        } else {
            return Optional.empty();
        }
    }
}


