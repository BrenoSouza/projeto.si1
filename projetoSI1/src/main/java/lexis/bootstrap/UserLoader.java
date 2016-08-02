package lexis.bootstrap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import lexis.models.User;
import lexis.repositories.UserRepository;

@Component
public class UserLoader implements ApplicationListener<ContextRefreshedEvent>{
	
	//Repositorio em que o Objeto usuario é manipulado
	private UserRepository userRepository;
	
	//exemplo simples de um futuro log
	private Logger log = Logger.getLogger(UserLoader.class);
	
	
	
	@Autowired
	public void setUserRepository(UserRepository userRepository){
		this.userRepository = userRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		//adicionando usuarios ao sistema
		User admin = new User();
	    admin.setLogin("admin");
	    admin.setPassword("admin");
	    admin.setEmail("admin@admin");
		admin.getRoot().addFolder("pasta1");
		admin.getRoot().addFolder("pasta2");
	    userRepository.save(admin);

	    log.info("Saved admin - id: " + admin.getId());

	    User guest = new User();
	    guest.setLogin("guest");
	    guest.setPassword("guest");
	    guest.setEmail("guest@guest");
	    guest.getRoot().addFolder("pastaguest");
	    userRepository.save(guest);

	    log.info("Saved guest - id:" + guest.getId());
		
	}

}
