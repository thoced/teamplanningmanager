package mainPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Setup 
{
	public static String adresse_serveur_sql;
	public static String nom_database;
	public static String login;
	public static String password;
	
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
		
	}
}
