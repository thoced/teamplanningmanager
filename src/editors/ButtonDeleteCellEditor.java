package editors;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import exceptions.NoTodoException;
import mainPackage.MainApp;
import mainPackage.MyTableModel;
import mainPackage.SqlTransaction;
import model.Info;
import renderers.ButtonDeleteRenderer;

public class ButtonDeleteCellEditor extends AbstractCellEditor implements TableCellEditor,ActionListener {

	private JButton buttonDelete;
	private ImageIcon icon = new ImageIcon(ButtonDeleteRenderer.class.getResource("/images/corbeille-windows.png"));
	private int numRow = -1;
	private JTable currentTable = null;
	
	
	public ButtonDeleteCellEditor() {
		super();
		buttonDelete = new JButton(icon);
		buttonDelete.setContentAreaFilled(true);
		buttonDelete.setBorderPainted(true);
		buttonDelete.addActionListener(this);
	}
	
	

	@Override
	public Object getCellEditorValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		
		this.cancelCellEditing();
		
		// si numRow est toujours à -1, on sort de la fonction
		if(this.numRow < 0 || this.currentTable == null)
			return;
		
		// demande de confirmation de suppression
		int ret = JOptionPane.showConfirmDialog(MainApp.frame, "Etes-vous sûr de vouloir supprimer la tâche numéro:" + (this.numRow +1)  + " ?","Suppression de tâche",0);
		if(ret == JOptionPane.OK_OPTION)
		{
			// récupération dans la table de l'Info
			try 
			{
				Info info = ((MyTableModel)this.currentTable.getModel()).getRow(this.numRow);
				if(info != null)
				{
					info.deleteData(); // suppression de l'objet sql
					((MyTableModel)this.currentTable.getModel()).removeAll();
					((MyTableModel)this.currentTable.getModel()).setupTodo();
					this.currentTable.updateUI();
					
					
				}
				
			} catch (NoTodoException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(MainApp.frame, "(ButtonDeleteCellEditor) Probleme de réception de l'info: " + e1.getMessage());
			}
			
			
		}
		
		
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object arg1,
			boolean arg2, int row, int arg4) 
	{
		// TODO Auto-generated method stub
		this.cancelCellEditing();
		// place du numéro de row en mémoire
		this.numRow = row;
		// placementd de la table en mémoire
		this.currentTable = table;
		// return du button
		return buttonDelete;
	}

}
