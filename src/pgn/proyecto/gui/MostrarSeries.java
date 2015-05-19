/**
 * 
 */
package pgn.proyecto.gui;

import pgn.proyecto.videoclub.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author Elisa Navarro Zuara
 * @version 1.0
 */
public class MostrarSeries extends VideoclubGUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Videoclub
	 */
	private Videoclub videoclub;
	
	/**
	 * Índice del producto
	 */
	private int index = -1;

	/**
	 * Create the dialog.
	 */
	public MostrarSeries() {
		super();
		setTitle("Mostrar series");
		
		this.videoclub = generarVideoclub();
		
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
	 * Genera un videoclub con los productos de un determinado tipo
	 * 
	 * @return Videoclub generado
	 */
	private Videoclub generarVideoclub() {
		Videoclub videoclubSeries = new Videoclub();
		for (int i = 0; i < Gestion.getVideoclub().size(); i++) {
			if (Gestion.getVideoclub().get(i).getTipo() == TipoItem.SERIE)
				try {
					videoclubSeries.annadir(Gestion.getVideoclub().get(i).getId(),
							Gestion.getVideoclub().get(i).getTitulo(),
							Gestion.getVideoclub().get(i).getAnnio(),
							((Serie) Gestion.getVideoclub().get(i)).getNumTemporadas(),
							((Serie) Gestion.getVideoclub().get(i)).getTemporada(), 
							((Serie) Gestion.getVideoclub().get(i)).getGenero());
				} catch (ProductoYaExisteException | IdNoValidoException
						| TituloNoValidoException | AnnioNoValidoException
						| TemporadaNoValidaException | GeneroNoValidoException
						| TipoNoValidoException e) {
				}
		}
		return videoclubSeries;
	}

	/**
	 * Actualiza el contenido del diálogo
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
	 * Comprueba si existe otro producto en el videoclub, tanto en el botón
	 * siguiente como en el botón anterior
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
	 * Muestra las características de un producto
	 * 
	 * @param producto
	 *            Representa el producto a mostrar
	 */
	private void mostrarProducto(Producto producto) {
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
	}
	
	/**
	 * Devuelve el videoclub
	 * 
	 * @return Videoclub
	 */
	Videoclub getVideoclub() {
		return videoclub;
	}

}