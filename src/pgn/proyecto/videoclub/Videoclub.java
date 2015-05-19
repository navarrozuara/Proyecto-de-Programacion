/**
 * 
 */
package pgn.proyecto.videoclub;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Queremos modelar un videoclub en Java. Nos limitaremos a las siguientes
 * opciones:
 * <ol>
 * <li>Añadir un producto (se pedirá identificador, título, año de estreno,
 * autor, temporada, número de temporadas y género).</li>
 * <li>Eliminar un producto (por identificador).</li>
 * <li>Mostrar un producto (por título).</li>
 * <li>Mostrar videoclub (todos los productos del videoclub).</li>
 * <li>Mostrar productos de un género.</li>
 * <li>Ordenar los productos del videoclub.</li>
 * </ol>
 * Lógicamente, no podrá añadirse un producto inválido o ya contenido (no pueden
 * existir dos productos con el mismo identificador en el videoclub). Por cada
 * producto que se añada, han de conocerse todas sus características. Ninguna de
 * las características del producto puede ser por defecto.
 * 
 * @author Elisa Navarro Zuara
 * @version 1.0
 */
public class Videoclub implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Colección de productos. No puede tener null.
	 */
	private ArrayList<Producto> videoclub = new ArrayList<Producto>();
	
	/**
	 * Añade un producto película al videoclub
	 * 
	 * @param id
	 *            Representa el identificador del producto a añadir
	 * @param titulo
	 *            Representa el título del producto a añadir
	 * @param annio
	 *            Representa el año de estreno del producto a añadir
	 * @param autor
	 *            Representa el director del producto a añadir
	 * @param genero
	 *            Representa el género del producto a añadir
	 * @return true si el producto se añade, false en otro caso (el producto ya
	 *         está contenido en el videoclub)
	 *         
	 * @throws ProductoYaExisteException
	 *             Si el producto ya está contenido en el videoclub
	 * @throws IdNoValidoException
	 *             Si el identificador no es válido
	 * @throws TituloNoValidoException
	 *             Si el título no es válido
	 * @throws AnnioNoValidoException
	 *             Si el año de estreno no es válido
	 * @throws AutorNoValidoException
	 *             Si el director no es válido
	 * @throws GeneroNoValidoException
	 *             Si el género no es válido
	 * @throws TipoNoValidoException
	 *             Si el tipo de producto no es válido
	 */
	public boolean annadir(String id, String titulo, int annio, String autor,
			Genero genero) throws ProductoYaExisteException,
			IdNoValidoException, TituloNoValidoException,
			AnnioNoValidoException, AutorNoValidoException,
			GeneroNoValidoException, TipoNoValidoException {
		Producto producto = new Pelicula(id, titulo, annio, autor, genero);
		if (videoclub.contains(producto))
			throw new ProductoYaExisteException("El producto ya existe.");
		return videoclub.add(producto);
	}
	
	/**
	 * Añade un producto serie al videoclub
	 * 
	 * @param id
	 *            Representa el identificador del producto a añadir
	 * @param titulo
	 *            Representa el título del producto a añadir
	 * @param annio
	 *            Representa el año de estreno del producto a añadir
	 * @param numTemporadas
	 *            Representa el número de temporadas del producto a añadir
	 * @param temporada
	 *            Representa la temporada del producto a añadir
	 * @param genero
	 *            Representa el género del producto a añadir
	 * @return true si el producto se añade, false en otro caso (el producto ya
	 *         está contenido en el videoclub)
	 *         
	 * @throws ProductoYaExisteException
	 *             Si el producto ya está contenido en el videoclub
	 * @throws IdNoValidoException
	 *             Si el identificador no es válido
	 * @throws TituloNoValidoException
	 *             Si el título no es válido
	 * @throws AnnioNoValidoException
	 *             Si el año de estreno no es válido
	 * @throws TemporadaNoValidaException
	 *             Si la temporada no es válida
	 * @throws GeneroNoValidoException
	 *             Si el género no es válido
	 * @throws TipoNoValidoException
	 *             Si el tipo de producto no es válido
	 */
	public boolean annadir(String id, String titulo, int annio,
			int numTemporadas, int temporada, Genero genero)
			throws ProductoYaExisteException, IdNoValidoException,
			TituloNoValidoException, AnnioNoValidoException,
			TemporadaNoValidaException, GeneroNoValidoException,
			TipoNoValidoException {
		Producto producto = new Serie(id, titulo, annio, numTemporadas, temporada, genero);
		if (videoclub.contains(producto))
			throw new ProductoYaExisteException("El producto ya existe.");
		return videoclub.add(producto);
	}
	
	/**
	 * Añade un producto música al videoclub
	 * 
	 * @param id
	 *            Representa el identificador del producto a añadir
	 * @param titulo
	 *            Representa el título del producto a añadir
	 * @param annio
	 *            Representa el año de estreno del producto a añadir
	 * @param autor
	 *            Representa el intérprete del producto a añadir
	 * @param generoMusical
	 *            Representa el género del producto a añadir
	 * @return true si el producto se añade, false en otro caso (el producto ya
	 *         está contenido en el videoclub)
	 *         
	 * @throws ProductoYaExisteException
	 *             Si el producto ya está contenido en el videoclub
	 * @throws IdNoValidoException
	 *             Si el identificador no es válido
	 * @throws TituloNoValidoException
	 *             Si el título no es válido
	 * @throws AnnioNoValidoException
	 *             Si el año de estreno no es válido
	 * @throws AutorNoValidoException
	 *             Si el intérprete no es válido
	 * @throws GeneroNoValidoException
	 *             Si el género no es válido
	 * @throws TipoNoValidoException
	 *             Si el tipo de producto no es válido
	 */
	public boolean annadir(String id, String titulo, int annio, String autor,
			GeneroMusical generoMusical) throws ProductoYaExisteException,
			IdNoValidoException, TituloNoValidoException,
			AnnioNoValidoException, AutorNoValidoException,
			GeneroNoValidoException, TipoNoValidoException {
		Producto producto = new Musica(id, titulo, annio, autor, generoMusical);
		if (videoclub.contains(producto))
			throw new ProductoYaExisteException("El producto ya existe.");
		return videoclub.add(producto);
	}
	
	/**
	 * Elimina un producto del videoclub
	 * 
	 * @param id
	 *            Representa el identificador del producto a eliminar
	 * @return true si el producto se elimina, false en otro caso (el producto
	 *         no está en el videoclub)
	 * @throws ProductoNoExisteException
	 *             Si el producto no existe en el videoclub
	 */
	public boolean eliminar(String id) throws ProductoNoExisteException {
		Producto producto = get(id);
		if (!videoclub.contains(producto))
			throw new ProductoNoExisteException("El producto no existe.");
		return videoclub.remove(producto);
	}
	
	/**
	 * Devuelve el número de productos del videoclub
	 * 
	 * @return Número de productos del videoclub
	 */
	public int size() {
		return videoclub.size();
	}
	
	/**
	 * Ordena los productos del videoclub
	 */
	public void ordenar() {
		Collections.sort(videoclub);
	}

	/**
	 * Devuelve el producto indicado por el identificador
	 * 
	 * @param id
	 *            Representa el identificador a buscar
	 * @return Producto contenido en el videoclub
	 * @throws ProductoNoExisteException
	 *             Si el producto no existe en el videoclub
	 */
	public Producto get(String id) throws ProductoNoExisteException {
		for (Producto producto: videoclub) {
			if (producto.getId().equals(id))
				return producto;
		}
		throw new ProductoNoExisteException("El producto no existe.");
	}
	
	/**
	 * Devuelve el producto indicado por el título
	 * 
	 * @param titulo
	 *            Representa el título a buscar
	 * @return Producto contenido en el videoclub
	 * @throws ProductoNoExisteException
	 *             Si el producto no existe en el videoclub
	 */
	public Producto getProducto(String titulo) throws ProductoNoExisteException {
		for (Producto producto: videoclub) {
			if (producto.getTitulo().equals(titulo))
				return producto;
		}
		throw new ProductoNoExisteException("El producto no existe.");
	}
	
	/**
	 * Devuelve el producto indicado por el índice
	 * 
	 * @param index
	 *            Representa el índice a buscar
	 * @return Producto contenido en el videoclub. null si no existe
	 */
	public Producto get(int index) {
		if(videoclub.isEmpty())
			return null;
		if(index < 0 || index > videoclub.size()-1)
			return null;
		return videoclub.get(index);
	}
	
	/**
	 * Genera una lista de productos de un determinado género
	 * 
	 * @param genero
	 *            Representa el género a buscar
	 * @param tipo
	 *            Representa el tipo de producto a buscar
	 * @return Lista de productos de un determinado género
	 */
	public ArrayList<Producto> getGenero(Genero genero, TipoItem tipo) {
		ArrayList<Producto> arrPeliculaGenero = new ArrayList<Producto>();
		ArrayList<Producto> arrSerieGenero = new ArrayList<Producto>();
		for (Producto producto : videoclub) {
			if (producto instanceof Pelicula) {
				Pelicula pelicula = (Pelicula) producto;
				if (pelicula.getGenero() == genero)
					arrPeliculaGenero.add(pelicula);
			} else if (producto instanceof Serie) {
				Serie serie = (Serie) producto;
				if (serie.getGenero() == genero)
					arrSerieGenero.add(serie);
			}
		}
		if (tipo == TipoItem.PELICULA)
			return arrPeliculaGenero;
		else
			return arrSerieGenero;
	}
	
	/**
	 * Genera una lista de productos de un determinado género
	 * 
	 * @param genero
	 *            Representa el género a buscar
	 * @return Lista de productos de un determinado género
	 */
	public ArrayList<Producto> getGenero(GeneroMusical genero) {
		ArrayList<Producto> arrMusicaGenero = new ArrayList<Producto>();
		for (Producto producto : videoclub) {
			if (producto instanceof Musica) {
				Musica musica = (Musica) producto;
				if (musica.getGenero() == genero)
					arrMusicaGenero.add(musica);
			}
		}
		return arrMusicaGenero;
	}

}