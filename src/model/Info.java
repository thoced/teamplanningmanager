package model;

import java.sql.Date;
import java.sql.SQLException;

import mainPackage.SqlTransaction;

public class Info 
{
	public int id_sql; // num id dans la base de donnée
	public int id_seq; // num id d'affichage
	
	
	public String text;
	public String owner; //  celui qui a créé l'info
	public String profil_cloture; // celui qui a cloturé l'info
	public Date   date_create; // date de création du texte
	public Date   date_cloture; // date cloture
	public boolean statut;  // en cours ou terminé
	
	public void updateData()
	{
		
		
		SqlTransaction trans = new SqlTransaction();
		try {
			trans.updateTodo(id_sql, this);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteData()
	{
		// suppression de la tâche en SQL
		SqlTransaction trans = new SqlTransaction();
		try 
		{
		trans.deleteTodo(id_sql);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
