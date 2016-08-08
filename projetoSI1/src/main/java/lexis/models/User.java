package lexis.models;

import javax.persistence.*;

/**
 * classe responsavel pelo objeto usuario
 * @author raimundoheitor
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
	 * @return -retorna a pasta do usuario
	 */
	public Folder getRoot() {
		return ROOT.getFolder(getLogin());
	}

	/*public void setRoot(Folder root) {
		this.ROOT = root;
	}*/

	/**
	 * @return - retorna o id do usuario
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * seta o id do usuario
	 * @param id Integer - novo id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return retorna o login do usuario
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * seta um novo login para o usuario
	 * @param login String - novo login
	 */
	public void setLogin(String login) {
		ROOT.addFolder(login);
		this.login = login;
	}

	/**
	 * @return retorna a senha do usuario
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * seta uma nova senha para o usuario
	 * @param password String - nova senha
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return retorna o email do usuario
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * seta um novo email de usuario
	 * @param email String - novo email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "id: " + getId() + " login: " + getLogin() + " email: " + getEmail() + " password: " + getPassword();
	}

}
