package exceptions;

public class NoProfilException extends Exception 
{

	@Override
	public String getMessage() 
	{
		return "Le profil n'existe pas";
	}

}
