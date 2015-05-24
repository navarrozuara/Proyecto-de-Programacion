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

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Elisa Navarro Zuara
 * @version 1.0
 */
public class Ayuda extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();
	private static Ayuda ayuda = null;

	/**
	 * Create the dialog.
	 */
	private Ayuda() {
		setTitle("Ver ayuda");
		setResizable(false);
		setBounds(100, 100, 592, 427);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				ayuda = null;
				dispose();
			}
		});
		
		final JTextPane textPane = new JTextPane();
		textPane.setBackground(new Color(220, 220, 220));
		textPane.setEditable(false);
		textPane.setBounds(246, 11, 330, 340);
		textPane.setContentType("text/html");
		
		JLabel lblFicheros = new JLabel();
		lblFicheros.setBounds(10, 11, 180, 35);
		lblFicheros.setText("<html><body><h2>Ficheros</h2></body></html>");
		
		JLabel lblNuevo = new JLabel();
		lblNuevo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				textPane.setText("<html><body><h2>Crear nuevo videoclub:</h2><p>Para crear un nuevo videoclub vaya a <b>Ficheros --> Nuevo</b> o pulse <b>Ctrl+N</b>.</body></html>");
			}
		});
		lblNuevo.setBounds(10, 41, 180, 35);
		lblNuevo.setText("<html><body><ul><li>Nuevo</ul></body></html>");
		
		JLabel lblAbrir = new JLabel();
		lblAbrir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textPane.setText("<html><body><h2>Abrir videoclub:</h2><p>Para abrir un videoclub existente vaya a <b>Ficheros --> Abrir</b> o pulse <b>Ctrl+O</b>.</body></html>");
			}
		});
		lblAbrir.setBounds(10, 61, 180, 35);
		lblAbrir.setText("<html><body><ul><li>Abrir</ul></body></html>");
		
		JLabel lblGuardar = new JLabel();
		lblGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textPane.setText("<html><body><h2>Guardar/Guardar como... videoclub:</h2><p>Para guardar un videoclub se puede hacer de varias maneras.<ul><li>La forma predeterminada es seleccionando <b>Ficheros --> Guardar</b> o pulsando <b>Ctrl+G</b>. Si se trata de un videoclub existente se sobrescribirá con el contenido actual.</li><li>Si el videoclub todavía no tiene nombre (por ejemplo, si ha creado un nuevo videoclub) o ha seleccionado <b>Ficheros --> Guardar como...</b>, aparecerá el diálogo de guardar pidiéndole el nombre del videoclub.</ul></body></html>");
			}
		});
		lblGuardar.setBounds(10, 81, 226, 35);
		lblGuardar.setText("<html><body><ul><li>Guardar/Guardar como...</ul></body></html>");
		
		JLabel lblProductos = new JLabel();
		lblProductos.setBounds(10, 111, 180, 35);
		lblProductos.setText("<html><body><h2>Productos</h2></body></html>");
		
		JLabel lblAnnadir = new JLabel();
		lblAnnadir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textPane.setText("<html><body><h2>Añadir nuevo producto:</h2><p>Para añadir nuevos productos se debe de cumplir una serie de requisitos:<ol><li>El identificador debe estar formado por cuatro dígitos y un grupo de tres caracteres.<ul><li>PEL, si el producto es una película.<li>SER, si el producto es una serie.<li>MUS, si el producto es una música.</ul></li><li>El título debe estar en mayúscula y debe de tener, al menos, tres caracteres.</li><li>El año ha de ser válido.</li><li>Un género.</li></ol></body></html>");
			}
		});
		lblAnnadir.setBounds(10, 141, 180, 35);
		lblAnnadir.setText("<html><body><ul><li>Añadir producto</ul></body></html>");
		
		JLabel lblEliminar = new JLabel();
		lblEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textPane.setText("<html><body><h2>Eliminar producto:</h2><p>Los productos se eliminaran introduciendo el identificador de dicho producto y haciendo clic en \"Eliminar\".</body></html>");
			}
		});
		lblEliminar.setBounds(10, 161, 180, 35);
		lblEliminar.setText("<html><body><ul><li>Eliminar producto</ul></body></html>");
		
		JLabel lblBuscar = new JLabel();
		lblBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textPane.setText("<html><body><h2>Búsqueda de productos:</h2><p>La búsqueda de productos se podrá realizar a través de dos opciones:<ol><li><b>Búsqueda por título</b>, se introducirá el título del producto a buscar.</li><li><b>Búsqueda por género</b>, se introducirá el género del producto a buscar.</li></ol></body></html>");
			}
		});
		lblBuscar.setBounds(10, 181, 226, 35);
		lblBuscar.setText("<html><body><ul><li>Buscar por título/por género...</ul></body></html>");
		
		JLabel lblMostrar = new JLabel();
		lblMostrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textPane.setText("<html><body><h2>Mostrar productos:</h2><p>Se mostrarán todos los productos del videoclub.</body></html>");
			}
		});
		lblMostrar.setBounds(10, 201, 180, 35);
		lblMostrar.setText("<html><body><ul><li>Mostrar productos</ul></body></html>");
		
		JLabel lblOrdenar = new JLabel();
		lblOrdenar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textPane.setText("<html><body><h2>Ordenar productos:</h2><p>Los productos se ordenarán según el año de estreno y alfabéticamente.</body></html>");
			}
		});
		lblOrdenar.setBounds(10, 221, 180, 35);
		lblOrdenar.setText("<html><body><ul><li>Ordenar productos</ul></body></html>");
		
		JLabel lblAlquiler = new JLabel();
		lblAlquiler.setBounds(10, 251, 180, 35);
		lblAlquiler.setText("<html><body><h2>Alquiler</h2></body></html>");
		
		JLabel lblRegistrar = new JLabel();
		lblRegistrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textPane.setText("<html><body><h2>Registrar alquiler:</h2><p>Para alquilar un producto se debe de seguir una serie de instrucciones:<ol><li>Introducir el identificador del producto a alquilar.<li>Seleccionar un tipo de alquiler.<li>Introducir la fecha de devolución del producto.</ol><p>Sólo se podrán alquilar aquellos productos que estén disponibles en el videoclub.</body></html>");
			}
		});
		lblRegistrar.setBounds(10, 281, 180, 35);
		lblRegistrar.setText("<html><body><ul><li>Registrar alquiler</ul></body></html>");
		
		JLabel lblRecibo = new JLabel();
		lblRecibo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textPane.setText("<html><body><h2>Recibo de alquiler:</h2><p>Una vez alquilado uno o varios productos se podrá generar un recibo de alquiler a través del botón \"Recibo\", el cual podrá ser guardado en un archivo .txt haciendo clic en \"Guardar\".</body></html>");
			}
		});
		lblRecibo.setBounds(10, 301, 180, 35);
		lblRecibo.setText("<html><body><ul><li>Recibo</ul></body></html>");
		
		JLabel lblDevolver = new JLabel();
		lblDevolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textPane.setText("<html><body><h2>Devolver alquiler:</h2><p>Los productos se devolveran introduciendo el identificador de dicho producto y haciendo clic en \"Devolver\".</body></html>");
			}
		});
		lblDevolver.setBounds(10, 321, 180, 35);
		lblDevolver.setText("<html><body><ul><li>Devolver alquiler</ul></body></html>");	
		
		contentPanel.setLayout(null);
		contentPanel.add(textPane);
		contentPanel.add(lblFicheros);
		contentPanel.add(lblNuevo);
		contentPanel.add(lblAbrir);
		contentPanel.add(lblGuardar);
		contentPanel.add(lblProductos);
		contentPanel.add(lblAnnadir);
		contentPanel.add(lblEliminar);
		contentPanel.add(lblBuscar);
		contentPanel.add(lblMostrar);
		contentPanel.add(lblOrdenar);
		contentPanel.add(lblAlquiler);
		contentPanel.add(lblRegistrar);
		contentPanel.add(lblRecibo);
		contentPanel.add(lblDevolver);
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
						ayuda = null;
						dispose();
					}
				});
				aceptar.setActionCommand("Aceptar");
				buttonPane.add(aceptar);
				getRootPane().setDefaultButton(aceptar);
			}
		}
	}
	
	/**
	 * Instancia la clase Ayuda
	 * 
	 * @return Instancia de la clase Ayuda
	 */
	public static synchronized Ayuda getInstance() {
		if (ayuda == null) {
			ayuda = new Ayuda();
		}
		return ayuda;
	}
	
}