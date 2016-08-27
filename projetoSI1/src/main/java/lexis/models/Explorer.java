package lexis.models;

import java.util.Stack;

public class Explorer implements Comparable<Explorer> {
	

	private Folder root;
	private Stack<Folder> stackFolder;
	

	
	
	public Explorer() {
		
	}
	
	public Explorer(String rootName) {
		if(rootName == null)
			throw new NullPointerException();
			
		root = new Folder(rootName, Permission.PRIVATE);
		stackFolder = new Stack<Folder>();
		stackFolder.push(root);
		
	}
	
	public Folder goUp() {
		if(stackFolder.size() != 1)
			stackFolder.pop();

		return stackFolder.peek();
	}
	
	
	public Folder goDown(String name) {
		Folder temp = stackFolder.peek().getFolder(name);
		
		if(temp != null)
			stackFolder.push(temp);
		
		return temp;
		
	}
	
	
	public File openFile(String name, Type type) {
		return stackFolder.peek().getOrCreateFile(name, type);
	}
	
	
	public void renameAFolder(String oldName, String newName) {
		Folder temp = stackFolder.peek().getFolder(oldName);
		
		if(stackFolder.peek().getFolder(newName) == null)
			temp.setName(newName);
		
	}
	
	public void renameAFile(String oldName, String newName, Type type) {
		File temp = stackFolder.peek().getFile(oldName, type);
		
		if(stackFolder.peek().getFile(newName, type) == null)
			temp.setName(newName);
		
		
	}
	
	
	public Folder currentFolder() {
		return stackFolder.peek();
	}
	
	
	public void addFolderInCurrentFolder(String name, Permission permission) {
		stackFolder.peek().addFolder(name, permission);
	
	}
	
	public Folder getRoot() {
		return root;
	}

	@Override
	public int compareTo(Explorer otherExplorer) {
		return root.getName().compareTo(otherExplorer.getRoot().getName());
	}
	
	
}
