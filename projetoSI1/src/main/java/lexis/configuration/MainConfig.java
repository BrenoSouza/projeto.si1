package lexis.configuration;


import java.net.URI;
import java.net.URISyntaxException;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MainConfig {

	   @Bean
	    @Primary
	    @ConfigurationProperties(prefix = "spring.datasource")
	    public DataSource dataSource() {
	        return DataSourceBuilder.create().build();
	    }
}