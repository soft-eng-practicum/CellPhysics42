package Model;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import exception.NotValidRuleException;

public class ruleModle
{

	public static void main(String[] args) throws NotValidRuleException
	{
		Rule2D rule = new Rule2D(494, 17);
		rule.fillArray();
				try {
					rule.save3DFileByLayers("WhileRule454");
					rule.save3DFileByLayers("partRule494", 4, 5);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


//		for (int s = 0; s < rule.getLayers(); s++)
//		{
//			byte[][] print = rule.nextLayer();
//			for (int i = 0; i < print.length; i++)
//			{
//				for(int n = 0; n < print[0].length; n++)
//				{
//					if(print[i][n] == 1)
//						System.out.print("*");
//					else
//						System.out.print(" ");
//				}
//				System.out.println();
//			}
//			System.out.println();
//		}

	}

}
