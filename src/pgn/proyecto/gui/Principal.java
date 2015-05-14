/**
 * 
 */
package pgn.proyecto.gui;

import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import pgn.proyecto.videoclub.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.KeyStroke;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Elisa Navarro Zuara
 * @version 1.0
 */
public class Principal {

	private JFrame frame;
	private JMenuBar menuBar;
	private JMenu mnFicheros;
	private JMenu mnProductos;
	private JMenu mnAlquiler;
	private JMenu mnAyuda;
	
	private JFileChooser fileChooser = new JFileChooser();
	private FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.obj", "obj");
	
	private Annadir annadir;
	private Eliminar eliminar;
	private Mostrar mostrar;
	private ElegirGenero elegirGenero;
	private MostrarPorTitulo mostrarPorTitulo;
	private Ordenar ordenar;
	private Alquilar alquilar;
	private Devolver devolver;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Sin título");
		frame.setBounds(100, 100, 455, 345);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		fileChooser.setFileFilter(filtro);
		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				if (Gestion.isModificado()) {
					int n = JOptionPane.showOptionDialog(frame.getContentPane(),
									"El videoclub ha sido modificado. ¿Desea guardar los cambios?",
									"Confirmar", JOptionPane.YES_NO_CANCEL_OPTION,
									JOptionPane.QUESTION_MESSAGE, null, null, null);
					switch (n) {
					case JOptionPane.YES_OPTION:
						guardar();
						System.exit(0);
					case JOptionPane.NO_OPTION:
						System.exit(0);
					case JOptionPane.CANCEL_OPTION:
						return;
					}
				} else {
					System.exit(0);
				}
			}
		});
		
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		mnFicheros = new JMenu("Ficheros");
		mnFicheros.setMnemonic('F');
		menuBar.add(mnFicheros);
		
		JMenuItem mntmNuevo = new JMenuItem("Nuevo");
		mntmNuevo.setMnemonic('N');
		mntmNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nuevo();
			}
		});
		mntmNuevo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		mnFicheros.add(mntmNuevo);
		
		JMenuItem mntmAbrir = new JMenuItem("Abrir...");
		mntmAbrir.setMnemonic('A');
		mntmAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrir();
			}
		});
		mntmAbrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mnFicheros.add(mntmAbrir);
		
		JMenuItem mntmGuardar = new JMenuItem("Guardar");
		mntmGuardar.setMnemonic('G');
		mntmGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardar();
			}
		});
		mntmGuardar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_MASK));
		mnFicheros.add(mntmGuardar);
		
		JMenuItem mntmGuardarComo = new JMenuItem("Guardar como...");
		mntmGuardarComo.setMnemonic('u');
		mntmGuardarComo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarComo();
			}
		});
		mntmGuardarComo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mnFicheros.add(mntmGuardarComo);
		
		mnFicheros.addSeparator();
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Gestion.isModificado()) {
					int n = JOptionPane.showOptionDialog(frame.getContentPane(),
									"El videoclub ha sido modificado. ¿Desea guardar los cambios?",
									"Confirmar", JOptionPane.YES_NO_CANCEL_OPTION,
									JOptionPane.QUESTION_MESSAGE, null, null, null);
					switch (n) {
					case JOptionPane.YES_OPTION:
						guardar();
						System.exit(0);
					case JOptionPane.NO_OPTION:
						System.exit(0);
					case JOptionPane.CANCEL_OPTION:
						return;
					}
				} else {
					System.exit(0);
				}
			}
		});
		mntmSalir.setMnemonic('S');
		mntmSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK));
		mnFicheros.add(mntmSalir);
		
		mnProductos = new JMenu("Productos");
		mnProductos.setMnemonic('P');
		menuBar.add(mnProductos);
		
		JMenuItem mntmAnnadir = new JMenuItem("A\u00F1adir producto");
		mntmAnnadir.setMnemonic('A');
		mntmAnnadir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		mntmAnnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				annadir = new Annadir();
				annadir.setVisible(true);
			}
		});
		mnProductos.add(mntmAnnadir);
		
		JMenuItem mntmEliminar = new JMenuItem("Eliminar producto");
		mntmEliminar.setMnemonic('E');
		mntmEliminar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
		mntmEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminar = new Eliminar();
				eliminar.setVisible(true);
			}
		});
		mnProductos.add(mntmEliminar);
		
		JMenu mnBuscar = new JMenu("Buscar");
		mnBuscar.setMnemonic('B');
		mnProductos.add(mnBuscar);
		
		JMenuItem mntmPorTitulo = new JMenuItem("Por t\u00EDtulo...");
		mntmPorTitulo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mntmPorTitulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarPorTitulo();
			}
		});
		mnBuscar.add(mntmPorTitulo);
		
		JMenuItem mntmPorGenero = new JMenuItem("Por g\u00E9nero...");
		mntmPorGenero.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mntmPorGenero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarPorGenero();
			}
		});
		mnBuscar.add(mntmPorGenero);
		
		JMenuItem mntmMostrar = new JMenuItem("Mostrar videoclub");
		mntmMostrar.setMnemonic('M');
		mntmMostrar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_MASK));
		mntmMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrar();
			}
		});
		mnProductos.add(mntmMostrar);
		
		JMenuItem mntmOrdenar = new JMenuItem("Ordenar");
		mntmOrdenar.setMnemonic('O');
		mntmOrdenar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		mntmOrdenar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ordenar();
			}
		});
		mnProductos.add(mntmOrdenar);
		
		mnAlquiler = new JMenu("Alquiler");
		mnAlquiler.setMnemonic('A');
		menuBar.add(mnAlquiler);
		
		JMenuItem mntmRegistrar = new JMenuItem("Registrar alquiler");
		mntmRegistrar.setMnemonic('R');
		mntmRegistrar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
		mntmRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				alquilar = new Alquilar();
				alquilar.setVisible(true);
			}
		});
		mnAlquiler.add(mntmRegistrar);
		
		JMenuItem mntmDevolver = new JMenuItem("Devolver alquiler");
		mntmDevolver.setMnemonic('D');
		mntmDevolver.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
		mntmDevolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				devolver = new Devolver();
				devolver.setVisible(true);
			}
		});
		mnAlquiler.add(mntmDevolver);
		
		mnAyuda = new JMenu("Ayuda");
		mnAyuda.setMnemonic('y');
		menuBar.add(mnAyuda);
		
		JLabel lblPeliculas = new JLabel();
		lblPeliculas.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/peliculas.png")));
		lblPeliculas.setBounds(134, 35, 100, 75);
		frame.getContentPane().add(lblPeliculas);
		
		JLabel lblSeries = new JLabel();
		lblSeries.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/series.png")));
		lblSeries.setBounds(244, 35, 100, 75);
		frame.getContentPane().add(lblSeries);
		
		JLabel lblMusica = new JLabel();
		lblMusica.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/musica.png")));
		lblMusica.setBounds(205, 114, 100, 75);
		frame.getContentPane().add(lblMusica);
		
		JLabel lblImagen = new JLabel();
		lblImagen.setBounds(0, 0, 450, 300);
		lblImagen.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/videoclub.png")));
		frame.getContentPane().add(lblImagen);
		frame.getContentPane().setLayout(null);
	}

	private void nuevo() {
		if (Gestion.isModificado()) {
			int n = JOptionPane.showOptionDialog(frame.getContentPane(),
							"El videoclub ha sido modificado. ¿Desea guardar los cambios?",
							"Confirmar", JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, null, null);
			switch (n) {
			case JOptionPane.YES_OPTION:
				guardar();
				break;
			case JOptionPane.NO_OPTION:
				break;
			case JOptionPane.CANCEL_OPTION:
				return;
			}
		}
		Gestion.setVideoclub(new Videoclub());
		Gestion.setFile(new File("Sin título"));
		Gestion.setModificado(false);
		frame.setTitle(Gestion.getFile().getName());
	}
	
	private void abrir() {
		if (Gestion.isModificado()) {
			int n = JOptionPane.showOptionDialog(frame.getContentPane(),
							"El videoclub ha sido modificado. ¿Desea guardar los cambios?",
							"Confirmar", JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, null, null);
			switch (n) {
			case JOptionPane.YES_OPTION:
				guardar();
				break;
			case JOptionPane.NO_OPTION:
				break;
			case JOptionPane.CANCEL_OPTION:
				return;
			}
		}
		int seleccion = fileChooser.showOpenDialog(frame);
		if (seleccion == JFileChooser.APPROVE_OPTION) {
			try {
				File file = fileChooser.getSelectedFile();
				Gestion.setVideoclub(Fichero.abrir(file));
				Gestion.setFile(file);
				frame.setTitle(Gestion.getFile().getName());
			} catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(frame.getContentPane(),
						"Fichero con información distinta a la esperada.",
						"Warning", JOptionPane.WARNING_MESSAGE);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(frame.getContentPane(),
						"El sistema no puede abrir el fichero.", "Warning",
						JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	
	private void guardar() {
		if (Gestion.getFile() == null || Gestion.getFile().getName().equals("Sin título"))
			guardarComo();
		else {
			try {
				File file = fileChooser.getSelectedFile();
				Fichero.guardar(file, Gestion.getVideoclub());
				Gestion.setModificado(false);
				Gestion.setFile(file);
				frame.setTitle(Gestion.getFile().getName());
			} catch (IOException e) {
				JOptionPane.showMessageDialog(frame.getContentPane(),
						"El sistema no puede guardar el fichero.", "Warning",
						JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	
	private void guardarComo() {
		int seleccion = fileChooser.showSaveDialog(frame);
		if (seleccion == JFileChooser.APPROVE_OPTION) {
			try {
				File file = fileChooser.getSelectedFile();
				if (Fichero.isExists(file)) {
					int n = JOptionPane.showOptionDialog(frame.getContentPane(),
							"El fichero ya existe. ¿Desea sobreescribirlo?",
							"Confirmar", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, null, null);
					switch (n) {
					case JOptionPane.NO_OPTION:
						return;
					}
				}
				Fichero.guardar(file, Gestion.getVideoclub());
				Gestion.setModificado(false);
				Gestion.setFile(file);
				frame.setTitle(Gestion.getFile().getName());
			} catch (IOException e) {
				JOptionPane.showMessageDialog(frame.getContentPane(),
						"El sistema no puede guardar el fichero.",
						"Warning", JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	private void mostrar() {
		if (Gestion.getVideoclub().size() == 0) {
			JOptionPane.showMessageDialog(frame.getContentPane(),
					"No hay productos en el videoclub.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		mostrar = new Mostrar();
		mostrar.setVisible(true);
	}
	
	private void mostrarPorGenero() {
		if (Gestion.getVideoclub().size() == 0) {
			JOptionPane.showMessageDialog(frame.getContentPane(),
					"No hay productos en el videoclub.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		elegirGenero = new ElegirGenero();
		elegirGenero.setVisible(true);
	}
	
	private void mostrarPorTitulo() {
		if (Gestion.getVideoclub().size() == 0) {
			JOptionPane.showMessageDialog(frame.getContentPane(),
					"No hay productos en el videoclub.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		mostrarPorTitulo = new MostrarPorTitulo();
		mostrarPorTitulo.setVisible(true);	
	}
	
	private void ordenar() {
		if (Gestion.getVideoclub().size() == 0) {
			JOptionPane.showMessageDialog(frame.getContentPane(),
					"No hay productos en el videoclub.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		ordenar = new Ordenar();
		ordenar.setVisible(true);
	}
	
}