package editors;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;


public class ButtonCellEditor extends AbstractCellEditor implements TableCellEditor,ActionListener {

	private JButton button;
	
	public ButtonCellEditor() {
		super();
		// TODO Auto-generated constructor stub
		button = new JButton();
		button.addActionListener(this);
	}

	@Override
	public Object getCellEditorValue()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Component getTableCellEditorComponent(JTable arg0, Object arg1,
			boolean arg2, int arg3, int arg4) 
	{
		
		return button;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{

		this.fireEditingStopped();
		JOptionPane.showMessageDialog(null, "test");
		
	}

}
