package mainPackage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import exceptions.NoDossierException;
import exceptions.NoProfilException;
import exceptions.NoTodoException;
import model.Dossier;
import model.Info;
import model.Profil;

public class SqlTransaction extends SqlConnection 
{
	public Profil checkProfil(String user) throws NoProfilException
	{
		Profil profil = null;
		String sql = "select * from t_profil where user = ?";
		
		try 
		{
			PreparedStatement ps = this.getStatement(sql);
			ps.setString(1, user);
			ResultSet result = this.ExecuteRequest(ps);
			
			if(result != null)
			{
				profil = new Profil();
				while(result.next())
				{
				
					profil.user = result.getString("user");
					profil.nom = result.getString("nom");
					profil.prenom = result.getString("prenom");
					profil.ip = result.getString("ip");
					profil.last_time_ip = result.getDate("last_time_ip");
					profil.level = result.getString("level");
					return profil;
				}
				
				
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		throw new NoProfilException();
		
	
		
	}
	
	public Dossier[] getAllDossiers(String user) throws ClassNotFoundException, SQLException, NoDossierException
	{
		
		
		String sql = "select * from t_dossier where group_owner IN (select t_group_groupname from rel_profil_group where t_profil_user = ?)";
		
		// récupération du ps
		PreparedStatement ps = this.getStatement(sql);
		ps.setString(1, user);
		ResultSet result = this.ExecuteRequest(ps);
		if(result != null)
		{
			// on va a la fin du result pour savoir le nombre
			result.last();
			int nb = result.getRow();
			Dossier[] dossiers = new Dossier[nb];
			int cpt = 0;
			result.beforeFirst();
			
			while(result.next())
			{
				Dossier dossier = new Dossier();
				dossier.name = result.getString("name");
				dossier.pv = result.getString("pv");
				dossier.profil_owner = result.getString("profil_owner");
				dossier.group_owner = result.getString("group_owner");
				dossier.dossier_instruction = result.getString("dossier_instruction");
				dossier.comment = result.getString("comment");
				dossiers[cpt] = dossier;
				cpt++;
			}
			
			return dossiers;
			
		}
		
		throw new NoDossierException();
	}
	
	public Info[] getAllToDo(String name_dossier) throws ClassNotFoundException, SQLException, NoTodoException
	{
		String sql = "select * from t_todo where t_dossier_id = (select id from t_dossier where name = ?)";
		// récupération du ps
		PreparedStatement ps = this.getStatement(sql);
		ps.setString(1, name_dossier);
		ResultSet result = this.ExecuteRequest(ps);
		int cpt = 0;
		
		if(result != null)
		{
		// on va a la fin du result pour savoir le nombre
		result.last();
		int nb = result.getRow();
		Info[] infos = new Info[nb];
		result.beforeFirst();
		
		while(result.next())
		{
			Info info = new Info();
			info.id_sql = result.getInt("id");
			info.owner = result.getString("profil_owner");
			info.date_create = result.getDate("datestart");
			info.statut = result.getBoolean("isfinish");
			info.text = result.getString("comment");
			infos[cpt] = info;
			cpt++;
		}
		
		return infos;
		
		}
		
		throw new NoTodoException();
		
		
	}
	
	public void createNewTodo(String name_dossier,String profil_owner) throws ClassNotFoundException, SQLException
	{
		String sql = "insert into t_todo (t_dossier_id,datestart,profil_owner,isfinish) select id,?,?,? from t_dossier where name = ?";
		
		// récupération du ps
		PreparedStatement ps = this.getStatement(sql);
		ps.setDate(1, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
		ps.setString(2, profil_owner);
		ps.setBoolean(3, false);
		ps.setString(4, name_dossier);
	
		
		ps.executeUpdate();
	}
	
	public void updateTodo(int id,Info info) throws ClassNotFoundException, SQLException
	{
		String sql = "update t_todo set datestart = ?,isfinish = ?,comment = ? where id = ?";
		
		// récupération du ps
		PreparedStatement ps = this.getStatement(sql);
		ps.setDate(1, info.date_create);
		ps.setBoolean(2, info.statut);
		ps.setString(3, info.text);
		ps.setInt(4, id);
			
				
		ps.executeUpdate();
	}
}
