package cellPhysics42.view;

import java.util.concurrent.locks.ReentrantLock;
import Controller.ControlClass;
import Model.Rule1D;
import exception.NotValidRuleException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**Class: DemoViewControl.java 
 * @author Bess Burnett 
 * @version 1.0 
 * Course : ITEC 3870 Spring 2017
 * Written: Feb 8, 2017 
 * 
 * This class –  
 * 
 * Purpose: –  
 */
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
	String[] strings;
	@FXML
	Pane rootPane;
	@FXML
	private GridPane mainDemoGrid;
	private int nextRule;
	private double rowDuration;
	private ReentrantLock lock;

	/**
	 * Method name: initialize
	 */
	@FXML
	public void initialize(){
		nextRow = 0;
		numRows = 50;
		numCols = 81;
		rowDuration = 200;
		lock = new ReentrantLock();
	}


	//	/**
	//	 * Method name: getRandomStrings
	//	 * @param numStrings
	//	 * @param stringLength
	//	 * @return
	//	 */
	//	public String[] getRandomStrings(int numStrings, int stringLength){
	//		String[] retStrings = new String[numStrings];
	//		for(int i = 0; i < numStrings; i++){
	//			retStrings[i] = getRandomString(stringLength);
	//		}
	//		return retStrings;
	//	}

	//	/**
	//	 * Method name: getRandomString
	//	 * @param length
	//	 * @return
	//	 */
	//	public String getRandomString(int length){
	//		StringBuilder rerString = new StringBuilder();
	//		for(int k = 0; k < length; k++){
	//			char toAdd = (Math.random() < 0.5) ? '0' : '1';
	//			rerString.append(toAdd);
	//		}
	//		return rerString.toString();
	//	}

	/**
	 * Method name: runDemo
	 */
	@FXML
	public void runDemo(){
		//strings = getRandomStrings(10, 20);
		//displayGrid.setGridLinesVisible(true);
		setGridSize();
		int[] validRules = new Rule1D().getRules();
		Thread fillThread = new Thread(new Runnable() {
			public void run() {
				fillGrid();
			}
		});
		for(int i = 0; i < validRules.length; i++){
			nextRule = validRules[i];
			fillThread.run();
			try {
				fillThread.wait();
			} catch (InterruptedException ex) {
			}
		}
	}
	//		for(int i = 0; i < validRules.length; i++){
	//			try{
	//				nextRule = validRules[i];
	//				fillGrid();
	//				lock.wait();
	//			}
	//			catch(Exception ex){
	//				System.out.println("Stuck");
	//			}
	//		}
	//fillGrid();


/**
 * Method name: setGridSize
 */
public void setGridSize(){
	for(int i = 0; i < numCols; i++){
		displayGrid.getColumnConstraints().add(new ColumnConstraints(displayGrid.getWidth()/numCols));
	}
	for(int i = 0; i < numRows; i++){
		displayGrid.getRowConstraints().add(new RowConstraints(displayGrid.getHeight()/numRows));
	}
}

/**
 * Method name: fillGrid
 */
public void fillGrid(){
	try{
		ControlClass controler = new ControlClass();

		controler.setRule1D(nextRule, numRows, numCols);
		Timeline timeline = new Timeline();
		KeyFrame kfFrame = new KeyFrame(Duration.millis(rowDuration), new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				fillNextLine(controler);
				nextRow++;
			}
		});
		timeline.setCycleCount(numRows);
		timeline.getKeyFrames().add(kfFrame);
		timeline.play();
	}
	catch (Exception ex) {
	}
}

//		for(int i = 0; i < numRows; i++){
//
//			try{
//				byte[][] toFill = controler.getNextLine1D();
//				fillNextLine(getStringRow(toFill));
//				//make line display and rest for second
//				nextRow++;
//			}
//			catch (Exception ex) {
//			}
//		}


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
 */
public void fillNextLine(String nextLine){
	displayGrid.setAlignment(Pos.TOP_CENTER);
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

//	public Rectangle getRectangle(int col, int row){
//		for(Node node : gridChildren){
//			if(displayGrid.getRowIndex(node) == row && displayGrid.getColumnIndex(node) == col)
//				return (Rectangle) node;
//		}
//		rectangle.setFill(Color.WHITE);
//		return rectangle;
//	}

/**
 * Method name: getStringRow
 * @param nextLineArray
 * @return
 */
public String getStringRow(byte[][] nextLineArray){
	StringBuilder nextLine = new StringBuilder();
	for(int i = 0; i < nextLineArray[0].length; i++){
		nextLine.append(nextLineArray[0][i]);
	}
	return nextLine.toString();
}

//	/**
//	 * Method name: fillGrid
//	 */
//	public void fillGrid(){
//		ControlClass controler = new ControlClass();
//		try{
//			controler.setRule1D(22, numRows, numCols);
//		}
//		catch (Exception ex) {
//		}
//		Timeline timeline = new Timeline();
//		for(int i = 0; i < numRows; i++){
//
//			try{
//				byte[][] toFill = controler.getNextLine1D();
//				fillNextLine(getStringRow(toFill), timeline);
//				//make line display and rest for second
//				timeline.play();
//				nextRow++;
//			}
//			catch (Exception ex) {
//			}
//		}
//	}
//	
//
//	/**
//	 * Method name: fillNextLine
//	 * @param nextLine
//	 */
//	public void fillNextLine(String nextLine, Timeline timeline){
//		for(int i = 0; i < nextLine.length(); i++){
//			if(nextLine.charAt(i) == '1'){
//				rectangle = new Rectangle(displayGrid.getWidth()/numCols, displayGrid.getHeight()/numRows, 
//						Color.BLACK);
//				displayGrid.add(rectangle, i, nextRow);
//			}
//			else{
//				rectangle = new Rectangle(displayGrid.getWidth()/numCols, displayGrid.getHeight()/numRows, 
//						Color.WHITE);
//				displayGrid.add(rectangle, i, nextRow);
//			}
//		}
//	}
}
