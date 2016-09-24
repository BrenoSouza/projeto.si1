package lexis.configuration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

public class SpringRememberMeService extends TokenBasedRememberMeServices{
	private final int DAY = 60*60*24;

	public SpringRememberMeService(String key, UserDetailsService userDetailsService) {
		super(key, userDetailsService);
	}

	@Override
    protected int calculateLoginLifetime(HttpServletRequest request, Authentication authentication) {
        return DAY;
    }
	
}
