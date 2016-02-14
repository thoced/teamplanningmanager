package mainPackage;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

import model.Info;

public class MyTableModel extends DefaultTableModel 
{
	// tableau de donn�e
	private List<Info> infos = new ArrayList<Info>();
	// nom des columns
	private String[] columns_name = {"num","todo","cr�ateur","date de cr�ation","statut","action"};

	
	
	
	@Override
	public void setValueAt(Object value, int row, int column) 
	{
		Info info = infos.get(row);
		if(info != null)
		{
			switch(column)
			{
			case 1 : info.text = ((JTextField)value).getText();
			break;
			
			default : break;
			}
			
			// update dans la base de donn�e
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
				
		if(column == 1 || column == 5)
			return true;
		else
			return false;
	}

	@Override
	public Class<?> getColumnClass(int arg0)
	{
		switch(arg0)
		{
		case 0 : return JLabel.class;
		
		case 1: return JTextField.class;
		
		case 2: return  JLabel.class;
		
		case 3: return JLabel.class;
		
		case 4: return Color.class;
		
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
		
		case 4: { if( infos.get(row).statut)
					return Color.GREEN;
				  else
					return Color.ORANGE;
			
				}
		
		case 5: return "test";
		
		default : return null;
		}
	}
	
	
	
}
