package lexis.models;


import java.util.Collection;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 * Classe responsavel pelo objeto usuario.
 * 
 * @author Grupo 7, Alexandre Gullo, Jose Breno, Matheus Benedito, Melisse
 *         Cabral, Raimundo Heitor, Rafael Klynger.
 *
 */
@Entity
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Version
	private Integer version;
	@Column(name = "user_email")
	private String email;
	@Column(name = "user_name")
	private String username;
	@Column(name = "user_password")
	private String password;
	
	
	public static final int MAX_LENGTH_LOGIN = 30;
	public static final int MIN_LENGTH_LOGIN = 6;
	public static final int MAX_LENGTH_PASSWORD = 30;
	public static final int MIN_LENGTH_PASSWORD = 8;
	
		
	public User() {
		username = null;
	}
	
	public User(String login, String password, String email) throws Exception {
		checkLogin(login);
		checkPassword(password);
		checkEmail(email);
		BCryptPasswordEncoder crypt = new BCryptPasswordEncoder();
		
		this.username = login.toLowerCase();
		this.password = crypt.encode(password);
		this.email = email.toLowerCase();
		
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * @return - Retorna o id do usuario.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Set o id do usuario.
	 * 
	 * @param id
	 *            Integer - novo id.
	 */
	public void setId(Integer id) throws Exception {
		if(id == null)
			throw new NullPointerException();
		this.id = id;
	}

	
	public void setUsername(String login) throws Exception {
		if(this.username == null) {
			checkLogin(login);
			this.username = login.toLowerCase();
			
		}
		
	}
	
	/**
	 * Set uma nova senha para o usuario.
	 * 
	 * @param password
	 *            String - nova senha.
	 */
	public void setPassword(String password) throws Exception {
		checkPassword(password);
		BCryptPasswordEncoder crypt = new BCryptPasswordEncoder();
		this.password = crypt.encode(password);
	}
	
	/**
	 * @return Retorna o email do usuario.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Set um novo email de usuario.
	 * 
	 * @param email
	 *            String - novo email.
	 */
	public void setEmail(String email) throws Exception {
		
		checkEmail(email);
		this.email = email.toLowerCase();
	}
	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
			
		User otherUser = (User) obj;
			
		return this.getUsername().equals(otherUser.getUsername()) && 
				getEmail().equals(otherUser.getEmail()) && 
				getPassword().equals(otherUser.getPassword());
	}
	
	@Override
	public String toString() {
		return "Id: " + getId() + "\nLogin: " + getUsername() + "\nEmail: " + getEmail() + "\nPassword: " + getPassword() + "\n";
	}
	

	private void checkPassword(String password) throws Exception {
		if(password == null)
			throw new NullPointerException();
		if(password.length() < MIN_LENGTH_PASSWORD || password.length() > MAX_LENGTH_PASSWORD) {
			//throw new SmallPasswordException();
		}
			
	}
	

	private void checkEmail(String email) throws Exception {
		if(email == null)
			throw new NullPointerException();
		if(!email.contains("@")) {
			//throw new InvalidEmailException();
		}
	}
	
	private void checkLogin(String login) throws Exception {
		if(login == null)
			throw new NullPointerException();
		
		if(login.length() < MIN_LENGTH_LOGIN || login.length() > MAX_LENGTH_LOGIN) {
			//throw new InvalidLoginException();
		}
			
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return null;
	}
	/**
	 * @return Retorna a senha do usuario.
	 */
	@Override
	public String getPassword() {
		return password;
	}

	/**
	 * @return Retorna o login do usuario.
	 */
	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
