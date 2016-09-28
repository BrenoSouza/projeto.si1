package lexis.services;

import java.util.List;

import lexis.models.File;
import lexis.models.Type;

public interface FileServiceDAO {
	
	Iterable<File> listAllFile();

	void saveFile(File file);

	File getFileByNameAndType(String name, Type type);
	
	void deleteFile(File file);
	
	List<File> getAllFileByName(String name);
	
	List<File> findAllByOwner(Integer id);
	
	List<File> findAllByParentFolder(Integer id);
	
}
