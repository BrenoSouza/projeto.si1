package lexis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Classe que ira inicializar a aplicação
 * 
 * @author Grupo 7, Alexandre Gullo, Jose Breno, Matheus Benedito, Melisse
 *         Cabral, Raimundo Heitor, Rafael Klynger.
 *
 */
@ComponentScan(basePackages = {"lexis"})
@EnableAutoConfiguration
@SpringBootApplication
public class Application {

	// Essa classe é a responsavel pela iniciação do programa
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
