package model;

import java.sql.Date;
import java.sql.SQLException;

import mainPackage.SqlTransaction;

public class Info 
{
	public int id_sql; // num id dans la base de donn�e
	public int id_seq; // num id d'affichage
	
	
	public String text;
	public String owner; //  celui qui a cr�� l'info
	public String profil_cloture; // celui qui a clotur� l'info
	public Date   date_create; // date de cr�ation du texte
	public Date   date_cloture; // date cloture
	public boolean statut;  // en cours ou termin�
	
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
	
}
