package lexis.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

public class Explorer implements Comparable<Explorer> {
	

	private Folder root;
	private String owner;
	private Stack<Folder> stackFolder;
	
	private TreeSet<String> pifo;
	
	private TreeMap<String, List<SharedFileReadAndWrite>> sharedFilesReadAndWrite;
	private TreeMap<String, List<SharedFileReadOnly>> sharedFilesReadOnly;

	
	/**
	 * Construtor padrao que recebe o nome do diretorio 
	 * raiz da arvore de diretorios.
	 * @param rootName Nome do diretorio raiz.
	 */
	public Explorer(String rootName) {
		if(rootName == null)
			throw new NullPointerException();
		
		owner = rootName.toLowerCase();
		root = new Folder(rootName.toLowerCase(), Permission.PRIVATE, new ArrayList<String>());
		stackFolder = new Stack<Folder>();
		stackFolder.push(root);
		
		pifo = new TreeSet<String>();
		sharedFilesReadAndWrite = new TreeMap<String, List<SharedFileReadAndWrite>>();
		sharedFilesReadOnly = new TreeMap<String, List<SharedFileReadOnly>>();
		
	}
	
	public void addUserToShare(String userLogin) {
		pifo.add(userLogin.toLowerCase());
	}
	
	public boolean removeUserThatImSharing(String userLogin) {
		return pifo.remove(userLogin.toLowerCase());
	}
	
	public String[] getUsersThatImSharing() {
		return pifo.toArray(new String[0]);
	}
	
	/**
	 * Adiciona arquivos de terceiros para esse explorer. O dono 
	 * desse explorer tem permissao apenas para ver alguns 
	 * atributos do arquivo e para  ver e alterar o conteudo 
	 * do arquivo.
	 * @param owner Criador/dono do arquivo.
	 * @param file Arquivo a ser compartilhado
	 */
	public SharedFileReadAndWrite addSharedFileReadAndWrite(File file, String owner) {
		
		if(file == null || owner == null)
			throw new NullPointerException();
		
		if(!sharedFilesReadAndWrite.containsKey(owner)) {
			sharedFilesReadAndWrite.put(owner, new ArrayList<SharedFileReadAndWrite>());
		}
		
		SharedFileReadAndWrite fileReadAndWrite = new SharedFileReadAndWrite(file, owner);
		
		List<SharedFileReadAndWrite> sharedFilesWithThisUser = sharedFilesReadAndWrite.get(owner); 
		
		if(!sharedFilesWithThisUser.contains(fileReadAndWrite))
			sharedFilesWithThisUser.add(fileReadAndWrite);
		
		return fileReadAndWrite;
	}
	
	/**
	 * Adiciona arquivos de terceiros para esse explorer. O dono 
	 * desse explorer tem permissao apenas para ver alguns 
	 * atributos do arquivo, como o conteudo, nome, entre outros.
	 * @param owner Criador/dono do arquivo.
	 * @param file Arquivo a ser compartilhado
	 */
	public SharedFileReadOnly addSharedFileReadOnly(File file, String owner) {
		if(!sharedFilesReadOnly.containsKey(owner)) {
			sharedFilesReadOnly.put(owner, new ArrayList<SharedFileReadOnly>());
		}
		
		SharedFileReadOnly fileReadOnly = new SharedFileReadOnly(file, owner);
		
		List<SharedFileReadOnly> sharedFilesWithThisUser = sharedFilesReadOnly.get(owner);
		
		if(!sharedFilesWithThisUser.contains(fileReadOnly))		
			sharedFilesWithThisUser.add(fileReadOnly);
		
		return fileReadOnly;
	}
	
	
	/**
	 * Recupera o mapa <Login do dono do arquivo, Lista de arquivos> 
	 * contento todos os arquivos compartilhados com esse usuario que 
	 * ele possui permissao para visualizar e editar o conteudo do arquivo.
	 * @return
	 */
	public TreeMap<String, List<SharedFileReadAndWrite>> getSharedFilesReadAndWrite() {
		return sharedFilesReadAndWrite;
	}
	
	/**
	 * Recupera o mapa <Login do dono do arquivo, Lista de arquivos> 
	 * contento todos os arquivos compartilhados com esse usuario que 
	 * ele possui permissao apenas visualizar.
	 * @return
	 */
	public TreeMap<String, List<SharedFileReadOnly>> getSharedFilesReadOnly() {
		return sharedFilesReadOnly;
	}
	
	/**
	 * Sobe um nivel na hierarquia de pastas. Por exemplo, 
	 * caso a pasta2 esteja contida na pasta1 e a pasta atual 
	 * e a pasta2, se esse metodo for utilizado ele ira para 
	 * pasta1. Caso a pasta atual ja seja a pasta root ele apenas 
	 * retorna o root.
	 * @return Retorna a pasta atual apos subir um nivel.
	 */
	public Folder goUp() {
		if(stackFolder.size() != 1)
			stackFolder.pop();

		return stackFolder.peek();
	}
	
	/**
	 * Entra na pasta que possui esse nome caso ela exista e 
	 * esteja contida na pasta atual.
	 * @param name Nome da pasta que se deseja entrar.
	 * @return Retorna a pasta atual apos a operacao.
	 */
	public Folder goDown(String name) {
		Folder temp = stackFolder.peek().getFolder(name);
		
		if(temp != null)
			stackFolder.push(temp);
		
		return temp;
		
	}
	
	/**
	 * Sobe todos os niveis ate que a pasta atual 
	 * seja o root.
	 * @return retorna o root.
	 */
	public Folder goToRoot() {
		while(stackFolder.size() > 1)
			stackFolder.pop();
		
		return stackFolder.peek();
	}
	
	/**
	 * Pesquisa em todos os diretorios por uma pasta ou 
	 * arquivo que possui o nome passado como parametro.
	 * @param name Nome a ser pesquisado.
	 * @return Retorna uma lista com todos os resultados encontrados.
	 */
	public List<FileAndFolder> find(String name) {
		return root.find(name);
	}
	
	/**
	 * Vai para o diretorio passado no parametro, caso o 
	 * diretorio nao seja valido ele vai para o root e o 
	 * retorna.
	 * @param path Diretorio de destino.
	 * @return Retorna o diretorio atual apos a operacao 
	 * do metodo.
	 */
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
	
	
	public void renameFolder(String oldName, String newName) {
		Folder temp = stackFolder.peek().getFolder(oldName);
		
		if(stackFolder.peek().getFolder(newName) == null) {
			temp.setName(newName);
			
			int index = stackFolder.size()-1;
			temp.setCellOfPath(index, newName);
		}
		
	}
	
	public void renameFile(String oldName, String newName, Type type) {
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
	
	
	public String getOwner() {
		return owner;
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
		if(arrayPath[arrayPath.length-1].equals(stackFolder.peek()))
			return stackFolder.peek();
		
		return goToRoot();
	}
	
}
