package cellPhysics42.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import Controller.ControlClass;
import cellPhysics42.MainApp;
import exception.NotValidRuleException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.FillTransition;

/**Class: StepThruViewControl.java 
 * @author Bess Burnett 
 * @version 1.0 
 * Course : ITEC  Spring 2017
 * Written: Mar 23, 2017 
 * 
 * This class –  
 * 
 * Purpose: –  
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
//	@FXML
//	private Button backBt;
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
	private double cellHighlightTime;
	private Timeline cellToFillTimeline;
	private Timeline referenceCellTimeline;
	private Timeline stateCellTimeline;
	private Timeline fillCellTimeline;
	private double explanationStepSize;
	private double explanationStartSize;

	/**
	 * Method name: initialize
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
		//		explanationTextPane.getChildren().addListener(new ListChangeListener<Node>() {
		//			@Override
		//			public void onChanged(javafx.collections.ListChangeListener.Change<? extends Node> c) {
		//				Scene scene = explanationTextPane.getScene();
		//				Stage currentStage = (Stage)explanationTextPane.getScene().getWindow();
		//				currentStage.setScene(scene);
		//			}
		//		});

		//here be the problem

		//squareSize = Math.floor(parentGrid.getWidth());
		nextBt.setVisible(false);
		//backBt.setVisible(false);
		squareSize = 50;
		hBoxBackground = Color.CORNFLOWERBLUE;
		setGridSize(numRows, numCols);
		setHBoxRims();
	}

	/**
	 * Method name: setHBoxRims
	 */
	public void setHBoxRims(){
		numberZero.setBackground(new Background(new BackgroundFill(hBoxBackground, null, null)));
		numberOne.setBackground(new Background(new BackgroundFill(hBoxBackground, null, null)));
		numberTwo.setBackground(new Background(new BackgroundFill(hBoxBackground, null, null)));
		numberThree.setBackground(new Background(new BackgroundFill(hBoxBackground, null, null)));
		numberFour.setBackground(new Background(new BackgroundFill(hBoxBackground, null, null)));
		numberFive.setBackground(new Background(new BackgroundFill(hBoxBackground, null, null)));
		numberSix.setBackground(new Background(new BackgroundFill(hBoxBackground, null, null)));
		numberSeven.setBackground(new Background(new BackgroundFill(hBoxBackground, null, null)));
	}

	/**
	 * Method name: setGridSize
	 * @param rows
	 * @param columns
	 */
	public void setGridSize(int rows, int columns){
		displayGrid.getColumnConstraints().removeAll(displayGrid.getColumnConstraints());
		displayGrid.getRowConstraints().removeAll(displayGrid.getRowConstraints());
		for(int i = 0; i < columns; i++){
			displayGrid.getColumnConstraints().add(new ColumnConstraints(squareSize + 1));
		}
		for(int i = 0; i < rows; i++){
			displayGrid.getRowConstraints().add(new RowConstraints(squareSize + 1));
		}
		displayGrid.setAlignment(Pos.CENTER);
	}

	/**
	 * Method name: setEdge
	 */
	public void setEdge(){
		edgeColor = oneColor.invert();
	}

	/**
	 * Method name: setRuleNum
	 * @param i
	 */
	private void setRuleNum(int i) {
		ruleNum = i;
		ruleNumLb.setText("Rule " + i);
	}

	@FXML
	public void stepBack(){
		nextCol--;
		if(nextCol < 0){
			nextCol = 0;
		}
		removeOldHighlights();
		removeOldText();
		clearPreviousCell();
	}
	
	private void clearPreviousCell(){
		
	}

	@FXML
	public void exitStepThru(){
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
	}

	/**
	 * Method name: startStepThru
	 */
	@FXML
	public void startStepThru(){
		setRuleNum(ruleSelectChoiceBox.getValue());
		updateExplanation("The on/off (bottom squares) are set be the binary of the rule number, " + ruleNum + ", which is " + Integer.toBinaryString(ruleNum) + 
				".  Which makes the on/off squares... \n (click next to see on/off squares fill)", 0,explanationStartSize);
		startBt.setVisible(false);
		nextBt.setVisible(true);
		//backBt.setVisible(true);
	}

	/**
	 * Method name: nextST
	 */
	@FXML
	public void nextST(){
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
	}

	private void removeOldText(){
		for(int i = explanationTextPane.getChildren().size() - 1; i > 1; i--){
			explanationTextPane.getChildren().remove(i);
		}
	}

	/**
	 * Method name: removeOldHighlights
	 */
	private void removeOldHighlights(){
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
	}

	/**
	 * Method name: clearHighlights
	 * @param squares
	 */
	public void clearHighlights(ArrayList<LabeledRectangle> squares){
		for(LabeledRectangle rect : squares){
			rect.setOutline(null);
		}
	}

	//	public void stepThruRule(){
	//		cellToFillTimeline = new Timeline();
	//		referenceCellTimeline = new Timeline();
	//		stateCellTimeline = new Timeline();
	//		fillCellTimeline = new Timeline();
	//		while(rowInProgressArrary[0][nextCol] == 0 && rowInProgressArrary[0][nextCol + 1] == 0){
	//			displayGrid.add(new LabeledRectangle(squareSize, squareSize, zeroColor), nextCol, nextRow);
	//			nextCol++;
	//		}
	//		nextBt.setVisible(false);
	//		backBt.setVisible(false);
	//		stateUsedToFill = rowInProgressArrary[1][nextCol];
	//		Duration exDur = new Duration(explanationTIme);
	//		Duration cellDur = new Duration(cellHighlightTime);
	//		KeyFrame ex1KF = new KeyFrame(exDur, e->{
	//			updateExplanation("This is the cell to fill...", true);
	//		});
	//		KeyFrame fillCellHighKF = new KeyFrame(cellDur, e->{
	//			highlightCellToFill();
	//		});
	//		KeyFrame refExKF = new KeyFrame(exDur, e->{
	//			//getText(2).setVisible(true);
	//			updateExplanation("... so we look at these 3 to decide the state");
	//		});
	//		KeyFrame refCellKF = new KeyFrame(cellDur, e->{
	//			highlightReferenceCells();
	//		});
	//		Timeline timeline2 = new Timeline(refExKF, refCellKF);
	//		KeyFrame stateExKF = new KeyFrame(exDur, e->{
	//			updateExplanation("Since the cells are "  + getRefCells() + " we get that the state is...");
	//		});
	//		KeyFrame stateCellKF = new KeyFrame(cellDur, e->{
	//			highlightStateNum();
	//		});
	//		KeyFrame fillEx = new KeyFrame(exDur, e->{
	//			updateExplanation(getState() + ", so the cell becomes " + getFillColorSt() + ".");
	//		});
	//		KeyFrame fillCellKF = new KeyFrame(cellDur, e->{
	//			fillCurentCell();
	//		});
	//		//		updateExplanation("This is the cell to fill...", false);
	//		//		updateExplanation("... so we look at these 3 to decide the state", false);
	//		//		updateExplanation("Since the cells are "  + getRefCells() + " we get that the state is...", false);
	//		//		updateExplanation(getState() + ", so the cell becomes " + getFillColorSt() + ".", false);
	//
	//	}
	/**
	 * Method name: stepThruRule
	 */
	public void stepThruRule(){
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
		//backBt.setVisible(false);
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
		//		updateExplanation("This is the cell to fill...", false);
		//		updateExplanation("... so we look at these 3 to decide the state", false);
		//		updateExplanation("Since the cells are "  + getRefCells() + " we get that the state is...", false);
		//		updateExplanation(getState() + ", so the cell becomes " + getFillColorSt() + ".", false);


	}
	//	public void stepThruRule(){
	//		cellToFillTimeline = new Timeline();
	//		referenceCellTimeline = new Timeline();
	//		stateCellTimeline = new Timeline();
	//		fillCellTimeline = new Timeline();
	//		
	//		while(rowInProgressArrary[0][nextCol] == 0 && rowInProgressArrary[0][nextCol + 1] == 0){
	//			displayGrid.add(new LabeledRectangle(squareSize, squareSize, zeroColor), nextCol, nextRow);
	//			nextCol++;
	//		}
	//		nextBt.setVisible(false);
	//		backBt.setVisible(false);
	//		stateUsedToFill = rowInProgressArrary[1][nextCol];
	//
	//		Duration exDur = new Duration(explanationTIme);
	//		Duration cellDur = new Duration(cellHighlightTime);
	//		KeyFrame ex1KF = new KeyFrame(exDur, e->{
	//			updateExplanation("This is the cell to fill...", true);
	//		});
	//		KeyFrame fillCellHighKF = new KeyFrame(cellDur, e->{
	//			highlightCellToFill();
	//		});
	//		cellToFillTimeline.getKeyFrames().addAll(ex1KF, fillCellHighKF);
	//		cellToFillTimeline.setOnFinished(e->{
	//			referenceCellTimeline.play();
	//		});
	//		cellToFillTimeline.setCycleCount(1);
	//		
	//		KeyFrame refExKF = new KeyFrame(exDur, e->{
	//			//getText(2).setVisible(true);
	//			updateExplanation("... so we look at these 3 to decide the state", true);
	//		});
	//		KeyFrame refCellKF = new KeyFrame(cellDur, e->{
	//			highlightReferenceCells();
	//		});
	//		referenceCellTimeline.getKeyFrames().addAll(refExKF, refCellKF);
	//		referenceCellTimeline.setOnFinished(e->{
	//			stateCellTimeline.play();
	//		});
	//		referenceCellTimeline.setCycleCount(1);
	//		
	//		KeyFrame stateExKF = new KeyFrame(exDur, e->{
	//			updateExplanation("Since the cells are "  + getRefCells() + " we get that the state is...", true);
	//		});
	//		KeyFrame stateCellKF = new KeyFrame(cellDur, e->{
	//			highlightStateNum();
	//		});
	//		stateCellTimeline.getKeyFrames().addAll(stateExKF, stateCellKF);
	//		stateCellTimeline.setOnFinished(e->{
	//			fillCellTimeline.play();
	//		});
	//		stateCellTimeline.setCycleCount(1);
	//		
	//		KeyFrame fillEx = new KeyFrame(exDur, e->{
	//			updateExplanation(getState() + ", so the cell becomes " + getFillColorSt() + ".", true);
	//		});
	//		KeyFrame fillCellKF = new KeyFrame(cellDur, e->{
	//			fillCurentCell();
	//		});
	//		fillCellTimeline.getKeyFrames().addAll(fillEx, fillCellKF);
	//		fillCellTimeline.setOnFinished(e->{
	//			nextBt.setVisible(true);
	//			backBt.setVisible(true);
	//			nextCol++;
	//			if(nextCol > numCols){
	//				nextCol = 0;
	//				nextRow++;
	//			}
	//		});
	//		fillCellTimeline.setCycleCount(1);
	//		
	//		cellToFillTimeline.play();
	//		//		updateExplanation("This is the cell to fill...", false);
	//		//		updateExplanation("... so we look at these 3 to decide the state", false);
	//		//		updateExplanation("Since the cells are "  + getRefCells() + " we get that the state is...", false);
	//		//		updateExplanation(getState() + ", so the cell becomes " + getFillColorSt() + ".", false);
	//
	//		
	//	}

	/**
	 * Method name: getText
	 * @param rowIndex
	 * @return
	 */
	private Text getText(int rowIndex){
		ObservableList<Node> labels = explanationTextPane.getChildren();
		for(Node label : labels){
			if(GridPane.getRowIndex(label) == rowIndex){
				return (Text)label;
			}
		}
		return null;
	}

	/**
	 * Method name: fillCurentCell
	 */
	private void fillCurentCell(){
		LabeledRectangle rectangle = new LabeledRectangle(squareSize, squareSize, "");
		if(rowInProgressArrary[0][nextCol] == 1){
			rectangle.setFill(oneColor);
		}
		else{
			rectangle.setFill(zeroColor);
		}
		displayGrid.add(rectangle, nextCol, nextRow);
	}

	/**
	 * Method name: getFillColorSt
	 * @return
	 */
	private String getFillColorSt(){
		return (rowInProgressArrary[0][nextCol] == 1) ? oneColorSt : zeroColorSt;
	}

	/**
	 * Method name: getRefCells
	 * @return
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
	 * @return
	 */
	private String getState(){
		return (rowInProgressArrary[0][nextCol] == 0) ? "off (0)" : "on (1)";
	}

	/**
	 * Method name: updateExplanation
	 * @param explanation
	 * @param canSee
	 */
	private void updateExplanation(String explanation, int rowToPlace, double fontSize) {
		Font font = new Font(fontSize);
		explanation  = explanation + "\n";
		Text explanationTx = new Text(explanation);
		explanationTx.setWrappingWidth(300);
		explanationTx.setFont(font);
		explanationTextPane.add(explanationTx, 0, rowToPlace);;
	}

	/**
	 * Method name: highlightReferenceCells
	 */
	private void highlightReferenceCells(){
		setRectangleHighlight(nextRow - 1, nextCol - 1, referenceCellHightlight);
		//		left.setStroke(referenceCellHightlight);
		//		displayGrid.add(left, nextCol - 1, nextRow - 1);
		setRectangleHighlight(nextRow - 1, nextCol, referenceCellHightlight);
		//center.setStroke(referenceCellHightlight);
		//		displayGrid.add(center, nextCol , nextRow - 1);
		if(nextCol != numCols - 1){
			setRectangleHighlight(nextRow - 1, nextCol + 1, referenceCellHightlight);
		}
		//right.setStroke(referenceCellHightlight);
		//		displayGrid.add(right, nextCol  + 1, nextRow - 1);
	}

	/**
	 * Method name: setRectangleHighlight
	 * @param row
	 * @param col
	 * @param edgeColor
	 */
	private void setRectangleHighlight(int row, int col, Color edgeColor) {
		ObservableList<Node> rects = displayGrid.getChildren();
		LabeledRectangle retRec = null;
		for(Node rect : rects){
			if(GridPane.getColumnIndex(rect) == col && GridPane.getRowIndex(rect) == row){
				retRec = (LabeledRectangle)rect;
			}
		}
		retRec.setOutline(edgeColor);
	}

	/**
	 * Method name: highlightCellToFill
	 * @return
	 */
	private LabeledRectangle highlightCellToFill(){
		LabeledRectangle rectangle = new LabeledRectangle(squareSize, squareSize, cellToFillHightlight);
		rectangle.setOutline(cellToFillHightlight);
		displayGrid.add(rectangle, nextCol, nextRow);
		return rectangle;
	}

	/**
	 * Method name: highlightStateNum
	 */
	private void highlightStateNum(){
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
	}

	/**
	 * Method name: setFirstRow
	 */
	private void setFirstRow() {
		control = new ControlClass();
		try{
			control.setRule1D(ruleSelectChoiceBox.getValue(), 10	, 11);
			rowInProgressArrary = control.getNextLine1D();
		}
		catch(NotValidRuleException e)	{

		}
		fillFirstRow();
	}

	/**
	 * Method name: fillFirstRow
	 */
	private void fillFirstRow() {
		for(int i = 0; i < rowInProgressArrary[0] .length; i++){
			if(rowInProgressArrary[0][i] == 0){
				displayGrid.add(new LabeledRectangle(squareSize, squareSize, zeroColor), i	, 0);
			}
			else{
				displayGrid.add(new LabeledRectangle(squareSize, squareSize, oneColor), i	, 0);
			}
		}
	}

	/**
	 * Method name: fillOnOffSquares
	 */
	private void fillOnOffSquares() {
		setSquareNums(Integer.toBinaryString(ruleNum));
	}

	/**
	 * Method name: setSquareNums
	 * @param binaryString
	 */
	/**
	 * Method name: setSquareNums
	 * @param binaryString
	 */
	private void setSquareNums(String binaryString) {
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
	}

	/**
	 * Method name: setRuleChoices
	 */
	/**
	 * Method name: setRuleChoices
	 */
	public void setRuleChoices(){
		ControlClass controlClass = new ControlClass();
		ObservableList<Integer> rules = FXCollections.observableArrayList();
		int[] rulesInt = controlClass.getValidRules();
		for(int i = 0; i < rulesInt.length; i++){
			rules.add(rulesInt[i]);
		}
		ruleSelectChoiceBox.setItems(rules);
	}

	/**
	 * Method name: setPaneNumbers
	 */
	/**
	 * Method name: setPaneNumbers
	 */
	public void setPaneNumbers(){
		setThreeDigBinaryNubmer(0, numberZero);
		setThreeDigBinaryNubmer(1, numberOne);
		setThreeDigBinaryNubmer(2, numberTwo);
		setThreeDigBinaryNubmer(3, numberThree);
		setThreeDigBinaryNubmer(4, numberFour);
		setThreeDigBinaryNubmer(5, numberFive);
		setThreeDigBinaryNubmer(6, numberSix);
		setThreeDigBinaryNubmer(7, numberSeven);
	}

	/**
	 * Method name: setThreeDigBinaryNubmer
	 * @param num
	 * @param numberHolder
	 */
	/**
	 * Method name: setThreeDigBinaryNubmer
	 * @param num
	 * @param numberHolder
	 */
	public void setThreeDigBinaryNubmer(int num, HBox numberHolder){
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
	}
}
