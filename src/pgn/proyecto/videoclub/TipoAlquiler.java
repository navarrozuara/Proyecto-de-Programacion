/**
 * 
 */
package pgn.proyecto.videoclub;

/**
 * Representa cada uno de los tipos de alquiler de los productos
 * 
 * @author Elisa Navarro Zuara
 * @version 1.0
 */
public enum TipoAlquiler {
	
	/**
	 * Representa el tipo de alquiler normal
	 */
	NORMAL(2.50f),
	
	/**
	 * Representa el tipo de alquiler de estreno
	 */
	ESTRENO(3.50f);
	
	/**
	 * Precio del alquiler
	 */
	private float precio;
	
	/**
	 * Construye un nuevo tipo de alquiler de precio especificado
	 * 
	 * @param precio
	 *            Representa el precio del nuevo tipo de alquiler
	 */
	private TipoAlquiler(float precio) {
		this.precio = precio;
	}
	
	/**
	 * Devuelve el precio del tipo de alquiler
	 * 
	 * @return Precio del tipo de alquiler
	 */
	public float getPrecio() {
		return precio;
	}

}