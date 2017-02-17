package Test;

import org.junit.*;
import org.junit.Assert.*;
import org.junit.Test;


import Model.Rule1D;
import exception.NotValidRuleException;

public class Rule1DTest {

	@Test
	public void setRuleTest() throws NotValidRuleException {
		//given
		Rule1D myrule = new Rule1D(90);


		//when
		System.out.println(myrule.toString());


		//then
		myrule.setRule(18);
		Assert.assertEquals(18, myrule.getRule());
		Assert.assertNotEquals(90, myrule.getRule());

	}

	@Test
	public void isOnTestRule90() throws NotValidRuleException {

		// given
		Rule1D myRule = new Rule1D(90);

		// when
		System.out.println(myRule.toString());
		// then
		byte zero = 0;
		byte one = 1;

		Assert.assertEquals("Wrong output rule 90", zero, myRule.isOn(zero, zero, zero));
		Assert.assertEquals("Wrong output rule 90", one, myRule.isOn(zero, zero, one));
		Assert.assertEquals("Wrong output rule 90", zero, myRule.isOn(zero, one, zero));
		Assert.assertEquals("Wrong output rule 90", one, myRule.isOn(zero, one, one));
		Assert.assertEquals("Wrong output rule 90", one, myRule.isOn(one, zero, zero));
		Assert.assertEquals("Wrong output rule 90", zero, myRule.isOn(one, zero, one));
		Assert.assertEquals("Wrong output rule 90", one, myRule.isOn(one, one, zero));
		Assert.assertEquals("Wrong output rule 90", zero, myRule.isOn(one, one, one));

	}

	@Test
	public void isOnTestRule126() throws NotValidRuleException {

		// given
		Rule1D myRule = new Rule1D(126);

		// when
		System.out.println(myRule.toString());
		// then
		byte zero = 0;
		byte one = 1;

		Assert.assertEquals("Wrong output rule 126", zero, myRule.isOn(zero, zero, zero));
		Assert.assertEquals("Wrong output rule 126", one, myRule.isOn(zero, zero, one));
		Assert.assertEquals("Wrong output rule 126", one, myRule.isOn(zero, one, zero));
		Assert.assertEquals("Wrong output rule 126", one, myRule.isOn(zero, one, one));
		Assert.assertEquals("Wrong output rule 126", one, myRule.isOn(one, zero, zero));
		Assert.assertEquals("Wrong output rule 126", one, myRule.isOn(one, zero, one));
		Assert.assertEquals("Wrong output rule 126", one, myRule.isOn(one, one, zero));
		Assert.assertEquals("Wrong output rule 126", zero, myRule.isOn(one, one, one));

	}

	@Test
	public void isOnTestRule18() throws NotValidRuleException {

		// given
		Rule1D myRule = new Rule1D(18);

		// when
		System.out.println(myRule.toString());
		// then
		byte zero = 0;
		byte one = 1;

		Assert.assertEquals("Wrong output rule 10", zero, myRule.isOn(zero, zero, zero));
		Assert.assertEquals("Wrong output rule 10", one, myRule.isOn(zero, zero, one));
		Assert.assertEquals("Wrong output rule 10", zero, myRule.isOn(zero, one, zero));
		Assert.assertEquals("Wrong output rule 10", zero, myRule.isOn(zero, one, one));
		Assert.assertEquals("Wrong output rule 10", one, myRule.isOn(one, zero, zero));
		Assert.assertEquals("Wrong output rule 10", zero, myRule.isOn(one, zero, one));
		Assert.assertEquals("Wrong output rule 10", zero, myRule.isOn(one, one, zero));
		Assert.assertEquals("Wrong output rule 10", zero, myRule.isOn(one, one, one));

	}


	@Test
	public void isOnTestRule22() throws NotValidRuleException {

		// given
		Rule1D myRule = new Rule1D(22);

		// when
		System.out.println(myRule.toString());
		// then
		byte zero = 0;
		byte one = 1;

		Assert.assertEquals("Wrong output rule 22", zero, myRule.isOn(zero, zero, zero));
		Assert.assertEquals("Wrong output rule 22", one, myRule.isOn(zero, zero, one));
		Assert.assertEquals("Wrong output rule 22", one, myRule.isOn(zero, one, zero));
		Assert.assertEquals("Wrong output rule 22", zero, myRule.isOn(zero, one, one));
		Assert.assertEquals("Wrong output rule 22", one, myRule.isOn(one, zero, zero));
		Assert.assertEquals("Wrong output rule 22", zero, myRule.isOn(one, zero, one));
		Assert.assertEquals("Wrong output rule 22", zero, myRule.isOn(one, one, zero));
		Assert.assertEquals("Wrong output rule 22", zero, myRule.isOn(one, one, one));

	}

	@Test
	public void isOnTestRule50() throws NotValidRuleException {

		// given
		Rule1D myRule = new Rule1D(50);

		// when
		System.out.println(myRule.toString());
		// then
		byte zero = 0;
		byte one = 1;

		Assert.assertEquals("Wrong output rule 50", zero, myRule.isOn(zero, zero, zero));
		Assert.assertEquals("Wrong output rule 50", one, myRule.isOn(zero, zero, one));
		Assert.assertEquals("Wrong output rule 50", zero, myRule.isOn(zero, one, zero));
		Assert.assertEquals("Wrong output rule 50", zero, myRule.isOn(zero, one, one));
		Assert.assertEquals("Wrong output rule 50", one, myRule.isOn(one, zero, zero));
		Assert.assertEquals("Wrong output rule 50", one, myRule.isOn(one, zero, one));
		Assert.assertEquals("Wrong output rule 50", zero, myRule.isOn(one, one, zero));
		Assert.assertEquals("Wrong output rule 50", zero, myRule.isOn(one, one, one));

	}

	@Test
	public void isOnTestRule54() throws NotValidRuleException {

		// given
		Rule1D myRule = new Rule1D(54);

		// when
		System.out.println(myRule.toString());
		// then
		byte zero = 0;
		byte one = 1;

		Assert.assertEquals("Wrong output rule 54", zero, myRule.isOn(zero, zero, zero));
		Assert.assertEquals("Wrong output rule 54", one, myRule.isOn(zero, zero, one));
		Assert.assertEquals("Wrong output rule 54", one, myRule.isOn(zero, one, zero));
		Assert.assertEquals("Wrong output rule 54", zero, myRule.isOn(zero, one, one));
		Assert.assertEquals("Wrong output rule 54", one, myRule.isOn(one, zero, zero));
		Assert.assertEquals("Wrong output rule 54", one, myRule.isOn(one, zero, one));
		Assert.assertEquals("Wrong output rule 54", zero, myRule.isOn(one, one, zero));
		Assert.assertEquals("Wrong output rule 54", zero, myRule.isOn(one, one, one));

	}

	@Test
	public void isOnTestRule94() throws NotValidRuleException {

		// given
		Rule1D myRule = new Rule1D(94);

		// when
		System.out.println(myRule.toString());
		// then
		byte zero = 0;
		byte one = 1;

		Assert.assertEquals("Wrong output rule 94", zero, myRule.isOn(zero, zero, zero));
		Assert.assertEquals("Wrong output rule 94", one, myRule.isOn(zero, zero, one));
		Assert.assertEquals("Wrong output rule 94", one, myRule.isOn(zero, one, zero));
		Assert.assertEquals("Wrong output rule 94", one, myRule.isOn(zero, one, one));
		Assert.assertEquals("Wrong output rule 94", one, myRule.isOn(one, zero, zero));
		Assert.assertEquals("Wrong output rule 94", zero, myRule.isOn(one, zero, one));
		Assert.assertEquals("Wrong output rule 94", one, myRule.isOn(one, one, zero));
		Assert.assertEquals("Wrong output rule 94", zero, myRule.isOn(one, one, one));

	}


	@Test
	public void isOnTestRule122() throws NotValidRuleException {

		// given
		Rule1D myRule = new Rule1D(122);

		// when
		System.out.println(myRule.toString());
		// then
		byte zero = 0;
		byte one = 1;

		Assert.assertEquals("Wrong output rule 122", zero, myRule.isOn(zero, zero, zero));
		Assert.assertEquals("Wrong output rule 122", one, myRule.isOn(zero, zero, one));
		Assert.assertEquals("Wrong output rule 122", zero, myRule.isOn(zero, one, zero));
		Assert.assertEquals("Wrong output rule 122", one, myRule.isOn(zero, one, one));
		Assert.assertEquals("Wrong output rule 122", one, myRule.isOn(one, zero, zero));
		Assert.assertEquals("Wrong output rule 122", one, myRule.isOn(one, zero, one));
		Assert.assertEquals("Wrong output rule 122", one, myRule.isOn(one, one, zero));
		Assert.assertEquals("Wrong output rule 122", zero, myRule.isOn(one, one, one));

	}

	@Test
	public void isOnTestRule146() throws NotValidRuleException {

		// given
		Rule1D myRule = new Rule1D(146);

		// when
		System.out.println(myRule.toString());
		// then
		byte zero = 0;
		byte one = 1;

		Assert.assertEquals("Wrong output rule 146", zero, myRule.isOn(zero, zero, zero));
		Assert.assertEquals("Wrong output rule 146", one, myRule.isOn(zero, zero, one));
		Assert.assertEquals("Wrong output rule 146", zero, myRule.isOn(zero, one, zero));
		Assert.assertEquals("Wrong output rule 146", zero, myRule.isOn(zero, one, one));
		Assert.assertEquals("Wrong output rule 146", one, myRule.isOn(one, zero, zero));
		Assert.assertEquals("Wrong output rule 146", zero, myRule.isOn(one, zero, one));
		Assert.assertEquals("Wrong output rule 146", zero, myRule.isOn(one, one, zero));
		Assert.assertEquals("Wrong output rule 146", one, myRule.isOn(one, one, one));

	}

	@Test
	public void isOnTestRule150() throws NotValidRuleException {

		// given
		Rule1D myRule = new Rule1D(150);

		// when
		System.out.println(myRule.toString());
		// then
		byte zero = 0;
		byte one = 1;

		Assert.assertEquals("Wrong output rule 150", zero, myRule.isOn(zero, zero, zero));
		Assert.assertEquals("Wrong output rule 150", one, myRule.isOn(zero, zero, one));
		Assert.assertEquals("Wrong output rule 150", one, myRule.isOn(zero, one, zero));
		Assert.assertEquals("Wrong output rule 150", zero, myRule.isOn(zero, one, one));
		Assert.assertEquals("Wrong output rule 150", one, myRule.isOn(one, zero, zero));
		Assert.assertEquals("Wrong output rule 150", zero, myRule.isOn(one, zero, one));
		Assert.assertEquals("Wrong output rule 150", zero, myRule.isOn(one, one, zero));
		Assert.assertEquals("Wrong output rule 150", one, myRule.isOn(one, one, one));

	}

	@Test
	public void isOnTestRule178() throws NotValidRuleException {

		// given
		Rule1D myRule = new Rule1D(178);

		// when
		System.out.println(myRule.toString());
		// then
		byte zero = 0;
		byte one = 1;

		Assert.assertEquals("Wrong output rule 178", zero, myRule.isOn(zero, zero, zero));
		Assert.assertEquals("Wrong output rule 178", one, myRule.isOn(zero, zero, one));
		Assert.assertEquals("Wrong output rule 178", zero, myRule.isOn(zero, one, zero));
		Assert.assertEquals("Wrong output rule 178", zero, myRule.isOn(zero, one, one));
		Assert.assertEquals("Wrong output rule 178", one, myRule.isOn(one, zero, zero));
		Assert.assertEquals("Wrong output rule 178", one, myRule.isOn(one, zero, one));
		Assert.assertEquals("Wrong output rule 178", zero, myRule.isOn(one, one, zero));
		Assert.assertEquals("Wrong output rule 178", one, myRule.isOn(one, one, one));

	}

	@Test
	public void isOnTestRule182() throws NotValidRuleException {

		// given
		Rule1D myRule = new Rule1D(182);

		// when
		System.out.println(myRule.toString());
		// then
		byte zero = 0;
		byte one = 1;

		Assert.assertEquals("Wrong output rule 182", zero, myRule.isOn(zero, zero, zero));
		Assert.assertEquals("Wrong output rule 182", one, myRule.isOn(zero, zero, one));
		Assert.assertEquals("Wrong output rule 182", one, myRule.isOn(zero, one, zero));
		Assert.assertEquals("Wrong output rule 182", zero, myRule.isOn(zero, one, one));
		Assert.assertEquals("Wrong output rule 182", one, myRule.isOn(one, zero, zero));
		Assert.assertEquals("Wrong output rule 182", one, myRule.isOn(one, zero, one));
		Assert.assertEquals("Wrong output rule 182", zero, myRule.isOn(one, one, zero));
		Assert.assertEquals("Wrong output rule 182", one, myRule.isOn(one, one, one));

	}

	@Test
	public void isOnTestRule210() throws NotValidRuleException {

		// given
		Rule1D myRule = new Rule1D(210);

		// when
		System.out.println(myRule.toString());
		// then
		byte zero = 0;
		byte one = 1;

		Assert.assertEquals("Wrong output rule 210", zero, myRule.isOn(zero, zero, zero));
		Assert.assertEquals("Wrong output rule 210", one, myRule.isOn(zero, zero, one));
		Assert.assertEquals("Wrong output rule 210", zero, myRule.isOn(zero, one, zero));
		Assert.assertEquals("Wrong output rule 210", zero, myRule.isOn(zero, one, one));
		Assert.assertEquals("Wrong output rule 210", one, myRule.isOn(one, zero, zero));
		Assert.assertEquals("Wrong output rule 210", zero, myRule.isOn(one, zero, one));
		Assert.assertEquals("Wrong output rule 210", one, myRule.isOn(one, one, zero));
		Assert.assertEquals("Wrong output rule 210", one, myRule.isOn(one, one, one));

	}

	@Test
	public void isOnTestRule222() throws NotValidRuleException {

		// given
		Rule1D myRule = new Rule1D(222);

		// when
		System.out.println(myRule.toString());
		// then
		byte zero = 0;
		byte one = 1;

		Assert.assertEquals("Wrong output rule 222", zero, myRule.isOn(zero, zero, zero));
		Assert.assertEquals("Wrong output rule 222", one, myRule.isOn(zero, zero, one));
		Assert.assertEquals("Wrong output rule 222", one, myRule.isOn(zero, one, zero));
		Assert.assertEquals("Wrong output rule 222", one, myRule.isOn(zero, one, one));
		Assert.assertEquals("Wrong output rule 222", one, myRule.isOn(one, zero, zero));
		Assert.assertEquals("Wrong output rule 222", zero, myRule.isOn(one, zero, one));
		Assert.assertEquals("Wrong output rule 222", one, myRule.isOn(one, one, zero));
		Assert.assertEquals("Wrong output rule 222", one, myRule.isOn(one, one, one));

	}

	@Test
	public void isOnTestRule250() throws NotValidRuleException {

		// given
		Rule1D myRule = new Rule1D(250);

		// when
		System.out.println(myRule.toString());
		// then
		byte zero = 0;
		byte one = 1;

		Assert.assertEquals("Wrong output rule 250", zero, myRule.isOn(zero, zero, zero));
		Assert.assertEquals("Wrong output rule 250", one, myRule.isOn(zero, zero, one));
		Assert.assertEquals("Wrong output rule 250", zero, myRule.isOn(zero, one, zero));
		Assert.assertEquals("Wrong output rule 250", one, myRule.isOn(zero, one, one));
		Assert.assertEquals("Wrong output rule 250", one, myRule.isOn(one, zero, zero));
		Assert.assertEquals("Wrong output rule 250", one, myRule.isOn(one, zero, one));
		Assert.assertEquals("Wrong output rule 250", one, myRule.isOn(one, one, zero));
		Assert.assertEquals("Wrong output rule 250", one, myRule.isOn(one, one, one));

	}

	@Test
	public void isOnTestRule254() throws NotValidRuleException {

		// given
		Rule1D myRule = new Rule1D(254);

		// when
		System.out.println(myRule.toString());
		// then
		byte zero = 0;
		byte one = 1;

		Assert.assertEquals("Wrong output rule 254", zero, myRule.isOn(zero, zero, zero));
		Assert.assertEquals("Wrong output rule 254", one, myRule.isOn(zero, zero, one));
		Assert.assertEquals("Wrong output rule 254", one, myRule.isOn(zero, one, zero));
		Assert.assertEquals("Wrong output rule 254", one, myRule.isOn(zero, one, one));
		Assert.assertEquals("Wrong output rule 254", one, myRule.isOn(one, zero, zero));
		Assert.assertEquals("Wrong output rule 254", one, myRule.isOn(one, zero, one));
		Assert.assertEquals("Wrong output rule 254", one, myRule.isOn(one, one, zero));
		Assert.assertEquals("Wrong output rule 254", one, myRule.isOn(one, one, one));

	}

	@Test
	public void getFillLine90Test() throws NotValidRuleException {
		Rule1D rule = new Rule1D(90, 11, 10);
		System.out.println(rule.toString());
		rule.fillArrays();

		Assert.assertArrayEquals(new byte[]{0,0,0,0,0,1,0,0,0,0,0,}, rule.getFillLine(0));
		Assert.assertArrayEquals(new byte[]{0,0,0,0,1,0,1,0,0,0,0,}, rule.getFillLine(1));
		Assert.assertArrayEquals(new byte[]{0,0,0,1,0,0,0,1,0,0,0,}, rule.getFillLine(2));
		Assert.assertArrayEquals(new byte[]{0,0,1,0,1,0,1,0,1,0,0,}, rule.getFillLine(3));
		Assert.assertArrayEquals(new byte[]{0,1,0,0,0,0,0,0,0,1,0,}, rule.getFillLine(4));
		Assert.assertArrayEquals(new byte[]{1,0,1,0,0,0,0,0,1,0,1,}, rule.getFillLine(5));
		Assert.assertArrayEquals(new byte[]{0,0,0,1,0,0,0,1,0,0,0,}, rule.getFillLine(6));
		Assert.assertArrayEquals(new byte[]{0,0,1,0,1,0,1,0,1,0,0,}, rule.getFillLine(7));
		Assert.assertArrayEquals(new byte[]{0,1,0,0,0,0,0,0,0,1,0,}, rule.getFillLine(8));
		Assert.assertArrayEquals(new byte[]{1,0,1,0,0,0,0,0,1,0,1,}, rule.getFillLine(9));

		}
	
	@Test
	public void getStateLine90Test() throws NotValidRuleException {
		Rule1D rule = new Rule1D(90, 11, 10);
		System.out.println(rule.toString());
		rule.fillArrays();

		Assert.assertArrayEquals(new byte[]{0,0,0,0,0,1,0,0,0,0,0,}, rule.getStateLine(0));
		Assert.assertArrayEquals(new byte[]{0,0,0,0,1,2,4,0,0,0,0}, rule.getStateLine(1));
		Assert.assertArrayEquals(new byte[]{0,0,0,1,2,5,2,4,0,0,0}, rule.getStateLine(2));
		Assert.assertArrayEquals(new byte[]{0,0,1,2,4,0,1,2,4,0,0}, rule.getStateLine(3));
		Assert.assertArrayEquals(new byte[]{0,1,2,5,2,5,2,5,2,4,0}, rule.getStateLine(4));
		Assert.assertArrayEquals(new byte[]{1,2,4,0,0,0,0,0,1,2,4}, rule.getStateLine(5));
		Assert.assertArrayEquals(new byte[]{2,5,2,4,0,0,0,1,2,5,2}, rule.getStateLine(6));
		Assert.assertArrayEquals(new byte[]{0,0,1,2,4,0,1,2,4,0,0}, rule.getStateLine(7));
		Assert.assertArrayEquals(new byte[]{0,1,2,5,2,5,2,5,2,4,0}, rule.getStateLine(8));
		Assert.assertArrayEquals(new byte[]{1,2,4,0,0,0,0,0,1,2,4}, rule.getStateLine(9));

		}
	
	@Test
	public void getLine90Test() throws NotValidRuleException {
		Rule1D rule = new Rule1D(90, 11, 10);
		System.out.println(rule.toString());
		rule.fillArrays();
		
		byte[][] line0 = {{0,0,0,0,0,1,0,0,0,0,0,},{0,0,0,0,0,1,0,0,0,0,0,}};
		byte[][] line1 = {{0,0,0,0,1,0,1,0,0,0,0,},{0,0,0,0,1,2,4,0,0,0,0}};
		byte[][] line2 = {{0,0,0,1,0,0,0,1,0,0,0,},{0,0,0,1,2,5,2,4,0,0,0}};
		byte[][] line3 = {{0,0,1,0,1,0,1,0,1,0,0,},{0,0,1,2,4,0,1,2,4,0,0}};
		byte[][] line4 = {{0,1,0,0,0,0,0,0,0,1,0,},{0,1,2,5,2,5,2,5,2,4,0}};
		byte[][] line5 = {{1,0,1,0,0,0,0,0,1,0,1,},{1,2,4,0,0,0,0,0,1,2,4}};
		byte[][] line6 = {{0,0,0,1,0,0,0,1,0,0,0,},{2,5,2,4,0,0,0,1,2,5,2}};
		byte[][] line7 = {{0,0,1,0,1,0,1,0,1,0,0,},{0,0,1,2,4,0,1,2,4,0,0}};
		byte[][] line8 = {{0,1,0,0,0,0,0,0,0,1,0,},{0,1,2,5,2,5,2,5,2,4,0}};
		byte[][] line9 = {{1,0,1,0,0,0,0,0,1,0,1,},{1,2,4,0,0,0,0,0,1,2,4}};

		Assert.assertArrayEquals(line0, rule.getLine(0));
		Assert.assertArrayEquals(line1, rule.getLine(1));
		Assert.assertArrayEquals(line2, rule.getLine(2));
		Assert.assertArrayEquals(line3, rule.getLine(3));
		Assert.assertArrayEquals(line4, rule.getLine(4));
		Assert.assertArrayEquals(line5, rule.getLine(5));
		Assert.assertArrayEquals(line6, rule.getLine(6));
		Assert.assertArrayEquals(line7, rule.getLine(7));
		Assert.assertArrayEquals(line8, rule.getLine(8));
		Assert.assertArrayEquals(line9, rule.getLine(9));

		}
	
	@Test
	public void getNext90Test() throws NotValidRuleException {
		Rule1D rule = new Rule1D(90, 11, 10);
		System.out.println(rule.toString());
		rule.fillArrays();
		
		byte[][] line0 = {{0,0,0,0,0,1,0,0,0,0,0,},{0,0,0,0,0,1,0,0,0,0,0,}};
		byte[][] line1 = {{0,0,0,0,1,0,1,0,0,0,0,},{0,0,0,0,1,2,4,0,0,0,0}};
		byte[][] line2 = {{0,0,0,1,0,0,0,1,0,0,0,},{0,0,0,1,2,5,2,4,0,0,0}};
		byte[][] line3 = {{0,0,1,0,1,0,1,0,1,0,0,},{0,0,1,2,4,0,1,2,4,0,0}};
		byte[][] line4 = {{0,1,0,0,0,0,0,0,0,1,0,},{0,1,2,5,2,5,2,5,2,4,0}};
		byte[][] line5 = {{1,0,1,0,0,0,0,0,1,0,1,},{1,2,4,0,0,0,0,0,1,2,4}};
		byte[][] line6 = {{0,0,0,1,0,0,0,1,0,0,0,},{2,5,2,4,0,0,0,1,2,5,2}};
		byte[][] line7 = {{0,0,1,0,1,0,1,0,1,0,0,},{0,0,1,2,4,0,1,2,4,0,0}};
		byte[][] line8 = {{0,1,0,0,0,0,0,0,0,1,0,},{0,1,2,5,2,5,2,5,2,4,0}};
		byte[][] line9 = {{1,0,1,0,0,0,0,0,1,0,1,},{1,2,4,0,0,0,0,0,1,2,4}};

		Assert.assertArrayEquals(line0, rule.getNextLine());
		Assert.assertArrayEquals(line1, rule.getNextLine());
		Assert.assertArrayEquals(line2, rule.getNextLine());
		Assert.assertArrayEquals(line3, rule.getNextLine());
		Assert.assertArrayEquals(line4, rule.getNextLine());
		Assert.assertArrayEquals(line5, rule.getNextLine());
		Assert.assertArrayEquals(line6, rule.getNextLine());
		Assert.assertArrayEquals(line7, rule.getNextLine());
		Assert.assertArrayEquals(line8, rule.getNextLine());
		Assert.assertArrayEquals(line9, rule.getNextLine());

		}

	@Test
	public void getFillLine178Test() throws NotValidRuleException {
		Rule1D rule = new Rule1D(178, 11, 10);
		System.out.println(rule.toString());
		rule.fillArrays();

		Assert.assertArrayEquals(new byte[]{0,0,0,0,0,1,0,0,0,0,0}, rule.getFillLine(0));
		Assert.assertArrayEquals(new byte[]{0,0,0,0,1,0,1,0,0,0,0}, rule.getFillLine(1));
		Assert.assertArrayEquals(new byte[]{0,0,0,1,0,1,0,1,0,0,0}, rule.getFillLine(2));
		Assert.assertArrayEquals(new byte[]{0,0,1,0,1,0,1,0,1,0,0}, rule.getFillLine(3));
		Assert.assertArrayEquals(new byte[]{0,1,0,1,0,1,0,1,0,1,0}, rule.getFillLine(4));
		Assert.assertArrayEquals(new byte[]{1,0,1,0,1,0,1,0,1,0,1}, rule.getFillLine(5));
		Assert.assertArrayEquals(new byte[]{0,1,0,1,0,1,0,1,0,1,0}, rule.getFillLine(6));
		Assert.assertArrayEquals(new byte[]{1,0,1,0,1,0,1,0,1,0,1}, rule.getFillLine(7));
		Assert.assertArrayEquals(new byte[]{0,1,0,1,0,1,0,1,0,1,0}, rule.getFillLine(8));
		Assert.assertArrayEquals(new byte[]{1,0,1,0,1,0,1,0,1,0,1}, rule.getFillLine(9));
		}

	@Test
	public void getFillLine126Test() throws NotValidRuleException {
		Rule1D rule = new Rule1D(126, 11, 10);
		System.out.println(rule.toString());
		rule.fillArrays();

		Assert.assertArrayEquals(new byte[]{0,0,0,0,0,1,0,0,0,0,0}, rule.getFillLine(0));
		Assert.assertArrayEquals(new byte[]{0,0,0,0,1,1,1,0,0,0,0}, rule.getFillLine(1));
		Assert.assertArrayEquals(new byte[]{0,0,0,1,1,0,1,1,0,0,0}, rule.getFillLine(2));
		Assert.assertArrayEquals(new byte[]{0,0,1,1,1,1,1,1,1,0,0}, rule.getFillLine(3));
		Assert.assertArrayEquals(new byte[]{0,1,1,0,0,0,0,0,1,1,0}, rule.getFillLine(4));
		Assert.assertArrayEquals(new byte[]{1,1,1,1,0,0,0,1,1,1,1}, rule.getFillLine(5));
		Assert.assertArrayEquals(new byte[]{1,0,0,1,1,0,1,1,0,0,1}, rule.getFillLine(6));
		Assert.assertArrayEquals(new byte[]{1,1,1,1,1,1,1,1,1,1,1}, rule.getFillLine(7));
		Assert.assertArrayEquals(new byte[]{1,0,0,0,0,0,0,0,0,0,1}, rule.getFillLine(8));
		Assert.assertArrayEquals(new byte[]{1,1,0,0,0,0,0,0,0,1,1}, rule.getFillLine(9));
		}
	
	@Test
	public void getFillLineStart18() throws NotValidRuleException
	{		
		Rule1D rule = new Rule1D(18, 20, 5);
		System.out.println(rule.toString());
		rule.fillArrays(new byte[]{0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0});
		
		Assert.assertArrayEquals(new byte[]{0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0}, rule.getFillLine(0));
		Assert.assertArrayEquals(new byte[]{0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,1,0,1}, rule.getFillLine(1));
		Assert.assertArrayEquals(new byte[]{0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,1,0,0,0}, rule.getFillLine(2));
		Assert.assertArrayEquals(new byte[]{0,0,0,0,0,0,1,0,1,0,1,0,1,0,0,1,0,1,0,0}, rule.getFillLine(3));
		Assert.assertArrayEquals(new byte[]{0,0,0,0,0,1,0,0,0,0,0,0,0,1,1,0,0,0,1,0}, rule.getFillLine(4));
		
	}

}
