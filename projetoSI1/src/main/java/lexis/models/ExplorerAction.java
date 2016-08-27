package lexis.models;

public enum ExplorerAction {
	CDUP("cd .."), CDDOWN("cd "), RMDIR("rmdir "), 
	RM("rm "), RENAME("rename "), RENAME_FILE("rm "), MKDIR("mkdir "), OPEN_FILE("./");
	
	
	private final String NAME;
	
	private ExplorerAction(String name) {
		this.NAME = name; 
	}
	
	public String getValue() {
		return NAME;
	}
	
	

	
}
