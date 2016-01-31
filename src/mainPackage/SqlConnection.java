package mainPackage;
import java.sql.*;

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
	
	
	
}
