/**
 * 
 */
package pgn.proyecto.gui;

import javax.swing.JOptionPane;

import pgn.proyecto.videoclub.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author Elisa Navarro Zuara
 * @version 1.0
 */
public class Devolver extends VideoclubGUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the dialog.
	 */
	public Devolver() {
		super();
		setTitle("Devolver alquiler");
		
		enviar.setText("Devolver");
		
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
		
		enviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Producto producto;
				Alquiler alquiler;
				try {
					producto = Gestion.getVideoclub().get(textFieldID.getText());
					alquiler = new Alquiler(producto);
					mostrarProducto(producto);
					isDisponible();
					int n = JOptionPane.showOptionDialog(null,
							"�Est� seguro de que desea devolverlo?",
							"Confirmar", JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, null, null);
					switch (n) {
					case JOptionPane.YES_OPTION:
						try {
							alquiler.devolver();
							isDisponible();
							Gestion.setModificado(true);
							clear();
							break;
						} catch (ProductoDisponibleException e) {
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