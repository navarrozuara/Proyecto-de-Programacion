/**
 * 
 */
package pgn.proyecto.videoclub;

import java.io.Serializable;
import java.util.Calendar;
import java.util.regex.Pattern;

/**
 * @author Elisa Navarro Zuara
 * @version 1.0
 */
public abstract class Producto implements Serializable, Comparable<Producto>, Calculable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Pattern patternTitulo = Pattern
			.compile("^[A-Z¡…Õ”⁄—\\d]{1,}" + "(\\s+[A-Z¡…Õ”⁄—\\d]{2,})*$");
	private Calendar fecha = Calendar.getInstance();
	private String id;
	private String titulo;
	private int annio;
	private TipoItem tipo;
	private boolean disponible;
	private int numAlquiler;

	Producto(String id, String titulo, int annio) throws IdNoValidoException,
			TituloNoValidoException, AnnioNoValidoException {
		setId(id);
		setTitulo(titulo);
		setAnnio(annio);
		setDisponible(true);
		setNumAlquiler(0);
	}
	
	private boolean esValidoTitulo(String titulo) {
		return patternTitulo.matcher(titulo).matches();
	}

	protected void setId(String id) throws IdNoValidoException {
		this.id = id;
	}

	private void setTitulo(String titulo) throws TituloNoValidoException {
		if (!esValidoTitulo(titulo))
			throw new TituloNoValidoException("El tÌtulo no es v·lido.");
		this.titulo = titulo;
	}
	
	private void setAnnio(int annio) throws AnnioNoValidoException {
		if (annio < 1900 || annio > fecha.get(Calendar.YEAR))
			throw new AnnioNoValidoException("El aÒo no es v·lido.");
		this.annio = annio;
	}
	
	protected void setTipo(TipoItem tipo) throws TipoNoValidoException {
		if (tipo == null)
			throw new TipoNoValidoException("El tipo no es v·lido.");
		this.tipo = tipo;
	}

	public String getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}
	
	public int getAnnio() {
		return annio;
	}
	
	public TipoItem getTipo() {
		return tipo;
	}

	public boolean isDisponible() {
		return disponible;
	}

	void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	int getNumAlquiler() {
		return numAlquiler;
	}

	void setNumAlquiler(int numAlquiler) {
		this.numAlquiler = numAlquiler;
	}
	
	/* (non-Javadoc)
	 * @see pgn.proyecto.videoclub.Calculable#getPrecio(int, pgn.proyecto.videoclub.TipoAlquiler)
	 */
	@Override
	public abstract float getPrecio(int dias, TipoAlquiler tipo);
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getClass().getName() + "[id=" + id + ", titulo=" + titulo
				+ ", annio=" + annio + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Producto p) {
		int estado = Integer.compare(this.annio, p.annio);
		if (estado != 0) 
			return estado;
		estado = this.titulo.compareToIgnoreCase(p.titulo);
		return estado;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}