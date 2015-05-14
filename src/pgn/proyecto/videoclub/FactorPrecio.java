/**
 * 
 */
package pgn.proyecto.videoclub;

/**
 * @author Elisa Navarro Zuara
 * @version 1.0
 */
public enum FactorPrecio {
	
	NORMAL(2.50f),
	ESTRENO(4.50f);
	
	private float precio;
	
	FactorPrecio(float precio) {
		setPrecio(precio);
	}
	
	private void setPrecio(float precio) {
		this.precio = precio;
	}
	
	public float getPrecio() {
		return precio;
	}

}