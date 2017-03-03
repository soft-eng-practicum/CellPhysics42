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
	final public int[] rules = {0};  /// need to add all the rules
	private byte[][][] filledArray;

	Rule2D()
	{

	}

	Rule2D(int ruleNumber, int steps) throws NotValidRuleException
	{
		setRule(ruleNumber);
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

	public byte isOn(byte one, byte two, byte three, byte four, byte five)
	{
		return 0;
	}

	public void fillArray()
	{
		//needs to fill the byte filledArray[z][x][y]
	}

	public byte[][] nextLayer()
	{
		//needs to use the nextLayer int to get the next layer
		return null;
	}

	public byte[][] nextLayer(int layer)
	{
		//needs to use the this layer int to get the next layer
		return null;
	}

	public void save3DFile()
	{

	}

}
