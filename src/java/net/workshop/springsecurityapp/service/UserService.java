package net.workshop.springsecurityapp.service;

import net.workshop.springsecurityapp.entity.User;

/**
 * User service for {@link net.workshop.springsecurityapp.entity.User}
 *
 * @author Andrii Androsiuk
 * @version 1.0
 * */

public interface UserService {

    void save(User user);

    User findByEmail(String useremail);

}
