package lexis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe que ira inicializar a aplicação
 * @author raimundoheitor
 *
 */
@EnableAutoConfiguration
@SpringBootApplication
public class Application {

	// Essa classe é a responsavel pela iniciação do programa
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
