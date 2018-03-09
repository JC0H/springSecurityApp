package net.workshop.springsecurityapp.service;

/**
 * Service for security
 *
 * @author Andrii Androsiuk
 * @version 1.0
 * */

public interface SecurityService {

    String findLoggedInEmail();

    void autoLogin(String emailname, String password);

}
