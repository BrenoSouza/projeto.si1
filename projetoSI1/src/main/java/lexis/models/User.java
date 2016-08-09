package lexis.models;

import static org.hamcrest.CoreMatchers.instanceOf;

import javax.persistence.*;

import javassist.expr.Instanceof;

/**
 * Classe responsavel pelo objeto usuario.
 * 
 * @author Grupo 7, Alexandre Gullo, Jose Breno, Matheus Benedito, Melisse
 *         Cabral, Raimundo Heitor, Rafael Klyger.
 *
 */
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Version
	private Integer version;
	private String login;
	private String password;
	private String email;
	@Embedded
	private static Folder ROOT = new Folder("root");

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
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return Retorna o login do usuario.
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Set um novo login para o usuario.
	 * 
	 * @param login
	 *            String - novo login.
	 */
	public void setLogin(String login) {
		ROOT.addFolder(login);
		this.login = login;
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
	public void setPassword(String password) {
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
	public void setEmail(String email) {
		this.email = email;
	}

	public boolean equals(Object objeto) {
		if (objeto instanceof User) {
			User user = (User) objeto;
			if (this.getEmail().equals(user.getEmail()) && (this.getId() == user.getId())) {
				return true; // sao iguails.
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "id: " + getId() + " login: " + getLogin() + " email: " + getEmail() + " password: " + getPassword();
	}

}
