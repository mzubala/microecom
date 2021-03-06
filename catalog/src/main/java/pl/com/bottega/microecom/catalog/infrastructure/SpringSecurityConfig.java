package pl.com.bottega.microecom.catalog.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.com.bottega.microecom.userclient.UserClient;

import javax.servlet.Filter;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserClient userClient;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                csrf().disable().
                addFilterBefore(apiAuthFilter(), UsernamePasswordAuthenticationFilter.class).
                authorizeRequests().
                    antMatchers(HttpMethod.POST, "/products").hasRole("MANAGER").
                    antMatchers(HttpMethod.PUT, "/products/**").hasRole("MANAGER").
                    anyRequest().permitAll();
    }

    private Filter apiAuthFilter() {
        return new ApiAuthFilter(userClient);
    }

}
