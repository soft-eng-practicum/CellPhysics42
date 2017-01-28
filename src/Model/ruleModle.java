package Model;
import exception.NotValidRuleException;

public class ruleModle
{

	public static void main(String[] args)
	{
		rule1D rule = new rule1D(90);
		System.out.println(rule.toString());
		
		
		try
		{
			System.out.println(rule.isOn((byte)1,(byte)1,(byte)1));
			System.out.println(rule.isOn((byte)1,(byte)1,(byte)0));
			System.out.println(rule.isOn((byte)1,(byte)0,(byte)1));
			System.out.println(rule.isOn((byte)1,(byte)0,(byte)0));
			System.out.println(rule.isOn((byte)0,(byte)1,(byte)1));
			System.out.println(rule.isOn((byte)0,(byte)1,(byte)0));
			System.out.println(rule.isOn((byte)0,(byte)0,(byte)1));
			System.out.println(rule.isOn((byte)0,(byte)0,(byte)0));
		} catch (NotValidRuleException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
