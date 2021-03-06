/**
 * 
 */
package pgn.proyecto.gui;

import javax.swing.JOptionPane;

import pgn.proyecto.videoclub.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

/**
 * @author Elisa Navarro Zuara
 * @version 1.0
 */
public class Alquilar extends VideoclubGUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Colecci�n de alquileres
	 */
	private static ArrayList<Alquiler> listaAlquileres = new ArrayList<Alquiler>();
	private Date fechaDevolucion;
	private JTextField textFieldAlquiler;
	private JTextField textFieldDevolucion;
	private JButton recibo;

	/**
	 * Create the dialog.
	 */
	public Alquilar() {
		super();
		setTitle("Registrar alquiler");
		setBounds(100, 100, 458, 258);
		
		panel1.setVisible(true);
		
		enviar.setText("Alquilar");
		
		recibo = new JButton("Recibo");
		buttonPane.add(recibo, 0);
		
		textFieldTitulo.setEditable(false);
		textFieldAutor.setEditable(false);
		textFieldTemporada.setEditable(false);
		textFieldNumTemporadas.setEditable(false);
		textFieldAnnio.setEditable(false);
		
		comboBoxGenero.setEnabled(false);
		comboBoxGeneroMusical.setEnabled(false);
		
		rdbtnPelicula.setEnabled(false);
		rdbtnSerie.setEnabled(false);
		rdbtnMusica.setEnabled(false);
		
		JLabel lblFechaAlquiler = new JLabel("Fecha alquiler");
		lblFechaAlquiler.setBounds(29, 160, 83, 14);
		contentPanel.add(lblFechaAlquiler);
		
		textFieldAlquiler = new JTextField();
		textFieldAlquiler.setEditable(false);
		textFieldAlquiler.setBounds(114, 157, 86, 20);
		contentPanel.add(textFieldAlquiler);
		textFieldAlquiler.setColumns(10);
		
		JLabel lblFechaDevolucion = new JLabel("Fecha devoluci\u00F3n");
		lblFechaDevolucion.setBounds(219, 160, 99, 14);
		contentPanel.add(lblFechaDevolucion);
		
		textFieldDevolucion = new JTextField();
		textFieldDevolucion.setBounds(328, 157, 86, 20);
		contentPanel.add(textFieldDevolucion);
		textFieldDevolucion.setColumns(10);
		
		panel1.setSize(88, 70);
		panel1.setLocation(354, 25);
		rdbtnEstreno.setSize(74, 23);
		rdbtnEstreno.setLocation(8, 42);
		
		enviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Producto producto;
				Alquiler alquilar;
				if (textFieldDevolucion.getText() == "" || !esValidaFecha(textFieldDevolucion.getText())) {
					JOptionPane.showMessageDialog(contentPanel,
							"La fecha no es v�lida.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (fechaDevolucion.before(new Date())) {
					JOptionPane.showMessageDialog(contentPanel,
							"La fecha ha de ser posterior a la actual.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					producto = Gestion.getVideoclub().get(textFieldID.getText());
					alquilar = new Alquiler(producto);
					producto.setFechaAlquiler(new Date());
					producto.setFechaDevolucion(fechaDevolucion);
					mostrarProducto(producto);
					isDisponible();
					alquilar.setFechaDevolucion(fechaDevolucion);
					textFieldAlquiler.setText(alquilar.getFechaAlquiler());
					if (!producto.isDisponible()) {
						JOptionPane.showMessageDialog(contentPanel,
								"El producto no est� disponible.", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					int n = JOptionPane.showOptionDialog(null,
							"�Est� seguro de que desea alquilarlo?",
							"Confirmar", JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, null, null);
					switch (n) {
					case JOptionPane.YES_OPTION:
						try {
							alquilar.alquilar();
							listaAlquileres.add(alquilar);
							isDisponible();
							Gestion.setModificado(true);
							clear();
							break;
						} catch (ProductoNoDisponibleException e) {
							JOptionPane.showMessageDialog(contentPanel,
									e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						}
					case JOptionPane.NO_OPTION:
						return;
					case JOptionPane.CANCEL_OPTION:
						return;
					}
				} catch (ProductoNoExisteException e) {
					JOptionPane.showMessageDialog(contentPanel, e.getMessage(),
							"Error", JOptionPane.ERROR_MESSAGE);
				} 
			}
		});
		
		recibo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (listaAlquileres.isEmpty()) {
					JOptionPane.showMessageDialog(contentPanel,
							"No se ha podido crear el recibo.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				int dias = Integer.parseInt(textFieldDevolucion.getText().substring(0, 2))
						- Integer.parseInt(textFieldAlquiler.getText().substring(0, 2));
				Recibo recibo = new Recibo(Alquiler.generarRecibo(listaAlquileres, dias, getPrecio()));
				recibo.setVisible(true);
			}
		});
		
		salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listaAlquileres.clear();
			}
		});
	}

	/**
	 * Comprueba si la fecha introducida es v�lida o no
	 * 
	 * @param fecha
	 *            Representa la fecha a validar
	 * @return true si la fecha es v�lida, false si la fecha no es v�lida
	 */
	private boolean esValidaFecha(String fecha) {
		try {
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			formato.setLenient(false);
			fechaDevolucion = formato.parse(fecha);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	/**
	 * Devuelve el precio del tipo de alquiler seleccionado
	 * 
	 * @return Precio del tipo de alquiler seleccionado
	 */
	private TipoAlquiler getPrecio() {
		if (rdbtnNormal.isSelected())
			return TipoAlquiler.NORMAL;
		else if (rdbtnEstreno.isSelected())
			return TipoAlquiler.ESTRENO;
		else
			return null;
	}

	/**
	 * Limpia el contenido del di�logo
	 */
	private void clear() {
		textFieldID.setText("");
		textFieldTitulo.setText("");
		textFieldAutor.setText("");
		textFieldTemporada.setText("");
		textFieldNumTemporadas.setText("");
		textFieldAnnio.setText("");
		buttonGroup.clearSelection();
		comboBoxGenero.setSelectedItem(null);
		comboBoxGeneroMusical.setSelectedItem(null);
	}
	
	/**
	 * Muestra las caracter�sticas de un producto
	 * 
	 * @param producto
	 *            Representa el producto a mostrar
	 */
	private void mostrarProducto(Producto producto) {
		if (producto instanceof Pelicula) {
			Pelicula pelicula = (Pelicula) producto;
			textFieldTitulo.setText(pelicula.getTitulo());
			textFieldAutor.setText(pelicula.getDirector());
			textFieldAnnio.setText(String.valueOf(pelicula.getAnnio()));
			rdbtnPelicula.setSelected(true);
			comboBoxGenero.addItem(pelicula.getGenero());
			comboBoxGenero.setSelectedItem(pelicula.getGenero());
		} else if (producto instanceof Serie) {
			Serie serie = (Serie) producto;
			textFieldTitulo.setText(serie.getTitulo());
			textFieldTemporada.setText(String.valueOf(serie.getTemporada()));
			textFieldNumTemporadas.setText(String.valueOf(serie.getNumTemporadas()));
			textFieldAnnio.setText(String.valueOf(serie.getAnnio()));
			rdbtnSerie.setSelected(true);
			comboBoxGenero.addItem(serie.getGenero());
			comboBoxGenero.setSelectedItem(serie.getGenero());
		} else {
			Musica musica = (Musica) producto;
			textFieldTitulo.setText(musica.getTitulo());
			textFieldAutor.setText(musica.getInterprete());
			textFieldAnnio.setText(String.valueOf(musica.getAnnio()));
			rdbtnMusica.setSelected(true);
			comboBoxGeneroMusical.addItem(musica.getGenero());
			comboBoxGeneroMusical.setSelectedItem(musica.getGenero());
		}
	}
	
}