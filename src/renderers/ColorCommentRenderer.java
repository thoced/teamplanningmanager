package renderers;


import java.awt.Color;
import java.awt.Component;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ColorCommentRenderer extends DefaultTableCellRenderer 
{
	private Color color_background;
	private Color color_text;
	
	public ColorCommentRenderer()
	{
		super();
		color_background = new Color(238,238,238);
		color_text = new Color(96,96,96);
	}

	@Override
	public Component getTableCellRendererComponent(JTable arg0, Object arg1,
			boolean arg2, boolean arg3, int arg4, int arg5)
	{
		this.setText((String)arg1);
		this.setBackground(color_background);
		this.setForeground(color_text);
		this.setAutoscrolls(true);
		
		
		
		return this;
	}
		

}
