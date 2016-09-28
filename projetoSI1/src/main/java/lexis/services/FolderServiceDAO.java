package lexis.services;

import java.util.List;

import lexis.models.Folder;

public interface FolderServiceDAO {
	
	Folder findByRoot(String rootName);
	
	Folder findByTrash(String trashName);
	
	Folder findByShared(String sharedName);
	
	Folder findByNameAndParentFolder(String name,Integer id);

	Iterable<Folder> listAllFolder();

	void saveFolder(Folder folder);
	
	void deleteFolder(Folder folder);
	
	List<Folder> getAllFolderByName(String name);
	
	List<Folder> findAllByOwner(Integer id);
	
	List<Folder> findAllByParentFolder(Integer id);
	
}
