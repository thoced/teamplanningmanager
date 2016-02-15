package renderers;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import model.Statut;

public class ButtonDeleteRenderer extends DefaultTableCellRenderer 
{
	private JButton button;
	private ImageIcon icon = new ImageIcon(ButtonDeleteRenderer.class.getResource("/images/corbeille-windows.png"));
	

	public ButtonDeleteRenderer() 
	{
		super();
		button = new JButton(icon);
		
	}



	@Override
	public Component getTableCellRendererComponent(JTable arg0, Object arg1,
			boolean arg2, boolean arg3, int arg4, int arg5)
	{
		// on récupère le statut
		if(((Statut)arg1).statut == false) // tache toujours en cours
		{
			button.setEnabled(false);
			
		}
		else
			button.setEnabled(true);
		
		return button;
	}



	
	
}
