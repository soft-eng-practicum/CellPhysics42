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
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
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
	
	@FXML
	private Button helpBt;

	private int gridWidthSquares;
	private Color oneColor;
	private Color zeroColor;
	private ArrayList<Integer> startSquares;

	/**
	 * Method name: initialize
	 * 
	 * called when the fxml document is loaded
	 */
	@FXML
	public void initialize(){
		ImageView image = new ImageView("questionMark.jpg");
		image.setFitWidth(20);
		image.setFitHeight(20);
		helpBt.setGraphic(image);
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
	 * Method name: startCustom
	 * 
	 * reads in the values selected, then loads the customize view and starts the rule
	 */
	@FXML
	public boolean startCustom(){
		int numSquaresWidth = gridWidthCB.getValue();
		int numSquaresHeight = Integer.parseInt(gridHeightTF.getText());
		double rowDuration = (speedSlidBar.getValue() < 1) ? 1 : speedSlidBar.getValue();
		oneColor = onesColorPicker.getValue();
		zeroColor = zeroColorPicker.getValue();
		int ruleNumber = ruleSelectChoiceBox.getValue();	
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
					cvc.runCustom(numSquaresWidth, numSquaresHeight, rowDuration, oneColor, zeroColor, ruleNumber, startSquares);
				}
			};
			newStage.sceneProperty().addListener(listener);
			newStage.setScene(scene);
		}
		catch(IOException ex){
			System.out.println("error" + ex.getMessage());
		}
		return true;
	}
	
	/**
	 * Method name: loadHelp
	 * @return
	 * 
	 * loads the user help screen in the default browser
	 */
	@FXML
	public boolean loadHelp(){
		ControlClass.loadHelpPage();
		return true;
	}


	/**
	 * Method name: setRuleChoices
	 * 
	 * fills the rule choice selection with all the valid 1D rules
	 */
	private boolean setRuleChoices(){
		ControlClass controlClass = new ControlClass();
		ObservableList<Integer> rules = FXCollections.observableArrayList();
		int[] rulesInt = controlClass.getValidRules1D();
		for(int i = 0; i < rulesInt.length; i++){
			rules.add(rulesInt[i]);
		}
		ruleSelectChoiceBox.setItems(rules);
		return true;
	}

	/**
	 * Method name: setWidthValues
	 * 
	 * sets the possible grid widths
	 */
	private boolean setWidthValues(){
		ObservableList<Integer> widths = FXCollections.observableArrayList();
		for(int i = 35; i < 99; i += 2){
			widths.add(i);
		}
		gridWidthCB.setItems(widths);
		return true;
	}

	/**
	 * Method name: clearFirstRow
	 * 
	 * clears any inputs the user has placed in the first row
	 */
	private boolean clearFirstRow(){
		ObservableList<Node> rectangles = firstRowInput.getChildren();
		for(Node rectangle : rectangles){
			((Rectangle)rectangle).setFill(zeroColor);
			if(startSquares.contains(Integer.parseInt(rectangle.getId()))){
				startSquares.remove((Integer)Integer.parseInt(rectangle.getId()));
			}
		}
		return true;
	}

	/**
	 * Method name: showFirstRow
	 * 
	 * shows a row so the user can select start states
	 */
	@FXML
	private boolean showFirstRow(){
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
					startSquares.remove((Integer)Integer.parseInt(((Rectangle)e.getSource()).getId()));
				}
				else{
					rectangle.setFill(oneColor);
					startSquares.add(Integer.parseInt(((Rectangle)e.getSource()).getId()));
				}
			});
			firstRowInput.add(rectangle, i, 0);
		}
		return true;
	}
}
