package pl.com.bottega.microecom.user.model;

import org.hibernate.annotations.Cascade;
import pl.com.bottega.microecom.commons.model.BaseAggregateRoot;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User extends BaseAggregateRoot {

    private String login;

    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Authentication authentication;

    User() {
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public Authentication createAuthentication() {
        authentication = new Authentication(this);
        return authentication;
    }

    public Set<Role> roles() {
        return Collections.unmodifiableSet(roles);
    }

    public String login() {
        return login;
    }

    public void updateRoles(Set<Role> roles) {
        this.roles.clear();
        this.roles.addAll(roles);
    }

    public String password() {
        return password;
    }
}
