package editors;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import renderers.ButtonDeleteRenderer;

public class ButtonDeleteCellEditor extends AbstractCellEditor implements TableCellEditor,ActionListener {

	private JButton buttonDelete;
	private ImageIcon icon = new ImageIcon(ButtonDeleteRenderer.class.getResource("/images/corbeille-windows.png"));
	
	
	public ButtonDeleteCellEditor() {
		super();
		buttonDelete = new JButton(icon);
		buttonDelete.setContentAreaFilled(true);
		buttonDelete.setBorderPainted(true);
	}

	@Override
	public Object getCellEditorValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Component getTableCellEditorComponent(JTable arg0, Object arg1,
			boolean arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
		return buttonDelete;
	}

}
