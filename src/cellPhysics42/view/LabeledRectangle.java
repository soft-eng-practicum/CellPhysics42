package cellPhysics42.view;

import com.sun.xml.internal.bind.v2.runtime.Coordinator;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**Class: LabeledRectangle.java 
   * @author Bess Burnett 
   * @version 1.0 
   * Course : ITEC  Spring 2017
   * Written: Mar 2, 2017 
  	   * 
   * This class –  
   * 
   * Purpose: –  
   */
public class LabeledRectangle extends StackPane {
	private Rectangle rectangle;
	private Label number;
	
	/**LabeledRectangle
	 * 
	 * no agr constructor
	 *
	 * creates a LabeledRectangle object with 
	 */
	public LabeledRectangle(){
		this(30.0,30.0, "1");
	}
	
	/**LabeledRectangle
	 * @param width
	 * @param height
	 * @param lString
	 * 3 agr constructor
	 *
	 * creates a LabeledRectangle object with 
	 */
	public LabeledRectangle(double width, double height, String lString){
		rectangle = new Rectangle(width, height);
		number = new Label(lString);
		super.getChildren().addAll(rectangle, number);
		super.setWidth(width + 2.0);
		super.setHeight(height + 2.0);
		setFill(Color.CYAN);
	}
	
	/**
	 * Method name: setFill
	 * @param color
	 */
	public void setFill(Color color){
		rectangle.setFill(color);
		number.setTextFill(color.invert());
	}
	
	/**
	 * Method name: setLabel
	 * @param text
	 */
	public void setLabel(String text){
		number.setText(text);
	}
	
	public void setOutline(Color color){
		rectangle.setStroke(color);
		rectangle.setStrokeWidth(2.0);
	}
	
	/**
	 * Method name: setLabel
	 * @param text
	 */
	public void setLabel(char text){
		number.setText(text + "");
	}
}
