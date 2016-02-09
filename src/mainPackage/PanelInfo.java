package mainPackage;

import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PanelInfo extends Panel 
{
	private JTable table;
	
	public PanelInfo(LayoutManager arg0) 
	{
		
		super(arg0);
		setLayout(new BorderLayout(0, 0));
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
			},
			new String[] {
				"New column", "New column"
			}
		));
		add(table, BorderLayout.CENTER);
		// TODO Auto-generated constructor stub
	}
	
	
}
