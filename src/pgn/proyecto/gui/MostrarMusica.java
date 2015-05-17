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
public class MostrarMusica extends VideoclubGUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Videoclub videoclub;
	private int index = -1;

	/**
	 * Create the dialog.
	 */
	public MostrarMusica() {
		super();
		setTitle("Mostrar musica");
		
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
		Videoclub videoclubMusica = new Videoclub();
		for (int i = 0; i < Gestion.getVideoclub().size(); i++) {
			if (Gestion.getVideoclub().get(i).getTipo() == TipoItem.MUSICA)
				try {
					videoclubMusica.annadir(Gestion.getVideoclub().get(i).getId(),
							Gestion.getVideoclub().get(i).getTitulo(), 
							Gestion.getVideoclub().get(i).getAnnio(),
							((Musica) Gestion.getVideoclub().get(i)).getInterprete(), 
							((Musica) Gestion.getVideoclub().get(i)).getGenero());
				} catch (ProductoYaExisteException | IdNoValidoException
						| TituloNoValidoException | AnnioNoValidoException
						| GeneroNoValidoException | TipoNoValidoException e) {
				}
		}
		return videoclubMusica;
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
	
	Videoclub getVideoclub() {
		return videoclub;
	}

}