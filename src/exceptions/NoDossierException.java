package exceptions;

public class NoDossierException extends Exception 
{

	@Override
	public String getMessage()
	{
		// TODO Auto-generated method stub
		return "Aucun dossier trouvé";
	}

}
