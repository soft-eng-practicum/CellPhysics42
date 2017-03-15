package Controller;

import Model.Rule1D;
import exception.NotValidRuleException;

public class ControlClass {
	private Rule1D rule1d;
	
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
	
	public int[] getValidRules(){
		return rule1d.getRules();
	}
}
