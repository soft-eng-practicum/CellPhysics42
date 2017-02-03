package cellPhysics42.view;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class DemoViewControl {
	@FXML
	private Label ruleName;
	@FXML
	private GridPane displayGrid;
	private int nextRow;
	private int numRows;
	private int numCols;
	@FXML
	private Button runDemoBt;
	
	String[] strings;
	@FXML
	public void initialize(){
		nextRow = 0;
		numRows = 10;
		numCols = 20;
		strings = getRandomStrings(10, 20);
	}
	
	public String[] getRandomStrings(int numStrings, int stringLength){
		ArrayList<String> retStrings = new ArrayList<>();
		for(int i = 0; i < numStrings; i++){
			retStrings.add(getRandomString(stringLength));
		}
		return (String[]) retStrings.toArray();
	}
	
	public String getRandomString(int length){
		StringBuilder rerString = new StringBuilder();
		for(int k = 0; k < length; k++){
			char toAdd = (Math.random() < 0.5) ? '0' : '1';
			rerString.append(toAdd);
		}
		return rerString.toString();
	}
	
	@FXML
	public void runDemo(){
		fillGrid();
	}
	
	public String getStringRow(byte[][] nextLineArray){
		StringBuilder nextLine = new StringBuilder();
		for(int i = 0; i < nextLineArray[1].length; i++){
			nextLine.append(nextLineArray[1][i]);
		}
		return nextLine.toString();
	}
	
	public void fillGrid(){
		for(int i = 0; i < numRows; i++){
			fillNextLine(strings[i]);
			nextRow++;
		}
	}
	
	
	public void fillNextLine(String nextLine){
		for(int i = 0; i < nextLine.length(); i++){
			if(nextLine.charAt(i) == '1'){
				Rectangle rectangle = new Rectangle(displayGrid.getWidth()/numCols, displayGrid.getHeight()/numRows,
						Color.BLACK);
				displayGrid.add(rectangle, i, nextRow);
			}
			else{
				Rectangle rectangle = new Rectangle(displayGrid.getWidth()/numCols, displayGrid.getHeight()/numRows,
						Color.WHITE);
				displayGrid.add(rectangle, i, nextRow);
			}
		}
	}
}
