package renderers;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ColorCellRenderer extends DefaultTableCellRenderer 
{

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) 
	{
	
		this.setText("");
		
		this.setBackground((Color) value);
		
		return this;
	}

	

}
