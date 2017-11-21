package pl.com.bottega.microecom.user.infrastructure;

import org.springframework.http.HttpStatus;
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
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String token = httpServletRequest.getHeader(REQUEST_TOKEN_HEADER);
        if(token == null)
            filterChain.doFilter(servletRequest, servletResponse);
        else {
            Authentication authentication = authenticationRepository.findByToken(token);
            if(authentication == null) {
                httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value());
                SecurityContextHolder.clearContext();
            }
            else {
                SecurityContextHolder.getContext().setAuthentication(new TokenAuthentication(authentication));
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }

}
