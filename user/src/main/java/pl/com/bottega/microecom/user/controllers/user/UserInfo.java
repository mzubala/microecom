package pl.com.bottega.microecom.user.controllers.user;

import pl.com.bottega.microecom.user.model.Role;
import pl.com.bottega.microecom.user.model.User;

import java.util.Set;

public class UserInfo {

    private String login;
    private Set<Role> roles;

    public UserInfo(User user) {
        this.login = user.login();
        this.roles = user.roles();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
