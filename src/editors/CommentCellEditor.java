package editors;

import java.awt.Color;
import java.awt.Component;
import java.awt.ScrollPane;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractCellEditor;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;

public class CommentCellEditor extends AbstractCellEditor implements TableCellEditor
{
	private JScrollPane scroll;
	private JTextPane commentTodo;

	public CommentCellEditor() 
	{
		super();
		commentTodo=  new JTextPane();
		scroll = new JScrollPane(commentTodo);
		
		
	}

	@Override
	public Object getCellEditorValue() 
	{
		// TODO Auto-generated method stub
		
		return commentTodo.getText();
	}

	@Override
	public Component getTableCellEditorComponent(JTable arg0, Object value,
			boolean arg2, int row, int column)
	{
		commentTodo.setText((String)value);
		
		return scroll;
		
	}

	

}
