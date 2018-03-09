package net.workshop.springsecurityapp.service;

import net.workshop.springsecurityapp.dao.RoleDao;
import net.workshop.springsecurityapp.dao.UserDao;
import net.workshop.springsecurityapp.entity.Role;
import net.workshop.springsecurityapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of {@link UserService} interface
 *
 * @author Andrii Androsiuk
 * @version 1.0
 * */

@Service
public class UserServiceImp  implements UserService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles =  new HashSet<>();
        roles.add(roleDao.getOne(1L));
        user.setRoleSet(roles);
        userDao.save(user);
    }

    @Override
    public User findByEmail(String useremail) {
        return userDao.findByEmail(useremail);
    }
}
