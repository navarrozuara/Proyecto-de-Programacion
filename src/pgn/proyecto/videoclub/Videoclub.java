/**
 * 
 */
package pgn.proyecto.videoclub;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Elisa Navarro Zuara
 * @version 1.0
 */
public class Videoclub implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Producto> listaProductos = new ArrayList<Producto>();
	
	public boolean annadir(String id, String titulo, int annio, String autor,
			Genero genero) throws ProductoYaExisteException,
			IdNoValidoException, TituloNoValidoException,
			AnnioNoValidoException, GeneroNoValidoException,
			TipoNoValidoException {
		Producto producto = new Pelicula(id, titulo, annio, autor, genero);
		if (listaProductos.contains(producto))
			throw new ProductoYaExisteException("El producto ya existe.");
		return listaProductos.add(producto);
	}
	
	public boolean annadir(String id, String titulo, int annio,
			int numTemporadas, int temporada, Genero genero)
			throws ProductoYaExisteException, IdNoValidoException,
			TituloNoValidoException, AnnioNoValidoException,
			TemporadaNoValidaException, GeneroNoValidoException,
			TipoNoValidoException {
		Producto producto = new Serie(id, titulo, annio, numTemporadas, temporada, genero);
		if (listaProductos.contains(producto))
			throw new ProductoYaExisteException("El producto ya existe.");
		return listaProductos.add(producto);
	}
	
	public boolean annadir(String id, String titulo, int annio, String autor,
			GeneroMusical generoMusical) throws ProductoYaExisteException,
			IdNoValidoException, TituloNoValidoException,
			AnnioNoValidoException, GeneroNoValidoException,
			TipoNoValidoException {
		Producto producto = new Musica(id, titulo, annio, autor, generoMusical);
		if (listaProductos.contains(producto))
			throw new ProductoYaExisteException("El producto ya existe.");
		return listaProductos.add(producto);
	}
	
	public boolean eliminar(String id) throws ProductoNoExisteException {
		Producto producto = get(id);
		if (!listaProductos.contains(producto))
			throw new ProductoNoExisteException("El producto no existe.");
		return listaProductos.remove(producto);
	}
	
	public int size() {
		return listaProductos.size();
	}
	
	public void ordenar() {
		Collections.sort(listaProductos);
	}

	public Producto get(String id) throws ProductoNoExisteException {
		for (Producto producto: listaProductos) {
			if (producto.getId().equals(id))
				return producto;
		}
		throw new ProductoNoExisteException("El producto no existe.");
	}
	
	public Producto getProducto(String titulo) throws ProductoNoExisteException {
		for (Producto producto: listaProductos) {
			if (producto.getTitulo().equals(titulo))
				return producto;
		}
		throw new ProductoNoExisteException("El producto no existe.");
	}
	
	public Producto get(int index) {
		if(listaProductos.isEmpty())
			return null;
		if(index < 0 || index > listaProductos.size()-1)
			return null;
		return listaProductos.get(index);
	}
	
	public ArrayList<Producto> getGenero(Genero genero, TipoItem tipo) {
		ArrayList<Producto> arrPeliculaGenero = new ArrayList<Producto>();
		ArrayList<Producto> arrSerieGenero = new ArrayList<Producto>();
		for (Producto producto : listaProductos) {
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
	
	public ArrayList<Producto> getGenero(GeneroMusical genero) {
		ArrayList<Producto> arrMusicaGenero = new ArrayList<Producto>();
		for (Producto producto : listaProductos) {
			if (producto instanceof Musica) {
				Musica musica = (Musica) producto;
				if (musica.getGenero() == genero)
					arrMusicaGenero.add(musica);
			}
		}
		return arrMusicaGenero;
	}

}