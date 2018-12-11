package com.example.mobile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityServiceInterface {

    @Autowired
   private  AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    public String findLogIn() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();

        if(userDetails instanceof UserDetails){
            return ((UserDetails) userDetails).getUsername();
        }
        return  null;
    }

    @Override
    public void authoLogin(String login, String password) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(login);
        UsernamePasswordAuthenticationToken authenticationToken  =
                new UsernamePasswordAuthenticationToken(userDetails, password);
        authenticationManager.authenticate(authenticationToken);

        if(authenticationToken.isAuthenticated()){
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
    }
}
