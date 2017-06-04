package pl.com.bottega.microecom.user.infrastructure;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.security.auth.Subject;
import java.util.Collection;
import java.util.stream.Collectors;

public class TokenAuthentication implements Authentication {

    private pl.com.bottega.microecom.user.model.Authentication auth;

    public TokenAuthentication(pl.com.bottega.microecom.user.model.Authentication authentication) {
        auth = authentication;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return auth.getUser().roles().stream().
                map((r)-> (GrantedAuthority) () -> "ROLE_" + r.name()).
                collect(Collectors.toSet());
    }

    @Override
    public Object getCredentials() {
        return auth.getToken();
    }

    @Override
    public Object getDetails() {
        return auth.getUser().login();
    }

    @Override
    public Object getPrincipal() {
        return auth.getUser();
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
        return auth.getUser().login();
    }

    @Override
    public boolean implies(Subject subject) {
        return false;
    }
}
