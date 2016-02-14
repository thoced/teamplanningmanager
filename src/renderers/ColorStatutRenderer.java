package renderers;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ColorStatutRenderer extends DefaultTableCellRenderer 
{

	private Font font;
	
	public ColorStatutRenderer()
	{
		super();
		font = new Font(" TimesRoman ",Font.PLAIN,10);
		this.setFont(font);
		
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) 
	
	{
		if((boolean)value)
		{
			this.setBackground(Color.GREEN);
			this.setText("T�che clotur�e");
		
		}
		else
		{
			this.setBackground(Color.ORANGE);
			this.setText("T�che en cours");
		}
		
		return this;
	}
	
	
}
