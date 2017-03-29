package Controller;

import java.util.ArrayList;

import Model.Rule1D;
import Model.Rule2D;
import exception.NotValidRuleException;

public class ControlClass {
	private Rule1D rule1d;
	private Rule2D rule2d;

	public ControlClass(){
		rule1d = new Rule1D();
	}

	public byte[][] getNextLine1D() throws NotValidRuleException{
		return rule1d.getNextLine();
	}

	public void setRule1D(int num, int rows, int columns) throws NotValidRuleException{
		rule1d = new Rule1D(num, columns, rows);
		rule1d.fillArrays();
	}

	public void setRule1D(int num, int rows, int columns, byte[] firstRow) throws NotValidRuleException{
		rule1d = new Rule1D(num, columns, rows);
		rule1d.fillArrays(firstRow);
	}

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

	public int[] getValidRules(){
		return rule1d.getRules();
	}
	
	public ArrayList<String> getCubeTranslations(int ruleNum){
		try {
			rule2d = new Rule2D(ruleNum, 10);
			rule2d.fillArray();
		} catch (NotValidRuleException ex) {
			ex.printStackTrace();
		}
		return rule2d.getCubeTranslations();
	}
}
