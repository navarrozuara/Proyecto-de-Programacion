/**
 * 
 */
package pgn.proyecto.videoclub;

import java.util.regex.Pattern;

/**
 * Una m�sica tendr� las siguientes caracter�sticas propias:
 * <ul>
 * <li>Int�rprete. El int�rprete debe estar en may�scula y debe de tener, al
 * menos, tres caracteres v�lidos seguidos.</li>
 * <li>G�nero. Se limitar�n los g�neros musicales a nueve: pop nacional, pop
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
	 * Patr�n para un identificador v�lido
	 */
	private static final Pattern patternID = Pattern.compile("^\\d{4}(MUS)$");
	
	/**
	 * Patr�n para un int�rprete v�lido
	 */
	private static final Pattern patternInterprete = Pattern
			.compile("^[A-Z������]{2,}" + "(\\s+[A-Z������]{2,})*$");
	
	/**
	 * Int�rprete de la m�sica
	 */
	private String interprete;
	
	/**
	 * G�nero de la m�sica
	 */
	private GeneroMusical genero;
	
	/**
	 * Construye una nueva m�sica de identificador, t�tulo, a�o, int�rprete y
	 * g�nero especificado
	 * 
	 * @param id
	 *            Representa el identificador de la nueva m�sica
	 * @param titulo
	 *            Representa el t�tulo de la nueva m�sica
	 * @param annio
	 *            Representa el a�o de estreno de la nueva m�sica
	 * @param interprete
	 *            Representa el int�rprete de la nueva m�sica
	 * @param genero
	 *            Representa el g�nero de la nueva m�sica
	 * 
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
	 * Comprueba si el int�rprete de la m�sica es v�lido o no
	 * 
	 * @param interprete
	 *            Representa el int�rprete a validar
	 * @return true si el int�rprete es v�lido, false si el int�rprete no es
	 *         v�lido
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
	 *             Si el identificador no es v�lido
	 */
	@Override
	protected void setId(String id) throws IdNoValidoException {
		if (!esValidoID(id))
			throw new IdNoValidoException("El id no es v�lido.");
		super.setId(id);
	}

	/**
	 * Modifica el int�rprete de la m�sica
	 * 
	 * @param interprete
	 *            Representa el nuevo int�rprete de la m�sica
	 * @throws AutorNoValidoException
	 *             Si el int�rprete no es v�lido
	 */
	private void setInterprete(String interprete) throws AutorNoValidoException {
		if (!esValidoInterprete(interprete))
			throw new AutorNoValidoException("El interprete no es v�lido.");
		this.interprete = interprete;
	}
	
	/**
	 * Modifica el g�nero de la m�sica
	 * 
	 * @param genero
	 *            Representa el nuevo g�nero de la m�sica
	 * @throws GeneroNoValidoException
	 *             Si el g�nero no es v�lido
	 */
	private void setGenero(GeneroMusical genero) throws GeneroNoValidoException {
		if (genero == null)
			throw new GeneroNoValidoException("El genero no es v�lido.");
		this.genero = genero;
	}
	
	/**
	 * Devuelve el int�rprete de la m�sica
	 * 
	 * @return Int�rprete de la m�sica
	 */
	public String getInterprete() {
		return interprete;
	}

	/**
	 * Devuelve el g�nero de la m�sica
	 * 
	 * @return G�nero de la m�sica
	 */
	public GeneroMusical getGenero() {
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