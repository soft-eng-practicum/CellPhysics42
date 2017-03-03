package Model;
import exception.NotValidRuleException;

public class ruleModle
{

	public static void main(String[] args)
	{

		try {
			Rule2D rule = new Rule2D(451, 4);
			rule.fillArray();
			for(int t = 0; t < 4; t++)
			{
			byte[][] test = rule.nextLayer();
			
			for (int n = 0 ; n < test.length; n++)
			{
				for(int i = 0; i < test[1].length; i++)
				{
					System.out.print(test[i][n]);
				}
				System.out.println();
			}
			}
			
			
			
		} catch (NotValidRuleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
