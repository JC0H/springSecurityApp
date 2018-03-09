package net.workshop.springsecurityapp.entity;

import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import java.util.Set;
/**
 * Simple JavaBean object that represents role of {@link User}
 *
 * @author Andrii Androsiuk
 * @version 1.0
 */

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "email")
    private String useremail;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return useremail;
    }

    public void setEmail(String email) {
        this.useremail= email;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", email='" + useremail + '\'' +
                ", users=" + users +
                '}';
    }
}
