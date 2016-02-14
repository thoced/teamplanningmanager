package exceptions;

public class NoTodoException extends Exception 
{
	@Override
	public String getMessage()
	{
		// TODO Auto-generated method stub
		return "Aucun Todo trouvé";
	}
}
