package com.handler;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@Component
public class PrincipalAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        String errorMessage;
        if (exception instanceof BadCredentialsException) {
            errorMessage = "BadCredentialsException";
        } else if (exception instanceof InternalAuthenticationServiceException) {
            errorMessage = "InternalAuthenticationServiceException";
        } else if (exception instanceof UsernameNotFoundException) {
            errorMessage = "UsernameNotFoundException";
        } else if (exception instanceof AuthenticationCredentialsNotFoundException) {
            errorMessage = "AuthenticationCredentialsNotFoundException";
        } else {
            errorMessage = "dont know";
        }
        setDefaultFailureUrl("/auth/loginForm?error=true&exception="+errorMessage);

        super.onAuthenticationFailure(request,response,exception);
    }
}
