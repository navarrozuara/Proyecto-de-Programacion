/**
 * 
 */
package pgn.proyecto.videoclub;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Elisa Navarro Zuara
 * @version 1.0
 */
public class Alquiler {

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
			throw new ProductoNoDisponibleException("El producto no está disponible.");
		producto.setDisponible(false);
		producto.setNumAlquiler(producto.getNumAlquiler() + 1);
	}
	
	public void devolver() throws ProductoDisponibleException {
		if (producto.isDisponible())
			throw new ProductoDisponibleException("El producto ya está disponible.");
		producto.setDisponible(true);
	}
	
	public static String mostrarRecibo(ArrayList<Alquiler> listaAlquileres, int dias, FactorPrecio factor) {
		String recibo;
		float total = 0f;
		Gestion.setFechaDevolucion(dias);
		recibo = "<H2>Recibo de alquiler:</H2>\n";
		for (Alquiler alquiler: listaAlquileres) {
			recibo += "ID: " + alquiler.getProducto().getId() + "<br>Titulo: "
					+ alquiler.getProducto().getTitulo() + " ("
					+ alquiler.getProducto().getPrecio(dias, factor) + " €)<br>";
			recibo += "Fecha alquiler: " + alquiler.getFechaAlquiler() + "<br>";
			recibo += "Fecha devolución: " + Gestion.getFechaDevolucion() + "<br><br>";
			total += alquiler.getProducto().getPrecio(dias, factor);
		}
		recibo += "<P>IMPORTE TOTAL = " + "<B>" + total + " €</B>\n";
		return recibo;
	}

}