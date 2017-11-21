package pl.com.bottega.microecom.user.infrastructure;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.stream.Collectors;

public class TokenAuthentication implements Authentication {

    private pl.com.bottega.microecom.user.model.Authentication authentication;

    public TokenAuthentication(pl.com.bottega.microecom.user.model.Authentication authentication) {
        this.authentication = authentication;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authentication.getUser().roles().stream().map((r) ->
                (GrantedAuthority) () -> "ROLE_" + r.name()).
                collect(Collectors.toList());
    }

    @Override
    public Object getCredentials() {
        return authentication.getToken();
    }

    @Override
    public Object getDetails() {
        return authentication.getUser().login();
    }

    @Override
    public Object getPrincipal() {
        return authentication;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return authentication.getUser().login();
    }
}
