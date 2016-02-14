package mainPackage;

import java.awt.EventQueue;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Dossier;
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

import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;

public class MainApp implements ActionListener {

	static public JFrame frame;
	private JLabel labelStatut;
	private JMenuBar menuBar;
	private JMenu menuFichier;
	private JMenuItem mFermer;
	private JMenu menuGestion;
	private JMenuItem mGestionDossier;
	private JTabbedPane tabbedDossiers;

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
		frame.setSize(800, 600);
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
			
			tabbedDossiers = new JTabbedPane(JTabbedPane.TOP);
			frame.getContentPane().add(tabbedDossiers, BorderLayout.CENTER);
			
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
			
			// load Setup dossier
			this.loadSetupDossiers();
			
		} catch (NoProfilException e) 
		{
			JOptionPane.showConfirmDialog(null, "Profil: " + e.getMessage() +  System.getProperty("user.name"));
		}
	}
	
	private void loadSetupDossiers()
	{
		
		// ouverture du fichier dossiers.cfg
		File file = new File(System.getProperty("user.home") + System.getProperty("file.separator") + "TeamPlanningManagerConfig" + System.getProperty("file.separator") + "dossiers.cfg");
		FileInputStream fis;
		try
		{
			fis = new FileInputStream(file);
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			String builder =  new String(buffer);
			builder.trim();
			String[] split = builder.split(System.getProperty("line.separator"));
			
			
			List<Dossier> dossiers = new ArrayList();
			for(String s : split)
			{
				
				if(s.length() != 0 || !s.isEmpty())
				{
					Dossier d = new Dossier();
					d.name = s;
					dossiers.add(d);
				}
			}
			
			// setupdossier
			this.setupDossiers(dossiers);
			fis.close();
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private void setupDossiers(List<Dossier> dossiers)
	{
		for(Dossier dos : dossiers)
		{
			tabbedDossiers.add(new PanelInfo(dos.name));
		
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
				if(gd.getListDossiers() != null)
				{
					List<Dossier> dossiers = gd.getListDossiers();
					this.setupDossiers(dossiers);
					
				}
				
			break;
		
			default:break;
		}
		
	}

}
