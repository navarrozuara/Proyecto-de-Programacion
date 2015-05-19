/**
 * 
 */
package pgn.proyecto.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pgn.proyecto.videoclub.*;

/**
 * @author Elisa Navarro Zuara
 * @version 1.0
 */
public class Ordenar extends VideoclubGUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Videoclub
	 */
	private Videoclub videoclub;
	
	/**
	 * �ndice del producto
	 */
	private int index = -1;

	/**
	 * Create the dialog.
	 */
	public Ordenar() {
		super();
		setTitle("Ordenar productos");
		
		this.videoclub = Gestion.getVideoclub();
		videoclub.ordenar();
		
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
			public void actionPerformed(ActionEvent e) {
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
	
	/**
	 * Actualiza el contenido del di�logo
	 */
	private void actualizar() {
		if (videoclub.size() == 0) {
			return;
		}
		index = 0;
		mostrarProducto(videoclub.get(index));
		comprobarBotones();		
	}
	
	/**
	 * Muestra el siguiente producto del videoclub
	 */
	private void mostrarSiguiente() {
		mostrarProducto(videoclub.get(++index));
		comprobarBotones();
	}
	
	/**
	 * Muestra el producto anterior del videoclub
	 */
	private void mostrarAnterior() {
		mostrarProducto(videoclub.get(--index));
		comprobarBotones();
	}
	
	/**
	 * Comprueba si existe otro producto en el videoclub, tanto en el bot�n
	 * siguiente como en el bot�n anterior
	 */
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
	
	/**
	 * Muestra las caracter�sticas de un producto
	 * 
	 * @param producto
	 *            Representa el producto a mostrar
	 */
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
			isDisponible();
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
			isDisponible();
		} else {
			Musica musica = (Musica) producto;
			textFieldID.setText(musica.getId());
			textFieldTitulo.setText(musica.getTitulo());
			textFieldAutor.setText(musica.getInterprete());
			textFieldAnnio.setText(String.valueOf(musica.getAnnio()));
			rdbtnMusica.setSelected(true);
			comboBoxGeneroMusical.addItem(musica.getGenero());
			comboBoxGeneroMusical.setSelectedItem(musica.getGenero());
			isDisponible();
		}
	}

}