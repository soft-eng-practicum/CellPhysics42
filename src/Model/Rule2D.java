package Model;

import exception.NotValidRuleException;

public class Rule2D {

	private byte state9;
	private byte state8;
	private byte state7;
	private byte state6;
	private byte state5;
	private byte state4;
	private byte state3;
	private byte state2;
	private byte state1;
	private byte state0;
	private int rule;
	private int nextLayer;
	final public int[] rules =
		{451, 452, 453, 454, 457, 459, 460, 461, 462, 465, 467, 468, 469, 470 +
			473, 475, 476, 478, 481, 483, 484, 485, 486, 489, 491, 492, 493, 494, 497};
	private byte[][][] filledArray;

	public Rule2D()
	{

	}

	public Rule2D(int ruleNumber, int layers) throws NotValidRuleException
	{
		setRule(ruleNumber);
		filledArray = new byte[layers][(2*layers) +1][(2*layers) +1];
	}

	public void setRule(int rule) throws NotValidRuleException {
		boolean check = false;
		for (int i : rules) {
			if (i == rule) {
				check = true;
			}
		}

		if (check) {
			this.rule = rule;
			String binary = toBinary(rule);
			state7 = Byte.parseByte(binary.substring(binary.length() - 10, binary.length() - 9));
			state8 = Byte.parseByte(binary.substring(binary.length() - 9, binary.length() - 8));
			state7 = Byte.parseByte(binary.substring(binary.length() - 8, binary.length() - 7));
			state6 = Byte.parseByte(binary.substring(binary.length() - 7, binary.length() - 6));
			state5 = Byte.parseByte(binary.substring(binary.length() - 6, binary.length() - 5));
			state4 = Byte.parseByte(binary.substring(binary.length() - 5, binary.length() - 4));
			state3 = Byte.parseByte(binary.substring(binary.length() - 4, binary.length() - 3));
			state2 = Byte.parseByte(binary.substring(binary.length() - 3, binary.length() - 2));
			state1 = Byte.parseByte(binary.substring(binary.length() - 2, binary.length() - 1));
			state0 = Byte.parseByte(binary.substring(binary.length() - 1, binary.length()));
		} else {
			throw new NotValidRuleException(rule + " is not a valid rule");
		}

		nextLayer = 0;
	}

	/** converts the int rule to a binary string
	 *
	 * @param rule int of rule to be applied
	 * @return String of 8 bits
	 */
	private String toBinary(int rule) {
		return String.format("%10s", Integer.toBinaryString(rule)).replace(' ', '0');
	}

	private byte isOn(byte one, byte two, byte three, byte four, byte five) throws NotValidRuleException
	{
		int sum = one  + two + three + four;

		if(five == 0 && sum == 0)
			return state0;
		else if(five == 1 && sum == 0)
			return state1;
		else if(five == 0 && sum == 1)
			return state2;
		else if(five == 1 && sum == 1)
			return state3;
		else if(five == 0 &&  sum == 2)
			return state4;
		else if(five == 1 && sum == 2)
			return state5;
		else if(five == 0 && sum == 3)
			return state6;
		else if(five == 1 && sum == 3)
			return state7;
		else if(five == 0 && sum == 4)
			return state8;
		else if(five == 1 && sum == 4)
			return state9;
		else
			throw new NotValidRuleException("Bad input for ison");
	}

	public void fillArray() throws NotValidRuleException
	{
		int middle = filledArray[1].length/2;
		filledArray[0][middle][middle] = 1;


		for(int z = 1; z < filledArray.length ; z++)
		{
			for(int x = 1; x < filledArray[1].length -1; x++)
			{
				for(int y = 1; y < filledArray[1][1].length - 1; y++)
				{
					filledArray[z][x][y] = isOn((byte)filledArray[z-1][x-1][y], (byte)filledArray[z-1][x+1][y],
							(byte)filledArray[z-1][x][y-1], (byte)filledArray[z-1][x][y+1],
							(byte)filledArray[z-1][x][y] );
				}
			}

		}
	}

	public byte[][] nextLayer()
	{
		int current = nextLayer;
		nextLayer++;
		return filledArray[current];
	}

	public byte[][] layer(int layer)
	{
		return filledArray[layer];
	}
	
	public byte[][] lastLayer()
	{
		return filledArray[filledArray.length - 1];
	}

	public int[] getRules() {
		return rules;
	}

	public void save3DFile()
	{

	}
	
	public void setLayers(int layers)
	{
		filledArray = new byte[layers][(2*layers) +1][(2*layers) +1];
	}

}
