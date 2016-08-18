package lexis.initialization;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import lexis.models.User;
import lexis.repositories.UserRepository;

/**
 * Classe responsavel por inciar o sistema com usuarios padrão.
 * 
 * @author Grupo 7, Alexandre Gullo, Jose Breno, Matheus Benedito, Melisse
 *         Cabral, Raimundo Heitor, Rafael Klyger.
 *
 */
@Component
public class UserLoader implements ApplicationListener<ContextRefreshedEvent> {

	// Repositorio em que o Objeto usuario é manipulado.
	private UserRepository userRepository;

	// Exemplo simples de um futuro log.
	private Logger log = Logger.getLogger(UserLoader.class);

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		// Adicionando usuarios ao sistema.
		User admin;
		try {
			admin = new User("admin", "admin", "admin@admin");
			
			admin.getRoot().addFolder("pasta1");
			admin.getRoot().addFolder("pasta2");
			userRepository.save(admin);
			
			log.info("Saved admin - id: " + admin.getId());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}




		User guest;
		try {
			guest = new User("guest", "guest", "guest@guest");
			
			guest.getRoot().addFolder("pastaguest");
			userRepository.save(guest);

			log.info("Saved guest - id:" + guest.getId());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
