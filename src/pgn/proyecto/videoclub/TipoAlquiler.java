/**
 * 
 */
package pgn.proyecto.videoclub;

/**
 * @author Elisa Navarro Zuara
 * @version 1.0
 */
public enum TipoAlquiler {
	
	NORMAL(2.50f),
	ESTRENO(3.50f);
	
	private float precio;
	
	private TipoAlquiler(float precio) {
		setPrecio(precio);
	}
	
	private void setPrecio(float precio) {
		this.precio = precio;
	}
	
	public float getPrecio() {
		return precio;
	}

}