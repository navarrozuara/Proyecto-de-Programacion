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
public class MostrarPeliculas extends VideoclubGUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Videoclub videoclub;
	private int index = -1;

	/**
	 * Create the dialog.
	 */
	public MostrarPeliculas() {
		super();
		setTitle("Mostrar peliculas");
		
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
		Pelicula pelicula = (Pelicula) producto;
		textFieldID.setText(pelicula.getId());
		textFieldTitulo.setText(pelicula.getTitulo());
		textFieldAutor.setText(pelicula.getDirector());
		textFieldAnnio.setText(String.valueOf(pelicula.getAnnio()));
		rdbtnPelicula.setSelected(true);
		comboBoxGenero.addItem(pelicula.getGenero());
		comboBoxGenero.setSelectedItem(pelicula.getGenero());
		comprobarProducto();
	}
	
	Videoclub getVideoclub() {
		return videoclub;
	}

}