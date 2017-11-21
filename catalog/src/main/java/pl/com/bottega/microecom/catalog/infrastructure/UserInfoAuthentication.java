package pl.com.bottega.microecom.catalog.infrastructure;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import pl.com.bottega.microecom.userclient.UserInfo;

import javax.security.auth.Subject;
import java.util.Collection;
import java.util.stream.Collectors;

public class UserInfoAuthentication implements Authentication {

    private UserInfo userInfo;

    public UserInfoAuthentication(UserInfo info) {
        userInfo = info;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userInfo.getRoles().stream().
                map((r)-> (GrantedAuthority) () -> "ROLE_" + r).
                collect(Collectors.toSet());
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return userInfo.getLogin();
    }

    @Override
    public Object getPrincipal() {
        return userInfo.getLogin();
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return userInfo.getLogin();
    }

    @Override
    public boolean implies(Subject subject) {
        return false;
    }
}
