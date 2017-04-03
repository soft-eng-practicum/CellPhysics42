package Controller;

import java.io.IOException;
import java.util.ArrayList;

import Model.Rule1D;
import Model.Rule2D;
import exception.NotValidRuleException;

/**Class: ControlClass.java 
   * @author Bess Burnett 
   * @version 1.0 
   * Course : ITEC  Spring 2017
   * Written: Mar 31, 2017 
  	   * 
   * This class –  controls the interactions between the view and the model
   *  
   */
public class ControlClass {
	private Rule1D rule1d;
	private Rule2D rule2d;

	/**ControlClass
	 * 
	 * agr constructor
	 *
	 * creates a ControlClass object with default values
	 */
	public ControlClass(){
		rule1d = new Rule1D();
		rule2d = new Rule2D();
	}

	/**
	 * Method name: getNextLine1D
	 * @return byte[][]
	 * @throws NotValidRuleException
	 * 
	 * returns the next line of the 1D rule
	 */
	public byte[][] getNextLine1D() throws NotValidRuleException{
		return rule1d.getNextLine();
	}

	/**
	 * Method name: setRule1D
	 * @param num
	 * @param rows
	 * @param columns
	 * @throws NotValidRuleException
	 * 
	 * sets the 1D rule to the given values and fills the array
	 */
	public void setRule1D(int num, int rows, int columns) throws NotValidRuleException{
		rule1d = new Rule1D(num, columns, rows);
		rule1d.fillArrays();
	}

	/**
	 * Method name: setRule1D
	 * @param num
	 * @param rows
	 * @param columns
	 * @param firstRow
	 * @throws NotValidRuleException
	 * 
	 * sets the 1D rule to the given values and fills the array
	 */
	public void setRule1D(int num, int rows, int columns, byte[] firstRow) throws NotValidRuleException{
		rule1d = new Rule1D(num, columns, rows);
		rule1d.fillArrays(firstRow);
	}

	/**
	 * Method name: setRule1D
	 * @param num
	 * @param rows
	 * @param columns
	 * @param firstRowList
	 * @throws NotValidRuleException
	 * 
	 * sets the 1D rule to the given values and fills the array
	 */
	public void setRule1D(int num, int rows, int columns, ArrayList<Integer> firstRowList) throws NotValidRuleException{
		rule1d = new Rule1D(num, columns, rows);
		if(firstRowList.isEmpty()){
			rule1d.fillArrays();
		}
		else{
		byte[] firstRow = new byte[columns];
		for(int i = 0; i < firstRow.length; i ++){
			firstRow[i] = (firstRowList.contains(i)) ? (byte)1 : (byte)0;
		}
		rule1d.fillArrays(firstRow);
		}
	}

	/**
	 * Method name: getValidRules1D
	 * @return int[]
	 * 
	 * returns all the valid 1D rules
	 */
	public int[] getValidRules1D(){
		return rule1d.getRules();
	}
	
	/**
	 * Method name: getCubeTranslations
	 * @param ruleNum
	 * @param numLayers
	 * @param startLayer
	 * @param endLayer
	 * @param factor
	 * @return ArrayList<String>
	 * 
	 * returns a set of Strings representing the x,y,z coordinates shifted back to center
	 */
	public ArrayList<String> getCubeTranslations(int ruleNum, int numLayers, int startLayer, int endLayer, int factor){
		try {
			rule2d = new Rule2D(ruleNum, numLayers);
			rule2d.fillArray();
		} catch (NotValidRuleException ex) {
			ex.printStackTrace();
		}
		return rule2d.getCubeTranslations(startLayer - 1, endLayer - 1);
	}
	
	/**
	 * Method name: get2DRules
	 * @return ArrayList<Integer>
	 * 
	 * returns all the valid 2D rules
	 */
	public ArrayList<Integer> get2DRules(){
		int[] rules = rule2d.getRules();
		ArrayList<Integer> rulesAL = new ArrayList<>();
		for(int i = 0; i < rules.length; i++){
			rulesAL.add(rules[i]);
		}
		return rulesAL;
	}

	public void save2D(String path, int start, int end) {
		try {
			rule2d.save3DFileByLayers(path, start, end);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
