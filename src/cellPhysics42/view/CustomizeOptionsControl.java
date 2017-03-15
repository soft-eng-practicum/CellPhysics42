package cellPhysics42.view;

import java.util.ArrayList;

import Controller.ControlClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CustomizeOptionsControl {
	@FXML
	private BorderPane mainPane;
	@FXML
	private ChoiceBox<Integer> ruleSelectChoiceBox;
	@FXML
	private ChoiceBox<Integer> gridWidthCB;
	@FXML
	private TextField gridHeightTF;
	@FXML
	private ColorPicker onesColorPicker;
	@FXML
	private ColorPicker zeroColorPicker;
	@FXML
	private Button startButton;
	@FXML
	private GridPane firstRowInput;
	@FXML
	private Button setStartState;

	private int gridWidthSquares;
	private int gridHeightSquares;
	private Color oneColor;
	private Color zeroColor;
	private ArrayList<Integer> startSquares;

	/**
	 * Method name: initialize
	 */
	@FXML
	public void initialize(){
		gridWidthCB.setValue(31);
		setWidthValues();
		setRuleChoices();
		oneColor = Color.BLACK;
		zeroColor = Color.WHITE;
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
	 * Method name: setWidthValues
	 */
	public void setWidthValues(){
		ObservableList<Integer> widths = FXCollections.observableArrayList();
		for(int i = 25; i < 99; i += 2){
			widths.add(i);
		}
		gridWidthCB.setItems(widths);
	}

	/**
	 * Method name: startCustom
	 */
	@FXML
	public void startCustom(){
		
	}

	/**
	 * Method name: clearFirstRow
	 */
	public void clearFirstRow(){
		ObservableList<Node> rectangles = firstRowInput.getChildren();
		for(Node rectangle : rectangles){
			((Rectangle)rectangle).setFill(zeroColor);
			if(startSquares.contains(Integer.parseInt(rectangle.getId()))){
				startSquares.remove(Integer.parseInt(rectangle.getId()));
			}
		}
	}

	/**
	 * Method name: showFirstRow
	 */
	@FXML
	public void showFirstRow(){
		setStartState.setText("Reset Start State");
		startSquares = new ArrayList<>();
		gridWidthSquares = gridWidthCB.getValue();
		double gridWidth = mainPane.getWidth();
		double gridHeight = gridWidth/gridWidthSquares;
		//		oneColor = onesColorPicker.getValue();
		//		zeroColor = zeroColorPicker.getValue();
		clearFirstRow();
		for(int i = 0; i < gridWidthSquares; i++){
			Rectangle rectangle = new Rectangle(gridWidth/gridWidthSquares, gridHeight);
			rectangle.setFill(zeroColor);
			rectangle.setId(i + "");
			rectangle.setOnMouseClicked(e->{
				if(startSquares.contains(Integer.parseInt(((Rectangle)e.getSource()).getId()))){
					rectangle.setFill(zeroColor);
					startSquares.remove(Integer.parseInt(((Rectangle)e.getSource()).getId()));
				}
				else{
					rectangle.setFill(oneColor);
					startSquares.add(Integer.parseInt(((Rectangle)e.getSource()).getId()));
				}
			});
			firstRowInput.add(rectangle, i, 0);
		}
	}
}
