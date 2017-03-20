package cellPhysics42.view;

import Controller.ControlClass;
import exception.NotValidRuleException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class StepThruViewControl extends AnchorPane {
	@FXML
	private TextArea explanationText;
	@FXML
	private ChoiceBox<Integer> ruleSelectChoiceBox;
	@FXML
	private Label ruleNumLb;
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
	private int stateUsedToFull;
	private Color oneColor;
	private Color zeroColor;
	private Color edgeColor;
	private Color cellToFillHightlight;
	private Color referenceCellHightlight;
	private Color stateUsedHeightlight;
	private String explanation;
	private ControlClass control;
	private double squareSize;
	private byte[][] nextRowArrary;

	@FXML
	public void initialize(){
		nextRow = 0;
		nextCol = 0;
		oneColor = Color.BLACK;
		zeroColor = Color.WHITE;
		stateUsedHeightlight = Color.HOTPINK;
		setEdge();
		ruleSelectChoiceBox.setValue(90);
		setRuleNum(90);
		setRuleChoices();
		setPaneNumbers();
		squareSize = Math.floor(displayGrid.getWidth());
		setGridSize(11, 11);
		setHBoxRims();
	}
	
	public void setHBoxRims(){
		numberZero.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, null, null)));
		numberOne.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, null, null)));
		numberTwo.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, null, null)));
		numberThree.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, null, null)));
		numberFour.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, null, null)));
		numberFive.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, null, null)));
		numberSix.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, null, null)));
		numberSeven.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, null, null)));
	}
	
	public void setGridSize(int rows, int columns){
		displayGrid.getColumnConstraints().removeAll(displayGrid.getColumnConstraints());
		displayGrid.getRowConstraints().removeAll(displayGrid.getRowConstraints());
		for(int i = 0; i < columns; i++){
			displayGrid.getColumnConstraints().add(new ColumnConstraints(squareSize));
		}
		for(int i = 0; i < rows; i++){
			displayGrid.getRowConstraints().add(new RowConstraints(squareSize));
		}
		displayGrid.setAlignment(Pos.CENTER);
	}
	
	public void setEdge(){
		edgeColor = oneColor.invert();
	}

	private void setRuleNum(int i) {
		ruleNum = 90;
		ruleNumLb.setText("Rule " + i);
	}

	@FXML
	public void startStepThru(){
		setRuleNum(ruleSelectChoiceBox.getValue());
		explanation = "The on/off (bottom squares) are set be the binary of the rule number, " + ruleNum + ", which is " + Integer.toBinaryString(ruleNum) + 
				".  Which makes the on/off squares... \n (click next to see on/off squares fill)";
		setExplanationText();
	}

	private void setExplanationText() {
		//explanationText.setWrapText(true);
		explanationText.setText(explanation);
	}

	@FXML
	public void nextST(){
		if(nextRow == 0){
			fillOnOffSquares();
			nextRow++;
			setFirstRow();
			explanation += "\nThe first row must be given at least one starting on state because only even numbers are used, so without a starting point "
					+ "nothing will ever turn on.";
			setExplanationText();
		}
		else{
			stepThruRule();
		}
	}

	public void stepThruRule(){
		while(nextRowArrary[0][nextCol] == 0 && nextRowArrary[0][nextCol + 1] == 0){
			nextCol++;
		}
		stateUsedToFull = nextRowArrary[1][nextCol];
		highlightStateNum();
	}
	
	private void highlightStateNum(){
		switch(stateUsedToFull){
		case 0: squareZero.setOutline(stateUsedHeightlight);
		break;
		}
	}
	
	private void setFirstRow() {
		control = new ControlClass();
		try{
			control.setRule1D(ruleSelectChoiceBox.getValue(), 10	, 11);
			nextRowArrary = control.getNextLine1D();
		}
		catch(NotValidRuleException e)	{

		}
		fillFirstRow();
	}

	private void fillFirstRow() {
		for(int i = 0; i < 11; i++){
			if(i != 5){
			displayGrid.add(new Rectangle(squareSize, squareSize, zeroColor), i	, 0);
			}
			else{
				displayGrid.add(new Rectangle(squareSize, squareSize, oneColor), i	, 0);
			}
		}
	}

	private void fillOnOffSquares() {
		setSquareNums(Integer.toBinaryString(ruleNum));
	}
	
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
