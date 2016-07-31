package lexis.services;

import lexis.models.User;


public interface UserService {
	Iterable<User> listAllUsers();

    User getUserById(Integer id);    

    User saveUser(User user);

    User getUserByLogin(String login);
    
    User getUserByEmail(String email);
    
    void deleteUser(Integer id);
    
    
    
    
	
}
