/**
 * 
 */
package pgn.proyecto.videoclub;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author Elisa Navarro Zuara
 * @version 1.0
 */
public class Fichero {

	/**
	 * Método para abrir un videoclub existente
	 * 
	 * @param file
	 *            Representa el fichero a abrir
	 * @return Videoclub existente
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static Videoclub abrir(File file) throws ClassNotFoundException,
			IOException {
		file = comprobarExtension(file);
		try (ObjectInputStream in = new ObjectInputStream(
				new BufferedInputStream(new FileInputStream(file)))) {
			return (Videoclub) in.readObject();
		}
	}
	
	/**
	 * Método para guardar un videoclub
	 * 
	 * @param file
	 *            Representa el fichero a guardar
	 * @param videoclub
	 *            Representa el videoclub a guardar
	 * @throws IOException
	 */
	public static void guardar(File file, Videoclub videoclub) throws IOException {
		file = comprobarExtension(file);
		try (ObjectOutputStream out = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(file, false)))) {
			out.writeObject(videoclub);
		}
	}
	
	/**
	 * Comprueba si el fichero existe
	 * 
	 * @param file
	 *            Representa el fichero a comprobar
	 * @return true si el fichero existe, false en otro caso
	 */
	public static boolean isExists(File file) {
		file = comprobarExtension(file);
		return file.exists();
	}
	
	/**
	 * Comprueba si la extensión del fichero es válida o no
	 * 
	 * @param file
	 *            Representa el fichero a comprobar
	 * @return Fichero con la extensión válida
	 */
	public static File comprobarExtension(File file) {
		String path = file.getPath();
        if (!path.endsWith(".obj"))
            return new File(path + ".obj");
        return file;
	}
	
}