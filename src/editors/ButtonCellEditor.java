package editors;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import mainPackage.MainApp;
import model.Statut;


public class ButtonCellEditor extends AbstractCellEditor implements TableCellEditor,ActionListener {

	private JButton button;
	private boolean value = false;;
	
	public ButtonCellEditor() {
		super();
		// TODO Auto-generated constructor stub
		button = new JButton();
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.addActionListener(this);
	}

	@Override
	public Object getCellEditorValue()
	{
		// TODO Auto-generated method stub
		return value;
	}

	@Override
	public Component getTableCellEditorComponent(JTable arg0, Object arg1,
			boolean arg2, int arg3, int arg4) 
	{
		value = ((Statut)arg1).statut;
		return button;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		
		int ret = JOptionPane.showConfirmDialog(MainApp.frame, "Etes-vous sûr de cloturer la tâche ? - Une fois cloturée, la tâche ne peut plus être modifiée","Cloture de la tâche",0);
		if(ret == JOptionPane.OK_OPTION)
			{
				value=!value;
				
			}
		this.fireEditingStopped();
		
		
		
		
		
	}

}
