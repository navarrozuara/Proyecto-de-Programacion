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
 * Clase que contiene todos los métodos necesarios para realizar alquileres y
 * devoluciones de productos
 * 
 * @author Elisa Navarro Zuara
 * @version 1.0
 */
public class Alquiler implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Fecha actual
	 */
	private static Calendar fecha = Calendar.getInstance();
	
	/**
	 * Formato de fecha (dd/MM/yyyy)
	 */
	private static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	
	/**
	 * Producto a alquilar
	 */
	private Producto producto;
	
	/**
	 * Fecha de alquiler
	 */
	private Date fechaAlquiler;
	
	/**
	 * Fecha de devolución
	 */
	private static Date fechaDevolucion;

	/**
	 * Construye un nuevo alquiler de producto especificado
	 * 
	 * @param producto
	 *            Representa el producto del nuevo alquiler
	 */
	public Alquiler(Producto producto) {
		setProducto(producto);
		setFechaAlquiler(fecha.getTime());
	}

	/**
	 * Modifica el producto a alquilar
	 * 
	 * @param producto
	 *            Representa el nuevo producto a alquilar
	 */
	private void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	/**
	 * Modifica la fecha de alquiler
	 * 
	 * @param fechaAlquiler
	 *            Representa la nueva fecha de alquiler
	 */
	private void setFechaAlquiler(Date fechaAlquiler) {
		this.fechaAlquiler = fechaAlquiler;
	}
	
	/**
	 * Modifica la fecha de devolución
	 * 
	 * @param dias
	 *            Representa el número de dias a alquilar
	 */
	public static void setFechaDevolucion(int dias) {
		fecha.add(Calendar.DAY_OF_MONTH, dias);
		fechaDevolucion = fecha.getTime();
		fecha.setTime(new Date());
	}

	/**
	 * Devuelve el producto a alquilar
	 * 
	 * @return Producto a alquilar
	 */
	private Producto getProducto() {
		return producto;
	}
	
	/**
	 * Devuelve la fecha de alquiler
	 * 
	 * @return Fecha de alquiler
	 */
	public String getFechaAlquiler() {
		return formato.format(fechaAlquiler);
	}
	
	/**
	 * Devuelve la fecha de devolución
	 * 
	 * @return Fecha de devolución
	 */
	public static String getFechaDevolucion() {
		return formato.format(fechaDevolucion);
	}
	
	/**
	 * Alquila un producto del videoclub
	 * 
	 * @throws ProductoNoDisponibleException
	 *             Si el producto no está disponible
	 */
	public void alquilar() throws ProductoNoDisponibleException {
		if (!producto.isDisponible())
			throw new ProductoNoDisponibleException("El producto no está disponible.");
		producto.setDisponible(false);
	}
	
	/**
	 * Devuelve un producto al videoclub
	 * 
	 * @throws ProductoDisponibleException
	 *             Si el producto ya está disponible
	 */
	public void devolver() throws ProductoDisponibleException {
		if (producto.isDisponible())
			throw new ProductoDisponibleException("El producto ya está disponible.");
		producto.setDisponible(true);
	}
	
	/**
	 * Genera el recibo de alquiler de los productos
	 * 
	 * @param listaAlquileres
	 *            Representa la lista de productos alquilados
	 * @param dias
	 *            Representa el número de dias a alquilar
	 * @param tipo
	 *            Representa el tipo de alquiler
	 * @return Recibo de alquiler
	 */
	public static String generarRecibo(ArrayList<Alquiler> listaAlquileres,
			int dias, TipoAlquiler tipo) {
		String recibo;
		float total = 0f;
		Alquiler.setFechaDevolucion(dias);
		recibo = "\r\n\r\nRecibo de alquiler:";
		for (Alquiler alquiler: listaAlquileres) {
			recibo += "\r\n\r\nID: " + alquiler.getProducto().getId() + "\r\nTitulo: "
					+ alquiler.getProducto().getTitulo() + " ("
					+ alquiler.getProducto().getPrecio(dias, tipo) + " euros)\r\n";
			recibo += "Fecha alquiler: " + alquiler.getFechaAlquiler();
			recibo += "\r\nFecha devolución: " + Alquiler.getFechaDevolucion();
			total += alquiler.getProducto().getPrecio(dias, tipo);
		}
		recibo += "\r\n\r\nIMPORTE TOTAL = " + total + " euros\r\n";
		return recibo;
	}

}