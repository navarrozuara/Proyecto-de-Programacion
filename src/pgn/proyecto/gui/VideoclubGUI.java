/**
 * 
 */
package pgn.proyecto.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.UIManager;
import javax.swing.JComboBox;

import pgn.proyecto.videoclub.Genero;
import pgn.proyecto.videoclub.GeneroMusical;
import pgn.proyecto.videoclub.Gestion;
import pgn.proyecto.videoclub.ProductoNoExisteException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author Elisa Navarro Zuara
 * @version 1.0
 */
public class VideoclubGUI extends JDialog implements ChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected final JPanel contentPanel = new JPanel();
	protected final ButtonGroup buttonGroup = new ButtonGroup();
	protected final ButtonGroup buttonGroup1 = new ButtonGroup();
	protected JTextField textFieldID;
	protected JTextField textFieldTitulo;
	protected JTextField textFieldAutor;
	protected JTextField textFieldTemporada;
	protected JTextField textFieldNumTemporadas;
	protected JTextField textFieldAnnio;
	protected JLabel lblIdentificador;
	protected JLabel lblTitulo;
	protected JLabel lblAutor;
	protected JLabel lblTemporada;
	protected JLabel lblNumTemporadas;
	protected JLabel lblAnnio;
	protected JLabel lblGenero;
	protected JPanel panel;
	protected JPanel panel1;
	protected JRadioButton rdbtnPelicula;
	protected JRadioButton rdbtnSerie;
	protected JRadioButton rdbtnMusica;
	protected JRadioButton rdbtnNormal;
	protected JRadioButton rdbtnEstreno;
	protected JComboBox<Genero> comboBoxGenero;
	protected JComboBox<GeneroMusical> comboBoxGeneroMusical;
	protected JButton disponible;
	protected JButton enviar;
	protected JButton salir;
	protected JButton anterior;

	/**
	 * Create the dialog.
	 */
	public VideoclubGUI() {
		super();
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 420, 260);
		
		lblIdentificador = new JLabel("Identificador");
		lblIdentificador.setBounds(29, 28, 71, 14);
		
		textFieldID = new JTextField();
		textFieldID.setBounds(106, 25, 112, 20);
		textFieldID.setColumns(7);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Producto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(25, 50, 87, 99);
		panel.setLayout(null);
		
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
		
		lblTitulo = new JLabel("T\u00EDtulo");
		lblTitulo.setBounds(122, 56, 58, 14);
		
		textFieldTitulo = new JTextField();
		textFieldTitulo.setBounds(190, 53, 160, 20);
		textFieldTitulo.setColumns(50);
		
		lblAutor = new JLabel("Autor");
		lblAutor.setBounds(122, 81, 58, 14);
		
		textFieldAutor = new JTextField();
		textFieldAutor.setBounds(190, 78, 160, 20);
		textFieldAutor.setColumns(50);
		
		lblTemporada = new JLabel("Temporada");
		lblTemporada.setVisible(false);
		lblTemporada.setBounds(122, 81, 68, 14);
		
		textFieldTemporada = new JTextField();
		textFieldTemporada.setVisible(false);
		textFieldTemporada.setBounds(190, 78, 32, 20);
		textFieldTemporada.setColumns(10);
		
		lblNumTemporadas = new JLabel("N\u00BA Temporadas");
		lblNumTemporadas.setVisible(false);
		lblNumTemporadas.setBounds(225, 81, 87, 14);
		
		textFieldNumTemporadas = new JTextField();
		textFieldNumTemporadas.setVisible(false);
		textFieldNumTemporadas.setBounds(318, 78, 32, 20);
		textFieldNumTemporadas.setColumns(10);
		
		lblAnnio = new JLabel("A\u00F1o");
		lblAnnio.setBounds(122, 106, 46, 14);
		
		textFieldAnnio = new JTextField();
		textFieldAnnio.setBounds(190, 103, 46, 20);
		textFieldAnnio.setColumns(4);
		
		lblGenero = new JLabel("G\u00E9nero");
		lblGenero.setBounds(122, 131, 46, 14);
		
		comboBoxGenero = new JComboBox<Genero>();
		comboBoxGenero.setBounds(190, 128, 99, 20);
		
		comboBoxGeneroMusical = new JComboBox<GeneroMusical>();
		comboBoxGeneroMusical.setVisible(false);
		comboBoxGeneroMusical.setBounds(190, 128, 154, 20);
		
		disponible = new JButton("Disponible");
		disponible.setBounds(232, 24, 112, 23);
		
		panel1 = new JPanel();
		panel1.setVisible(false);
		panel1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tipo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel1.setBounds(122, 148, 172, 45);
		panel1.setLayout(null);
		
		rdbtnNormal = new JRadioButton("Normal", true);
		rdbtnNormal.setBounds(8, 16, 68, 23);
		panel1.add(rdbtnNormal);
		buttonGroup1.add(rdbtnNormal);
		
		rdbtnEstreno = new JRadioButton("Estreno");
		rdbtnEstreno.setBounds(76, 16, 90, 23);
		panel1.add(rdbtnEstreno);
		buttonGroup1.add(rdbtnEstreno);
		
		contentPanel.setLayout(null);
		contentPanel.add(lblIdentificador);
		contentPanel.add(lblTitulo);
		contentPanel.add(lblAutor);
		contentPanel.add(lblTemporada);
		contentPanel.add(lblNumTemporadas);
		contentPanel.add(lblAnnio);
		contentPanel.add(lblGenero);
		contentPanel.add(textFieldID);
		contentPanel.add(textFieldTitulo);
		contentPanel.add(textFieldAutor);
		contentPanel.add(textFieldTemporada);
		contentPanel.add(textFieldNumTemporadas);
		contentPanel.add(textFieldAnnio);
		contentPanel.add(comboBoxGenero);
		contentPanel.add(comboBoxGeneroMusical);
		contentPanel.add(panel);
		contentPanel.add(panel1);
		contentPanel.add(disponible);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				anterior = new JButton("<");
				anterior.setVisible(false);
				anterior.setActionCommand("<");
				buttonPane.add(anterior);
				getRootPane().setDefaultButton(anterior);
			}
			{
				enviar = new JButton(">");
				enviar.setActionCommand(">");
				buttonPane.add(enviar);
				getRootPane().setDefaultButton(enviar);
			}
			{
				salir = new JButton("Salir");
				salir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				salir.setActionCommand("Salir");
				buttonPane.add(salir);
			}
		}
	}

	/**
	 * Comprueba si un producto está o no disponible en el videoclub
	 */
	protected void isDisponible() {
		try {
			if (!Gestion.getVideoclub().get(textFieldID.getText()).isDisponible())
				disponible.setEnabled(false);
			else
				disponible.setEnabled(true);
		} catch (ProductoNoExisteException e) {
			JOptionPane.showMessageDialog(contentPanel, e.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	@Override
	public void stateChanged(ChangeEvent e) { 
		 if (rdbtnPelicula.isSelected()) {
			 mostrarAutor(true);
			 lblAutor.setText("Director");
			 mostrarTemporada(false);
			 comboBoxGenero.setVisible(true);
			 comboBoxGeneroMusical.setVisible(false);
			 comboBoxGenero.setModel(new DefaultComboBoxModel<Genero>(Genero.values()));
		 }
		 if (rdbtnSerie.isSelected()) {
			 mostrarAutor(false);
			 mostrarTemporada(true);
			 comboBoxGenero.setVisible(true);
			 comboBoxGeneroMusical.setVisible(false);
			 comboBoxGenero.setModel(new DefaultComboBoxModel<Genero>(Genero.values()));
		 }
		 if (rdbtnMusica.isSelected()) {
			 mostrarAutor(true);
			 lblAutor.setText("Intérprete");
			 mostrarTemporada(false);
			 comboBoxGenero.setVisible(false);
			 comboBoxGeneroMusical.setVisible(true);
			 comboBoxGeneroMusical.setModel(new DefaultComboBoxModel<GeneroMusical>(
							GeneroMusical.values()));
		}
	 }
	
	/**
	 * Muestra o no todos los elementos relacionados con el autor
	 * 
	 * @param b
	 *            Representa el estado de los elementos
	 */
	private void mostrarAutor(boolean b) {
		lblAutor.setVisible(b);
		textFieldAutor.setVisible(b);
	}

	/**
	 * Muestra o no todos los elementos relacionados con las temporadas
	 * 
	 * @param b
	 *            Representa el estado de los elementos
	 */
	private void mostrarTemporada(boolean b) {
		lblTemporada.setVisible(b);
		lblNumTemporadas.setVisible(b);
		textFieldTemporada.setVisible(b);
		textFieldNumTemporadas.setVisible(b);
	}
	
}