/**
 * 
 */
package pgn.proyecto.gui;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import pgn.proyecto.videoclub.*;

/**
 * @author Elisa Navarro Zuara
 * @version 1.0
 */
public class MostrarPorTitulo extends VideoclubGUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the dialog.
	 */
	public MostrarPorTitulo() {
		super();
		setTitle("Mostrar por titulo");
		
		enviar.setText("Buscar");
		
		textFieldID.setEditable(false);
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
			public void actionPerformed(ActionEvent e) {
				Producto producto;
				try {
					producto = Gestion.getVideoclub().getProducto(textFieldTitulo.getText());
					mostrarProducto(producto);
					comprobarProducto();
				} catch (ProductoNoExisteException e1) {
					JOptionPane.showMessageDialog(contentPanel,
							e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
	
	private void mostrarProducto(Producto producto) {
		if (producto instanceof Pelicula) {
			Pelicula pelicula = (Pelicula) producto;
			textFieldID.setText(pelicula.getId());
			textFieldAutor.setText(pelicula.getDirector());
			textFieldAnnio.setText(String.valueOf(pelicula.getAnnio()));
			rdbtnPelicula.setSelected(true);
			comboBoxGenero.addItem(pelicula.getGenero());
			comboBoxGenero.setSelectedItem(pelicula.getGenero());
		} else if (producto instanceof Serie) {
			Serie serie = (Serie) producto;
			textFieldID.setText(serie.getId());
			textFieldTemporada.setText(String.valueOf(serie.getTemporada()));
			textFieldNumTemporadas.setText(String.valueOf(serie.getNumTemporadas()));
			textFieldAnnio.setText(String.valueOf(serie.getAnnio()));
			rdbtnSerie.setSelected(true);
			comboBoxGenero.addItem(serie.getGenero());
			comboBoxGenero.setSelectedItem(serie.getGenero());
		} else {
			Musica musica = (Musica) producto;
			textFieldID.setText(musica.getId());
			textFieldAutor.setText(musica.getInterprete());
			textFieldAnnio.setText(String.valueOf(musica.getAnnio()));
			rdbtnMusica.setSelected(true);
			comboBoxGeneroMusical.addItem(musica.getGenero());
			comboBoxGeneroMusical.setSelectedItem(musica.getGenero());
		}
	}

}