package cellPhysics42.view;

import java.io.IOException;
import java.util.ArrayList;

import Controller.ControlClass;
import cellPhysics42.MainApp;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

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
	@FXML
	private Slider speedSlidBar;

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
		gridWidthCB.setValue(43);
		setWidthValues();
		setRuleChoices();
		oneColor = Color.BLACK;
		zeroColor = Color.WHITE;
		onesColorPicker.setValue(Color.BLACK);
		zeroColorPicker.setValue(Color.WHITE);
		startSquares = new ArrayList<>();
		gridHeightTF.setText("55");
		ruleSelectChoiceBox.setValue(90);
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
		for(int i = 35; i < 99; i += 2){
			widths.add(i);
		}
		gridWidthCB.setItems(widths);
	}

	/**
	 * Method name: startCustom
	 */
	@FXML
	public void startCustom(){
		int numSquaresWidth = gridWidthCB.getValue();
		int numSquaresHeight = Integer.parseInt(gridHeightTF.getText());
		double rowDuration = (speedSlidBar.getValue() < 1) ? 1 : speedSlidBar.getValue();
		oneColor = onesColorPicker.getValue();
		zeroColor = zeroColorPicker.getValue();
		int ruleNumber = ruleSelectChoiceBox.getValue();
		//Scene currentScene = startButton.getScene();
		
//		BorderPane cvPane = (BorderPane)mainPane.getParent();
//		cvPane.setP
		
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/CustomizeView.fxml"));
			AnchorPane pane = (AnchorPane) loader.load();
			Scene scene = new Scene(pane);
			Stage newStage = (Stage) startButton.getScene().getWindow();

			ChangeListener<Scene> listener = new ChangeListener<Scene>() {
				@Override
				public void changed(ObservableValue<? extends Scene> observable, Scene oldValue, Scene newValue) {
					CustomizeViewControl cvc = loader.getController();
					//set custom options before running?
					cvc.runCustom(numSquaresWidth, numSquaresHeight, rowDuration, oneColor, zeroColor, ruleNumber, startSquares);
				}
			};

			newStage.sceneProperty().addListener(listener);
			newStage.setScene(scene);
		}

		catch(IOException ex){
			System.out.println("error" + ex.getMessage());
		}
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
		gridWidthSquares = gridWidthCB.getValue();
		double gridWidth = mainPane.getWidth();
		double gridHeight = gridWidth/gridWidthSquares;
		oneColor = onesColorPicker.getValue();
		zeroColor = zeroColorPicker.getValue();
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
