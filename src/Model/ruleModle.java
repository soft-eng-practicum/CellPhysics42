package Model;
import exception.NotValidRuleException;

public class ruleModle
{

	public static void main(String[] args)
	{
//		try
//		{
			
//			System.out.println(rule.toString());
//			System.out.println(rule.isOn((byte)1,(byte)1,(byte)1));
//			System.out.println(rule.isOn((byte)1,(byte)1,(byte)0));
//			System.out.println(rule.isOn((byte)1,(byte)0,(byte)1));
//			System.out.println(rule.isOn((byte)1,(byte)0,(byte)0));
//			System.out.println(rule.isOn((byte)0,(byte)1,(byte)1));
//			System.out.println(rule.isOn((byte)0,(byte)1,(byte)0));
//			System.out.println(rule.isOn((byte)0,(byte)0,(byte)1));
//			System.out.println(rule.isOn((byte)0,(byte)0,(byte)0));
//			rule.fillArrays();
//
//
//			for (int x = 0; x < rule.getLength(); x++)
//			{
//				byte[] fill  = rule.getFillLine(x);
//				byte[] state = rule.getStateLine(x);
//				for (int i = 0; i < fill.length; i ++)
//				{
//					System.out.print(fill[i]);
//				}
//				System.out.println("");
//			}
//
//			for (int x = 0; x < rule.getLength(); x++)
//			{
//				byte[][] fill  = rule.getLine(x);
//
//				for (int i = 0; i < 2; i ++)
//				{
//					for (int j = 0; j < 40; j ++)
//					{
//					System.out.print(fill[i][j]);
//					}
//				}
//				System.out.println("");
//			}
//
//		} catch (NotValidRuleException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		try {
			Rule1D rule = new Rule1D(22, 101, 125);
			rule.fillArrays();
			
			for(int i = 0; i < 125; i++)
			{
				byte stat[] = rule.getFillLine(i);
				for(int n = 0; n < 101; n++)
				{
					System.out.print(stat[n]+",");
				}
				System.out.println();
			}
			
		} catch (NotValidRuleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
