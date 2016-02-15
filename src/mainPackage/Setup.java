package mainPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import model.Dossier;
import model.Profil;

public class Setup 
{
	public static String adresse_serveur_sql;
	public static String nom_database;
	public static String login;
	public static String password;
	
	// Dossiers
	private static List<Dossier> dossiers;
	
	// Profil
	private static Profil profil;
	
	public static Profil getProfil() {
		return profil;
	}

	public static void setProfil(Profil p) {
		profil = p;
	}
	
	

	public static List<Dossier> getDossiers() {
		return dossiers;
	}

	public Setup() throws IOException
	{
		Properties prop = new Properties();
		
		FileInputStream input = new FileInputStream("./Config/setup.cfg");
		prop.load(input);
		
		// init variable
		adresse_serveur_sql = prop.getProperty("adresse_serveur_sql");
		nom_database = prop.getProperty("nom_database");
		login = prop.getProperty("login");
		password = prop.getProperty("password");
		
		// Chargement des dossiers pouvant être vu par l'utilisteur
		// ouverture du fichier dossiers.cfg
				File file = new File(System.getProperty("user.home") + System.getProperty("file.separator") + "TeamPlanningManagerConfig" + System.getProperty("file.separator") + "dossiers.cfg");
				FileInputStream fis;
				try
				{
					fis = new FileInputStream(file);
					byte[] buffer = new byte[fis.available()];
					fis.read(buffer);
					String builder =  new String(buffer);
					builder.trim();
					String[] split = builder.split(System.getProperty("line.separator"));
					
					
					dossiers = new ArrayList();
					for(String s : split)
					{
						
						if(s.length() != 0 || !s.isEmpty())
						{
							Dossier d = new Dossier();
							d.name = s;
							dossiers.add(d);
						}
					}
					
					
					fis.close();
					
					
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		
	}
	
	
}
