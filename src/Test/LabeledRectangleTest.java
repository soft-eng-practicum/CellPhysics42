package Test;

import org.junit.Assert;
import org.junit.Test;

import cellPhysics42.view.LabeledRectangle;
import javafx.scene.paint.Color;

public class LabeledRectangleTest {
	
	  
	  JavaFXThreadingRule jfxRule = new JavaFXThreadingRule();
	 
	
	@Test
	public void setFillTest(){
		
		LabeledRectangle lr = new LabeledRectangle();
		lr.setFill(Color.CORNSILK);
		Color actual = lr.getFill();
		Color expected = Color.CORNSILK;
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void setLabelStringTest(){
		LabeledRectangle lr = new LabeledRectangle(10, 10, Color.BEIGE);
		lr.setLabel("Label");
		String actual = lr.getLabel();
		String expected = "Label";
		Assert.assertEquals(expected, actual);
	}
	@Test
	public void setOutlineTest(){
		LabeledRectangle lr = new LabeledRectangle(10, 10, "char");
		lr.setOutline(Color.PURPLE);
		Color actual = lr.getOutline();
		Color expected = Color.PURPLE;
		Assert.assertEquals(expected, actual);
	}
	@Test
	public void setLabelCharTest(){
		LabeledRectangle lr = new LabeledRectangle(10, 10, Color.BEIGE);
		lr.setLabel('r');
		String actual = lr.getLabel();
		String expected = "r";
		Assert.assertEquals(expected, actual);
	}
}
