package Model;

import exception.NotValidRuleException;

public class Rule1D {
	private byte state7;
	private byte state6;
	private byte state5;
	private byte state4;
	private byte state3;
	private byte state2;
	private byte state1;
	private byte state0;
	private int rule;
	final private int[] rules = { 126, 10, 22, 50, 54, 90, 94, 122, 146, 150, 170, 182, 210, 222, 250, 254 };
	private byte[][] filledArray;
	private byte[][] stateArray;
	private int width;
	private int length;
	private int widthCount;
	private int lengthCount;

	public Rule1D(int rule) throws NotValidRuleException {
		setRule(rule);
		this.width = 40;
		this.length = 75;
		filledArray = new byte[length][width];
		stateArray = new byte[length][width];
	}

	public Rule1D(int rule, int width, int length) throws NotValidRuleException {
		setRule(rule);
		this.width = width;
		this.length = length;
		filledArray = new byte[length][width];
		stateArray = new byte[length][width];
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
	}

	public byte isOn(byte one, byte two, byte three) throws NotValidRuleException {

		if (one == 1 && two == 1 && three == 1) {
			stateArray[lengthCount][widthCount] = (byte)7;
			return state7;
		} else if (one == 1 && two == 1 && three == 0) {
			stateArray[lengthCount][widthCount] = (byte)6;
			return state6;
		} else if (one == 1 && two == 0 && three == 1) {
			stateArray[lengthCount][widthCount] = (byte)5;
			return state5;
		} else if (one == 1 && two == 0 && three == 0) {
			stateArray[lengthCount][widthCount] = (byte)4;
			return state4;
		} else if (one == 0 && two == 1 && three == 1) {
			stateArray[lengthCount][widthCount] = (byte)3;
			return state3;
		} else if (one == 0 && two == 1 && three == 0) {
			stateArray[lengthCount][widthCount] = (byte)2;
			return state2;
		} else if (one == 0 && two == 0 && three == 1) {
			stateArray[lengthCount][widthCount] = (byte)1;
			return state1;
		} else if (one == 0 && two == 0 && three == 0) {
			stateArray[lengthCount][widthCount] = (byte)0;
			return state0;
		} else
			throw new NotValidRuleException("Bad input for isOn method");

	}

	public void fillArrays() throws NotValidRuleException {

		for (int i = 0; i < this.width; i++) {
			int middle = this.width / 2;

			if (i != middle) {
				filledArray[0][i] = 0;
				stateArray[0][i] = 0;
			} else {
				filledArray[0][i] = 1;
				stateArray[0][i] = 1;
			}
		}
		for (lengthCount = 1; lengthCount < this.length; lengthCount++) {
			for (widthCount = 0; widthCount < this.width; widthCount++) {
				if (widthCount == 0) {
					filledArray[lengthCount][widthCount] = isOn((byte) 0, filledArray[lengthCount - 1][widthCount],
							filledArray[lengthCount - 1][widthCount + 1]);
				} else if (widthCount + 1 == width) {
					filledArray[lengthCount][widthCount] = isOn(filledArray[lengthCount - 1][widthCount - 1],
							filledArray[lengthCount - 1][widthCount], (byte) 0);
				} else {
					filledArray[lengthCount][widthCount] = isOn(filledArray[lengthCount - 1][widthCount - 1],
							filledArray[lengthCount - 1][widthCount], filledArray[lengthCount - 1][widthCount + 1]);
				}
			}
		}
	}

	public void fillArrays(byte[] firstLine) throws NotValidRuleException {

		if (firstLine.length == width) {
			for (int i = 0; i < firstLine.length; i++) {
				filledArray[0][i] = firstLine[i];
				stateArray[0][i] = firstLine[i];
			}

			for (lengthCount = 1; lengthCount < this.length; lengthCount++) {
				for (widthCount = 0; widthCount < this.width; widthCount++) {
					if (widthCount == 0) {
						filledArray[lengthCount][widthCount] = isOn((byte) 0, filledArray[lengthCount - 1][widthCount],
								filledArray[lengthCount - 1][widthCount + 1]);
					} else if (widthCount == width) {
						filledArray[lengthCount][widthCount] = isOn(filledArray[lengthCount - 1][widthCount - 1],
								filledArray[lengthCount - 1][widthCount], (byte) 0);
					} else {
						filledArray[lengthCount][widthCount] = isOn(filledArray[lengthCount - 1][widthCount - 1],
								filledArray[lengthCount - 1][widthCount], filledArray[lengthCount - 1][widthCount + 1]);
					}
				}
			}
		}

		else {
			throw new NotValidRuleException("the array lengths are off");
		}
	}
	
	public byte[] getNextFillLine(int line) throws NotValidRuleException{
		if (line >= length)
			throw new NotValidRuleException("line out of bounds");
		return filledArray[line];
	}

	public byte[] getNextStateLine(int line) throws NotValidRuleException{
		if (line >= length)
			throw new NotValidRuleException("line out of bounds");
		return stateArray[line];
	}
	
	public byte[][] getNextLine(int line) throws NotValidRuleException{
		if (line >= length)
			throw new NotValidRuleException("line out of bounds");
		
		byte[][] next = new byte[2][width];
		next[0] = getNextFillLine(line);
		next[1] = getNextStateLine(line);
		return next;
	}
	@Override
	public String toString() {
		return "rule1D [state7=" + state7 + ", state6=" + state6 + ", state5=" + state5 + ", state4=" + state4
				+ ", state3=" + state3 + ", state2=" + state2 + ", state1=" + state1 + ", state0=" + state0 + "]";
	}

	private String toBinary(int rule) {
		return String.format("%16s", Integer.toBinaryString(rule)).replace(' ', '0');
	}

	public int[] getRules() {
		return rules;
	}

	public int getRule() {
		return rule;
	}

	public int getLength() {
		return length;
	}

}
