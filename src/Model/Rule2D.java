package Model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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
	private int factor, size, layers;
	final public int[] rules = {451, 452, 453, 454, 457, 459, 460, 461, 462, 465, 467, 468, 469, 470, 473, 475, 476,
			478, 481, 483, 484, 485, 486, 489, 491, 492, 493, 494, 497 };
	private byte[][][] filledArray;

	/**
	 *
	 */
	public Rule2D() {
		factor = 5;
		size = 6;
	}

	/**
	 * @param ruleNumber
	 * @param layers
	 * @throws NotValidRuleException
	 */
	public Rule2D(int ruleNumber, int layers) throws NotValidRuleException {
		this.layers = layers;
		setRule(ruleNumber);
		filledArray = new byte[layers][(2 * layers) + 1][(2 * layers) + 1];
		factor = 5;
		size = 6;
		nextLayer = 0;
	}
	

	/**
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
			state9 = Byte.parseByte(binary.substring(binary.length() - 10, binary.length() - 9));
			state8 = Byte.parseByte(binary.substring(binary.length() - 9, binary.length() - 8));
			state7 = Byte.parseByte(binary.substring(binary.length() - 8, binary.length() - 7));
			state6 = Byte.parseByte(binary.substring(binary.length() - 7, binary.length() - 6));
			state5 = Byte.parseByte(binary.substring(binary.length() - 6, binary.length() - 5));
			state4 = Byte.parseByte(binary.substring(binary.length() - 5, binary.length() - 4));
			state3 = Byte.parseByte(binary.substring(binary.length() - 4, binary.length() - 3));
			state2 = Byte.parseByte(binary.substring(binary.length() - 3, binary.length() - 2));
			state1 = Byte.parseByte(binary.substring(binary.length() - 2, binary.length() - 1));
			state0 = Byte.parseByte(binary.substring(binary.length() - 1, binary.length()));
			this.rule = rule;
			} else {
			throw new NotValidRuleException(rule + " is not a valid rule");
		}

		nextLayer = 0;
	}

	/**
	 * converts the int rule to a binary string
	 *
	 * @param rule int of rule to be applied
	 * @return String of 8 bits
	 */
	private String toBinary(int rule) {
		return String.format("%10s", Integer.toBinaryString(rule)).replace(' ', '0');
	}

	/**
	 * @param one
	 * @param two
	 * @param three
	 * @param four
	 * @param five
	 * @return
	 * @throws NotValidRuleException
	 */
	private byte isOn(byte one, byte two, byte three, byte four, byte five) throws NotValidRuleException {
		int sum = one + two + three + four;

		if (five == 0 && sum == 0)
			return state0;
		else if (five == 1 && sum == 0)
			return state1;
		else if (five == 0 && sum == 1)
			return state2;
		else if (five == 1 && sum == 1)
			return state3;
		else if (five == 0 && sum == 2)
			return state4;
		else if (five == 1 && sum == 2)
			return state5;
		else if (five == 0 && sum == 3)
			return state6;
		else if (five == 1 && sum == 3)
			return state7;
		else if (five == 0 && sum == 4)
			return state8;
		else if (five == 1 && sum == 4)
			return state9;
		else
			throw new NotValidRuleException("Bad input for ison");


	}

	/**
	 * @throws NotValidRuleException
	 */
	public void fillArray() throws NotValidRuleException {
		int middle = filledArray[1].length / 2;
		filledArray[0][middle][middle] = 1;
		byte first, second, third, forth, fith;

		for (int z = 1; z < filledArray.length; z++) {
			for (int x = 0; x < filledArray[1].length; x++) {
				for (int y = 0; y < filledArray[1][1].length; y++) {
					try{
					first = (byte) filledArray[z - 1][x - 1][y];
					}catch (ArrayIndexOutOfBoundsException e)
					{
						first = (byte) filledArray[z - 1][x][y];
					}

					try {
					second = (byte) filledArray[z - 1][x + 1][y];
					}catch (ArrayIndexOutOfBoundsException e)
					{
						second = (byte) filledArray[z - 1][x][y];
					}

					try{
					third = (byte) filledArray[z - 1][x][y - 1];
					}catch (ArrayIndexOutOfBoundsException e)
					{
						third = (byte) filledArray[z - 1][x][y];
					}

					try{
					forth = (byte) filledArray[z - 1][x][y + 1];
					}catch (ArrayIndexOutOfBoundsException e)
					{
						forth = (byte) filledArray[z - 1][x][y];
					}
					fith = (byte) filledArray[z - 1][x][y];
					filledArray[z][x][y] = isOn(first, second, third, forth, fith);

				}
			}
		}

	}

	/**
	 * @return
	 */
	public byte[][] nextLayer() {
		int current = nextLayer;
		nextLayer++;
		return filledArray[current];
	}

	/**
	 * @param layer
	 * @return
	 */
	public byte[][] layer(int layer) {
		return filledArray[layer];
	}

	/**
	 * @return
	 */
	public byte[][] lastLayer() {
		return filledArray[filledArray.length - 1];
	}

	/**
	 * @return
	 */
	public int[] getRules() {
		return rules;
	}

	public ArrayList<String> getCubeTranslations(int startLayer, int endLayer){
		ArrayList<String> translations = new ArrayList<>(200);
		for (int z = startLayer; z < endLayer; z++) {
			for (int y = 0; y < filledArray[1].length; y++) {
				for (int x = 0; x < filledArray[1][1].length; x++) {
					if (filledArray[z][x][y] == 1) {
						int level = (z - filledArray.length + 1) * -1;
						String translation =  translateToCenter(factor * x , factor * y , factor * level);
						translations.add(translation);
					}
				}
			}
		}
		return translations;
	}
	
	private String translateToCenter(int x, int y, int z){
		x = x - (factor * (layers *2)+ 1)/2;
		y = y -(factor * (layers * 2) +1)/2;
		z = z -(factor * layers + 1)/2;
		return "" + x + "," + y + "," + z;	
	}
	
	public void setFactor(int factor){
		this.factor = factor;
		size = factor - 1;
	}


	/**
	 * @param fileName
	 * @throws IOException
	 */
	public void save3DFile(String fileName) throws IOException {
		FileWriter file = getFile(fileName + ".scad");
		file.write("module whole(){\r\n");
		if (file != null) {
			int level;
			file.write("//Top of the object\r\n");
			for (int z = 0; z < filledArray.length; z++) {
				level = (z - filledArray.length + 1) * -1;
				file.write("//Layer " + level + "\r\n");
				for (int y = 0; y < filledArray[1].length; y++) {
					for (int x = 0; x < filledArray[1][1].length; x++) {
						if (filledArray[z][x][y] == 1) {
							file.write(
									"translate([" + factor * x + "," + factor * y + "," + factor * level + "]){\r\n");
							file.write("cube(" + size + ");}\r\n");
						}
					}
				}
			}
			int finTran = (((filledArray.length * 2 + 1) * 5) / 2) + 1;
			file.write("}\r\n");
			file.write("translate([-" + finTran + ",-" + finTran + ",0]){\r\n");
			file.write("whole();}\r\n");
		}

		file.close();

	}

	/**
	 * @param fileName
	 * @throws IOException
	 */
	public void save3DFileByLayers(String fileName) throws IOException {
		String newFileName = "";
		if (filledArray.length % 2 != 0 && filledArray.length > 2) {
			newFileName = fileName + "Layers1-3.scad";
			save3DFileLayers(getFile(newFileName), 0, 3);
			for (int i = 3; i < filledArray.length; i += 2) {
				newFileName = fileName + "Layers" + (i + 1) + "-" + (i + 2) + ".scad";
				save3DFileLayers(getFile(newFileName), i, i + 2);
			}
		} else {
			for (int i = 0; i < filledArray.length; i += 2) {
				newFileName = fileName + "Layers" + (i) + "-" + (i + 1) + ".scad";
				save3DFileLayers(getFile(newFileName), i, i + 2);
			}
		}

	}

	/**
	 * @param file
	 * @param start
	 * @param end
	 * @throws IOException
	 */
	private void save3DFileLayers(FileWriter file, int start, int end) throws IOException {
		int level;
		boolean diff = start > 0;

		if (diff) {
			level = ((start - 1) - filledArray.length + 1) * -1;
			file.write("//Layer " + level + "\r\n");
			file.write("module diff(){\r\n");

			for (int y = 0; y < filledArray[1].length; y++) {
				for (int x = 0; x < filledArray[1][1].length; x++) {
					if (filledArray[start - 1][x][y] == 1) {
						file.write("translate([" + ((factor * x)-.5) + "," + ((factor * y)-.5) + "," + factor * level + "]){\r\n");
						file.write("cube(" + (size + 1) + ");}\r\n");
					}
				}
			}
			file.write("}\r\n");
		}

		file.write("module whole(){\r\n");
		for (; start < end; start++) {
			level = (start - filledArray.length + 1) * -1;
			file.write("//Layer " + level + "\r\n");

			for (int y = 0; y < filledArray[1].length; y++) {
				for (int x = 0; x < filledArray[1][1].length; x++) {
					if (filledArray[start][x][y] == 1) {
						file.write("translate([" + factor * x + "," + factor * y + "," + factor * level + "]){\r\n");
						file.write("cube(" + size + ");}\r\n");
					}
				}
			}
		}

		int finTran = (((filledArray.length * 2 + 1) * factor) / 2) + 1;
		int finZ = (filledArray.length - end) * factor;
		file.write("}\r\n");

		if (diff) {
			file.write("translate([-" + finTran + ",-" + finTran + ",-" + finZ + "]){\r\n");
			file.write("difference(){\r\n");
			file.write("whole();\r\n");
			file.write("diff();}}\r\n");
		} else {
			file.write("translate([-" + finTran + ",-" + finTran + ",-" + finZ + "]){\r\n");
			file.write("whole();}\r\n");
		}
		file.close();
	}

	/**
	 * @param fileName
	 * @return
	 */
	private FileWriter getFile(String fileName) {
		File file = new File(fileName);
		FileWriter fr;
		try {
			fr = new FileWriter(file);

		} catch (IOException e) {
			fr = null;
			e.printStackTrace();
		}

		return fr;
	}

	/**
	 * @param layers
	 */
	public void setLayers(int layers) {
		filledArray = new byte[layers][(2 * layers) + 1][(2 * layers) + 1];
		this.layers = layers;
	}

	public int getLayers() {
		return layers;
	}

}
