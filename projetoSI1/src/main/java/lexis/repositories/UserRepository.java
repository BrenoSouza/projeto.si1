package lexis.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import lexis.models.User;


public interface UserRepository extends CrudRepository<User, Integer> {
	
	User findByEmail(String email);
	User findByLogin(String login);

}
