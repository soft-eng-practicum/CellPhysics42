package Model;
import exception.NotValidRuleException;

public class ruleModle
{

	public static void main(String[] args)
	{


		try
		{
			Rule1D rule = new Rule1D(90);
			System.out.println(rule.toString());
			System.out.println(rule.isOn((byte)1,(byte)1,(byte)1));
			System.out.println(rule.isOn((byte)1,(byte)1,(byte)0));
			System.out.println(rule.isOn((byte)1,(byte)0,(byte)1));
			System.out.println(rule.isOn((byte)1,(byte)0,(byte)0));
			System.out.println(rule.isOn((byte)0,(byte)1,(byte)1));
			System.out.println(rule.isOn((byte)0,(byte)1,(byte)0));
			System.out.println(rule.isOn((byte)0,(byte)0,(byte)1));
			System.out.println(rule.isOn((byte)0,(byte)0,(byte)0));
			rule.fillArrays();


			for (int x = 0; x < rule.getLength(); x++)
			{
				byte[] fill  = rule.getNextFillLine(x);
				byte[] state = rule.getNextStateLine(x);
				for (int i = 0; i < fill.length; i ++)
				{
					System.out.print(fill[i]);
				}
				System.out.println("");
			}
			
			for (int x = 0; x < rule.getLength(); x++)
			{
				byte[][] fill  = rule.getNextLine(x);
				
				for (int i = 0; i < 2; i ++)
				{
					for (int j = 0; j < 40; j ++)
					{
					System.out.print(fill[i][j]);
					}
				}
				System.out.println("");
			}
			

		} catch (NotValidRuleException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
