package lexis.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe com algumas funcionalidades e alguns metodos 
 * de verificacao que sao comuns em classes que nao pussuem 
 * uma hierarquia em comum.
 * @author klynger
 *
 */
public class ModelsUtil {
	
	
	/**
	 * Verifica se o nome passado e um nome valido seguindo 
	 * algumas restricoes como por exemplo se o nome e uma 
	 * string vazia.
	 * @param name Nome a ser avaliado.
	 * @return Retorna true caso o nome seja valico e false 
	 * caso contrario.
	 */
	public static boolean isAValidName(String name) {
		boolean result = true;
		if(name == null)
			result = false;
		else if(name.equals(""))
			result = false;
		
		return result;

	}
	
	public static Pair<List<TrashFile>, List<TrashFolder>> transformGetTrashOutput(TrashFileAndFolder[] trash) {
		List<TrashFile> trashFiles = new ArrayList<TrashFile>();
		List<TrashFolder> trashFolders = new ArrayList<TrashFolder>();
		
		for(TrashFileAndFolder elem : trash) {
			if(elem instanceof TrashFile) {
				trashFiles.add((TrashFile) elem);
			} else {
				trashFolders.add((TrashFolder) elem);
			}
		}
		
		return new Pair<List<TrashFile>, List<TrashFolder>>(trashFiles, trashFolders);
	}

}
