package lexis.services.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lexis.models.File;
import lexis.models.Type;
import lexis.repositories.FileRepository;
import lexis.services.FileServiceDAO;

@Service
public class FileServiceDAOImpl implements FileServiceDAO{

	private FileRepository fileRepository;

	@Autowired
	public void setFileRepository(FileRepository fileRepository) {
		this.fileRepository = fileRepository;
	}

	@Override
	public Iterable<File> listAllFile() {
		return fileRepository.findAll();
	}

	@Override
	public void saveFile(File file) {
		fileRepository.save(file);
		
	}

	@Override
	public File getFileByNameAndType(String name, Type type) {
		return fileRepository.findByNameAndType(name, type);
	}

	@Override
	public void deleteFile(File file) {
		fileRepository.delete(file);
	}

	@Override
	public List<File> getAllFileByName(String name) {
		Iterator<File> interator = fileRepository.findAllByName(name).iterator();
		List<File> files = new ArrayList<File>();
		while (interator.hasNext()) {
			files.add(interator.next());
		}
		return files;
	}

	@Override
	public List<File> findAllByOwner(Integer id) {
		Iterator<File> interator = fileRepository.findAllByOwner(id).iterator();
		List<File> files = new ArrayList<File>();
		while (interator.hasNext()) {
			files.add(interator.next());
		}
		return files;
	}

	@Override
	public List<File> findAllByParentFolder(Integer id) {
		Iterator<File> interator = fileRepository.findAllByParentFolder(id).iterator();
		List<File> files = new ArrayList<File>();
		while (interator.hasNext()) {
			files.add(interator.next());
		}
		return files;
	}
	
}
