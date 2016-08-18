package lexis.models;

import static org.hamcrest.CoreMatchers.instanceOf;

import javax.persistence.*;

import javassist.expr.Instanceof;

/**
 * Classe responsavel pelo objeto usuario.
 * 
 * @author Grupo 7, Alexandre Gullo, Jose Breno, Matheus Benedito, Melisse
 *         Cabral, Raimundo Heitor, Rafael Klynger.
 *
 */
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	
	public static final int MAX_LENGTH_LOGIN = 30;
	public static final int MIN_LENGTH_LOGIN = 6;
	
	public static final int MAX_LENGTH_PASSWORD = 30;
	public static final int MIN_LENGTH_PASSWORD = 8;
	
	@Version
	private Integer version;
	private String login;
	private String password;
	private String email;
	@Embedded
	private static Folder ROOT = new Folder("root");
	
	public User() {
		login = null;
	}
	
	public User(String login, String password, String email) throws Exception {
		checkLogin(login);
		checkPassword(password);
		checkEmail(email);
		
		this.login = login.toLowerCase();
		this.password = password;
		this.email = email.toLowerCase();
		
		ROOT.addFolder(login.toLowerCase());
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * @return - Retorna a pasta do usuario.
	 */
	public Folder getRoot() {
		return ROOT.getFolder(getLogin());
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

	/**
	 * @return Retorna o login do usuario.
	 */
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) throws Exception {
		if(this.login == null) {
			checkLogin(login);
			this.login = login.toLowerCase();
			
			ROOT.addFolder(login.toLowerCase());
		}
		
	}

	/**
	 * @return Retorna a senha do usuario.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Set uma nova senha para o usuario.
	 * 
	 * @param password
	 *            String - nova senha.
	 */
	public void setPassword(String password) throws Exception {
		
		checkPassword(password);
		this.password = password;
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
		result = prime * result + ((login == null) ? 0 : login.hashCode());
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
			
		return this.getLogin().equals(otherUser.getLogin());
	}
	
	@Override
	public String toString() {
		return "Id: " + getId() + "\nLogin: " + getLogin() + "\nEmail: " + getEmail() + "\nPassword: " + getPassword() + "\n";
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
	
}
