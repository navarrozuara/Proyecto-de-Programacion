/**
 * 
 */
package pgn.proyecto.videoclub;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Un producto tendrá las siguientes características:
 * <ul>
 * <li>Identificador (único por producto). El identificador constará de cuatro
 * dígitos y un grupo de tres caracteres.
 * <ul>
 * <li>PEL, si el producto es una película.</li>
 * <li>SER, si el producto es una serie.</li>
 * <li>MUS, si el producto es una música.</li>
 * </ul>
 * </li>
 * <li>Título. El título debe estar en mayúscula y debe de tener, al menos, tres
 * caracteres.</li>
 * <li>Año. Un año válido comprendido entre 1900 y el año actual.</li>
 * </ul>
 * 
 * @author Elisa Navarro Zuara
 * @version 1.0
 */
public abstract class Producto implements Serializable, Comparable<Producto>, Calculable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Patrón para un título válido
	 */
	private static final Pattern patternTitulo = Pattern
			.compile("(?i)^[a-záéíóúÁÉÍÓÚñÑ0-9]{1,}" + "(\\s+[a-záéíóúÁÉÍÓÚñÑ]{2,})*$");
	
	/**
	 * Fecha actual
	 */
	private Calendar fecha = Calendar.getInstance();
	
	/**
	 * Identificador del producto
	 */
	private String id;
	
	/**
	 * Título del producto
	 */
	private String titulo;
	
	/**
	 * Año de estreno del producto
	 */
	private int annio;
	
	/**
	 * Tipo de producto
	 */
	private TipoItem tipo;
	
	/**
	 * Estado del producto
	 */
	private boolean disponible;
	
	/**
	 * Formato de fecha (dd/MM/yyyy)
	 */
	private static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	
	/**
	 * Fecha de alquiler del producto
	 */
	private Date fechaAlquiler;
	
	/**
	 * Fecha de devolución del producto
	 */
	private Date fechaDevolucion;

	/**
	 * Construye un nuevo producto de identificador, título y año especificado
	 * 
	 * @param id
	 *            Representa el identificador del nuevo producto
	 * @param titulo
	 *            Representa el título del nuevo producto
	 * @param annio
	 *            Representa el año de estreno del nuevo producto
	 * 
	 * @throws IdNoValidoException
	 *             Si el identificador no es válido
	 * @throws TituloNoValidoException
	 *             Si el título no es válido
	 * @throws AnnioNoValidoException
	 *             Si el año de estreno no es válido
	 */
	Producto(String id, String titulo, int annio) throws IdNoValidoException,
			TituloNoValidoException, AnnioNoValidoException {
		super();
		setId(id);
		setTitulo(titulo);
		setAnnio(annio);
		setDisponible(true);
	}
	
	/**
	 * Comprueba si el título del producto es válido o no
	 * 
	 * @param titulo
	 *            Representa el título a validar
	 * @return true si el título es válido, false si el título no es válido
	 */
	private boolean esValidoTitulo(String titulo) {
		return patternTitulo.matcher(titulo).matches();
	}

	/**
	 * Modifica el identificador del producto
	 * 
	 * @param id
	 *            Representa el nuevo identificador del producto
	 * @throws IdNoValidoException
	 *             Si el identificador no es válido
	 */
	protected void setId(String id) throws IdNoValidoException {
		this.id = id;
	}

	/**
	 * Modifica el título del producto
	 * 
	 * @param titulo
	 *            Representa el nuevo título del producto
	 * @throws TituloNoValidoException
	 *             Si el título no es válido
	 */
	private void setTitulo(String titulo) throws TituloNoValidoException {
		if (!esValidoTitulo(titulo))
			throw new TituloNoValidoException("El título no es válido.");
		this.titulo = titulo;
	}
	
	/**
	 * Modifica el año de estreno del producto
	 * 
	 * @param annio
	 *            Representa el nuevo año de estreno del producto
	 * @throws AnnioNoValidoException
	 *             Si el año de estreno no es válido
	 */
	private void setAnnio(int annio) throws AnnioNoValidoException {
		if (annio < 1900 || annio > fecha.get(Calendar.YEAR))
			throw new AnnioNoValidoException("El año no es válido.");
		this.annio = annio;
	}
	
	/**
	 * Modifica el tipo de producto
	 * 
	 * @param tipo
	 *            Representa el nuevo tipo de producto
	 * @throws TipoNoValidoException
	 *             Si el tipo de producto no es válido
	 */
	protected void setTipo(TipoItem tipo) throws TipoNoValidoException {
		if (tipo == null)
			throw new TipoNoValidoException("El tipo no es válido.");
		this.tipo = tipo;
	}

	/**
	 * Devuelve el identificador del producto
	 * 
	 * @return Identificador del producto
	 */
	public String getId() {
		return id;
	}

	/**
	 * Devuelve el título del producto
	 * 
	 * @return Título del producto
	 */
	public String getTitulo() {
		return titulo;
	}
	
	/**
	 * Devuelve el año de estreno del producto
	 * 
	 * @return Año de estreno del producto
	 */
	public int getAnnio() {
		return annio;
	}
	
	/**
	 * Devuelve el tipo de producto
	 * 
	 * @return Tipo de producto
	 */
	public TipoItem getTipo() {
		return tipo;
	}

	/**
	 * Devuelve el estado del producto
	 * 
	 * @return Estado del producto
	 */
	public boolean isDisponible() {
		return disponible;
	}

	/**
	 * Modifica el estado del producto
	 * 
	 * @param disponible
	 *            Representa el nuevo estado del producto
	 */
	void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	
	/**
	 * Devuelve la fecha de alquiler del producto
	 * 
	 * @return Fecha de alquiler del producto
	 */
	public String getFechaAlquiler() {
		return formato.format(fechaAlquiler);
	}

	/**
	 * Modifica la fecha de alquiler del producto
	 * 
	 * @param fechaAlquiler
	 *            Representa la nueva fecha de alquiler del producto
	 */
	public void setFechaAlquiler(Date fechaAlquiler) {
		this.fechaAlquiler = fechaAlquiler;
	}

	/**
	 * Devuelve la fecha de devolución del producto
	 * 
	 * @return Fecha de devolución del producto
	 */
	public String getFechaDevolucion() {
		return formato.format(fechaDevolucion);
	}

	/**
	 * Modifica la fecha de devolución del producto
	 * 
	 * @param fechaDevolucion
	 *            Representa la nueva fecha de devolución del producto
	 */
	public void setFechaDevolucion(Date fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
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
	public abstract float getPrecio(int dias, TipoAlquiler tipo);

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