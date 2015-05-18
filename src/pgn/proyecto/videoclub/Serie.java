/**
 * 
 */
package pgn.proyecto.videoclub;

import java.util.regex.Pattern;

/**
 * @author Elisa Navarro Zuara
 * @version 1.0
 */
public class Serie extends Producto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Pattern patternID = Pattern.compile("^\\d{4}(SER)$");
	private int temporada;
	private int numTemporadas;
	private Genero genero;
	
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
	
	private boolean esValidoID(String id) {
		return patternID.matcher(id).matches();
	}
	
	@Override
	protected void setId(String id) throws IdNoValidoException {
		if (!esValidoID(id))
			throw new IdNoValidoException("El id no es válido.");
		super.setId(id);
	}
	
	private void setNumTemporadas(int numTemporadas)
			throws TemporadaNoValidaException {
		if (numTemporadas <= 0)
			throw new TemporadaNoValidaException(
					"El número de temporadas ha de ser mayor de 0.");
		this.numTemporadas = numTemporadas;
	}

	private void setTemporada(int temporada) throws TemporadaNoValidaException {
	   	if (temporada < 1 || temporada > numTemporadas)
			throw new TemporadaNoValidaException("La temporada no es válida.");
		this.temporada = temporada;	
	}
	
	private void setGenero(Genero genero) throws GeneroNoValidoException {
		if (genero == null)
			throw new GeneroNoValidoException("El genero no es válido.");
		this.genero = genero;
	}

	public int getTemporada() {
		return temporada;
	}

	public int getNumTemporadas() {
		return numTemporadas;
	}
	
	public Genero getGenero() {
		return genero;
	}

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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + "[temporada=" + temporada + ", numTemporadas="
				+ numTemporadas + ", genero=" + genero + "]";
	}
	
}