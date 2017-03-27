package Test;

import org.junit.Assert;
import org.junit.Test;

import Model.Rule2D;
import exception.NotValidRuleException;

public class Rule2DTest {
	//	 452, 453, 454, 457, 459, 460, 461, 462, 465, 467, 468, 469, 470 +
	//	473, 475, 476, 478, 481, 483, 484, 485, 486, 489, 491, 492, 493, 494, 497
	@Test
	public void getLayer451Test() throws NotValidRuleException
	{
		Rule2D rule = new Rule2D(451, 4);
		rule.fillArray();
		printAray(rule.layer(0));
		Assert.assertArrayEquals(new byte[][]{{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,1,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0}
		}, rule.layer(0));

		printAray(rule.layer(1));
		Assert.assertArrayEquals(new byte[][]{{0,0,0,0,0,0,0,0,0},
			{0,1,1,1,1,1,1,1,0},
			{0,1,1,1,1,1,1,1,0},
			{0,1,1,1,0,1,1,1,0},
			{0,1,1,0,1,0,1,1,0},
			{0,1,1,1,0,1,1,1,0},
			{0,1,1,1,1,1,1,1,0},
			{0,1,1,1,1,1,1,1,0},
			{0,0,0,0,0,0,0,0,0}
		}, rule.layer(1));

		printAray(rule.layer(2));
		Assert.assertArrayEquals(new byte[][]{{0,0,0,0,0,0,0,0,0},
			{0,0,1,1,1,1,1,0,0},
			{0,1,0,0,1,0,0,1,0},
			{0,1,0,0,1,0,0,1,0},
			{0,1,1,1,1,1,1,1,0},
			{0,1,0,0,1,0,0,1,0},
			{0,1,0,0,1,0,0,1,0},
			{0,0,1,1,1,1,1,0,0},
			{0,0,0,0,0,0,0,0,0}
		}, rule.layer(2));

		printAray(rule.layer(3));
		Assert.assertArrayEquals(new byte[][]{{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,1,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,1,0,0,0,0,0,1,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,1,0,0,0,0},
			{0,0,0,0,0,0,0,0,0}
		}, rule.layer(3));

	}
	
	@Test
	public void getLastLayer() throws NotValidRuleException
	{
		
		Rule2D rule = new Rule2D(451, 4);
		rule.fillArray();
		printAray(rule.layer(3));
		Assert.assertArrayEquals(new byte[][]{{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,1,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,1,0,0,0,0,0,1,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,1,0,0,0,0},
			{0,0,0,0,0,0,0,0,0}
		}, rule.lastLayer());
	}


	@Test
	public void getLayerSOMENUMBERTest() throws NotValidRuleException {
		//make for 2 rules 5 steps
	}

	@Test
	public void getNextLayerSOMENUMBERTest() throws NotValidRuleException{
		Rule2D rule = new Rule2D(451, 4);
		rule.fillArray();
		printAray(rule.layer(0));
		Assert.assertArrayEquals(new byte[][]{{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,1,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0}
		}, rule.nextLayer());

		printAray(rule.layer(1));
		Assert.assertArrayEquals(new byte[][]{{0,0,0,0,0,0,0,0,0},
			{0,1,1,1,1,1,1,1,0},
			{0,1,1,1,1,1,1,1,0},
			{0,1,1,1,0,1,1,1,0},
			{0,1,1,0,1,0,1,1,0},
			{0,1,1,1,0,1,1,1,0},
			{0,1,1,1,1,1,1,1,0},
			{0,1,1,1,1,1,1,1,0},
			{0,0,0,0,0,0,0,0,0}
		}, rule.nextLayer());

		printAray(rule.layer(2));
		Assert.assertArrayEquals(new byte[][]{{0,0,0,0,0,0,0,0,0},
			{0,0,1,1,1,1,1,0,0},
			{0,1,0,0,1,0,0,1,0},
			{0,1,0,0,1,0,0,1,0},
			{0,1,1,1,1,1,1,1,0},
			{0,1,0,0,1,0,0,1,0},
			{0,1,0,0,1,0,0,1,0},
			{0,0,1,1,1,1,1,0,0},
			{0,0,0,0,0,0,0,0,0}
		}, rule.nextLayer());

		printAray(rule.layer(3));
		Assert.assertArrayEquals(new byte[][]{{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,1,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,1,0,0,0,0,0,1,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,1,0,0,0,0},
			{0,0,0,0,0,0,0,0,0}
		}, rule.nextLayer());

	}
	
	@Test
	public void getRulesTest()
	{
		int[] rules = {451, 452, 453, 454, 457, 459, 460, 461, 462, 465, 467, 468, 469, 470, 473, 475, 476,
				478, 481, 483, 484, 485, 486, 489, 491, 492, 493, 494, 497 };
		Rule2D rule = new Rule2D();
		int[] returnRules = rule.getRules();
		
		Assert.assertArrayEquals(rules , returnRules);
	}

	@Test
	public void save3DTest(){
		//I have not figured out how to test this just yet....probably read the text file
	}

	private void printAray(byte[][] layer) {

		for (int n = 0 ; n < layer.length; n++)
		{
			for(int i = 0; i < layer[1].length; i++)
			{
				System.out.print(layer[i][n]);
			}
			System.out.println();
		}
		System.out.println();
	}

}
