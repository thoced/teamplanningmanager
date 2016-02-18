package mainPackage;
import java.sql.*;

import javax.swing.JOptionPane;

public class SqlConnection
{
	private static Connection connection;
	
	public void Connection() throws ClassNotFoundException, SQLException
	{
		if(connection == null || connection.isClosed())
		{
			Class.forName("com.mysql.jdbc.Driver");
			//System.out.println(Setup.adresse_serveur_sql + Setup.login + Setup.nom_database);
			connection = DriverManager.getConnection("jdbc:mysql://" + Setup.adresse_serveur_sql + "/" + Setup.nom_database ,Setup.login,Setup.password);
		}
			
		
	}
	
	public void Close() throws SQLException
	{
		if(connection != null && !connection.isClosed())
		{
			connection.close();
		}
		
	}
	
	
	
	protected PreparedStatement getStatement(String sql) throws SQLException, ClassNotFoundException
	{
		this.Connection();
		return connection.prepareStatement(sql);
	}
	
	protected ResultSet ExecuteRequest(PreparedStatement ps) throws SQLException
	{
		return ps.executeQuery();
	}
	
	protected void ExecuteTransaction(PreparedStatement ps) throws SQLException
	{
		ps.executeUpdate();
	}
	
	protected void SetAutoCommit(boolean v)
	{
		try 
		{
			connection.setAutoCommit(v);
		} catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(MainApp.frame, "(SqlConnection) probleme de modification de l'auto-commit : " + e.getMessage());
		}
	}
	
	protected void ForceCommit()
	{
		try {
			connection.commit();
		} catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(MainApp.frame, "(SqlConnection) probleme lors du ForceCommit : " + e.getMessage());
		}
	}
	
}
