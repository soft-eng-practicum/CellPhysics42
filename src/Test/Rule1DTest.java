package Test;

import org.junit.*;

import Model.Rule1D;
import exception.NotValidRuleException;

public class Rule1DTest {

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
	public void isOnTestRule10() throws NotValidRuleException {

		// given
		Rule1D myRule = new Rule1D(10);

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
	public void isOnTestRule170() throws NotValidRuleException {

		// given
		Rule1D myRule = new Rule1D(170);

		// when
		System.out.println(myRule.toString());
		// then
		byte zero = 0;
		byte one = 1;

		Assert.assertEquals("Wrong output rule 170", zero, myRule.isOn(zero, zero, zero));
		Assert.assertEquals("Wrong output rule 170", one, myRule.isOn(zero, zero, one));
		Assert.assertEquals("Wrong output rule 170", zero, myRule.isOn(zero, one, zero));
		Assert.assertEquals("Wrong output rule 170", zero, myRule.isOn(zero, one, one));
		Assert.assertEquals("Wrong output rule 170", one, myRule.isOn(one, zero, zero));
		Assert.assertEquals("Wrong output rule 170", one, myRule.isOn(one, zero, one));
		Assert.assertEquals("Wrong output rule 170", zero, myRule.isOn(one, one, zero));
		Assert.assertEquals("Wrong output rule 170", one, myRule.isOn(one, one, one));

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
		Rule1D myRule = new Rule1D(90);

		// when
		System.out.println(myRule.toString());
		// then
		byte zero = 0;
		byte one = 1;

		Assert.assertEquals("Wrong output rule 210", zero, myRule.isOn(zero, zero, zero));
		Assert.assertEquals("Wrong output rule 210", one, myRule.isOn(zero, zero, one));
		Assert.assertEquals("Wrong output rule 210", zero, myRule.isOn(zero, one, zero));
		Assert.assertEquals("Wrong output rule 210", one, myRule.isOn(zero, one, one));
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

}
