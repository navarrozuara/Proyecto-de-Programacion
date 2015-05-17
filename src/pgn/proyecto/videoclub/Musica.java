/**
 * 
 */
package pgn.proyecto.videoclub;

import java.util.regex.Pattern;

/**
 * @author Elisa Navarro Zuara
 * @version 1.0
 */
public class Musica extends Producto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Pattern patternID = Pattern.compile("^\\d{4}(MUS)$");
	private String interprete;
	private GeneroMusical genero;
	
	Musica(String id, String titulo, int annio, String interprete,
			GeneroMusical genero) throws IdNoValidoException,
			TituloNoValidoException, AnnioNoValidoException,
			GeneroNoValidoException, TipoNoValidoException {
		super(id, titulo, annio);
		setInterprete(interprete);
		setGenero(genero);
		setTipo(TipoItem.MUSICA);
	}
	
	public static boolean esValidoID(String id) {
		return patternID.matcher(id).matches();
	}
	
	@Override
	protected void setId(String id) throws IdNoValidoException {
		if (!esValidoID(id))
			throw new IdNoValidoException("El id no es válido.");
		super.setId(id);
	}

	private void setInterprete(String interprete) {
		this.interprete = interprete;
	}
	
	private void setGenero(GeneroMusical genero) throws GeneroNoValidoException {
		if (genero == null)
			throw new GeneroNoValidoException("El genero no es válido.");
		this.genero = genero;
	}
	
	public String getInterprete() {
		return interprete;
	}

	public GeneroMusical getGenero() {
		return genero;
	}

	@Override
	public float getPrecio(int dias, FactorPrecio factor) {
		float total;
		if (factor == FactorPrecio.NORMAL) {
			total = factor.getPrecio();
			if (dias > 3)
				total += factor.getPrecio() * 0.2 * (dias - 3);
			return total;
		} else if (factor == FactorPrecio.ESTRENO) {
			total = factor.getPrecio();
			if (dias > 3)
				total += factor.getPrecio() * 0.2 * (dias - 3);
			return total;
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + "[interprete=" + interprete + ", genero=" + genero + "]";
	}

}