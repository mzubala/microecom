package pl.com.bottega.microecom.user.infrastructure;

import org.springframework.web.filter.GenericFilterBean;
import pl.com.bottega.microecom.user.model.AuthenticationRepository;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class ApiAuthFilter extends GenericFilterBean {

    private static final String REQUEST_TOKEN_HEADER = "X-Auth-Token";

    private AuthenticationRepository authenticationRepository;

    public ApiAuthFilter(AuthenticationRepository authenticationRepository) {
        this.authenticationRepository = authenticationRepository;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // TODO Zadanie 2
    }

}
