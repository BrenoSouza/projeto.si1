package lexis.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.TreeMap;

public class Explorer implements Comparable<Explorer> {
	

	private Folder root;
	private Stack<Folder> stackFolder;
	
	private TreeMap<String, List<SharedFileReadAndWrite>> sharedFilesReadAndWrite;
	private TreeMap<String, List<SharedFileReadOnly>> s;

	
	
	public Explorer() {
		
	}
	
	public Explorer(String rootName) {
		if(rootName == null)
			throw new NullPointerException();
			
		root = new Folder(rootName, Permission.PRIVATE, new ArrayList<String>());
		stackFolder = new Stack<Folder>();
		stackFolder.push(root);
		
		sharedFilesReadAndWrite = new TreeMap<String, List<SharedFileReadAndWrite>>();
		s = new TreeMap<String, List<SharedFileReadOnly>>();
		
	}
	
	/**
	 * Adiciona arquivos de terceiros para esse explorer. O dono 
	 * desse explorer tem permissao apenas para ver alguns 
	 * atributos do arquivo e para  ver e alterar o conteudo 
	 * do arquivo.
	 * @param owner Criador/dono do arquivo.
	 * @param file Arquivo a ser compartilhado
	 */
	public void addSharedFileReadAndWrite(File file, String owner) {
		if(!sharedFilesReadAndWrite.containsKey(owner)) {
			sharedFilesReadAndWrite.put(owner, new ArrayList<SharedFileReadAndWrite>());
		}
		
		SharedFileReadAndWrite fileReadAndWrite = new SharedFileReadAndWrite(file, owner);
		sharedFilesReadAndWrite.get(owner).add(fileReadAndWrite);
	}
	
	/**
	 * Adiciona arquivos de terceiros para esse explorer. O dono 
	 * desse explorer tem permissao apenas para ver alguns 
	 * atributos do arquivo, como o conteudo, nome, entre outros.
	 * @param owner Criador/dono do arquivo.
	 * @param file Arquivo a ser compartilhado
	 */
	public void addSharedFileReadOnly(File file, String owner) {
		if(!s.containsKey(owner)) {
			s.put(owner, new ArrayList<SharedFileReadOnly>());
		}
		
		SharedFileReadOnly fileReadOnly = new SharedFileReadOnly(file, owner);
		s.get(owner).add(fileReadOnly);
	}
	
	
	public TreeMap<String, List<SharedFileReadAndWrite>> getSharedFilesReadAndWrite() {
		return sharedFilesReadAndWrite;
	}
	
	public TreeMap<String, List<SharedFileReadOnly>> getSharedFilesReadOnly() {
		return s;
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
	
	public Folder goToRoot() {
		while(stackFolder.size() > 1)
			stackFolder.pop();
		
		return stackFolder.peek();
	}
	
	public List<FileAndFolder> find(String name) {
		return root.find(name);
	}
	
	public Folder goTo(String path) {
		
		goToRoot();
		
		if(path == null)
			return stackFolder.peek();
		
		String[] arrayPath = path.split(Folder.SEP);
	
		return auxGoTo(arrayPath);
	}
	
	public Folder goTo(List<String> listPath) {
		String[] arrayPath = (String[]) listPath.toArray();
		
		return auxGoTo(arrayPath);
	}
	
	
	public File openFile(String name, Type type) {
		return stackFolder.peek().getOrCreateFile(name, type);
	}
	
	public File getFile(String name, Type type) {
		return stackFolder.peek().getFile(name, type);
	}
	
	public boolean removeFolder(String name) {
		return stackFolder.peek().removeFolder(name);
	}
	
	public boolean removeFile(String name, Type type) {
		return stackFolder.peek().removeFile(name, type);
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
	
	
	public Folder getRoot() {
		return root;
	}

	@Override
	public int compareTo(Explorer otherExplorer) {
		return root.getName().compareTo(otherExplorer.getRoot().getName());
	}
	
	
	private Folder auxGoTo(String[] arrayPath) {
		if(arrayPath.length != 0) {
			Folder aux;
			if(arrayPath[0].equals(stackFolder.peek().getName())) {
				for(int i = 1; i < arrayPath.length; i++) {
					aux = stackFolder.peek().getFolder(arrayPath[i]);
					
					if(aux == null)
						break;
					else
						stackFolder.push(aux);
				}
			}
		}
			
		return stackFolder.peek();
	}
	
}
