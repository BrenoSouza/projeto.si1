package lexis.repositories;

import org.springframework.data.repository.CrudRepository;

import lexis.models.Folder;

public interface FolderRepository extends CrudRepository<Folder, Integer>{
	
	Folder findByRoot(String root);
	
	Folder findByTrash(String trash); 
	
	Folder findByShared(String shared);
	
	Folder findByNameAndParentFolder(String name,Integer id);

	Iterable<Folder> findAllByName(String name);
	
	Iterable<Folder> findAllByOwner(Integer id);
	
	Iterable<Folder> findAllByParentFolder(Integer id);
}
