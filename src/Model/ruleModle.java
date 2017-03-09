package Model;
import java.io.IOException;

import exception.NotValidRuleException;

public class ruleModle
{

	public static void main(String[] args) throws NotValidRuleException
	{
		Rule2D rule = new Rule2D(494,17);
		rule.fillArray();
		try {
			rule.save3DFile("rule494");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
