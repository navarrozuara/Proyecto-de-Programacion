/**
 * 
 */
package pgn.proyecto.videoclub;

/**
 * @author Elisa Navarro Zuara
 * @version 1.0
 */
public interface Calculable {

	/**
	 * Calcula el precio del producto
	 * 
	 * @param dias
	 *            Representa el número de dias a alquilar
	 * @param tipo
	 *            Representa el tipo de alquiler del producto
	 * 
	 * @return Precio del producto
	 */
	float getPrecio(int dias, TipoAlquiler tipo);

}