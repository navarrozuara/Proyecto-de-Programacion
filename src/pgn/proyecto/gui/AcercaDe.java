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
import javax.swing.ImageIcon;
import java.awt.SystemColor;

/**
 * @author Elisa Navarro Zuara
 * @version 1.0
 */
public class AcercaDe extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public AcercaDe() {
		setTitle("Acerca de...");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 450, 300);
		
		JLabel lblImagen = new JLabel();
		lblImagen.setIcon(new ImageIcon(AcercaDe.class.getResource("/imagenes/videoclub2.png")));
		lblImagen.setBounds(90, 10, 250, 150);
		
		JLabel lblAutor = new JLabel("Realizado por: Elisa Navarro Zuara");
		lblAutor.setBounds(10, 145, 226, 50);
		
		JLabel lblVersion = new JLabel("Version: 1.0");
		lblVersion.setBounds(20, 172, 92, 50);
		
		JLabel lblCopyright = new JLabel("(c) Copyright 2015.  All rights reserved.");
		lblCopyright.setBounds(10, 192, 259, 50);
		
		contentPanel.setLayout(null);
		contentPanel.add(lblImagen);
		contentPanel.add(lblAutor);
		contentPanel.add(lblVersion);
		contentPanel.add(lblCopyright);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.inactiveCaptionText);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(SystemColor.inactiveCaptionText);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton aceptar = new JButton("Aceptar");
				aceptar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				aceptar.setActionCommand("Aceptar");
				buttonPane.add(aceptar);
				getRootPane().setDefaultButton(aceptar);
			}
		}
	}
}