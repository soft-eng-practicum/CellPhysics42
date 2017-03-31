package Test;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import Controller.ControlClass;
import Model.Rule1D;
import exception.NotValidRuleException;

public class ControlTest {
	
	@Test
	public void testGetNextLine1D() throws NotValidRuleException {
		
		ControlClass con = new ControlClass();
		con.setRule1D(90, 11, 11);
		
		
		

		byte[][] line0 = {{0,0,0,0,0,1,0,0,0,0,0,},{0,0,0,0,0,1,0,0,0,0,0,}};
		byte[][] line1 = {{0,0,0,0,1,0,1,0,0,0,0,},{0,0,0,0,1,2,4,0,0,0,0}};
		byte[][] line2 = {{0,0,0,1,0,0,0,1,0,0,0,},{0,0,0,1,2,5,2,4,0,0,0}};
		byte[][] line3 = {{0,0,1,0,1,0,1,0,1,0,0,},{0,0,1,2,4,0,1,2,4,0,0}};
		byte[][] line4 = {{0,1,0,0,0,0,0,0,0,1,0,},{0,1,2,5,2,5,2,5,2,4,0}};
		byte[][] line5 = {{1,0,1,0,0,0,0,0,1,0,1,},{1,2,4,0,0,0,0,0,1,2,4}};
		byte[][] line6 = {{0,0,0,1,0,0,0,1,0,0,0,},{2,5,2,4,0,0,0,1,2,5,2}};
		byte[][] line7 = {{0,0,1,0,1,0,1,0,1,0,0,},{0,0,1,2,4,0,1,2,4,0,0}};
		byte[][] line8 = {{0,1,0,0,0,0,0,0,0,1,0,},{0,1,2,5,2,5,2,5,2,4,0}};
		byte[][] line9 = {{1,0,1,0,0,0,0,0,1,0,1,},{1,2,4,0,0,0,0,0,1,2,4}};

		Assert.assertArrayEquals(line0, con.getNextLine1D());
		Assert.assertArrayEquals(line1, con.getNextLine1D());
		Assert.assertArrayEquals(line2, con.getNextLine1D());
		Assert.assertArrayEquals(line3, con.getNextLine1D());
		Assert.assertArrayEquals(line4, con.getNextLine1D());
		Assert.assertArrayEquals(line5, con.getNextLine1D());
		Assert.assertArrayEquals(line6, con.getNextLine1D());
		Assert.assertArrayEquals(line7, con.getNextLine1D());
		Assert.assertArrayEquals(line8, con.getNextLine1D());
		Assert.assertArrayEquals(line9, con.getNextLine1D());

		
	}
	
	@Test
	public void testGetNextLine1DFillingFirstRowManually() throws NotValidRuleException {
		
		ControlClass con = new ControlClass();
		byte[] firstLine = {0,0,0,0,0,1,0,0,0,0,0,};
		con.setRule1D(90, 10, 11,firstLine);
		
		
		

		byte[][] line0 = {{0,0,0,0,0,1,0,0,0,0,0,},{0,0,0,0,0,1,0,0,0,0,0,}};
		byte[][] line1 = {{0,0,0,0,1,0,1,0,0,0,0,},{0,0,0,0,1,2,4,0,0,0,0}};
		byte[][] line2 = {{0,0,0,1,0,0,0,1,0,0,0,},{0,0,0,1,2,5,2,4,0,0,0}};
		byte[][] line3 = {{0,0,1,0,1,0,1,0,1,0,0,},{0,0,1,2,4,0,1,2,4,0,0}};
		byte[][] line4 = {{0,1,0,0,0,0,0,0,0,1,0,},{0,1,2,5,2,5,2,5,2,4,0}};
		byte[][] line5 = {{1,0,1,0,0,0,0,0,1,0,1,},{1,2,4,0,0,0,0,0,1,2,4}};
		byte[][] line6 = {{0,0,0,1,0,0,0,1,0,0,0,},{2,5,2,4,0,0,0,1,2,5,2}};
		byte[][] line7 = {{0,0,1,0,1,0,1,0,1,0,0,},{0,0,1,2,4,0,1,2,4,0,0}};
		byte[][] line8 = {{0,1,0,0,0,0,0,0,0,1,0,},{0,1,2,5,2,5,2,5,2,4,0}};
		byte[][] line9 = {{1,0,1,0,0,0,0,0,1,0,1,},{1,2,4,0,0,0,0,0,1,2,4}};

		Assert.assertArrayEquals(line0, con.getNextLine1D());
		Assert.assertArrayEquals(line1, con.getNextLine1D());
		Assert.assertArrayEquals(line2, con.getNextLine1D());
		Assert.assertArrayEquals(line3, con.getNextLine1D());
		Assert.assertArrayEquals(line4, con.getNextLine1D());
		Assert.assertArrayEquals(line5, con.getNextLine1D());
		Assert.assertArrayEquals(line6, con.getNextLine1D());
		Assert.assertArrayEquals(line7, con.getNextLine1D());
		Assert.assertArrayEquals(line8, con.getNextLine1D());
		Assert.assertArrayEquals(line9, con.getNextLine1D());

		
	}
	
	@Test
	public void setRule1DTest() throws NotValidRuleException {
		ControlClass con = new ControlClass();
		
		ArrayList<Integer> firstLine = new ArrayList<>();
		
		byte[][] line0 = {{0,0,0,0,0,1,0,0,0,0,0,},{0,0,0,0,0,1,0,0,0,0,0,}};
		byte[][] line1 = {{0,0,0,0,1,0,1,0,0,0,0,},{0,0,0,0,1,2,4,0,0,0,0}};
		byte[][] line2 = {{0,0,0,1,0,0,0,1,0,0,0,},{0,0,0,1,2,5,2,4,0,0,0}};
		byte[][] line3 = {{0,0,1,0,1,0,1,0,1,0,0,},{0,0,1,2,4,0,1,2,4,0,0}};
		byte[][] line4 = {{0,1,0,0,0,0,0,0,0,1,0,},{0,1,2,5,2,5,2,5,2,4,0}};
		byte[][] line5 = {{1,0,1,0,0,0,0,0,1,0,1,},{1,2,4,0,0,0,0,0,1,2,4}};
		byte[][] line6 = {{0,0,0,1,0,0,0,1,0,0,0,},{2,5,2,4,0,0,0,1,2,5,2}};
		byte[][] line7 = {{0,0,1,0,1,0,1,0,1,0,0,},{0,0,1,2,4,0,1,2,4,0,0}};
		byte[][] line8 = {{0,1,0,0,0,0,0,0,0,1,0,},{0,1,2,5,2,5,2,5,2,4,0}};
		byte[][] line9 = {{1,0,1,0,0,0,0,0,1,0,1,},{1,2,4,0,0,0,0,0,1,2,4}};
		
		firstLine.add(5);
		
		con.setRule1D(90, 10, 11,firstLine);
		
		byte[][] test = con.getNextLine1D();
		Assert.assertArrayEquals(line0, test);
	}
}
