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
	final private int[] rules = { 126, 10, 22, 50, 54, 90, 94, 122, 146, 150, 178, 182, 210, 222, 250, 254 };
	private byte[][] filledArray;
	private byte[][] stateArray;
	private int width;
	private int length;
	private int widthCount;
	private int lengthCount;
	private int nextLine;

	/** Constructor that takes a int of a valid rule that the class can build.
	 *
	 * @param rule
	 * @throws NotValidRuleException
	 */
	public Rule1D(int rule) throws NotValidRuleException {
		setRule(rule);
		this.width = 40;
		this.length = 75;
		filledArray = new byte[length][width];
		stateArray = new byte[length][width];
		nextLine = 0;
	}

	/** Constructor that takes a int of a valid rule that the class can build and the custom length and width of the array.
	 *
	 * @param rule
	 * @param width
	 * @param length
	 * @throws NotValidRuleException
	 */
	public Rule1D(int rule, int width, int length) throws NotValidRuleException {
		setRule(rule);
		this.width = width;
		this.length = length;
		filledArray = new byte[length][width];
		stateArray = new byte[length][width];
		nextLine = 0;
	}

	/** set rule will update the rule number and states on the object.
	 *
	 * @param rule
	 * @throws NotValidRuleException
	 */
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

	/** Takes the previous state and uses as input for the selected cell to determine if it's state is on or off.
	 *  Updates the stateArrya at the this time.
	 *
	 * @param one Is the left most bit of the previous state
	 * @param two Is the middle bit of the previous state
	 * @param three Right most bit of the previous state
	 * @return Returns 1 if cell is on 0 if off
	 * @throws NotValidRuleException
	 */
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

	/** This fills the fillArray applying the current rule.  With no input the first line is auto generated with one on state
	 *  in the middle of the line.  Note: uses integer devision so that any width can be used.
	 *
	 * @throws NotValidRuleException
	 */
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

	/** This fills the fillArray applying the current rule.  UI needs to provide a valid first line of states.
	 *   Note: uses integer devision so that any width can be used.
	 *
	 * @param firstLine Array of bytes with the beginning states
	 * @throws NotValidRuleException
	 */
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

	/** Returns a array of the line selected in the fillArray.
	 *
	 * @param line int of line to be returned
	 * @return reference of the byte array of the line selected
	 * @throws NotValidRuleException
	 */
	public byte[] getFillLine(int line) throws NotValidRuleException{
		if (line >= length)
			throw new NotValidRuleException("line out of bounds");
		return filledArray[line];
	}

	/** Returns a array of the line selected in the stateAray
	 *
	 * @param line int of line to be returned
	 * @return reference to the byte array of the line selected
	 * @throws NotValidRuleException
	 */
	public byte[] getStateLine(int line) throws NotValidRuleException{
		if (line >= length)
			throw new NotValidRuleException("line out of bounds");
		return stateArray[line];
	}
	
	public byte[][] getNextLine() throws NotValidRuleException{
		if (nextLine >= length)
			throw new NotValidRuleException("line out of bounds");
		
		byte[][] next = new byte[2][width];
		next[0] = getNextFillLine(nextLine);
		next[1] = getNextStateLine(nextLine);
		nextLine++;
		return next;
	}
	
	public byte[][] getNextLine(int line) throws NotValidRuleException{

	/** Returns a 2D array with both references to the fillArray line and the stateArray line. [0] is the fillArray and
	 * [1] is the stateArray
	 *
	 * @param line int of line to be returned
	 * @return [0] is the fillArray and [1] is the stateArray of the line selected
	 * @throws NotValidRuleException
	 */
	public byte[][] getLine(int line) throws NotValidRuleException{
		if (line >= length)
			throw new NotValidRuleException("line out of bounds");

		byte[][] next = new byte[2][width];
		next[0] = getFillLine(line);
		next[1] = getStateLine(line);
		return next;
	}

	/** Override of the toString that returns the string vales of each state in the object
	 * Note: used in testing
	 *
	 */
	@Override
	public String toString() {
		return "rule1D [rule number = " + rule + " state7=" + state7 + ", state6=" + state6 + ", state5=" + state5 + ", state4=" + state4
				+ ", state3=" + state3 + ", state2=" + state2 + ", state1=" + state1 + ", state0=" + state0 + "]";
	}

	/** Creates a new blank array of given size
	 *
	 * @param length
	 * @param width
	 */
	public void changeArraySize(int length, int width) {
		filledArray = new byte[length][width];
		stateArray = new byte[length][width];
	}


	/** converts the int rule to a binary string
	 *
	 * @param rule int of rule to be applied
	 * @return String of 8 bits
	 */
	private String toBinary(int rule) {
		return String.format("%8s", Integer.toBinaryString(rule)).replace(' ', '0');
	}

	/** Array of valid rules that the object can use
	 *
	 * @return
	 */
	public int[] getRules() {
		return rules;
	}

	/** current rule of the object
	 *
	 * @return
	 */
	public int getRule() {
		return rule;
	}

	/** current length of the array.
	 *
	 * @return
	 */
	public int getLength() {
		return length;
	}

}
