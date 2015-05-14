/**
 * 
 */
package pgn.proyecto.videoclub;

/**
 * @author Elisa Navarro Zuara
 * @version 1.0
 */
public class ProductoNoExisteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductoNoExisteException(String string) {
		super(string);
	}

}