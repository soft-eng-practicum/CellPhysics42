package Model;
import java.io.IOException;

import exception.NotValidRuleException;

public class ruleModle
{

	public static void main(String[] args) throws NotValidRuleException
	{
		Rule2D rule = new Rule2D();
		int[] rules = rule.getRules();
		String fileName;

		for(int rl = 0; rl < rules.length; rl++)
		{
			rule.setRule(rules[rl]);
			rule.setLayers(17);
			try {

				rule.fillArray();
				byte[][] test = rule.lastLayer();

				for (int n = 0 ; n < test.length; n++)
				{
					for(int i = 0; i < test[1].length; i++)
					{
						if(test[i][n] == 1)
							System.out.print("*");
						else
							System.out.print(" ");
					}
					System.out.println();
				}
				fileName = "Rule" + rules[rl]+"layers17.scad";
				rule.save3DFile(fileName);

			} catch (NotValidRuleException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}
	}

}
