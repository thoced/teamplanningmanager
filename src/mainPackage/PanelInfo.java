package mainPackage;

import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Info;

import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import renderers.ColorCellRenderer;
import renderers.ColorCommentRenderer;
import renderers.ColorStatutRenderer;
import editors.ButtonCellEditor;
import editors.CommentCellEditor;
import exceptions.NoTodoException;

import javax.swing.ListSelectionModel;

public class PanelInfo extends Panel implements ActionListener
{
	private JTable table;
	
	private String name_dossier;
	
	private MyTableModel model;
	
	public PanelInfo(String name_dossier) 
	{
		this.name_dossier = name_dossier;
		
		// affichage du nom du dossier
		this.setName(this.name_dossier);
		
		setLayout(new BorderLayout(0, 0));
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane,BorderLayout.CENTER);
		
		model = new MyTableModel();
		table.setModel(model);
		// renderers
		table.setDefaultRenderer(Color.class, new ColorCellRenderer());
		table.setDefaultRenderer(JScrollPane.class, new ColorCommentRenderer());
		table.getColumnModel().getColumn(4).setCellRenderer(new ColorStatutRenderer()); // statut color
		// editors
		table.setDefaultEditor(JButton.class, new ButtonCellEditor());
		table.setDefaultEditor(JScrollPane.class, new CommentCellEditor());
		
		table.setRowHeight(64);
		table.getColumnModel().getColumn(0).setPreferredWidth(24);
		table.getColumnModel().getColumn(1).setPreferredWidth(256);
	
		
		
		JPanel panelButton = new JPanel();
		add(panelButton, BorderLayout.SOUTH);
		
		JButton buttonAddInfo = new JButton("Cr\u00E9er une nouvelle t\u00E2che");
		buttonAddInfo.setActionCommand("ADD_TODO");
		buttonAddInfo.addActionListener(this);
		panelButton.add(buttonAddInfo);
		
		// setup des Todo
		this.setupTodo();
	}
	
	private void setupTodo()
	{
		// réception des dossiers
		model.removeAll();
		SqlTransaction trans = new SqlTransaction();
		try
		{
			Info[] infos = trans.getAllToDo(this.name_dossier);
			// création du tableau 
			if(infos != null)
			{
				for(Info i : infos)
				{
					model.addRow(i);
					
				}
			}
			
		} catch (ClassNotFoundException | SQLException | NoTodoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void createNewTodo()
	{
		SqlTransaction trans = new SqlTransaction();
		try 
		{
			trans.createNewTodo(this.name_dossier, System.getProperty("user.name"));
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		switch(arg0.getActionCommand())
		{
			case "ADD_TODO" : this.createNewTodo();// créer une nouvelle tache
							  this.setupTodo();   // recharge les tâches
							  this.table.updateUI();
							  break;
		}
		
	}
}
