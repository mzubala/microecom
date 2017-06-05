package pl.com.bottega.microecom.user.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.com.bottega.microecom.user.model.AuthenticationRepository;

import javax.servlet.Filter;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationRepository authenticationRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                csrf().disable().
                addFilterBefore(apiAuthFilter(), UsernamePasswordAuthenticationFilter.class).
                authorizeRequests().
                    antMatchers("/users/**/roles").hasRole("ADMIN").
                    antMatchers("/users/authentications", "/users/registrations").permitAll().
                and().authorizeRequests().
                    antMatchers("/users", "/users/**").authenticated()
        ;
    }

    private Filter apiAuthFilter() {
        return new ApiAuthFilter(authenticationRepository);
    }

}
