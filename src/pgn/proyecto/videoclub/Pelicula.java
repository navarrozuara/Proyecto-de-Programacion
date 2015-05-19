/**
 * 
 */
package pgn.proyecto.videoclub;

import java.util.regex.Pattern;

/**
 * Una película tendrá las siguientes características propias:
 * <ul>
 * <li>Director. El director debe estar en mayúscula y debe de tener, al menos,
 * tres caracteres válidos seguidos.</li>
 * <li>Género. Se limitarán los géneros a diez: acción, animación, aventura,
 * comedia, drama, española, sci-fi, terror, thriller y western.</li>
 * </ul>
 * 
 * @author Elisa Navarro Zuara
 * @version 1.0
 */
public class Pelicula extends Producto {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Patrón para un identificador válido
	 */
	private static final Pattern patternID = Pattern.compile("^\\d{4}(PEL)$");
	
	/**
	 * Patrón para un director válido
	 */
	private static final Pattern patternDirector = Pattern
			.compile("^[A-ZÁÉÍÓÚÑ]{2,}" + "(\\s+[A-ZÁÉÍÓÚÑ]{2,})*$");
	
	/**
	 * Director de la película
	 */
	private String director;
	
	/**
	 * Género de la película
	 */
	private Genero genero;
	
	/**
	 * Construye una nueva película de identificador, título, año, director y
	 * género especificado
	 * 
	 * @param id
	 *            Representa el identificador de la nueva película
	 * @param titulo
	 *            Representa el título de la nueva película
	 * @param annio
	 *            Representa el año de estreno de la nueva película
	 * @param director
	 *            Representa el director de la nueva película
	 * @param genero
	 *            Representa el género de la nueva película
	 *            
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
	Pelicula(String id, String titulo, int annio, String director, Genero genero)
			throws IdNoValidoException, TituloNoValidoException,
			AnnioNoValidoException, AutorNoValidoException,
			GeneroNoValidoException, TipoNoValidoException {
		super(id, titulo, annio);
		setDirector(director);
		setGenero(genero);
		setTipo(TipoItem.PELICULA);
	}
	
	/**
	 * Comprueba si el identificador del producto es válido o no
	 * 
	 * @param id
	 *            Representa el identificador a validar
	 * @return true si el identificador es válido, false si el identificador no
	 *         es válido
	 */
	private boolean esValidoID(String id) {
		return patternID.matcher(id).matches();
	}
	
	/**
	 * Comprueba si el director de la película es válido o no
	 * 
	 * @param director
	 *            Representa el director a validar
	 * @return true si el director es válido, false si el director no es válido
	 */
	private boolean esValidoDirector(String director) {
		return patternDirector.matcher(director).matches();
	}
	
	/**
	 * Modifica el identificador del producto
	 * 
	 * @param id
	 *            Representa el nuevo identificador del producto
	 * @throws IdNoValidoException
	 *             Si el identificador no es válido
	 */
	@Override
	protected void setId(String id) throws IdNoValidoException {
		if (!esValidoID(id))
			throw new IdNoValidoException("El id no es válido.");
		super.setId(id);
	}
	
	/**
	 * Modifica el director de la película
	 * 
	 * @param director
	 *            Representa el nuevo director de la película
	 * @throws AutorNoValidoException
	 *             Si el director no es válido
	 */
	private void setDirector(String director) throws AutorNoValidoException {
		if (!esValidoDirector(director))
			throw new AutorNoValidoException("El director no es válido.");
		this.director = director;
	}
	
	/**
	 * Modifica el género de la película
	 * 
	 * @param genero
	 *            Representa el nuevo género de la película
	 * @throws GeneroNoValidoException
	 *             Si el género no es válido
	 */
	private void setGenero(Genero genero) throws GeneroNoValidoException {
		if (genero == null)
			throw new GeneroNoValidoException("El genero no es válido.");
		this.genero = genero;
	}
	
	/**
	 * Devuelve el director de la película
	 * 
	 * @return Director de la película
	 */
	public String getDirector() {
		return director;
	}

	/**
	 * Devuelve el género de la película
	 * 
	 * @return Género de la película
	 */
	public Genero getGenero() {
		return genero;
	}

	/**
	 * Calcula el precio del producto
	 * 
	 * @param dias
	 *            Representa el número de dias a alquilar
	 * @param tipo
	 *            Representa el tipo de alquiler
	 * @return Precio del producto
	 */
	@Override
	public float getPrecio(int dias, TipoAlquiler tipo) {
		float total;
		if (tipo == TipoAlquiler.NORMAL) {
			total = tipo.getPrecio();
			if (dias > 2)
				total += tipo.getPrecio() * 0.2 * (dias - 2);
			return total;
		} else if (tipo == TipoAlquiler.ESTRENO) {
			total = tipo.getPrecio();
			if (dias > 2)
				total += tipo.getPrecio() * 0.2 * (dias - 2);
			return total;
		}
		return 0;
	}
	
}