package cellPhysics42.view;

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
   * This class –  provides a rectangle with a centered label
   * 
   * Purpose: –  to have a square with an on/off state in it to demonstrate the link between 1, 0 and on/off
   */
public class LabeledRectangle extends StackPane {
	private Rectangle rectangle;
	private Label number;
	
	/**LabeledRectangle
	 * 
	 * no agr constructor
	 *
	 * creates a LabeledRectangle object with default values
	 */
	public LabeledRectangle(){
		this(25.0,25.0, "1");
	}
	
	/**LabeledRectangle
	 * @param width
	 * @param height
	 * @param lString
	 * 3 agr constructor
	 *
	 * creates a LabeledRectangle object with the given values
	 */
	public LabeledRectangle(double width, double height, String lString){
		rectangle = new Rectangle(width, height);
		number = new Label(lString);
		super.getChildren().addAll(rectangle, number);
		super.setWidth(width + 2.0);
		super.setHeight(height + 2.0);
	}
	
	/**LabeledRectangle
	 * @param width
	 * @param height
	 * @param fillColor
	 * 3-agr constructor
	 *
	 * creates a LabeledRectangle object with the given values
	 */
	public LabeledRectangle(double width, double height, Color fillColor){
		rectangle = new Rectangle(width, height);
		number = new Label("");
		super.getChildren().addAll(rectangle, number);
		super.setWidth(width + 2.0);
		super.setHeight(height + 2.0);
		setFill(fillColor);
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
	 * Method name: getFill
	 * @return
	 */
	public Color getFill(){
		return (Color)rectangle.getFill();
	}
	
	/**
	 * Method name: setLabel
	 * @param text
	 */
	public void setLabel(String text){
		number.setText(text);
	}
	
	/**
	 * Method name: getLabel
	 * @return
	 */
	public String getLabel(){
		return number.getText();
	}
	
	/**
	 * Method name: setOutline
	 * @param color
	 */
	public void setOutline(Color color){
		rectangle.setStroke(color);
		rectangle.setStrokeWidth(2.0);
	}
	
	/**
	 * Method name: getOutline
	 * @return
	 */
	public Color getOutline(){
		return (Color)rectangle.getStroke();
	}
	
	/**
	 * Method name: setLabel
	 * @param text
	 */
	public void setLabel(char text){
		number.setText(text + "");
	}
}
