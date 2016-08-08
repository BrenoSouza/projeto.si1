package lexis.services;

import java.util.Iterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lexis.models.User;
import lexis.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	private UserRepository userRepository;

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public Iterable<User> listAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUserById(Integer id) {
		return userRepository.findOne(id);
	}

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(Integer id) {
		userRepository.delete(id);
	}

	@Override
	public User getUserByLogin(String login) {
		return userRepository.findByLogin(login);
	}

	@Override
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public Boolean existsByLogin(String login) {
		Iterator<User> interator = userRepository.findAll().iterator();
		while (interator.hasNext()) {
			User user = interator.next();
			if (user.getLogin().equals(login)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Boolean existsByEmail(String email) {
		Iterator<User> interator = userRepository.findAll().iterator();
		while (interator.hasNext()) {
			User user = interator.next();
			if (user.getEmail().equals(email)) {
				return true;
			}
		}
		return false;
	}

}
