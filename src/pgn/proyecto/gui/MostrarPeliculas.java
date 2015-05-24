/**
 * 
 */
package pgn.proyecto.gui;

import pgn.proyecto.videoclub.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * @author Elisa Navarro Zuara
 * @version 1.0
 */
public class MostrarPeliculas extends VideoclubGUI {

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
	
	private JButton btnAnterior;
	private JButton btnSiguiente;

	/**
	 * Create the dialog.
	 */
	public MostrarPeliculas() {
		super();
		setTitle("Mostrar peliculas");
		
		this.videoclub = generarVideoclub();
		
		buttonPane.setVisible(false);
		
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
		
		btnAnterior = new JButton();
		btnAnterior.setIcon(new ImageIcon(Mostrar.class.getResource("/imagenes/anterior.png")));
		btnAnterior.setBounds(29, 160, 68, 54);
		contentPanel.add(btnAnterior);
		
		btnSiguiente = new JButton();
		btnSiguiente.setIcon(new ImageIcon(Mostrar.class.getResource("/imagenes/siguiente.png")));
		btnSiguiente.setBounds(286, 160, 68, 54);
		contentPanel.add(btnSiguiente);
		
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarAnterior();
			}
		});
		
		btnSiguiente.addActionListener(new ActionListener() {
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
		Videoclub videoclubPeliculas = new Videoclub();
		for (int i = 0; i < Gestion.getVideoclub().size(); i++) {
			if (Gestion.getVideoclub().get(i).getTipo() == TipoItem.PELICULA)
				try {
					videoclubPeliculas.annadir(Gestion.getVideoclub().get(i).getId(),
							Gestion.getVideoclub().get(i).getTitulo(), 
							Gestion.getVideoclub().get(i).getAnnio(),
							((Pelicula) Gestion.getVideoclub().get(i)).getDirector(), 
							((Pelicula) Gestion.getVideoclub().get(i)).getGenero());
				} catch (ProductoYaExisteException | IdNoValidoException
						| TituloNoValidoException | AnnioNoValidoException
						| AutorNoValidoException | GeneroNoValidoException
						| TipoNoValidoException e) {
				}
		}
		return videoclubPeliculas;
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
			btnSiguiente.setEnabled(false);
		else
			btnSiguiente.setEnabled(true);

		if (videoclub.get(index - 1) == null)
			btnAnterior.setEnabled(false);
		else
			btnAnterior.setEnabled(true);
	}
	
	/**
	 * Muestra las características de un producto
	 * 
	 * @param producto
	 *            Representa el producto a mostrar
	 */
	private void mostrarProducto(Producto producto) {
		Pelicula pelicula = (Pelicula) producto;
		textFieldID.setText(pelicula.getId());
		textFieldTitulo.setText(pelicula.getTitulo());
		textFieldAutor.setText(pelicula.getDirector());
		textFieldAnnio.setText(String.valueOf(pelicula.getAnnio()));
		rdbtnPelicula.setSelected(true);
		comboBoxGenero.addItem(pelicula.getGenero());
		comboBoxGenero.setSelectedItem(pelicula.getGenero());
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