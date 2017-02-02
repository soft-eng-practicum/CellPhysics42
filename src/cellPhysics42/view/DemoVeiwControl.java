package cellPhysics42.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class DemoVeiwControl {
	@FXML
	private Label ruleName;
	@FXML
	private GridPane displayGrid;
	private int nextRow;
	private int numRows;
	private int numCols;
	
	public String getStringRow(byte[][] nextLineArray){
		StringBuilder nextLine = new StringBuilder();
		for(int i = 0; i < nextLineArray[1].length; i++){
			nextLine.append(nextLineArray[1][i]);
		}
		return nextLine.toString();
	}
	
	public void fillGrid(){
		for(int i = 0; i < numRows; i++){
			fillNextLine(getStringRow(getNext));
			nextRow++;
		}
	}
	
	
	public void fillNextLine(String nextLine){
		for(int i = 0; i < nextLine.length(); i++){
			if(nextLine.charAt(i) == '1'){
				displayGrid.add(new Rectangle(displayGrid.getWidth()/numCols, displayGrid.getHeight()/numRows,
						Color.BLACK), i, nextRow);
			}
		}
	}
}
