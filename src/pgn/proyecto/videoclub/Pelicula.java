/**
 * 
 */
package pgn.proyecto.videoclub;

import java.util.regex.Pattern;

/**
 * @author Elisa Navarro Zuara
 * @version 1.0
 */
public class Pelicula extends Producto {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Pattern patternID = Pattern.compile("^\\d{4}(PEL)$");
	private String director;
	private Genero genero;
	
	Pelicula(String id, String titulo, int annio, String director, Genero genero)
			throws IdNoValidoException, TituloNoValidoException,
			AnnioNoValidoException, GeneroNoValidoException,
			TipoNoValidoException {
		super(id, titulo, annio);
		setDirector(director);
		setGenero(genero);
		setTipo(TipoItem.PELICULA);
	}
	
	private static boolean esValidoID(String id) {
		return patternID.matcher(id).matches();
	}
	
	@Override
	protected void setId(String id) throws IdNoValidoException {
		if (!esValidoID(id))
			throw new IdNoValidoException("El id no es válido.");
		super.setId(id);
	}
	
	private void setDirector(String director) {
		this.director = director;
	}
	
	private void setGenero(Genero genero) throws GeneroNoValidoException {
		if (genero == null)
			throw new GeneroNoValidoException("El genero no es válido.");
		this.genero = genero;
	}
	
	public String getDirector() {
		return director;
	}

	public Genero getGenero() {
		return genero;
	}

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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + "[director=" + director + ", genero=" + genero + "]";
	}
	
}