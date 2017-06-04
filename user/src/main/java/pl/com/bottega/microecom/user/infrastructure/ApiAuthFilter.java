package pl.com.bottega.microecom.user.infrastructure;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import pl.com.bottega.microecom.user.model.Authentication;
import pl.com.bottega.microecom.user.model.AuthenticationRepository;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ApiAuthFilter extends GenericFilterBean {

    private static final String REQUEST_TOKEN_HEADER = "X-Auth-Token";

    private AuthenticationRepository authenticationRepository;

    public ApiAuthFilter(AuthenticationRepository authenticationRepository) {
        this.authenticationRepository = authenticationRepository;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String token = request.getHeader(REQUEST_TOKEN_HEADER);
        if (token != null) {
            Authentication authentication = authenticationRepository.findByToken(token);
            if (authentication == null) {
                unsuccessfulAuthentication(request, response, new BadCredentialsException("invalid auth token"));
            } else {
                SecurityContextHolder.getContext().setAuthentication(new TokenAuthentication(authentication));
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } else
            filterChain.doFilter(servletRequest, servletResponse);
    }

    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        response.sendError(401);
    }

}
