package net.workshop.springsecurityapp.dao;

import net.workshop.springsecurityapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDao extends JpaRepository<User, Long> {
    User findByEmail(String useremail);
}
