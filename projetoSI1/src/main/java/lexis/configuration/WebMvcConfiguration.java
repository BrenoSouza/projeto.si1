package lexis.configuration;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfiguration extends  WebMvcConfigurerAdapter{
	
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer(){
		return new  EmbeddedServletContainerCustomizer(){

			@Override
			public void customize(ConfigurableEmbeddedServletContainer arg0) {
				arg0.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND,"/404"));	
				arg0.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN,"/403"));
			}
		};
	}
}
