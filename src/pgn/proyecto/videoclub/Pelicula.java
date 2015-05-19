/**
 * 
 */
package pgn.proyecto.videoclub;

import java.util.regex.Pattern;

/**
 * Una pel�cula tendr� las siguientes caracter�sticas propias:
 * <ul>
 * <li>Director. El director debe estar en may�scula y debe de tener, al menos,
 * tres caracteres v�lidos seguidos.</li>
 * <li>G�nero. Se limitar�n los g�neros a diez: acci�n, animaci�n, aventura,
 * comedia, drama, espa�ola, sci-fi, terror, thriller y western.</li>
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
	 * Patr�n para un identificador v�lido
	 */
	private static final Pattern patternID = Pattern.compile("^\\d{4}(PEL)$");
	
	/**
	 * Patr�n para un director v�lido
	 */
	private static final Pattern patternDirector = Pattern
			.compile("^[A-Z������]{2,}" + "(\\s+[A-Z������]{2,})*$");
	
	/**
	 * Director de la pel�cula
	 */
	private String director;
	
	/**
	 * G�nero de la pel�cula
	 */
	private Genero genero;
	
	/**
	 * Construye una nueva pel�cula de identificador, t�tulo, a�o, director y
	 * g�nero especificado
	 * 
	 * @param id
	 *            Representa el identificador de la nueva pel�cula
	 * @param titulo
	 *            Representa el t�tulo de la nueva pel�cula
	 * @param annio
	 *            Representa el a�o de estreno de la nueva pel�cula
	 * @param director
	 *            Representa el director de la nueva pel�cula
	 * @param genero
	 *            Representa el g�nero de la nueva pel�cula
	 *            
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
	 * Comprueba si el identificador del producto es v�lido o no
	 * 
	 * @param id
	 *            Representa el identificador a validar
	 * @return true si el identificador es v�lido, false si el identificador no
	 *         es v�lido
	 */
	private boolean esValidoID(String id) {
		return patternID.matcher(id).matches();
	}
	
	/**
	 * Comprueba si el director de la pel�cula es v�lido o no
	 * 
	 * @param director
	 *            Representa el director a validar
	 * @return true si el director es v�lido, false si el director no es v�lido
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
	 *             Si el identificador no es v�lido
	 */
	@Override
	protected void setId(String id) throws IdNoValidoException {
		if (!esValidoID(id))
			throw new IdNoValidoException("El id no es v�lido.");
		super.setId(id);
	}
	
	/**
	 * Modifica el director de la pel�cula
	 * 
	 * @param director
	 *            Representa el nuevo director de la pel�cula
	 * @throws AutorNoValidoException
	 *             Si el director no es v�lido
	 */
	private void setDirector(String director) throws AutorNoValidoException {
		if (!esValidoDirector(director))
			throw new AutorNoValidoException("El director no es v�lido.");
		this.director = director;
	}
	
	/**
	 * Modifica el g�nero de la pel�cula
	 * 
	 * @param genero
	 *            Representa el nuevo g�nero de la pel�cula
	 * @throws GeneroNoValidoException
	 *             Si el g�nero no es v�lido
	 */
	private void setGenero(Genero genero) throws GeneroNoValidoException {
		if (genero == null)
			throw new GeneroNoValidoException("El genero no es v�lido.");
		this.genero = genero;
	}
	
	/**
	 * Devuelve el director de la pel�cula
	 * 
	 * @return Director de la pel�cula
	 */
	public String getDirector() {
		return director;
	}

	/**
	 * Devuelve el g�nero de la pel�cula
	 * 
	 * @return G�nero de la pel�cula
	 */
	public Genero getGenero() {
		return genero;
	}

	/**
	 * Calcula el precio del producto
	 * 
	 * @param dias
	 *            Representa el n�mero de dias a alquilar
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