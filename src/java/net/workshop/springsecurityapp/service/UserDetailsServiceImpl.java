package net.workshop.springsecurityapp.service;

import net.workshop.springsecurityapp.dao.UserDao;
import net.workshop.springsecurityapp.entity.Role;
import net.workshop.springsecurityapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of {@link org.springframework.security.core.userdetails.UserDetailsService} interface
 *
 * @author Andrii Androsiuk
 * @version 1.0
 * */

public class UserDetailsServiceImpl  implements UserDetailsService{

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String emailname) throws UsernameNotFoundException {
        User user = userDao.findByEmail(emailname);

        Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();

        for (Role role: user.getRoleSet()) {
            grantedAuthoritySet.add(new SimpleGrantedAuthority(role.getEmail()));
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUseremail(), user.getPassword(),grantedAuthoritySet);
    }
}
