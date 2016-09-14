package lexis.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

public class Explorer implements Comparable<Explorer> {
	
	public static final int MAX_NOTIFICATIONS = 10;

	private Folder root;
	private String owner;
	private Stack<Folder> stackFolder;
	
	private TreeSet<String> usersThatImSharing;
	
	private TreeMap<String, List<SharedFile>> sharedFiles;
	
	private List<Notification> notifications;
	
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
		
		usersThatImSharing = new TreeSet<String>();
		sharedFiles = new TreeMap<String, List<SharedFile>>();
		
		notifications = new ArrayList<Notification>();
		
	}
	
	private void addNotification(Notification notification) {
		if(notifications.size() == MAX_NOTIFICATIONS)
			notifications.remove(MAX_NOTIFICATIONS-1);
		
		notifications.add(notification);
		notifications.sort(new NotificationComparator());
	}
	
	public void addUserToShare(String userLogin) {
		usersThatImSharing.add(userLogin.toLowerCase());
	}
	
	public boolean removeUserThatImSharing(String userLogin) {
		return usersThatImSharing.remove(userLogin.toLowerCase());
	}
	
	public String[] getUsersThatImSharing() {
		return usersThatImSharing.toArray(new String[0]);
	}
	
	/**
	 * Adiciona arquivos de terceiros para esse explorer. O dono 
	 * desse explorer tem permissao apenas para ver alguns 
	 * atributos do arquivo e para  ver e alterar o conteudo 
	 * do arquivo. Uma notificacao e criada ao adicionar um arquivo.
	 * @param owner Criador/dono do arquivo.
	 * @param file Arquivo a ser compartilhado
	 * @param log Horario em que foi feito o compartilhamento.
	 */
	public SharedFileReadAndWrite addSharedFileReadAndWrite(File file, String owner, LocalDateTime log) {
		
		if(file == null || owner == null)
			throw new NullPointerException();
		
		if(!sharedFiles.containsKey(owner)) {
			sharedFiles.put(owner, new ArrayList<SharedFile>());
		}
		
		SharedFileReadAndWrite fileReadAndWrite = new SharedFileReadAndWrite(file, owner);
		
		List<SharedFile> sharedFilesWithThisUser = sharedFiles.get(owner); 
		
		if(!sharedFilesWithThisUser.contains(fileReadAndWrite)) {
			
			sharedFilesWithThisUser.add(fileReadAndWrite);
			addNotification(new Notification(owner, fileReadAndWrite, log));
		}
		
		return fileReadAndWrite;
	}
	
	/**
	 * Adiciona arquivos de terceiros para esse explorer. O dono 
	 * desse explorer tem permissao apenas para ver alguns 
	 * atributos do arquivo, como o conteudo, nome, entre outros. Uma notificacao 
	 * e criada ao adicionar um arquivo.
	 * @param owner Criador/dono do arquivo.
	 * @param file Arquivo a ser compartilhado
	 * @param log Horario em que foi feito o compartilhamento.
	 */
	public SharedFileReadOnly addSharedFileReadOnly(File file, String owner, LocalDateTime log) {
		if(file == null || owner == null)
			throw new NullPointerException();
		
		if(!sharedFiles.containsKey(owner)) {
			sharedFiles.put(owner, new ArrayList<SharedFile>());
		}
		
		SharedFileReadOnly fileReadOnly = new SharedFileReadOnly(file, owner);
		
		List<SharedFile> sharedFilesWithThisUser = sharedFiles.get(owner);
		
		if(!sharedFilesWithThisUser.contains(fileReadOnly)) {		
		
			sharedFiles.get(owner).add(fileReadOnly);
			addNotification(new Notification(owner, fileReadOnly, log));
		}
		
		return fileReadOnly;
	}
	
	
	public List<Pair<String, List<SharedFile>>> getSharedFiles() {
		
		String[] users = getUsersThatImSharing();
		
		List<Pair<String, List<SharedFile>>> output = new ArrayList<Pair<String, List<SharedFile>>>();
		
		for(String user : users) {
			output.add(new Pair<String, List<SharedFile>>(user, new ArrayList<SharedFile>()));
			List<SharedFile> aux = sharedFiles.get(user);
			output.get(output.size()-1).getSecond().addAll(aux);
		}
		
		return output;
	}
	
	public SharedFile getSharedFile(String owner, String fileName, Type typeFile, int index) {
		
		if(owner == null || fileName == null || typeFile == null)
			throw new NullPointerException();
		
		List<SharedFile> sharedFilesWithThisOwner = this.sharedFiles.get(owner);
		SharedFile output = null;
		
		
		if(sharedFilesWithThisOwner != null && index < sharedFilesWithThisOwner.size()) {
			output = sharedFilesWithThisOwner.get(index);
		}
		
		
		return output;
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
		
		if(oldName == null || newName == null)
			throw new NullPointerException();
		
		Folder temp = stackFolder.peek().getFolder(oldName);
		
		if(stackFolder.peek().getFolder(newName) == null) {
			temp.setName(newName);
			
			int index = stackFolder.size();
			temp.setCellOfPath(index, newName);
		}
		
	}
	
	public void renameFile(String oldName, String newName, Type oldType, Type newType) {
		
		if(oldName == null || newName == null || 
				oldType == null || newType == null)
			throw new NullPointerException();
		
		File temp = stackFolder.peek().getFile(oldName, oldType);
		
		if(temp != null && stackFolder.peek().getFile(newName, newType) == null) {
			temp.setName(newName);
			temp.setType(newType);
		}
		
	}
	
	
	public Folder currentFolder() {
		return stackFolder.peek();
	}
	
	
	public Folder getRoot() {
		return root;
	}
	
	public Notification[] getNotifications() {
		return notifications.toArray(new Notification[0]);
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
