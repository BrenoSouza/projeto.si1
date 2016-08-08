package lexis.models;

import javax.persistence.*;

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

	public Folder getRoot() {
		return ROOT.getFolder(getLogin());
	}

	/*public void setRoot(Folder root) {
		this.ROOT = root;
	}*/

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		ROOT.addFolder(login);
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "id: " + getId() + " login: " + getLogin() + " email: " + getEmail() + " password: " + getPassword();
	}

}
