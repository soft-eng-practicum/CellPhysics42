package exception;


import java.io.IOException;

public class NotValidRuleException extends IOException
{

	/**NotValidRuleException
	 * 
	 * no-agr constructor
	 *
	 * creates a NotValidRuleException object with no message
	 */
	public NotValidRuleException()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**NotValidRuleException
	 * @param message
	 * 1-agr constructor
	 *
	 * creates a NotValidRuleException object with the given String as a message
	 */
	public NotValidRuleException(String message)
	{
		super(message);
		// TODO Auto-generated constructor stub
	}
	
}
