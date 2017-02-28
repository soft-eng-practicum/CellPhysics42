package cellPhysics42.view;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class LabeledRectangle extends StackPane {
	private Rectangle rectangle;
	private Label number;
	
	public LabeledRectangle(){
		this(30.0,30.0, "1");
	}
	
	public LabeledRectangle(double width, double height, String lString){
		rectangle = new Rectangle(width, height);
		number = new Label(lString);
		super.getChildren().addAll(rectangle, number);
		super.setWidth(width);
		super.setHeight(height);
		setFill(Color.CYAN);
	}
	
	public void setFill(Color color){
		rectangle.setFill(color);
	}
	
	public void setLabel(String text){
		number.setText(text);
	}
}
