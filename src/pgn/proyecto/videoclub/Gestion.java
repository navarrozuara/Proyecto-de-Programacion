/**
 * 
 */
package pgn.proyecto.videoclub;

import java.io.File;

/**
 * Clase que gestiona el videoclub
 * 
 * @author Elisa Navarro Zuara
 * @version 1.0
 */
public class Gestion {
	
	/**
	 * Videoclub
	 */
	private static Videoclub videoclub = new Videoclub();
	
	/**
	 * Fichero
	 */
	private static File file;
	
	/**
	 * Estado del videoclub
	 */
	private static boolean modificado;
	
	/**
	 * Devuelve el videoclub
	 * 
	 * @return Videoclub
	 */
	public static Videoclub getVideoclub() {
		return videoclub;
	}
	
	/**
	 * Modifica el videoclub
	 * 
	 * @param videoclub
	 *            Representa el nuevo videoclub
	 */
	public static void setVideoclub(Videoclub videoclub) {
		Gestion.videoclub = videoclub;
	}
	
	/**
	 * Devuelve el fichero
	 * 
	 * @return Fichero
	 */
	public static File getFile() {
		return file;
	}
	
	/**
	 * Modifica el fichero
	 * 
	 * @param file
	 *            Representa el nuevo fichero
	 */
	public static void setFile(File file) {
		Gestion.file = file;
	}
	
	/**
	 * Devuelve el estado del videoclub
	 * 
	 * @return Estado del videoclub
	 */
	public static boolean isModificado() {
		return modificado;
	}
	
	/**
	 * Modifica el estado del videoclub
	 * 
	 * @param modificado
	 *            Representa el nuevo estado del videoclub
	 */
	public static void setModificado(boolean modificado) {
		Gestion.modificado = modificado;
	}

}