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
		rule2d = new Rule2D();
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
	
	
	public ArrayList<String> getCubeTranslations(int ruleNum, int numLayers, int startLayer, int endLayer, int factor){
		try {
			rule2d = new Rule2D(ruleNum, numLayers);
			rule2d.fillArray();
		} catch (NotValidRuleException ex) {
			ex.printStackTrace();
		}
		return rule2d.getCubeTranslations(startLayer - 1, endLayer - 1);
	}
	
	public ArrayList<Integer> get2DRules(){
		int[] rules = rule2d.getRules();
		ArrayList<Integer> rulesAL = new ArrayList<>();
		for(int i = 0; i < rules.length; i++){
			rulesAL.add(rules[i]);
		}
		return rulesAL;
	}
}
