package mainPackage;

import java.awt.Frame;

import javax.swing.JDialog;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import java.awt.FlowLayout;

import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
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
import java.sql.SQLException;

public class GestionDossier extends JDialog 
{
	private JList listDossiers;
	private JPanel panelChoose;
	private JButton buttonAdd;
	private JButton buttonSub;
	private JList listDossierSelected;
	
	private Model model;
	
	public GestionDossier(Frame arg0, String arg1, boolean arg2) {
		super(arg0, arg1, arg2);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		this.setSize(640, 320);
		this.setLocationRelativeTo(null);
		
		JPanel panelbutton = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelbutton.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(panelbutton, BorderLayout.SOUTH);
		
		JButton buttonAnnuler = new JButton("Annuler");
		panelbutton.add(buttonAnnuler);
		
		JButton buttonOk = new JButton("Confirmer");
		panelbutton.add(buttonOk);
		
		JPanel panelDossier = new JPanel();
		getContentPane().add(panelDossier, BorderLayout.WEST);
		panelDossier.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		listDossiers = new JList();
		JScrollPane scrollPane = new JScrollPane(listDossiers);
		scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		panelDossier.add(scrollPane);
		
		panelChoose = new JPanel();
		getContentPane().add(panelChoose, BorderLayout.CENTER);
		
		buttonSub = new JButton("Enlever");
		panelChoose.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("68px"),},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("23px"),}));
		
		buttonAdd = new JButton("Ajouter");
		panelChoose.add(buttonAdd, "2, 2, center, top");
		panelChoose.add(buttonSub, "2, 4, center, top");
		
		JPanel panelDossierSelected = new JPanel();
		getContentPane().add(panelDossierSelected, BorderLayout.EAST);
		panelDossierSelected.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		listDossierSelected = new JList();
		JScrollPane scrollPane_1 = new JScrollPane(listDossierSelected);
		scrollPane_1.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		panelDossierSelected.add(scrollPane_1);
		
		// init model
		model = new Model();
		
		
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
					for(Dossier d : dossiers)
					{
						System.out.println(d.name);
					}
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

