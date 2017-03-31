package Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
		Assert.assertArrayEquals(new byte[][]{{1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1},
			{1,1,1,1,0,1,1,1,1},
			{1,1,1,0,1,0,1,1,1},
			{1,1,1,1,0,1,1,1,1},
			{1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1}
		}, rule.layer(1));

		printAray(rule.layer(2));
		Assert.assertArrayEquals(new byte[][]{{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,1,0,0,0,0},
			{0,0,0,0,1,0,0,0,0},
			{0,0,1,1,1,1,1,0,0},
			{0,0,0,0,1,0,0,0,0},
			{0,0,0,0,1,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0}
		}, rule.layer(2));

		printAray(rule.layer(3));
		Assert.assertArrayEquals(new byte[][]{{1,1,1,1,1,1,1,1,1},
			{1,1,1,1,0,1,1,1,1},
			{1,1,1,0,0,0,1,1,1},
			{1,1,0,0,0,0,0,1,1},
			{1,0,0,0,0,0,0,0,1},
			{1,1,0,0,0,0,0,1,1},
			{1,1,1,0,0,0,1,1,1},
			{1,1,1,1,0,1,1,1,1},
			{1,1,1,1,1,1,1,1,1}
		}, rule.layer(3));

	}

	@Test
	public void getLastLayer() throws NotValidRuleException
	{

		Rule2D rule = new Rule2D(451, 4);
		rule.fillArray();
		printAray(rule.layer(3));
		Assert.assertArrayEquals(new byte[][]{{1,1,1,1,1,1,1,1,1},
			{1,1,1,1,0,1,1,1,1},
			{1,1,1,0,0,0,1,1,1},
			{1,1,0,0,0,0,0,1,1},
			{1,0,0,0,0,0,0,0,1},
			{1,1,0,0,0,0,0,1,1},
			{1,1,1,0,0,0,1,1,1},
			{1,1,1,1,0,1,1,1,1},
			{1,1,1,1,1,1,1,1,1}
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
		Assert.assertArrayEquals(new byte[][]{{1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1},
			{1,1,1,1,0,1,1,1,1},
			{1,1,1,0,1,0,1,1,1},
			{1,1,1,1,0,1,1,1,1},
			{1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1}
		}, rule.nextLayer());

		printAray(rule.layer(2));
		Assert.assertArrayEquals(new byte[][]{{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,1,0,0,0,0},
			{0,0,0,0,1,0,0,0,0},
			{0,0,1,1,1,1,1,0,0},
			{0,0,0,0,1,0,0,0,0},
			{0,0,0,0,1,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0}
		}, rule.nextLayer());

		printAray(rule.layer(3));
		Assert.assertArrayEquals(new byte[][]{{1,1,1,1,1,1,1,1,1},
			{1,1,1,1,0,1,1,1,1},
			{1,1,1,0,0,0,1,1,1},
			{1,1,0,0,0,0,0,1,1},
			{1,0,0,0,0,0,0,0,1},
			{1,1,0,0,0,0,0,1,1},
			{1,1,1,0,0,0,1,1,1},
			{1,1,1,1,0,1,1,1,1},
			{1,1,1,1,1,1,1,1,1}
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
		boolean fileFound = false;
		Rule2D rule;

		try {
			rule = new Rule2D(451, 4);
			rule.fillArray();
			rule.save3DFile("Rule451");
		} catch (NotValidRuleException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		File file = new File("Rule451.scad");

		if(file.exists())
		{
			fileFound = true;
		}

		file.delete();
		Assert.assertTrue(fileFound);
	}

	@Test
	public void save3DTestByLayerEven(){
		boolean fileFoundL1 = false;
		boolean fileFoundL2 = false;
		Rule2D rule;

		try {
			rule = new Rule2D(451, 4);
			rule.fillArray();
			rule.save3DFileByLayers("Rule451");
		} catch (NotValidRuleException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		File fileL1 = new File("Rule451Layers0-1.scad");
		File fileL2 = new File("Rule451Layers2-3.scad");

		if(fileL1.exists())
		{
			fileFoundL1 = true;
		}

		if(fileL2.exists())
		{
			fileFoundL2 = true;
		}

		fileL1.delete();
		fileL2.delete();
		Assert.assertTrue(fileFoundL1);
		Assert.assertTrue(fileFoundL2);
	}

	@Test
	public void save3DTestByLayerOdd(){
		boolean fileFoundL1 = false;
		boolean fileFoundL2 = false;
		Rule2D rule;

		try {
			rule = new Rule2D(451, 5);
			rule.fillArray();
			rule.save3DFileByLayers("Rule451");
		} catch (NotValidRuleException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		File fileL1 = new File("Rule451Layers1-3.scad");
		File fileL2 = new File("Rule451Layers4-5.scad");

		if(fileL1.exists())
		{
			fileFoundL1 = true;
		}

		if(fileL2.exists())
		{
			fileFoundL2 = true;
		}

		fileL1.delete();
		fileL2.delete();
		Assert.assertTrue(fileFoundL1);
		Assert.assertTrue(fileFoundL2);
	}

	@Test
	public void setAndGetLayers()
	{
		Rule2D rule = null;
		try {
			rule = new Rule2D(451, 4);
			rule.fillArray();
		} catch (NotValidRuleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		rule.setLayers(6);

		Assert.assertEquals(6, rule.getLayers());
	}

	@Test
	public void getCubeTranslationsTest()
	{
		ArrayList<String> trans = new ArrayList<String>();
		ArrayList<String> correct = new ArrayList<String>();

		correct.add("10,10,5");
		correct.add("0,0,0");
		correct.add("5,0,0");
		correct.add("10,0,0");
		correct.add("15,0,0");
		correct.add("20,0,0");
		correct.add("0,5,0");
		correct.add("5,5,0");
		correct.add("15,5,0");
		correct.add("20,5,0");
		correct.add("0,10,0");
		correct.add("10,10,0");
		correct.add("20,10,0");
		correct.add("0,15,0");
		correct.add("5,15,0");
		correct.add("15,15,0");
		correct.add("20,15,0");
		correct.add("0,20,0");
		correct.add("5,20,0");
		correct.add("10,20,0");
		correct.add("15,20,0");
		correct.add("20,20,0");


		Rule2D rule = null;
		try {
			rule = new Rule2D(451, 2);
			rule.fillArray();
		} catch (NotValidRuleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		printAray(rule.layer(0));
		printAray(rule.layer(1));

		trans = rule.getCubeTranslations();

			System.out.println(trans.toString());
			System.out.println(correct.toString());
	
			Assert.assertEquals(trans.toString(), correct.toString());


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
