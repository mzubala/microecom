package pl.com.bottega.microecom.catalog.infrastructure;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import pl.com.bottega.microecom.userclient.UserClient;
import pl.com.bottega.microecom.userclient.UserInfo;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ApiAuthFilter extends GenericFilterBean {

    private static final String REQUEST_TOKEN_HEADER = "X-Auth-Token";

    private UserClient userClient;

    public ApiAuthFilter(UserClient userClient) {
        this.userClient = userClient;
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // TODO Zadanie 1
    }

}
