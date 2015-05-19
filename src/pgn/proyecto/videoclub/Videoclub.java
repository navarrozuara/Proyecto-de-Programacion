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
 * <li>A�adir un producto (se pedir� identificador, t�tulo, a�o de estreno,
 * autor, temporada, n�mero de temporadas y g�nero).</li>
 * <li>Eliminar un producto (por identificador).</li>
 * <li>Mostrar un producto (por t�tulo).</li>
 * <li>Mostrar videoclub (todos los productos del videoclub).</li>
 * <li>Mostrar productos de un g�nero.</li>
 * <li>Ordenar los productos del videoclub.</li>
 * </ol>
 * L�gicamente, no podr� a�adirse un producto inv�lido o ya contenido (no pueden
 * existir dos productos con el mismo identificador en el videoclub). Por cada
 * producto que se a�ada, han de conocerse todas sus caracter�sticas. Ninguna de
 * las caracter�sticas del producto puede ser por defecto.
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
	 * Colecci�n de productos. No puede tener null.
	 */
	private ArrayList<Producto> videoclub = new ArrayList<Producto>();
	
	/**
	 * A�ade un producto pel�cula al videoclub
	 * 
	 * @param id
	 *            Representa el identificador del producto a a�adir
	 * @param titulo
	 *            Representa el t�tulo del producto a a�adir
	 * @param annio
	 *            Representa el a�o de estreno del producto a a�adir
	 * @param autor
	 *            Representa el director del producto a a�adir
	 * @param genero
	 *            Representa el g�nero del producto a a�adir
	 * @return true si el producto se a�ade, false en otro caso (el producto ya
	 *         est� contenido en el videoclub)
	 *         
	 * @throws ProductoYaExisteException
	 *             Si el producto ya est� contenido en el videoclub
	 * @throws IdNoValidoException
	 *             Si el identificador no es v�lido
	 * @throws TituloNoValidoException
	 *             Si el t�tulo no es v�lido
	 * @throws AnnioNoValidoException
	 *             Si el a�o de estreno no es v�lido
	 * @throws AutorNoValidoException
	 *             Si el director no es v�lido
	 * @throws GeneroNoValidoException
	 *             Si el g�nero no es v�lido
	 * @throws TipoNoValidoException
	 *             Si el tipo de producto no es v�lido
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
	 * A�ade un producto serie al videoclub
	 * 
	 * @param id
	 *            Representa el identificador del producto a a�adir
	 * @param titulo
	 *            Representa el t�tulo del producto a a�adir
	 * @param annio
	 *            Representa el a�o de estreno del producto a a�adir
	 * @param numTemporadas
	 *            Representa el n�mero de temporadas del producto a a�adir
	 * @param temporada
	 *            Representa la temporada del producto a a�adir
	 * @param genero
	 *            Representa el g�nero del producto a a�adir
	 * @return true si el producto se a�ade, false en otro caso (el producto ya
	 *         est� contenido en el videoclub)
	 *         
	 * @throws ProductoYaExisteException
	 *             Si el producto ya est� contenido en el videoclub
	 * @throws IdNoValidoException
	 *             Si el identificador no es v�lido
	 * @throws TituloNoValidoException
	 *             Si el t�tulo no es v�lido
	 * @throws AnnioNoValidoException
	 *             Si el a�o de estreno no es v�lido
	 * @throws TemporadaNoValidaException
	 *             Si la temporada no es v�lida
	 * @throws GeneroNoValidoException
	 *             Si el g�nero no es v�lido
	 * @throws TipoNoValidoException
	 *             Si el tipo de producto no es v�lido
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
	 * A�ade un producto m�sica al videoclub
	 * 
	 * @param id
	 *            Representa el identificador del producto a a�adir
	 * @param titulo
	 *            Representa el t�tulo del producto a a�adir
	 * @param annio
	 *            Representa el a�o de estreno del producto a a�adir
	 * @param autor
	 *            Representa el int�rprete del producto a a�adir
	 * @param generoMusical
	 *            Representa el g�nero del producto a a�adir
	 * @return true si el producto se a�ade, false en otro caso (el producto ya
	 *         est� contenido en el videoclub)
	 *         
	 * @throws ProductoYaExisteException
	 *             Si el producto ya est� contenido en el videoclub
	 * @throws IdNoValidoException
	 *             Si el identificador no es v�lido
	 * @throws TituloNoValidoException
	 *             Si el t�tulo no es v�lido
	 * @throws AnnioNoValidoException
	 *             Si el a�o de estreno no es v�lido
	 * @throws AutorNoValidoException
	 *             Si el int�rprete no es v�lido
	 * @throws GeneroNoValidoException
	 *             Si el g�nero no es v�lido
	 * @throws TipoNoValidoException
	 *             Si el tipo de producto no es v�lido
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
	 *         no est� en el videoclub)
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
	 * Devuelve el n�mero de productos del videoclub
	 * 
	 * @return N�mero de productos del videoclub
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
	 * Devuelve el producto indicado por el t�tulo
	 * 
	 * @param titulo
	 *            Representa el t�tulo a buscar
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
	 * Devuelve el producto indicado por el �ndice
	 * 
	 * @param index
	 *            Representa el �ndice a buscar
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
	 * Genera una lista de productos de un determinado g�nero
	 * 
	 * @param genero
	 *            Representa el g�nero a buscar
	 * @param tipo
	 *            Representa el tipo de producto a buscar
	 * @return Lista de productos de un determinado g�nero
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
	 * Genera una lista de productos de un determinado g�nero
	 * 
	 * @param genero
	 *            Representa el g�nero a buscar
	 * @return Lista de productos de un determinado g�nero
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