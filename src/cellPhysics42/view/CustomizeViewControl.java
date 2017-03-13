package cellPhysics42.view;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import cellPhysics42.MainApp;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

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
	private Button stopBt;
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
	private Color oneColor;
	private Color zeroColor;
	private Color edgeColor;
	

	/**
	 * Method name: initialize
	 */
	@FXML
	public void initialize(){
		setRuleNum(90);
		edgeColor = Color.BLACK;
		squaresGrid.setVisible(true);
	}
	
	/**
	 * Method name: setRuleNum
	 * @param ruleNum
	 */
	private void setRuleNum(int ruleNum) {
		ruleNameLabel.setText("Rule " + ruleNum);
		setSquareNums(Integer.toBinaryString(ruleNum));
		setPaneNumbers();
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
		squareOne.setLabel(binaryString.charAt(6));
		squareOne.setOutline(edgeColor);
		squareTwo.setLabel(binaryString.charAt(5));
		squareTwo.setOutline(edgeColor);
		squareThree.setLabel(binaryString.charAt(4));
		squareThree.setOutline(edgeColor);
		squareFour.setLabel(binaryString.charAt(3));
		squareFour.setOutline(edgeColor);
		squareFive.setLabel(binaryString.charAt(2));
		squareFive.setOutline(edgeColor);
		squareSix.setLabel(binaryString.charAt(1));
		squareSix.setOutline(edgeColor);
		squareSeven.setLabel(binaryString.charAt(0));
		squareSeven.setOutline(edgeColor);
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
			((LabeledRectangle)rects.get(index)).setOutline(edgeColor);
		}
	}

	/**
	 * Method name: runDemo
	 */
	@FXML
	public void runDemo(){
		try{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("view/DemoView.fxml"));
		System.out.println(loader.getLocation());
		AnchorPane pane = (AnchorPane)loader.load();
		Scene scene = new Scene(pane);
		Stage newStage = (Stage) demoButton.getScene().getWindow();
		
		ChangeListener<Scene> listener = new ChangeListener<Scene>() {
			@Override
			public void changed(ObservableValue<? extends Scene> observable, Scene oldValue, Scene newValue) {
				DemoViewControl dvc = loader.getController();
				dvc.runDemo();
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
	 * Method name: showCustomOptions
	 */
	@FXML
	public void showCustomOptions(){
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/CustomizeOptions.fxml"));
			AnchorPane pane = (AnchorPane)loader.load();
			pane.setBackground(new Background(new BackgroundFill(Color.CADETBLUE, null, null)));
			mainPane.setCenter(pane);
			
			System.out.println(loader.getLocation());
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
}
