package pl.com.bottega.microecom.user.controllers.user;

import pl.com.bottega.microecom.user.model.Role;

import javax.validation.constraints.Size;
import java.util.Set;

public class UpdateUserRolesRequest {

    @Size(min = 1)
    private Set<Role> roles;

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

}
