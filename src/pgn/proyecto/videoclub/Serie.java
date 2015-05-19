/**
 * 
 */
package pgn.proyecto.videoclub;

import java.util.regex.Pattern;

/**
 * Una serie tendr� las siguientes caracter�sticas propias:
 * <ul>
 * <li>Temporada. Temporada actual de la serie.</li>
 * <li>N�mero de temporadas. N�mero de temporadas de la serie, debe de ser mayor
 * de cero.</li>
 * <li>G�nero. Se limitar�n los g�neros a diez: acci�n, animaci�n, aventura,
 * comedia, drama, espa�ola, sci-fi, terror, thriller y western.</li>
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
	 * Patr�n para un identificador v�lido
	 */
	private static final Pattern patternID = Pattern.compile("^\\d{4}(SER)$");
	
	/**
	 * Temporada de la serie
	 */
	private int temporada;
	
	/**
	 * N�mero de temporadas de la serie
	 */
	private int numTemporadas;
	
	/**
	 * G�nero de la serie
	 */
	private Genero genero;
	
	/**
	 * Construye una nueva serie de identificador, t�tulo, a�o, n�mero de
	 * temporadas, temporada y g�nero especificado
	 * 
	 * @param id
	 *            Representa el identificador de la nueva serie
	 * @param titulo
	 *            Representa el t�tulo de la nueva serie
	 * @param annio
	 *            Representa el a�o de estreno de la nueva serie
	 * @param numTemporadas
	 *            Representa el n�mero de temporadas de la nueva serie
	 * @param temporada
	 *            Representa la temporada de la nueva serie
	 * @param genero
	 *            Representa el g�nero de la nueva serie
	 * 
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
	 * Modifica el n�mero de temporadas de la serie
	 * 
	 * @param numTemporadas
	 *            Representa el nuevo n�mero de temporadas de la serie
	 * @throws TemporadaNoValidaException
	 *             Si el n�mero de temporadas no es v�lido
	 */
	private void setNumTemporadas(int numTemporadas)
			throws TemporadaNoValidaException {
		if (numTemporadas <= 0)
			throw new TemporadaNoValidaException(
					"El n�mero de temporadas ha de ser mayor de 0.");
		this.numTemporadas = numTemporadas;
	}

	/**
	 * Modifica la temporada de la serie
	 * 
	 * @param temporada
	 *            Representa la nueva temporada de la serie
	 * @throws TemporadaNoValidaException
	 *             Si la temporada no es v�lida
	 */
	private void setTemporada(int temporada) throws TemporadaNoValidaException {
	   	if (temporada < 1 || temporada > numTemporadas)
			throw new TemporadaNoValidaException("La temporada no es v�lida.");
		this.temporada = temporada;	
	}
	
	/**
	 * Modifica el g�nero de la serie
	 * 
	 * @param genero
	 *            Representa el nuevo g�nero de la serie
	 * @throws GeneroNoValidoException
	 *             Si el g�nero no es v�lido
	 */
	private void setGenero(Genero genero) throws GeneroNoValidoException {
		if (genero == null)
			throw new GeneroNoValidoException("El genero no es v�lido.");
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
	 * Devuelve el n�mero de temporadas de la serie
	 * 
	 * @return N�mero de temporadas de la serie
	 */
	public int getNumTemporadas() {
		return numTemporadas;
	}
	
	/**
	 * Devuelve el g�nero de la serie
	 * 
	 * @return G�nero de la serie
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