package lexis.repositories;

import org.springframework.data.repository.CrudRepository;

import lexis.models.File;
import lexis.models.Type;

public interface FileRepository extends CrudRepository<File, Integer>{
	
	File findByNameAndType(String name, Type type);	
	
	Iterable<File> findAllByName(String name);
	
	Iterable<File> findAllByOwner(Integer id);
	
	Iterable<File> findAllByParentFolder(Integer id);
}
