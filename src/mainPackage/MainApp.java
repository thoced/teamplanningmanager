package mainPackage;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Profil;
import exceptions.NoProfilException;

import javax.swing.JMenuBar;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;

import javax.swing.BoxLayout;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MainApp implements ActionListener {

	private JFrame frame;
	private JLabel labelStatut;
	private JMenuBar menuBar;
	private JMenu menuFichier;
	private JMenuItem mFermer;
	private JMenu menuGestion;
	private JMenuItem mGestionDossier;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainApp window = new MainApp();
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
	public MainApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panelStatut = new JPanel();
		panelStatut.setBackground(Color.GRAY);
		frame.getContentPane().add(panelStatut, BorderLayout.SOUTH);
		
		// Chargement du Setup
		try 
		{
			Setup setup = new Setup();
			
		} catch (IOException e) 
		{
			JOptionPane.showConfirmDialog(null, "Probleme de chargement du fichie de configuration " + e.getMessage());
		}
		
		// Chargement du profil
		SqlTransaction trans = new SqlTransaction();
		try
		{
			Profil profil = trans.checkProfil(System.getProperty("user.name"));
			panelStatut.setLayout(new GridLayout(0, 1, 0, 0));
			
			labelStatut = new JLabel("");
			labelStatut.setHorizontalAlignment(SwingConstants.RIGHT);
			panelStatut.add(labelStatut);
			labelStatut.setText("Profil connecté: " + profil.user + "," + profil.nom + "," + profil.prenom);
			
			menuBar = new JMenuBar();
			frame.setJMenuBar(menuBar);
			
			menuFichier = new JMenu("Fichier");
			menuBar.add(menuFichier);
			
			mFermer = new JMenuItem("Fermer");
			menuFichier.add(mFermer);
			
			menuGestion = new JMenu("Gestion");
			menuBar.add(menuGestion);
			
			mGestionDossier = new JMenuItem("Gestion des dossiers");
			menuGestion.add(mGestionDossier);
			mGestionDossier.setActionCommand("GESTION_DOSSIER");
			mGestionDossier.addActionListener(this);
			
		} catch (NoProfilException e) 
		{
			JOptionPane.showConfirmDialog(null, "Profil: " + e.getMessage() +  System.getProperty("user.name"));
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		switch(arg0.getActionCommand())
		{
			case "GESTION_DOSSIER":
				GestionDossier gd = new GestionDossier(frame,"Gestion des dossier à visualiser",true);
				gd.setVisible(true);
			break;
		
			default:break;
		}
		
	}

}
