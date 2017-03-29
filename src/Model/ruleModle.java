package Model;
import java.io.IOException;

import exception.NotValidRuleException;

public class ruleModle
{

	public static void main(String[] args) throws NotValidRuleException
	{
		Rule2D rule = new Rule2D(452,32);
		rule.fillArray();
//		try {
//			rule.save3DFileByLayers("rule454");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		byte[][] print = rule.lastLayer();
		
		for (int i = 0; i < print.length; i++)
		{
			for(int n = 0; n < print[0].length; n++)
			{
				if(print[i][n] == 1)
					System.out.print("*");
				else
					System.out.print(" ");
			}
			System.out.println();
		}
		
//		Rule1D test = new Rule1D(90,2,2);
//		test.fillArrays();

	}

}
