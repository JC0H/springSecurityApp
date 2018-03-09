package net.workshop.springsecurityapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * Implementation of interface {@link SecurityService}
 *
 * @author Andrii Androsiuk
 * @version 1.0
 * */

@Service
public class SecurityServiceImp implements SecurityService{

    private static final Logger logger = LoggerFactory.getLogger(SecurityService.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public String findLoggedInEmail() {
        Object userDetail = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetail instanceof UserDetails){
            return ((UserDetails)userDetail).getUsername();
        }
        return null;
    }

    @Override
    public void autoLogin(String emailname, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(emailname);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails,password, userDetails.getAuthorities());

        authenticationManager.authenticate(authenticationToken);
        if (authenticationToken.isAuthenticated()){
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            logger.debug(String.format("Successfully %s auto logged in ", emailname));
        }
    }
}
