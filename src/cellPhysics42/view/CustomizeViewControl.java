package cellPhysics42.view;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.util.ArrayList;
import Controller.ControlClass;
import cellPhysics42.MainApp;
import exception.NotValidRuleException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;

/**Class: CustomizeViewControl.java 
 * @author Bess Burnett 
 * @version 1.0 
 * Course : ITEC  Spring 2017
 * Written: Feb 17, 2017 
 * 
 * This class –  Controls the customized view
 * 
 * Purpose: –  This enables the user to select which rule they would like, how big they want the screen and 
 * which colors they would like to see the view displayed in.
 */
public class CustomizeViewControl extends BorderPane {
	@FXML
	private BorderPane mainPane;
	@FXML
	private ScrollPane gridScroll;
	@FXML
	private GridPane displayGrid;
	@FXML
	private Label ruleNameLabel;
	@FXML
	private GridPane ruleGrid;
	@FXML
	private GridPane squaresGrid;
	@FXML
	private Button demoButton;
	@FXML
	private Button customizeBt;
	@FXML
	private Button threeDButton;
	@FXML
	private Button stopBt;
	@FXML
	private Button stepThruButton;
	@FXML
	private HBox numberZero;
	@FXML
	private HBox numberOne;
	@FXML
	private HBox numberTwo;
	@FXML
	private HBox numberThree;
	@FXML
	private HBox numberFour;
	@FXML
	private HBox numberFive;
	@FXML
	private HBox numberSix;
	@FXML
	private HBox numberSeven;
	@FXML
	private LabeledRectangle squareZero;
	@FXML
	private LabeledRectangle squareOne;
	@FXML
	private LabeledRectangle squareTwo;
	@FXML
	private LabeledRectangle squareThree;
	@FXML
	private LabeledRectangle squareFour;
	@FXML
	private LabeledRectangle squareFive;
	@FXML
	private LabeledRectangle squareSix;
	@FXML
	private LabeledRectangle squareSeven;
	private Timeline timeline;
	private Color oneColor;
	private Color zeroColor;
	private Color edgeColor;
	private double rowDuration;
	private int nextRow;
//	private int numRows;
//	private int numCols;
	private double squareSize;
	
	/**
	 * Method name: initialize
	 * 
	 * called when fxml document is loaded to set initial values
	 */
	@FXML
	public void initialize(){
		edgeColor = Color.BLACK;
		stopBt.setText("Start");
		oneColor = Color.BLACK;
		zeroColor = Color.WHITE;
	}

	/**
	 * Method name: run3DView
	 * 
	 * switches to the 3D view
	 */
	@FXML
	public boolean run3DView(){
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/View3D.fxml"));
			System.out.println(loader.getLocation());
			AnchorPane pane = (AnchorPane)loader.load();
			Scene scene = new Scene(pane);
			Stage newStage = (Stage) threeDButton.getScene().getWindow();
			newStage.setScene(scene);
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return true;
	}

	/**
	 * Method name: runCustom
	 * @param width
	 * @param height
	 * @param rowDur
	 * @param oneC
	 * @param zeroC
	 * @param ruleNum
	 * @param firstRow - the indexes of on squares in the first row
	 * 
	 * runs the rule with the given inputs
	 */
	public boolean runCustom(int width, int height, double rowDur, Color oneC, Color zeroC, int ruleNum, ArrayList<Integer> firstRow){
		ControlClass controler = new ControlClass();
		//mainPane.setCenter(gridScroll);
		displayGrid.getChildren().removeAll(displayGrid.getChildren());
		this.zeroColor = zeroC;
		this.oneColor = oneC;
		setRuleNum(ruleNum);
		//mainPane.setTop(ruleGrid);
		squareSize = Math.floor(gridScroll.getWidth()/width);
		setGridSize(height, width);
		try {
			controler.setRule1D(ruleNum, height, width, firstRow);
		} catch (NotValidRuleException ex) {
			ex.printStackTrace();
		}
		rowDuration = rowDur * 100;
		timeline = new Timeline();
		KeyFrame keyFrame = new KeyFrame(new Duration(rowDuration), e->{
			fillNextLine(controler);
			nextRow++;
		});
		timeline.setCycleCount(height);
		timeline.getKeyFrames().add(keyFrame);
		timeline.setOnFinished(e->{
			nextRow = 0;
		});
		stopBt.setText("Stop");
		timeline.play();
		return true;
	}

	/**
		 * Method name: runDemo
		 * 
		 * switches to the demo view and starts the demo
		 */
		@FXML
		public boolean runDemo(){
			try{
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(MainApp.class.getResource("view/DemoView.fxml"));
				System.out.println(loader.getLocation());
				AnchorPane pane = (AnchorPane)loader.load();
				Scene scene = new Scene(pane);
				Stage newStage = (Stage) demoButton.getScene().getWindow();
				DemoViewControl dvc = loader.getController();
				newStage.setScene(scene);
				dvc.runDemo(oneColor, zeroColor);
			}
	
			catch(IOException ex){
				System.out.println("error" + ex.getMessage());
			}
			return true;
		}

	@FXML
	public boolean stopRunThru(){
		if(stopBt.getText().equals("Stop")){
			stopBt.setText("Start");
			timeline.stop();
		}
		else{
			stopBt.setText("Stop");
			runCustom(37, 55, 1000.0, oneColor, zeroColor, 90, new ArrayList<Integer>());
		}
		return true;
	}

	/**
	 * Method name: showCustomOptions
	 * 
	 * loads the cusomize options
	 */
	@FXML
	public boolean showCustomOptions(){
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/CustomizeOptions.fxml"));
			StackPane pane = (StackPane)loader.load();
			pane.setPrefWidth(mainPane.getWidth());
			pane.setPrefHeight(mainPane.getHeight());
			//pane.setBackground(new Background(new BackgroundFill(Color.CADETBLUE, null, null)));
			Stage currentStage = (Stage) customizeBt.getScene().getWindow();
			currentStage.setScene(new Scene(pane));
	
			System.out.println(loader.getLocation());
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return true;
	}

	/**
	 * Method name: runStepThru
	 * 
	 * changes to the step-thru view
	 */
	@FXML
	public boolean runStepThru(){
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("view/StepThruView.fxml"));
		System.out.println(loader.getLocation());
		try{
		AnchorPane pane = loader.load();
		Stage stage = (Stage)stepThruButton.getScene().getWindow();
		stage.setScene(new Scene(pane));
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return true;
	}

	/**
	 * Method name: setColors
	 * @param oneColor2
	 * @param zeroColor2
	 * 
	 * sets the one and zero color to the given inputs
	 */
	public boolean setColors(Color oneColor2, Color zeroColor2) {
		oneColor = oneColor2;
		zeroColor = zeroColor2;
		setPaneNumbers();
		return true;
	}

	/**
	 * Method name: setRuleNum
	 * @param ruleNum
	 * 
	 * sets the rule number, rule label, and state squares to the given rule number
	 */
	private boolean setRuleNum(int ruleNum) {
		ruleNameLabel.setText("Rule " + ruleNum);
		setSquareNums(Integer.toBinaryString(ruleNum));
		setPaneNumbers();
		return true;
	}


	/**
	 * Method name: setSquareNums
	 * @param binaryString
	 * 
	 * sets the state squares to match the given binary string
	 */
	private boolean setSquareNums(String binaryString) {
		while(binaryString.length() < 8){
			binaryString = "0" + binaryString;
		}
		squareZero.setLabel(binaryString.charAt(7));
		squareZero.setOutline(edgeColor);
		squareZero.setFill((binaryString.charAt(7) == '1') ? oneColor : zeroColor);
		squareOne.setLabel(binaryString.charAt(6));
		squareOne.setOutline(edgeColor);
		squareOne.setFill((binaryString.charAt(6) == '1') ? oneColor : zeroColor);
		squareTwo.setLabel(binaryString.charAt(5));
		squareTwo.setOutline(edgeColor);
		squareTwo.setFill((binaryString.charAt(5) == '1') ? oneColor : zeroColor);
		squareThree.setLabel(binaryString.charAt(4));
		squareThree.setOutline(edgeColor);
		squareThree.setFill((binaryString.charAt(4) == '1') ? oneColor : zeroColor);
		squareFour.setLabel(binaryString.charAt(3));
		squareFour.setOutline(edgeColor);
		squareFour.setFill((binaryString.charAt(3) == '1') ? oneColor : zeroColor);
		squareFive.setLabel(binaryString.charAt(2));
		squareFive.setOutline(edgeColor);
		squareFive.setFill((binaryString.charAt(2) == '1') ? oneColor : zeroColor);
		squareSix.setLabel(binaryString.charAt(1));
		squareSix.setOutline(edgeColor);
		squareSix.setFill((binaryString.charAt(1) == '1') ? oneColor : zeroColor);
		squareSeven.setLabel(binaryString.charAt(0));
		squareSeven.setOutline(edgeColor);
		squareSeven.setFill((binaryString.charAt(0) == '1') ? oneColor : zeroColor);
		return true;
	}

	/**
	 * Method name: setPaneNumbers
	 * 
	 * sets the 3 digit state squares
	 */
	private boolean setPaneNumbers(){
		setThreeDigBinaryNubmer(0, numberZero);
		setThreeDigBinaryNubmer(1, numberOne);
		setThreeDigBinaryNubmer(2, numberTwo);
		setThreeDigBinaryNubmer(3, numberThree);
		setThreeDigBinaryNubmer(4, numberFour);
		setThreeDigBinaryNubmer(5, numberFive);
		setThreeDigBinaryNubmer(6, numberSix);
		setThreeDigBinaryNubmer(7, numberSeven);
		return true;
	}

	/**
	 * Method name: setThreeDigBinaryNubmer
	 * @param num
	 * @param numberHolder
	 * 
	 * sets the 3 squares to match the binary of the number
	 */
	private boolean setThreeDigBinaryNubmer(int num, HBox numberHolder){
		ObservableList<Node> rects = numberHolder.getChildren();
		String binNum = Integer.toBinaryString(num);
		while(binNum.length() < 3){
			binNum = "0" + binNum;
		}
		for(int index = 0; index < binNum.length(); index++){
			((LabeledRectangle)rects.get(index)).setLabel(binNum.charAt(index));
			((LabeledRectangle)rects.get(index)).setOutline(edgeColor);
			Color fillColor = (binNum.charAt(index) == '1') ? oneColor : zeroColor;
			((LabeledRectangle)rects.get(index)).setFill(fillColor);
		}
		return true;
	}
	
	/**
	 * Method name: setGridSize
	 * @param rows
	 * @param columns
	 * 
	 * sets the display grid to the given number of rows and columns
	 */
	private boolean setGridSize(int rows, int columns){
		displayGrid.getColumnConstraints().removeAll(displayGrid.getColumnConstraints());
		displayGrid.getRowConstraints().removeAll(displayGrid.getRowConstraints());
		for(int i = 0; i < columns; i++){
			displayGrid.getColumnConstraints().add(new ColumnConstraints(squareSize));
		}
		for(int i = 0; i < rows; i++){
			displayGrid.getRowConstraints().add(new RowConstraints(squareSize));
		}
		displayGrid.setAlignment(Pos.CENTER);
		return true;
	}

	/**
	 * Method name: fillNextLine
	 * @param controler
	 * 
	 * helper method  to use the controler to get the string to build the next line
	 */
	private boolean fillNextLine(ControlClass controler){
		try {
			byte[][] toFill = controler.getNextLine1D();
			fillNextLine(getStringRow(toFill));
		} catch (NotValidRuleException ex) {
			ex.printStackTrace();
		}
		return true;
	}

	/**
	 * Method name: fillNextLine
	 * @param nextLine
	 * 
	 * takes a bit string and fills the row accordingly 
	 */
	private boolean fillNextLine(String nextLine){
		Rectangle rectangle;
		for(int i = 0; i < nextLine.length(); i++){
			if(nextLine.charAt(i) == '1'){
				rectangle = new Rectangle(squareSize -1 , squareSize -1 , oneColor);
				rectangle.setStroke(edgeColor);
				rectangle.setStrokeWidth(0.75);
				displayGrid.add(rectangle, i, nextRow);
			}
			else{
				rectangle = new Rectangle(squareSize -1 , squareSize -1 , zeroColor);
				displayGrid.add(rectangle, i, nextRow);
			}
		}
		return true;
	}
	
	/**
	 * Method name: getStringRow
	 * @param nextLineArray
	 * @return String
	 * 
	 * takes a 2d arrary that contains the information for the next line and returns the bit string 
	 * needed to fill the row
	 */
	private String getStringRow(byte[][] nextLineArray){
		StringBuilder nextLine = new StringBuilder();
		for(int i = 0; i < nextLineArray[0].length; i++){
			nextLine.append(nextLineArray[0][i]);
		}
		return nextLine.toString();
	}
}

