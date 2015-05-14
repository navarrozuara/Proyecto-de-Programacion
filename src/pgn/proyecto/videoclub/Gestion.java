/**
 * 
 */
package pgn.proyecto.videoclub;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
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
	 * Formato de fecha (dd/MM/yyyy)
	 */
	private static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	
	/**
	 * Fecha actual
	 */
	private static Calendar fecha = Calendar.getInstance();
	
	/**
	 * Fecha de devolución
	 */
	private static Date fechaDevolucion;
	
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
	
	/**
	 * Devuelve la fecha de devolución
	 * 
	 * @return Fecha de devolución
	 */
	public static String getFechaDevolucion() {
		return formato.format(fechaDevolucion);
	}
	
	/**
	 * Modifica la fecha de devolución
	 * 
	 * @param dias
	 *            Representa el número de dias a alquilar
	 */
	public static void setFechaDevolucion(int dias) {
		fecha.add(Calendar.DAY_OF_MONTH, dias);
		fechaDevolucion = fecha.getTime();
		fecha.setTime(new Date());
	}

}