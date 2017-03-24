package cellPhysics42.view;

import java.io.IOException;

import Controller.ControlClass;
import Model.Rule1D;
import cellPhysics42.MainApp;
import exception.NotValidRuleException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**Class: DemoViewControl.java 
 * @author Bess Burnett 
 * @version 1.0 
 * Course : ITEC 3870 Spring 2017
 * Written: Feb 8, 2017 
 * 
 * This class –  controls the demo view
 * 
 * Purpose: –  cycle through all the valid rules and displays their output in a GUI
 */
public class DemoViewControl {
	@FXML
	private Label ruleName;
	@FXML
	private Button exitBt;
	@FXML
	private GridPane displayGrid;
	private int nextRow;
	private int numRows;
	private int numCols;
	private Rectangle rectangle;
	@FXML
	private Button runDemoBt;
	String[] strings;
	@FXML
	private Pane rootPane;
	@FXML
	private GridPane mainDemoGrid;
	private int nextRule;
	private long rowDuration;
	private int[] validRules;
	private int ruleIndex;
	private Color oneColor;
	private Color zeroColor;
	private Color edgeColor;
	private ControlClass controler;

	/**
	 * Method name: initialize
	 * 
	 * FX method called when associated fxml file is built
	 */
	/**
	 * Method name: initialize
	 */
	@FXML
	public void initialize(){
		nextRow = 0;
		numRows = 60;
		numCols = 81;
		rowDuration = 200;
		validRules = new Rule1D().getRules();
		ruleIndex = 0;
		nextRule = validRules[ruleIndex];
		ruleIndex++;
		oneColor = Color.AQUA;
		zeroColor = Color.LIGHTSKYBLUE;
		edgeColor = Color.BLACK;
		rootPane.setBackground(new Background(new BackgroundFill(zeroColor, null, null)));
	}	

	/**
	 * Method name: runDemo
	 * 
	 * button on action to run the demo
	 */
	/**
	 * Method name: runDemo
	 */
	public void runDemo(){
		controler = new ControlClass();
		ruleName.setText("Rule " + nextRule);
		clearGrid();
		//displayGrid.setGridLinesVisible(true);
		fillGrid();
	}
	
	/**
	 * Method name: runDemo
	 * @param oneColor
	 * @param zeroColor
	 */
	public void runDemo(Color oneColor, Color zeroColor){
		this.oneColor = oneColor;
		this.zeroColor = zeroColor;
		edgeColor = oneColor.invert();
		ruleName.setTextFill(zeroColor.invert());
		rootPane.setBackground(new Background(new BackgroundFill(zeroColor, null, null)));
		runDemo();
	}

	/**
	 * Method name: setGridSize
	 * 
	 * sets the correct number and size of columns and rows
	 */
	/**
	 * Method name: setGridSize
	 */
	public void setGridSize(){
		displayGrid.getColumnConstraints().removeAll(displayGrid.getColumnConstraints());
		displayGrid.getRowConstraints().removeAll(displayGrid.getRowConstraints());
		for(int i = 0; i < numCols; i++){
			displayGrid.getColumnConstraints().add(new ColumnConstraints(Math.floor(displayGrid.getWidth()/numCols)));
		}
		for(int i = 0; i < numRows; i++){
			displayGrid.getRowConstraints().add(new RowConstraints(Math.floor(displayGrid.getHeight()/numRows)));
		}
		displayGrid.setAlignment(Pos.CENTER);
	}

	/**
	 * Method name: fillGrid
	 * 
	 * fills in the grid based on what the next rule is
	 */
	/**
	 * Method name: fillGrid
	 */
	public void fillGrid(){
			ControlClass controler = new ControlClass();
			try {
				controler.setRule1D(nextRule, numRows, numCols);
			} catch (NotValidRuleException ex) {
				ex.printStackTrace();
			}

			Timeline timeline = new Timeline();
			KeyFrame keyFrame = new KeyFrame(new Duration(rowDuration), e->{
				fillNextLine(controler);
				nextRow++;
			});
			timeline.setCycleCount(numRows);
			timeline.getKeyFrames().add(keyFrame);
			timeline.setOnFinished(e->{
				if(ruleIndex == validRules.length){
					ruleIndex = 0;
				}
				nextRule = validRules[ruleIndex];
				ruleIndex++;
				nextRow = 0;
				runDemo();
			});
			timeline.play();
	}
	
	/**
	 * Method name: clearGrid
	 * 
	 * resets the grid to blank so a new rule can be started
	 */
	/**
	 * Method name: clearGrid
	 */
	public void clearGrid(){
		displayGrid.getChildren().removeAll(displayGrid.getChildren());
		setGridSize();
	}


	/**
	 * Method name: fillNextLine
	 * @param controler
	 * 
	 * helper method so the to use the controler to get the string to build the next line
	 */
	/**
	 * Method name: fillNextLine
	 * @param controler
	 */
	public void fillNextLine(ControlClass controler){
		try {
			byte[][] toFill = controler.getNextLine1D();
			fillNextLine(getStringRow(toFill));
		} catch (NotValidRuleException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Method name: fillNextLine
	 * @param nextLine
	 * 
	 * takes a bit string and fills the row accordingly 
	 */
	/**
	 * Method name: fillNextLine
	 * @param nextLine
	 */
	public synchronized void fillNextLine(String nextLine){
		for(int i = 0; i < nextLine.length(); i++){
			if(nextLine.charAt(i) == '1'){
				rectangle = new Rectangle(Math.floor(displayGrid.getWidth()/numCols) -1 , Math.floor(displayGrid.getHeight()/numRows) -1 , 
						oneColor);
				rectangle.setStroke(edgeColor);
				rectangle.setStrokeWidth(0.75);
				displayGrid.add(rectangle, i, nextRow);
			}
			else{
				rectangle = new Rectangle(Math.floor(displayGrid.getWidth()/numCols) - 1, Math.floor(displayGrid.getHeight()/numRows) -1 , 
						zeroColor);
				//rectangle.setStroke(edgeColor);
				//rectangle.setStrokeWidth(0.75);
				displayGrid.add(rectangle, i, nextRow);
			}
		}
	}
	
	/**
	 * Method name: exitDemo
	 */
	/**
	 * Method name: exitDemo
	 */
	@FXML
	public void exitDemo(){
		try{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("view/CustomizeView.fxml"));
		System.out.println(loader.getLocation());
		AnchorPane pane = (AnchorPane)loader.load();
		Scene scene = new Scene(pane);
		Stage newStage = (Stage) exitBt.getScene().getWindow();
		newStage.setScene(scene);
		}
		
		catch(IOException ex){
			System.out.println("error" + ex.getMessage());
		}
	}
//	public synchronized void fillNextLine(String nextLine){
//		for(int i = 0; i < nextLine.length(); i++){
//			if(nextLine.charAt(i) == '1'){
//				triangle = new Triangle(Math.floor(displayGrid.getWidth()/numCols), Math.floor(displayGrid.getHeight()/numRows));
//				triangle.setFill(oneColor);
//				//triangle.setStroke(edgeColor);
//				displayGrid.add(triangle, i, nextRow);
//			}
//			else{
//				triangle = new Triangle(Math.floor(displayGrid.getWidth()/numCols), Math.floor(displayGrid.getHeight()/numRows));
//				//rectangle.setStroke(edgeColor);
//				triangle.setFill(zeroColor);
//				displayGrid.add(triangle, i, nextRow);
//			}
//		}
//	}

	/**
	 * Method name: getStringRow
	 * @param nextLineArray
	 * @return String
	 * 
	 * takes a 2d arrary that contains the information for the next line and returns the bit string 
	 * needed to fill the row
	 */
	public String getStringRow(byte[][] nextLineArray){
		StringBuilder nextLine = new StringBuilder();
		for(int i = 0; i < nextLineArray[0].length; i++){
			nextLine.append(nextLineArray[0][i]);
		}
		return nextLine.toString();
	}
}
