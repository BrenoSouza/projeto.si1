package lexis.initialization;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import lexis.models.DataBase;
import lexis.models.Explorer;
import lexis.models.File;
import lexis.models.Folder;
import lexis.models.Permission;
import lexis.models.Type;
import lexis.models.User;
import lexis.repositories.FileRepository;
import lexis.repositories.FolderRepository;
import lexis.repositories.UserRepository;

/**
 * Classe responsavel por inciar o sistema com usuarios padrão.
 * 
 * @author Grupo 7, Alexandre Gullo, Jose Breno, Matheus Benedito, Melisse
 *         Cabral, Raimundo Heitor, Rafael Klyger.
 *
 */
@Component
public class UserLoader implements ApplicationListener<ContextRefreshedEvent> {

	// Repositorio em que o Objeto usuario é manipulado.
	private UserRepository userRepository;

	// Exemplo simples de um futuro log.
	private Logger log = Logger.getLogger(UserLoader.class);

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	private FileRepository fileRepository;

	@Autowired
	public void setFileRepository(FileRepository fileRepository) {
		this.fileRepository = fileRepository;
	}
	
	private FolderRepository folderRepository;

	@Autowired
	public void setFolderRepository(FolderRepository folderRepository) {
		this.folderRepository = folderRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		// Adicionando usuarios ao sistema.
		User admin;
		User admin2;
		try {
			List<String> a = new ArrayList<>();
			a.add("pqp");
			//------------SIMULAÇÃO DO SISTEMA
			
			//criando e salvando usuarios usuarios
			admin = new User("admin", "admin", "admin@admin");
			admin2 = new User("admin2", "admin2", "admin2@admin");
			
			// criando pastas padroes de cada usuario
			Folder rootFolder = new Folder();
			Folder rootFolder2 = new Folder();
			Folder trashFolder = new Folder();
			Folder trashFolder2 = new Folder();
			Folder sharedFolder = new Folder();
			Folder sharedFolder2 = new Folder();
			
			rootFolder.setRoot(admin.getUsername());
			rootFolder2.setRoot(admin2.getUsername());
			trashFolder.setTrash(admin.getUsername());
			trashFolder2.setTrash(admin2.getUsername());
			sharedFolder.setShared(admin.getUsername());
			sharedFolder2.setShared(admin2.getUsername());
			folderRepository.save(rootFolder);
			folderRepository.save(rootFolder2);
			folderRepository.save(trashFolder);
			folderRepository.save(trashFolder2);
			folderRepository.save(sharedFolder);
			folderRepository.save(sharedFolder2);
			
			// salvando no banco de dados
			DataBase.getInstance().addNewUser(admin);
			userRepository.save(admin);
			userRepository.save(admin2);
			
			/*//criando e salvando pastas
			Folder folder1 = new Folder("folder1", Permission.PRIVATE, a);
			Folder folder2 = new Folder("folder2", Permission.PRIVATE, a);
			Folder folder3 = new Folder("folder3", Permission.PRIVATE, a);
			
			
			folder1.setOwner(admin.getId());
			folder2.setOwner(admin2.getId());
			folder3.setOwner(admin2.getId());
			folderRepository.save(folder1);
			folder2.setParentFolder(folder1.getId());			
			folderRepository.save(folder2);
			folderRepository.save(folder3);
			
			//criando e salvando arquivos
			File file = new File("testeDeArquivo", Type.TXT, Permission.PRIVATE, a);
			File file2 = new File("testeDeArquivo2", Type.MD, Permission.PUBLIC, a);
			File file3 = new File("testeDeArquivo3", Type.TXT, Permission.PRIVATE, a);
			
			//set data
			file.setData("codigo fonte inserido com sucesso no file");
			file2.setData("codigo fonte inserido com sucesso no file2");
			file3.setData("codigo fonte inserido com sucesso no file3");
			
			file.setOwner(admin.getId());
			file2.setOwner(admin2.getId());
			file3.setOwner(admin.getId());
			
			file.setParentFolder(folder1.getId());
			file2.setParentFolder(folder2.getId());
			file3.setParentFolder(folder1.getId());
			fileRepository.save(file);
			fileRepository.save(file2);
			fileRepository.save(file3);
			log.info("Saved admin - id:" + admin.getId());*/
			
			//System.out.println("file count: "+ fileRepository.count());
			//System.out.println("file find all: " +fileRepository.findAll());
			//System.out.println("find one 1: "+fileRepository.findOne(1));
			//System.out.println("find by Name and Type: "+fileRepository.findByNameAndType("testeDeArquivo2", Type.TXT));
			//System.out.println("find all by parent folder: "+ fileRepository.findAllByParentFolder(folder1.getId()));
			//System.out.println("get user name folder root: "+ folderRepository.findByRoot(admin.getUsername()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		User guest;
		try {
			guest = new User("guest", "guest", "guest@guest");
			Explorer explorer = DataBase.getInstance().addNewUser(guest);
			explorer.currentFolder().addFolder("geustpasta1", Permission.PRIVATE);
			explorer.currentFolder().addFolder("geustpasta2", Permission.PRIVATE);
			userRepository.save(guest);

			log.info("Saved guest - id:" + guest.getId());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
