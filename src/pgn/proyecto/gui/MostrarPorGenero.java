/**
 * 
 */
package pgn.proyecto.gui;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import pgn.proyecto.videoclub.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author Elisa Navarro Zuara
 * @version 1.0
 */
public class MostrarPorGenero extends VideoclubGUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Videoclub videoclub;
	private int index = -1;

	/**
	 * Create the dialog.
	 */
	public MostrarPorGenero(ArrayList<Producto> videoclub) {
		super();
		setTitle("Mostrar por genero");
		
		this.videoclub = generarVideoclub(videoclub);
		
		anterior.setVisible(true);
		
		textFieldID.setEditable(false);
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
		
		anterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mostrarAnterior();
			}
		});
		
		enviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarSiguiente();
			}
		});
		actualizar();
	}
	
	private void actualizar() {
		if (videoclub.size() == 0) {
			return;
		}
		index = 0;
		mostrarProducto(videoclub.get(index));
		comprobarBotones();		
	}
	
	private void mostrarSiguiente() {
		mostrarProducto(videoclub.get(++index));
		comprobarBotones();
	}
	
	private void mostrarAnterior() {
		mostrarProducto(videoclub.get(--index));
		comprobarBotones();
	}
	
	private void comprobarBotones() {
		if (videoclub.get(index + 1) == null)
			enviar.setEnabled(false);
		else
			enviar.setEnabled(true);

		if (videoclub.get(index - 1) == null)
			anterior.setEnabled(false);
		else
			anterior.setEnabled(true);
	}
	
	private void mostrarProducto(Producto producto) {
		if (producto instanceof Pelicula) {
			Pelicula pelicula = (Pelicula) producto;
			textFieldID.setText(pelicula.getId());
			textFieldTitulo.setText(pelicula.getTitulo());
			textFieldAutor.setText(pelicula.getDirector());
			textFieldAnnio.setText(String.valueOf(pelicula.getAnnio()));
			rdbtnPelicula.setSelected(true);
			comboBoxGenero.addItem(pelicula.getGenero());
			comboBoxGenero.setSelectedItem(pelicula.getGenero());
			comprobarProducto();
		} else if (producto instanceof Serie) {
			Serie serie = (Serie) producto;
			textFieldID.setText(serie.getId());
			textFieldTitulo.setText(serie.getTitulo());
			textFieldTemporada.setText(String.valueOf(serie.getTemporada()));
			textFieldNumTemporadas.setText(String.valueOf(serie.getNumTemporadas()));
			textFieldAnnio.setText(String.valueOf(serie.getAnnio()));
			rdbtnSerie.setSelected(true);
			comboBoxGenero.addItem(serie.getGenero());
			comboBoxGenero.setSelectedItem(serie.getGenero());
			comprobarProducto();
		} else {
			Musica musica = (Musica) producto;
			textFieldID.setText(musica.getId());
			textFieldTitulo.setText(musica.getTitulo());
			textFieldAutor.setText(musica.getInterprete());
			textFieldAnnio.setText(String.valueOf(musica.getAnnio()));
			rdbtnMusica.setSelected(true);
			comboBoxGeneroMusical.addItem(musica.getGenero());
			comboBoxGeneroMusical.setSelectedItem(musica.getGenero());
			comprobarProducto();
		}
	}

	private Videoclub generarVideoclub(ArrayList<Producto> videoclub) {
		Videoclub videoclubPorGenero = new Videoclub();
		for (Producto producto: videoclub) {
			try {
				if (producto instanceof Pelicula) {
					Pelicula pelicula = (Pelicula) producto;
					videoclubPorGenero.annadir(pelicula.getId(),
							pelicula.getTitulo(), pelicula.getAnnio(),
							pelicula.getDirector(), pelicula.getGenero());
				} else if (producto instanceof Serie) {
					Serie serie = (Serie) producto;
					videoclubPorGenero.annadir(serie.getId(),
							serie.getTitulo(), serie.getAnnio(),
							serie.getNumTemporadas(), serie.getTemporada(),
							serie.getGenero());
				} else {
					Musica musica = (Musica) producto;
					videoclubPorGenero.annadir(musica.getId(),
							musica.getTitulo(), musica.getAnnio(),
							musica.getInterprete(), musica.getGenero());
				}
			} catch (ProductoYaExisteException | IdNoValidoException
					| TituloNoValidoException | AnnioNoValidoException
					| AutorNoValidoException | TemporadaNoValidaException
					| GeneroNoValidoException | TipoNoValidoException e) {
				JOptionPane.showMessageDialog(contentPanel, e.getMessage(),
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		return videoclubPorGenero;
	}

}