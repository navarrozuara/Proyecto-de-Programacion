/**
 * 
 */
package pgn.proyecto.gui;

import pgn.proyecto.videoclub.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

/**
 * @author Elisa Navarro Zuara
 * @version 1.0
 */
public class Eliminar extends VideoclubGUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the dialog.
	 */
	public Eliminar() {
		super();
		setTitle("Eliminar producto");
		
		enviar.setText("Eliminar");
		
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
			public void actionPerformed(ActionEvent e) {
				Producto producto;
				try {
					producto = Gestion.getVideoclub().get(textFieldID.getText());
					mostrarProducto(producto);
					comprobarProducto();
					int n = JOptionPane.showOptionDialog(contentPanel,
							"¿Está seguro de que desea eliminarlo?",
							"Confirmar", JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, null, null);
					switch (n) {
					case JOptionPane.YES_OPTION:
						Gestion.getVideoclub().eliminar(textFieldID.getText());
						Gestion.setModificado(true);
						clear();
						break;
					case JOptionPane.NO_OPTION:
						return;
					case JOptionPane.CANCEL_OPTION:
						return;
					}
				} catch (ProductoNoExisteException e1) {
					JOptionPane.showMessageDialog(contentPanel,
							e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

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