/**
 * 
 */
package pgn.proyecto.gui;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.Color;

/**
 * @author Elisa Navarro Zuara
 * @version 1.0
 */
public class Recibo extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();
	private String mensaje;
	
	/**
	 * Create the dialog.
	 */
	public Recibo(String mensaje) {
		setResizable(false);
		setModal(true);
		setTitle("Recibo de alquiler");
		setBounds(100, 100, 290, 300);
		
		this.mensaje = mensaje;
		
		JTextPane textPanel = new JTextPane();
		textPanel.setBackground(new Color(220, 220, 220));
		textPanel.setBounds(10, 11, 424, 179);
		textPanel.setEditable(false);
		textPanel.setText(mensaje);
		
		JScrollPane scrollPane = new JScrollPane(textPanel);
		scrollPane.setBounds(10, 11, 264, 213);
		
		contentPanel.setLayout(null);
		contentPanel.add(scrollPane);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton guardar = new JButton("Guardar");
				guardar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							File file = new File("recibo.txt");
							guardar(file);
							JOptionPane.showMessageDialog(contentPanel, "Recibo guardado con éxito.");
						} catch (IOException e) {
							JOptionPane.showMessageDialog(contentPanel,
									"El sistema no puede guardar el fichero.",
									"Warning", JOptionPane.WARNING_MESSAGE);
						}
					}
				});
				guardar.setActionCommand("Guardar");
				buttonPane.add(guardar);
				getRootPane().setDefaultButton(guardar);
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
	
	private void guardar(File file) throws IOException {
		try (DataOutputStream out = new DataOutputStream(
				new BufferedOutputStream(new FileOutputStream(file, true)))) {
			out.writeUTF(mensaje);
		}
	}
	
}