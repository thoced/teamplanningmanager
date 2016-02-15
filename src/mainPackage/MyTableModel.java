package mainPackage;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

import model.Info;
import model.Statut;

public class MyTableModel extends DefaultTableModel 
{
	// tableau de donnée
	private List<Info> infos = new ArrayList<Info>();
	// nom des columns
	private String[] columns_name = {"num","todo","créateur","date de création","statut","action"};

	
	
	
	@Override
	public void setValueAt(Object value, int row, int column) 
	{
		Info info = infos.get(row);
		if(info != null)
		{
			switch(column)
			{
				case 1 : info.text = (String)value;
				break;
				
				case 4: info.statut = (boolean)value;
						info.profil_cloture = Setup.getProfil().nom;
						info.date_cloture = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
						break;
				
				default : break;
			}
			
			// update dans la base de donnée	
			info.updateData();
		}
		
	}

	public void addRow(Info info)
	{
		if(infos != null)
			infos.add(info);
	}
	
	public void removeAll()
	{
		if(infos != null)
			infos.clear();
	}

	@Override
	public int getColumnCount() 
	{
		return columns_name.length;
	}

	@Override
	public String getColumnName(int arg0) 
	{
		return columns_name[arg0];
	}

	@Override
	public int getRowCount() 
	{
		if(infos != null)
			return infos.size();
		else
			return super.getRowCount();
	}

	@Override
	public boolean isCellEditable(int row, int column) 
	{
		if(infos.get(row).statut == false) // si le statut est toujours false, on peut accédé à certain champs
		{
				
			if(column == 1 || column == 4)
				return true;
			else
				return false;
		}
		else
		{
			if(column == 5)
				return true;
			else
				return false; // toute la fiche est innacessible
		}
	}

	@Override
	public Class<?> getColumnClass(int arg0)
	{
		switch(arg0)
		{
		case 0 : return JLabel.class;
		
		case 1: return JScrollPane.class;
		
		case 2: return  JLabel.class;
		
		case 3: return JLabel.class;
		
		case 4: return JButton.class;
		
		case 5: return JButton.class;
		
		default: return JLabel.class;
		}
		
		
	}

	@Override
	public Object getValueAt(int row, int column) 
	{
		
		switch(column)
		{
		case 0 : return row + 1;
		
		case 1 : return infos.get(row).text;
		
		case 2 : return infos.get(row).owner;
		
		case 3 : return  infos.get(row).date_create.toString();
		
		case 4:  Statut statut = new Statut(infos.get(row).statut,infos.get(row).profil_cloture);
				 return statut;		
		
		case 5: statut = new Statut(infos.get(row).statut,infos.get(row).profil_cloture);
		 		return statut;
		
		default : return null;
		}
	}
	
	
	
}
