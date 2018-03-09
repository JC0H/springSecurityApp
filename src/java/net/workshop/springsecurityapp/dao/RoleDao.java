package net.workshop.springsecurityapp.dao;

import net.workshop.springsecurityapp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role,Long>{

}
