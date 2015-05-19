/**
 * 
 */
package pgn.proyecto.videoclub;

import java.util.regex.Pattern;

/**
 * Una serie tendrá las siguientes características propias:
 * <ul>
 * <li>Temporada. Temporada actual de la serie.</li>
 * <li>Número de temporadas. Número de temporadas de la serie, debe de ser mayor
 * de cero.</li>
 * <li>Género. Se limitarán los géneros a diez: acción, animación, aventura,
 * comedia, drama, española, sci-fi, terror, thriller y western.</li>
 * </ul>
 * 
 * @author Elisa Navarro Zuara
 * @version 1.0
 */
public class Serie extends Producto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Patrón para un identificador válido
	 */
	private static final Pattern patternID = Pattern.compile("^\\d{4}(SER)$");
	
	/**
	 * Temporada de la serie
	 */
	private int temporada;
	
	/**
	 * Número de temporadas de la serie
	 */
	private int numTemporadas;
	
	/**
	 * Género de la serie
	 */
	private Genero genero;
	
	/**
	 * Construye una nueva serie de identificador, título, año, número de
	 * temporadas, temporada y género especificado
	 * 
	 * @param id
	 *            Representa el identificador de la nueva serie
	 * @param titulo
	 *            Representa el título de la nueva serie
	 * @param annio
	 *            Representa el año de estreno de la nueva serie
	 * @param numTemporadas
	 *            Representa el número de temporadas de la nueva serie
	 * @param temporada
	 *            Representa la temporada de la nueva serie
	 * @param genero
	 *            Representa el género de la nueva serie
	 * 
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
	Serie(String id, String titulo, int annio, int numTemporadas,
			int temporada, Genero genero) throws IdNoValidoException,
			TituloNoValidoException, AnnioNoValidoException,
			TemporadaNoValidaException, GeneroNoValidoException,
			TipoNoValidoException {
		super(id, titulo, annio);
		setNumTemporadas(numTemporadas);
		setTemporada(temporada);
		setGenero(genero);
		setTipo(TipoItem.SERIE);
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
	 * Modifica el número de temporadas de la serie
	 * 
	 * @param numTemporadas
	 *            Representa el nuevo número de temporadas de la serie
	 * @throws TemporadaNoValidaException
	 *             Si el número de temporadas no es válido
	 */
	private void setNumTemporadas(int numTemporadas)
			throws TemporadaNoValidaException {
		if (numTemporadas <= 0)
			throw new TemporadaNoValidaException(
					"El número de temporadas ha de ser mayor de 0.");
		this.numTemporadas = numTemporadas;
	}

	/**
	 * Modifica la temporada de la serie
	 * 
	 * @param temporada
	 *            Representa la nueva temporada de la serie
	 * @throws TemporadaNoValidaException
	 *             Si la temporada no es válida
	 */
	private void setTemporada(int temporada) throws TemporadaNoValidaException {
	   	if (temporada < 1 || temporada > numTemporadas)
			throw new TemporadaNoValidaException("La temporada no es válida.");
		this.temporada = temporada;	
	}
	
	/**
	 * Modifica el género de la serie
	 * 
	 * @param genero
	 *            Representa el nuevo género de la serie
	 * @throws GeneroNoValidoException
	 *             Si el género no es válido
	 */
	private void setGenero(Genero genero) throws GeneroNoValidoException {
		if (genero == null)
			throw new GeneroNoValidoException("El genero no es válido.");
		this.genero = genero;
	}

	/**
	 * Devuelve la temporada de la serie
	 * 
	 * @return Temporada de la serie
	 */
	public int getTemporada() {
		return temporada;
	}

	/**
	 * Devuelve el número de temporadas de la serie
	 * 
	 * @return Número de temporadas de la serie
	 */
	public int getNumTemporadas() {
		return numTemporadas;
	}
	
	/**
	 * Devuelve el género de la serie
	 * 
	 * @return Género de la serie
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
			if (dias > 4)
				total += tipo.getPrecio() * 0.2 * (dias - 4);
			return total;
		} else if (tipo == TipoAlquiler.ESTRENO) {
			total = tipo.getPrecio();
			if (dias > 4)
				total += tipo.getPrecio() * 0.2 * (dias - 4);
			return total;
		}
		return 0;
	}
	
}