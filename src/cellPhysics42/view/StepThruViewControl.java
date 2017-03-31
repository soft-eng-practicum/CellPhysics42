package cellPhysics42.view;

import java.util.ArrayList;
import Controller.ControlClass;
import cellPhysics42.MainApp;
import exception.NotValidRuleException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**Class: StepThruViewControl.java 
 * @author Bess Burnett 
 * @version 1.0 
 * Course : ITEC  Spring 2017
 * Written: Mar 23, 2017 
 * 
 * This class –  Controls the StepThruView.fxml
 * 
 * Purpose: –  Allows the user to step through each rule as it fills one cell at at time and gives an explanation of
 * what is happening at each step and why the cell is filled the way it is.
 */
public class StepThruViewControl extends AnchorPane {
	@FXML
	private GridPane explanationTextPane;
	@FXML
	private GridPane parentGrid;
	@FXML
	private GridPane squaresGrid;
	@FXML
	private ChoiceBox<Integer> ruleSelectChoiceBox;
	@FXML
	private Label ruleNumLb;
	@FXML
	private Button exitBt;
	@FXML
	private Button startBt;
	@FXML
	private Button nextBt;
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
	@FXML
	private GridPane displayGrid;
	private int ruleNum;
	private int nextRow;
	private int nextCol;
	private int stateUsedToFill;
	private Color oneColor;
	private String oneColorSt;
	private Color zeroColor;
	private String zeroColorSt;
	private Color edgeColor;
	private Color cellToFillHightlight;
	private Color referenceCellHightlight;
	private Color stateUsedHightlight;
	private Color hBoxBackground;
	private ControlClass control;
	private double squareSize;
	private byte[][] previousRowArray;
	private byte[][] rowInProgressArrary;
	private int numRows;
	private int numCols;
	private double explanationTIme;
	private Timeline cellToFillTimeline;
	private Timeline referenceCellTimeline;
	private Timeline stateCellTimeline;
	private Timeline fillCellTimeline;
	private double explanationStepSize;
	private double explanationStartSize;

	
	/**
	 * Method name: initialize
	 * 
	 * called when the fxml file is loaded to set the start values need for the view
	 */
	@FXML
	public void initialize(){
		nextRow = 0;
		nextCol = 0;
		explanationStepSize = 18;
		explanationStartSize = 13;
		explanationTIme = 1000;
		oneColor = Color.BLACK;
		oneColorSt = "black";
		zeroColor = Color.WHITE;
		zeroColorSt = "white";
		stateUsedHightlight = Color.HOTPINK;
		cellToFillHightlight = Color.AQUA;
		referenceCellHightlight = Color.GREENYELLOW;
		setEdge();
		ruleSelectChoiceBox.setValue(90);
		setRuleNum(90);
		setRuleChoices();
		setPaneNumbers();
		numRows = 10;
		numCols = 11;
		nextBt.setVisible(false);
		squareSize = 50;
		hBoxBackground = Color.CORNFLOWERBLUE;
		setGridSize(numRows, numCols);
		setHBoxRims();
	}

	@FXML
	public boolean exitStepThru(){
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("view/CustomizeView.fxml"));
		Stage currentStage = (Stage)exitBt.getScene().getWindow();
		try{
			Scene scene = new Scene(loader.load());
			currentStage.setScene(scene);
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return true;
	}

	/**
	 * Method name: startStepThru
	 * 
	 * begins a step through explanation, sets the next button visible and updates the explanation
	 */
	@FXML
	public boolean startStepThru(){
		setRuleNum(ruleSelectChoiceBox.getValue());
		updateExplanation("The on/off (bottom squares) are set be the binary of the rule number, " + ruleNum + ", which is " + Integer.toBinaryString(ruleNum) + 
				".  Which makes the on/off squares... \n (click next to see on/off squares fill)", 0,explanationStartSize);
		startBt.setVisible(false);
		nextBt.setVisible(true);
		//backBt.setVisible(true);
		return true;
	}

	/**
	 * Method name: nextST
	 * 
	 * preforms the next step in the walk through
	 */
	@FXML
	public boolean nextST(){
		if(nextRow == 0){
			fillOnOffSquares();
			nextRow++;
			setFirstRow();
			updateExplanation("The first row must be given at least one starting on state because only even numbers are used, so without a starting point "
					+ "nothing will ever turn on.", 1, explanationStartSize);
			previousRowArray = rowInProgressArrary;
			try {
				rowInProgressArrary = control.getNextLine1D();
			} catch (NotValidRuleException ex) {
				ex.printStackTrace();
			}
		}
		else if(nextRow == numRows && numCols == nextCol){
			removeOldHighlights();
			stepThruRule();
			startBt.setVisible(true);
		}
		else{
			removeOldHighlights();
			removeOldText();
			stepThruRule();
			System.out.println("col index: " + nextCol);
		}
		return true;
	}

	/**
	 * Method name: setHBoxRims
	 * 
	 * sets the edge color for the state numbers (3 digit binary)
	 */
	private boolean setHBoxRims(){
		numberZero.setBackground(new Background(new BackgroundFill(hBoxBackground, null, null)));
		numberOne.setBackground(new Background(new BackgroundFill(hBoxBackground, null, null)));
		numberTwo.setBackground(new Background(new BackgroundFill(hBoxBackground, null, null)));
		numberThree.setBackground(new Background(new BackgroundFill(hBoxBackground, null, null)));
		numberFour.setBackground(new Background(new BackgroundFill(hBoxBackground, null, null)));
		numberFive.setBackground(new Background(new BackgroundFill(hBoxBackground, null, null)));
		numberSix.setBackground(new Background(new BackgroundFill(hBoxBackground, null, null)));
		numberSeven.setBackground(new Background(new BackgroundFill(hBoxBackground, null, null)));
		return true;
	}

	/**
	 * Method name: setGridSize
	 * @param rows - number of rows
	 * @param columns - number of columns
	 * 
	 * places constraints on the column and row sizes to create the grid to be filled
	 */
	private boolean setGridSize(int rows, int columns){
		displayGrid.getColumnConstraints().removeAll(displayGrid.getColumnConstraints());
		displayGrid.getRowConstraints().removeAll(displayGrid.getRowConstraints());
		for(int i = 0; i < columns; i++){
			displayGrid.getColumnConstraints().add(new ColumnConstraints(squareSize + 1));
		}
		for(int i = 0; i < rows; i++){
			displayGrid.getRowConstraints().add(new RowConstraints(squareSize + 1));
		}
		displayGrid.setAlignment(Pos.CENTER);
		return true;
	}

	/**
	 * Method name: setEdge
	 * 
	 * sets the edge of the block to the inverse color of the oneColor
	 */
	private boolean setEdge(){
		edgeColor = oneColor.invert();
		return true;
	}

	/**
	 * Method name: setRuleNum
	 * @param i - rule number
	 * 
	 * sets the rule number and changes the label to match
	 */
	private void setRuleNum(int i) {
		ruleNum = i;
		ruleNumLb.setText("Rule " + i);
	}

	/**
	 * Method name: removeOldText
	 * 
	 * removes the previous step's explanation
	 */
	private boolean removeOldText(){
		for(int i = explanationTextPane.getChildren().size() - 1; i > 1; i--){
			explanationTextPane.getChildren().remove(i);
		}
		return true;
	}

	/**
	 * Method name: removeOldHighlights
	 * 
	 * bundles the squares to remove their highlights 
	 */
	private boolean removeOldHighlights(){
		ObservableList<Node> rects = displayGrid.getChildren();
		for(Node rect : rects){
			((LabeledRectangle)rect).setOutline(null);
		}
		ArrayList<LabeledRectangle> squares = new ArrayList<>();
		squares.add(squareZero);
		squares.add(squareOne);
		squares.add(squareTwo);
		squares.add(squareThree);
		squares.add(squareFour);
		squares.add(squareFive);
		squares.add(squareSix);
		squares.add(squareSeven);
		clearHighlights(squares);
		setHBoxRims();
		return true;
	}

	/**
	 * Method name: clearHighlights
	 * @param squares - ArrayList<LabeledRectangle>
	 * 
	 * removes any highlights from the list of squares
	 */
	private boolean clearHighlights(ArrayList<LabeledRectangle> squares){
		for(LabeledRectangle rect : squares){
			rect.setOutline(null);
		}
		return true;
	}

	/**
	 * Method name: stepThruRule
	 * 
	 * creates and plays a timeline that steps through how one cell is filled
	 */
	private boolean stepThruRule(){
		cellToFillTimeline = new Timeline();
		referenceCellTimeline = new Timeline();
		stateCellTimeline = new Timeline();
		fillCellTimeline = new Timeline();

		if(nextCol < numCols/2){
			while(rowInProgressArrary[0][nextCol] == 0 && rowInProgressArrary[0][nextCol + 1] == 0){
				displayGrid.add(new LabeledRectangle(squareSize, squareSize, zeroColor), nextCol, nextRow);
				nextCol++;
			}
		}
		nextBt.setVisible(false);
		stateUsedToFill = rowInProgressArrary[1][nextCol];
		System.out.println("fill state: " + stateUsedToFill);

		Duration exDur = new Duration(explanationTIme);
		KeyFrame ex1KF = new KeyFrame(new Duration(500), e->{
			highlightCellToFill();
			updateExplanation("This is the cell to fill...", 2, explanationStepSize);
		});
		cellToFillTimeline.getKeyFrames().addAll(ex1KF);
		cellToFillTimeline.setOnFinished(e->{
			referenceCellTimeline.play();
		});
		cellToFillTimeline.setCycleCount(1);

		KeyFrame refExKF = new KeyFrame(exDur, e->{
			//getText(2).setVisible(true);
			highlightReferenceCells();
			updateExplanation("... so we look at these 3 to decide the state.", 3, explanationStepSize);
		});
		referenceCellTimeline.getKeyFrames().addAll(refExKF);
		referenceCellTimeline.setOnFinished(e->{
			stateCellTimeline.play();
		});
		referenceCellTimeline.setCycleCount(1);

		KeyFrame stateExKF;
		if(nextCol == numCols - 1){
			stateExKF = new KeyFrame(exDur, e->{
				updateExplanation("Since we are on the edge we treat the last square as off so the cells are " + 
						getRefCells() + " so the state is ...", 4, explanationStepSize);
				highlightStateNum();
			});
		}
		else if(nextCol == 0){
			stateExKF = new KeyFrame(exDur, e->{
				updateExplanation("Since we are on the edge we treat the first square as off so the cells are " + 
						getRefCells() + " so the state is ...", 4, explanationStepSize);
				highlightStateNum();
			});
		}
		else{
			stateExKF = new KeyFrame(exDur, e->{
				updateExplanation("Since the cells are "  + getRefCells() + " we get that the state is...", 4, explanationStepSize);
				highlightStateNum();
			});
		}
		stateCellTimeline.getKeyFrames().addAll(stateExKF);
		stateCellTimeline.setOnFinished(e->{
			fillCellTimeline.play();
		});
		stateCellTimeline.setCycleCount(1);

		KeyFrame fillEx = new KeyFrame(exDur, e->{
			updateExplanation(getState() + ", so the cell becomes " + getFillColorSt() + ".", 5, explanationStepSize);
			fillCurentCell();
		});
		fillCellTimeline.getKeyFrames().addAll(fillEx);
		fillCellTimeline.setOnFinished(e->{
			nextCol++;
			if(nextCol == numCols){
				nextCol = 0;
				nextRow++;
				previousRowArray = rowInProgressArrary;
				try {
					rowInProgressArrary = control.getNextLine1D();
				} catch (NotValidRuleException ex) {
					ex.printStackTrace();
				}
			}
			nextBt.setVisible(true);
			//backBt.setVisible(true);
			if(nextCol == numCols && nextRow == numRows){
				nextBt.setVisible(false);
				//backBt.setVisible(false);
				startBt.setVisible(true);
			}
		});
		fillCellTimeline.setCycleCount(1);

		cellToFillTimeline.play();
		return true;
	}

	/**
	 * Method name: fillCurentCell
	 * 
	 * fills the target cell of the step
	 */
	private boolean fillCurentCell(){
		LabeledRectangle rectangle = new LabeledRectangle(squareSize, squareSize, "");
		if(rowInProgressArrary[0][nextCol] == 1){
			rectangle.setFill(oneColor);
		}
		else{
			rectangle.setFill(zeroColor);
		}
		displayGrid.add(rectangle, nextCol, nextRow);
		return true;
	}

	/**
	 * Method name: getFillColorSt
	 * @return String - color
	 * 
	 * return a string represenation for the color of the cell being filled
	 */
	private String getFillColorSt(){
		return (rowInProgressArrary[0][nextCol] == 1) ? oneColorSt : zeroColorSt;
	}

	/**
	 * Method name: getRefCells
	 * @return String - three previous states
	 * 
	 * returns a string containing the 3 previous states being used
	 */
	private String getRefCells(){
		String retString;
		if(nextCol == numCols - 1){
			retString = "" +  previousRowArray[0][nextCol -1] + previousRowArray[1][nextCol ]  + "0";
		}
		else if(nextCol == 0){
			retString = "0" + previousRowArray[1][nextCol ] + previousRowArray[1][nextCol +1];
		}
		else{
			retString =  "" +  previousRowArray[0][nextCol -1] + previousRowArray[1][nextCol ] + previousRowArray[1][nextCol +1];
		}
		return retString;
	}

	/**
	 * Method name: getState
	 * @return String - state
	 * 
	 * returns an off/on string of the cell state
	 */
	private String getState(){
		return (rowInProgressArrary[0][nextCol] == 0) ? "off (0)" : "on (1)";
	}

	/**
	 * Method name: updateExplanation
	 * @param explanation - String 
	 * @param rowToPlace - int
	 * @param fontSize - double
	 * 
	 * updates the explanationPane by setting the given string to the given font size and placing it in the given row of the 
	 * explanationPane
	 */
	private boolean updateExplanation(String explanation, int rowToPlace, double fontSize) {
		Font font = new Font(fontSize);
		explanation  = explanation + "\n";
		Text explanationTx = new Text(explanation);
		explanationTx.setWrappingWidth(300);
		explanationTx.setFont(font);
		explanationTextPane.add(explanationTx, 0, rowToPlace);
		return true;
	}

	/**
	 * Method name: highlightReferenceCells
	 * 
	 * highlights the cells beinging referenced to fill the next cell
	 */
	private boolean highlightReferenceCells(){
		setRectangleHighlight(nextRow - 1, nextCol - 1, referenceCellHightlight);
		setRectangleHighlight(nextRow - 1, nextCol, referenceCellHightlight);
		if(nextCol != numCols - 1){
			setRectangleHighlight(nextRow - 1, nextCol + 1, referenceCellHightlight);
		}
		return true;
	}

	/**
	 * Method name: setRectangleHighlight
	 * @param row - int
	 * @param col - int
	 * @param edgeColor - Color
	 * 
	 * sets the edge color of the rectangle in the given position of the grid
	 */
	private boolean setRectangleHighlight(int row, int col, Color edgeColor) {
		ObservableList<Node> rects = displayGrid.getChildren();
		LabeledRectangle retRec = null;
		for(Node rect : rects){
			if(GridPane.getColumnIndex(rect) == col && GridPane.getRowIndex(rect) == row){
				retRec = (LabeledRectangle)rect;
			}
		}
		retRec.setOutline(edgeColor);
		return true;
	}

	/**
	 * Method name: highlightCellToFill
	 * @return rectangle - LabeledRectangle
	 * 
	 * highlights the cell to fill in this step
	 */
	private LabeledRectangle highlightCellToFill(){
		LabeledRectangle rectangle = new LabeledRectangle(squareSize, squareSize, cellToFillHightlight);
		rectangle.setOutline(cellToFillHightlight);
		displayGrid.add(rectangle, nextCol, nextRow);
		return rectangle;
	}

	/**
	 * Method name: highlightStateNum
	 * 
	 * highlights the state the cell is in
	 */
	private boolean highlightStateNum(){
		switch(stateUsedToFill){
		case 0: squareZero.setOutline(stateUsedHightlight);
		numberZero.setBackground(new Background(new BackgroundFill(stateUsedHightlight, null, null)));
		break;
		case 1: squareOne.setOutline(stateUsedHightlight);
		numberOne.setBackground(new Background(new BackgroundFill(stateUsedHightlight, null, null)));
		break;
		case 2: squareTwo.setOutline(stateUsedHightlight);
		numberTwo.setBackground(new Background(new BackgroundFill(stateUsedHightlight, null, null)));
		break;
		case 3: squareThree.setOutline(stateUsedHightlight);
		numberThree.setBackground(new Background(new BackgroundFill(stateUsedHightlight, null, null)));
		break;
		case 4: squareFour.setOutline(stateUsedHightlight);
		numberFour.setBackground(new Background(new BackgroundFill(stateUsedHightlight, null, null)));
		break;
		case 5: squareFive.setOutline(stateUsedHightlight);
		numberFive.setBackground(new Background(new BackgroundFill(stateUsedHightlight, null, null)));
		break;
		case 6: squareSix.setOutline(stateUsedHightlight);
		numberSix.setBackground(new Background(new BackgroundFill(stateUsedHightlight, null, null)));
		break;
		case 7: squareSeven.setOutline(stateUsedHightlight);
		numberSeven.setBackground(new Background(new BackgroundFill(stateUsedHightlight, null, null)));
		break;
		}
		return true;
	}

	/**
	 * Method name: setFirstRow
	 * 
	 * sets the first row with a block in the center
	 */
	private boolean setFirstRow() {
		control = new ControlClass();
		try{
			control.setRule1D(ruleSelectChoiceBox.getValue(), 10	, 11);
			rowInProgressArrary = control.getNextLine1D();
		}
		catch(NotValidRuleException e)	{

		}
		fillFirstRow();
		return true;
	}

	/**
	 * Method name: fillFirstRow
	 * 
	 * fills the first row
	 */
	private boolean fillFirstRow() {
		for(int i = 0; i < rowInProgressArrary[0] .length; i++){
			if(rowInProgressArrary[0][i] == 0){
				displayGrid.add(new LabeledRectangle(squareSize, squareSize, zeroColor), i	, 0);
			}
			else{
				displayGrid.add(new LabeledRectangle(squareSize, squareSize, oneColor), i	, 0);
			}
		}
		return true;
	}

	/**
	 * Method name: fillOnOffSquares
	 * 
	 * get a binary string of rule number and passes it to the set squares
	 */
	private boolean fillOnOffSquares() {
		return setSquareNums(Integer.toBinaryString(ruleNum));
	}

	/**
	 * Method name: setSquareNums
	 * @param binaryString - String
	 * 
	 * takes the binary string and fill the state squares to the correct value
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
	 * Method name: setRuleChoices
	 * 
	 * sets the valid rules in the rule selection choice box
	 */
	private boolean setRuleChoices(){
		ControlClass controlClass = new ControlClass();
		ObservableList<Integer> rules = FXCollections.observableArrayList();
		int[] rulesInt = controlClass.getValidRules();
		for(int i = 0; i < rulesInt.length; i++){
			rules.add(rulesInt[i]);
		}
		ruleSelectChoiceBox.setItems(rules);
		return true;
	}

	/**
	 * Method name: setPaneNumbers
	 * 
	 * sets the three digit binary in the 3 number states
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
	 * creates a 3 bit string from the number and sets the blocks in the hBox to hold that bit string
	 */
	private boolean setThreeDigBinaryNubmer(int num, HBox numberHolder){
		ObservableList<Node> rects = numberHolder.getChildren();
		String binNum = Integer.toBinaryString(num);
		while(binNum.length() < 3){
			binNum = "0" + binNum;
		}
		for(int index = 0; index < binNum.length(); index++){
			((LabeledRectangle)rects.get(index)).setLabel(binNum.charAt(index));
			//((LabeledRectangle)rects.get(index)).setOutline(edgeColor);
			Color fillColor = (binNum.charAt(index) == '1') ? oneColor : zeroColor;
			((LabeledRectangle)rects.get(index)).setFill(fillColor);
		}
		return true;
	}
}
