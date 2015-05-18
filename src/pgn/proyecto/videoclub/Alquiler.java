/**
 * 
 */
package pgn.proyecto.videoclub;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Elisa Navarro Zuara
 * @version 1.0
 */
public class Alquiler implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Calendar fecha = Calendar.getInstance();
	private static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	private Producto producto;
	private Date fechaAlquiler;

	public Alquiler(Producto producto) {
		setProducto(producto);
		setFechaAlquiler(fecha.getTime());
	}

	private void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	private void setFechaAlquiler(Date fechaAlquiler) {
		this.fechaAlquiler = fechaAlquiler;
	}

	private Producto getProducto() {
		return producto;
	}
	
	public String getFechaAlquiler() {
		return formato.format(fechaAlquiler);
	}
	
	public void alquilar() throws ProductoNoDisponibleException {
		if (!producto.isDisponible())
			throw new ProductoNoDisponibleException("El producto no est� disponible.");
		producto.setDisponible(false);
		producto.setNumAlquiler(producto.getNumAlquiler() + 1);
	}
	
	public void devolver() throws ProductoDisponibleException {
		if (producto.isDisponible())
			throw new ProductoDisponibleException("El producto ya est� disponible.");
		producto.setDisponible(true);
	}
	
	public static String mostrarRecibo(ArrayList<Alquiler> listaAlquileres, int dias, TipoAlquiler tipo) {
		String recibo;
		float total = 0f;
		Gestion.setFechaDevolucion(dias);
		recibo = "\r\n\r\nRecibo de alquiler:";
		for (Alquiler alquiler: listaAlquileres) {
			recibo += "\r\n\r\nID: " + alquiler.getProducto().getId() + "\r\nTitulo: "
					+ alquiler.getProducto().getTitulo() + " ("
					+ alquiler.getProducto().getPrecio(dias, tipo) + " euros)\r\n";
			recibo += "Fecha alquiler: " + alquiler.getFechaAlquiler();
			recibo += "\r\nFecha devoluci�n: " + Gestion.getFechaDevolucion();
			total += alquiler.getProducto().getPrecio(dias, tipo);
		}
		recibo += "\r\n\r\nIMPORTE TOTAL = " + total + " euros\r\n";
		return recibo;
	}

}