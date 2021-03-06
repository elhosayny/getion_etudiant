package org.estc.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import org.estc.dao.EtudiantDB;
import org.estc.dao.FiliereDB;
import org.estc.dao.Tools;
import org.estc.metier.Etudiant;
import org.estc.metier.Filiere;

public class FrmSupprimerFiliere extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JPanel contentPane;
	private static JTextField txtCode;

	public FrmSupprimerFiliere() {
		setModal(true);
		setTitle("Supprimer Filiere");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 359, 145);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Supprimer un Filiere", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(11, 11, 338, 102);
		contentPane.add(panel);
		
		JLabel label = new JLabel("libelle :");
		label.setBounds(30, 31, 58, 14);
		panel.add(label);
		
		txtCode = new JTextField();
		txtCode.setColumns(10);
		txtCode.setBounds(98, 28, 223, 20);
		panel.add(txtCode);
		
		JButton btnSupprimer = new JButton("Supprimer");
		
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtCode.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Veillez saisir le libelle de la Filiere", "Erreur", JOptionPane.ERROR_MESSAGE);
				}else
				{
					Filiere f;
					try {
						f = FiliereDB.get(txtCode.getText());
						if(f != null){
							
							String message = "Vouslez-vous supprimer "+f.getLibelleFiliere()+"?";
						    String title = "Confirmer Suppression !";
						    
						    int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
						    if (reply == JOptionPane.YES_OPTION)
						    {
						    	try {
									FiliereDB.delete(f.getCodeFiliere());
									JOptionPane.showMessageDialog(null,"Bien Supprimer","Info",JOptionPane.INFORMATION_MESSAGE);
								} catch (Exception e1) {
									
									e1.printStackTrace();
								}
						    }
						}else{
							JOptionPane.showMessageDialog(null, "Filiere inexistant", "Erreur", JOptionPane.ERROR_MESSAGE);
						}
					} catch (Exception e2) {
						//nothing
					}
				}
				
			}
		});
		btnSupprimer.setBounds(121, 59, 97, 23);
		panel.add(btnSupprimer);
		setLocationRelativeTo(null);
	}

}
