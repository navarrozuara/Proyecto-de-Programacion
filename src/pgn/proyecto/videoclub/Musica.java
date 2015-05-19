/**
 * 
 */
package pgn.proyecto.videoclub;

import java.util.regex.Pattern;

/**
 * Una música tendrá las siguientes características propias:
 * <ul>
 * <li>Intérprete. El intérprete debe estar en mayúscula y debe de tener, al
 * menos, tres caracteres válidos seguidos.</li>
 * <li>Género. Se limitarán los géneros musicales a nueve: pop nacional, pop
 * internacional, flamenco, rock, heavy metal, rap, reggaeton, salsa y techno.</li>
 * </ul>
 * 
 * @author Elisa Navarro Zuara
 * @version 1.0
 */
public class Musica extends Producto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Patrón para un identificador válido
	 */
	private static final Pattern patternID = Pattern.compile("^\\d{4}(MUS)$");
	
	/**
	 * Patrón para un intérprete válido
	 */
	private static final Pattern patternInterprete = Pattern
			.compile("^[A-ZÁÉÍÓÚÑ]{2,}" + "(\\s+[A-ZÁÉÍÓÚÑ]{2,})*$");
	
	/**
	 * Intérprete de la música
	 */
	private String interprete;
	
	/**
	 * Género de la música
	 */
	private GeneroMusical genero;
	
	/**
	 * Construye una nueva música de identificador, título, año, intérprete y
	 * género especificado
	 * 
	 * @param id
	 *            Representa el identificador de la nueva música
	 * @param titulo
	 *            Representa el título de la nueva música
	 * @param annio
	 *            Representa el año de estreno de la nueva música
	 * @param interprete
	 *            Representa el intérprete de la nueva música
	 * @param genero
	 *            Representa el género de la nueva música
	 * 
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
	Musica(String id, String titulo, int annio, String interprete,
			GeneroMusical genero) throws IdNoValidoException,
			TituloNoValidoException, AnnioNoValidoException,
			AutorNoValidoException, GeneroNoValidoException,
			TipoNoValidoException {
		super(id, titulo, annio);
		setInterprete(interprete);
		setGenero(genero);
		setTipo(TipoItem.MUSICA);
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
	 * Comprueba si el intérprete de la música es válido o no
	 * 
	 * @param interprete
	 *            Representa el intérprete a validar
	 * @return true si el intérprete es válido, false si el intérprete no es
	 *         válido
	 */
	private boolean esValidoInterprete(String interprete) {
		return patternInterprete.matcher(interprete).matches();
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
	 * Modifica el intérprete de la música
	 * 
	 * @param interprete
	 *            Representa el nuevo intérprete de la música
	 * @throws AutorNoValidoException
	 *             Si el intérprete no es válido
	 */
	private void setInterprete(String interprete) throws AutorNoValidoException {
		if (!esValidoInterprete(interprete))
			throw new AutorNoValidoException("El interprete no es válido.");
		this.interprete = interprete;
	}
	
	/**
	 * Modifica el género de la música
	 * 
	 * @param genero
	 *            Representa el nuevo género de la música
	 * @throws GeneroNoValidoException
	 *             Si el género no es válido
	 */
	private void setGenero(GeneroMusical genero) throws GeneroNoValidoException {
		if (genero == null)
			throw new GeneroNoValidoException("El genero no es válido.");
		this.genero = genero;
	}
	
	/**
	 * Devuelve el intérprete de la música
	 * 
	 * @return Intérprete de la música
	 */
	public String getInterprete() {
		return interprete;
	}

	/**
	 * Devuelve el género de la música
	 * 
	 * @return Género de la música
	 */
	public GeneroMusical getGenero() {
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
			if (dias > 3)
				total += tipo.getPrecio() * 0.2 * (dias - 3);
			return total;
		} else if (tipo == TipoAlquiler.ESTRENO) {
			total = tipo.getPrecio();
			if (dias > 3)
				total += tipo.getPrecio() * 0.2 * (dias - 3);
			return total;
		}
		return 0;
	}
	
}