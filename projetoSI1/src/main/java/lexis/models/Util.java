package lexis.models;

/**
 * Classe com algumas funcionalidades e alguns metodos 
 * de verificacao que sao comuns em classes que nao pussuem 
 * uma hierarquia em comum.
 * @author klynger
 *
 */
public class Util {
	
	
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
}
