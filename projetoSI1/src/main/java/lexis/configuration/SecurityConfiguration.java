package lexis.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	private final int DAY = 60*60*24;
	
	@Autowired
	private UserDetailsService users;
	
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	SpringRememberMeService springRememberMeService = new SpringRememberMeService("key", users);
    	springRememberMeService.setTokenValiditySeconds(DAY);
    	
        http
            .authorizeRequests()
            	.antMatchers(HttpMethod.POST,"/userRegister").permitAll()
            	.antMatchers("/console/**").permitAll()
            	.antMatchers(HttpMethod.GET,"/index").permitAll()
            	.anyRequest().authenticated()
            		.and()
            	.formLogin()
            		.loginPage("/login").permitAll()
            		.and()
                .rememberMe().tokenValiditySeconds(DAY)
                .rememberMeServices(springRememberMeService)//servletContext.getSessionCookieConfig().setMaxAge(600);
                    .and()
            	.logout()
                	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
                

        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
    	web.ignoring()
    		.antMatchers("/css/**","/images/**","/js/**");
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(users)
            .passwordEncoder(new BCryptPasswordEncoder());
    }

}
