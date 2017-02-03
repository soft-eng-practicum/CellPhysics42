package cellPhysics42.view;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
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
	private Rectangle rectangle;
	@FXML
	private Button runDemoBt;
	private ObservableList<Node> gridChildren;
	String[] strings;
	@FXML
	Pane rootPane;
	
	@FXML
	public void initialize(){
		gridChildren = displayGrid.getChildren();
		nextRow = 0;
		numRows = 10;
		numCols = 20;
	}
	
	public String[] getRandomStrings(int numStrings, int stringLength){
		String[] retStrings = new String[numStrings];
		for(int i = 0; i < numStrings; i++){
			retStrings[i] = getRandomString(stringLength);
		}
		return retStrings;
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
		strings = getRandomStrings(10, 20);
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
			//displayGrid.p
			nextRow++;
		}
	}
	
	
	public void fillNextLine(String nextLine){
		for(int i = 0; i < nextLine.length(); i++){
			if(nextLine.charAt(i) == '1'){
				rectangle = new Rectangle(displayGrid.getWidth()/numCols, displayGrid.getHeight()/numRows, 
						Color.BLACK);
				displayGrid.add(rectangle, i, nextRow);
			}
			else{
				rectangle = new Rectangle(displayGrid.getWidth()/numCols, displayGrid.getHeight()/numRows, 
						Color.WHITE);
				displayGrid.add(rectangle, i, nextRow);
			}
		}
	}
}
