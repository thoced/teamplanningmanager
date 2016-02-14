package mainPackage;

import java.awt.Frame;

import javax.swing.JDialog;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import model.Dossier;
import net.miginfocom.swing.MigLayout;

import javax.swing.JScrollPane;

import java.awt.CardLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;

import exceptions.NoDossierException;

import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import javax.swing.ListSelectionModel;

public class GestionDossier extends JDialog implements ActionListener
{
	private JList listDossiers;
	private JButton buttonAdd;
	private JButton buttonSub;
	private JList listDossierSelected;
	
	private List<Object> listDossier = null;
	
	private Model model;
	
	public GestionDossier(Frame arg0, String arg1, boolean arg2) {
		super(arg0, arg1, arg2);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		this.setSize(655, 320);
		this.setLocationRelativeTo(null);
		
		JPanel panelbutton = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelbutton.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(panelbutton, BorderLayout.SOUTH);
		
		JButton buttonAnnuler = new JButton("Annuler");
		buttonAnnuler.setActionCommand("ANNULER");
		buttonAnnuler.addActionListener(this);
		panelbutton.add(buttonAnnuler);
		
		JButton buttonOk = new JButton("Confirmer");
		buttonOk.setActionCommand("CONFIRMER");
		buttonOk.addActionListener(this);
		panelbutton.add(buttonOk);
		
		JPanel panelDossier = new JPanel();
		getContentPane().add(panelDossier, BorderLayout.CENTER);
		panelDossier.setLayout(null);
		
		listDossierSelected = new JList();
		listDossierSelected.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listDossierSelected.setModel(new DefaultListModel());
		
			JScrollPane scrollPane_1 = new JScrollPane(listDossierSelected);
			scrollPane_1.setBounds(419, 11, 210, 132);
			panelDossier.add(scrollPane_1);
			scrollPane_1.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
			
			listDossiers = new JList();
			JScrollPane scrollPane = new JScrollPane(listDossiers);
			scrollPane.setBounds(10, 11, 210, 132);
			panelDossier.add(scrollPane);
			scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
			
			buttonAdd = new JButton("Ajouter");
			buttonAdd.setBounds(277, 11, 89, 36);
			panelDossier.add(buttonAdd);
			buttonAdd.setActionCommand("AJOUTER");
			
			buttonSub = new JButton("Enlever");
			buttonSub.setBounds(277, 58, 89, 36);
			panelDossier.add(buttonSub);
			buttonSub.setActionCommand("ENLEVER");
			buttonSub.addActionListener(this);
			buttonAdd.addActionListener(this);
		
		// init model
		model = new Model();
		
		
	}
	
	
	
	public List getListDossiers() {
		return listDossier;
	}



	private void ajouterDossiers()
	{
		
		// transfert des dossier sélèctionnés vers la liste des dossiers selectionnés
		List<Dossier> listDossier = this.listDossiers.getSelectedValuesList();
		DefaultListModel<Dossier> lm =  (DefaultListModel<Dossier>) this.listDossierSelected.getModel();
		
		for(Dossier d : listDossier)
		{
			if(!lm.contains(d))
			{
				lm.addElement(d);
				// suppression dans la liste primaire du dossier ajouté
				
			}
		}
		this.listDossierSelected.setModel(lm);
		
		
		
	}
	
	private void enleverDossier()
	{
		int[] indices = this.listDossierSelected.getSelectedIndices();
		if(indices != null)
		{
			for(int i : indices)
				((DefaultListModel)this.listDossierSelected.getModel()).removeElementAt(i);
		}
		
	}
	
	private void confirmer() 
	{
		listDossier = Arrays.asList(((DefaultListModel)this.listDossierSelected.getModel()).toArray());
		
		try
		{
			// enregistrement dans un fichier de configuration des dossiers devant être visualisés
			
			// création du répertoire TeamPlanningMangerConfig si il n'existe pas
			File file = new File(System.getProperty("user.home") + System.getProperty("file.separator") + "TeamPlanningManagerConfig");
			file.mkdir();
			// création du fichier dossiers.cfg
			file = new File(System.getProperty("user.home") + System.getProperty("file.separator") + "TeamPlanningManagerConfig" + System.getProperty("file.separator") + "dossiers.cfg");
			FileOutputStream fos = new FileOutputStream(file);
			StringBuilder builder =  new StringBuilder();
			for(Object o : listDossier)
			{
				((Dossier)o).toString();
				builder.append(((Dossier)o).toString() + System.getProperty("line.separator"));
			}

			fos.write(builder.toString().getBytes());
			fos.flush();
			fos.close();
		}
		catch(IOException e)
		{
			JOptionPane.showMessageDialog(null, "(GestionDossier) erreur IO:  " + e.getMessage());
		}
		
		this.setVisible(false);

	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		switch(arg0.getActionCommand())
		{
		case "AJOUTER": ajouterDossiers();break;
		
		case "ENLEVER" : enleverDossier();break;
		
		case "CONFIRMER" : confirmer();break;
		
		case "ANNULER" : this.setVisible(false);break;
		}
		
	}
	

	public class Model
	{
		public GestionDossier view;
		
		public Model()
		{
			view = GestionDossier.this;
			
			// Chargement des dossiers qui peuvent être vu par l'utilisateur
			SqlTransaction trans = new SqlTransaction();
			try
			{
				Dossier[] dossiers = trans.getAllDossiers(System.getProperty("user.name"));
				if(dossiers != null)
				{
					// affichage des dossiers
					DefaultListModel lm = new DefaultListModel();
					for(Dossier d : dossiers)
						lm.addElement(d);
					
					view.listDossiers.setModel(lm);
				}
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoDossierException e) 
			{
				JOptionPane.showConfirmDialog(null, "Probleme Dossier " + e.getMessage());
			}
			
			
		}
	}

	
}

