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
public class Annadir extends VideoclubGUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the dialog.
	 */
	public Annadir() {
		super();
		setTitle("Añadir producto");
		
		enviar.setText("Añadir");
		
		enviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (getTipo() == TipoItem.PELICULA) {
						Gestion.getVideoclub().annadir(textFieldID.getText(),
								textFieldTitulo.getText(),
								Integer.parseInt(textFieldAnnio.getText()),
								textFieldAutor.getText(),
								(Genero) comboBoxGenero.getSelectedItem());
						Gestion.setModificado(true);
						JOptionPane.showMessageDialog(contentPanel, "Película añadida con éxito.");
					} else if (getTipo() == TipoItem.SERIE) {
						Gestion.getVideoclub().annadir(
								textFieldID.getText(),
								textFieldTitulo.getText(),
								Integer.parseInt(textFieldAnnio.getText()),
								Integer.parseInt(textFieldNumTemporadas.getText()),
								Integer.parseInt(textFieldTemporada.getText()),
								(Genero) comboBoxGenero.getSelectedItem());
						Gestion.setModificado(true);
						JOptionPane.showMessageDialog(contentPanel, "Serie añadida con éxito.");
					} else if (getTipo() == TipoItem.MUSICA) {
						Gestion.getVideoclub().annadir(
								textFieldID.getText(),
								textFieldTitulo.getText(),
								Integer.parseInt(textFieldAnnio.getText()),
								textFieldAutor.getText(),
								(GeneroMusical) comboBoxGeneroMusical.getSelectedItem());
						Gestion.setModificado(true);
						JOptionPane.showMessageDialog(contentPanel, "Música añadida con éxito.");
					} else {
						JOptionPane.showMessageDialog(contentPanel,
								"Debe seleccionar un producto.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (NumberFormatException | ProductoYaExisteException
						| IdNoValidoException | TituloNoValidoException
						| AnnioNoValidoException | AutorNoValidoException
						| TemporadaNoValidaException | GeneroNoValidoException
						| TipoNoValidoException e) {
					JOptionPane.showMessageDialog(contentPanel,
							e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
	
	private TipoItem getTipo() {
		if (rdbtnPelicula.isSelected())
			return TipoItem.PELICULA;
		else if (rdbtnSerie.isSelected())
			return TipoItem.SERIE;
		else if (rdbtnMusica.isSelected())
			return TipoItem.MUSICA;
		else
			return null;
	}

}