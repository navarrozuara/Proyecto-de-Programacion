/**
 * 
 */
package pgn.proyecto.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.UIManager;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;

import pgn.proyecto.videoclub.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * @author Elisa Navarro Zuara
 * @version 1.0
 */
public class ElegirGenero extends JDialog implements ChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPanel panel;
	private JLabel lblGenero;
	private JRadioButton rdbtnPelicula;
	private JRadioButton rdbtnSerie;
	private JRadioButton rdbtnMusica;
	private JComboBox<Genero> comboBoxGenero;
	private JComboBox<GeneroMusical> comboBoxGeneroMusical;
	private MostrarPorGenero mostrarPorGenero;

	/**
	 * Create the dialog.
	 */
	public ElegirGenero() {
		setTitle("Elegir genero");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 348, 185);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Producto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(20, 11, 87, 99);
		contentPanel.add(panel);
		
		rdbtnPelicula = new JRadioButton("Pel\u00EDcula", true);
		rdbtnPelicula.addChangeListener(this);
		rdbtnPelicula.setBounds(8, 16, 71, 23);
		panel.add(rdbtnPelicula);
		buttonGroup.add(rdbtnPelicula);
		
		rdbtnSerie = new JRadioButton("Serie", false);
		rdbtnSerie.addChangeListener(this);
		rdbtnSerie.setBounds(8, 42, 71, 23);
		panel.add(rdbtnSerie);
		buttonGroup.add(rdbtnSerie);
		
		rdbtnMusica = new JRadioButton("M\u00FAsica", false);
		rdbtnMusica.addChangeListener(this);
		rdbtnMusica.setBounds(8, 68, 71, 23);
		panel.add(rdbtnMusica);
		buttonGroup.add(rdbtnMusica);
		
		lblGenero = new JLabel("G\u00E9nero");
		lblGenero.setBounds(117, 55, 46, 14);
		
		comboBoxGenero = new JComboBox<Genero>();
		comboBoxGenero.setBounds(173, 52, 99, 20);
		comboBoxGenero.setModel(new DefaultComboBoxModel<Genero>(Genero.values()));
		
		comboBoxGeneroMusical = new JComboBox<GeneroMusical>();
		comboBoxGeneroMusical.setVisible(false);
		comboBoxGeneroMusical.setBounds(173, 52, 154, 20);
		
		contentPanel.setLayout(null);
		contentPanel.add(lblGenero);
		contentPanel.add(comboBoxGenero);
		contentPanel.add(comboBoxGeneroMusical);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton aceptar = new JButton("Aceptar");
				aceptar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ArrayList<Producto> productos;
						if (getTipo() == TipoItem.PELICULA) {
							productos = Gestion.getVideoclub().getGenero(
									(Genero) comboBoxGenero.getSelectedItem(), getTipo());
						} else if (getTipo() == TipoItem.SERIE) {
							productos = Gestion.getVideoclub().getGenero(
									(Genero) comboBoxGenero.getSelectedItem(), getTipo());
						} else {
							productos = Gestion.getVideoclub().getGenero(
									(GeneroMusical) comboBoxGeneroMusical.getSelectedItem());
						}
						if (productos.isEmpty()) {
							JOptionPane.showMessageDialog(contentPanel,
									"No existe ningún producto de ese género.",
									"Error", JOptionPane.ERROR_MESSAGE);
							return;
						}
						mostrarPorGenero = new MostrarPorGenero(productos);
						mostrarPorGenero.setVisible(true);
					}
				});
				aceptar.setActionCommand("Aceptar");
				buttonPane.add(aceptar);
				getRootPane().setDefaultButton(aceptar);
			}
			{
				JButton salir = new JButton("Salir");
				salir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				salir.setActionCommand("Salir");
				buttonPane.add(salir);
			}
		}
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		if (rdbtnPelicula.isSelected()) {
			 comboBoxGenero.setVisible(true);
			 comboBoxGeneroMusical.setVisible(false);
			 comboBoxGenero.setModel(new DefaultComboBoxModel<Genero>(Genero.values()));
		 }
		 if (rdbtnSerie.isSelected()) {
			 comboBoxGenero.setVisible(true);
			 comboBoxGeneroMusical.setVisible(false);
			 comboBoxGenero.setModel(new DefaultComboBoxModel<Genero>(Genero.values()));
		 }
		 if (rdbtnMusica.isSelected()) {
			 comboBoxGenero.setVisible(false);
			 comboBoxGeneroMusical.setVisible(true);
			 comboBoxGeneroMusical.setModel(new DefaultComboBoxModel<GeneroMusical>(
							GeneroMusical.values()));
		}
	}
	
	/**
	 * Devuelve el tipo de producto seleccionado
	 * 
	 * @return Tipo de producto seleccionado
	 */
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