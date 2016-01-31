package mainPackage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.NoDossierException;
import exceptions.NoProfilException;
import model.Dossier;
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
			}
			
			return dossiers;
		}
		
		throw new NoDossierException();
	}
}
