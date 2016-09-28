package lexis.services.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lexis.models.Folder;
import lexis.repositories.FolderRepository;
import lexis.services.FolderServiceDAO;

@Service
public class FolderServiceDAOImpl implements FolderServiceDAO{

	private FolderRepository folderRepository;

	@Autowired
	public void setFileRepository(FolderRepository folderRepository) {
		this.folderRepository = folderRepository;
	}
	
	@Override
	public Iterable<Folder> listAllFolder() {
		return folderRepository.findAll();
	}

	@Override
	public void saveFolder(Folder folder) {
		folderRepository.save(folder);
		
	}

	@Override
	public void deleteFolder(Folder folder) {
		folderRepository.delete(folder);
		
	}

	@Override
	public List<Folder> getAllFolderByName(String name) {
		Iterator<Folder> interator = folderRepository.findAllByName(name).iterator();
		List<Folder> folder = new ArrayList<Folder>();
		while (interator.hasNext()) {
			folder.add(interator.next());
		}
		return folder;
	}

	@Override
	public List<Folder> findAllByOwner(Integer id) {
		Iterator<Folder> interator = folderRepository.findAllByOwner(id).iterator();
		List<Folder> folder = new ArrayList<Folder>();
		while (interator.hasNext()) {
			folder.add(interator.next());
		}
		return folder;
	}

	@Override
	public List<Folder> findAllByParentFolder(Integer id) {
		Iterator<Folder> interator = folderRepository.findAllByParentFolder(id).iterator();
		List<Folder> folder = new ArrayList<Folder>();
		while (interator.hasNext()) {
			folder.add(interator.next());
		}
		return folder;
	}

	@Override
	public Folder findByRoot(String rootName) {
		return folderRepository.findByRoot(rootName);
	}

	@Override
	public Folder findByTrash(String trashName) {
		return folderRepository.findByTrash(trashName);
	}

	@Override
	public Folder findByShared(String sharedName) {
		return folderRepository.findByShared(sharedName);
	}

	@Override
	public Folder findByNameAndParentFolder(String name, Integer id) {
		return folderRepository.findByNameAndParentFolder(name, id);
	}

}
