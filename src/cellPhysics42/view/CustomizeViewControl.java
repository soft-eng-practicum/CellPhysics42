package cellPhysics42.view;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

import cellPhysics42.MainApp;
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
	BorderPane mainPane;
	@FXML
	Label ruleNameLabel;
	@FXML
	GridPane binaryRuleGrid;
	@FXML
	private Button demoButton;
	
	@FXML
	public void runDemo(){
		try{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("view/DemoView.fxml"));
		System.out.println(loader.getLocation());
		AnchorPane pane = (AnchorPane)loader.load();
		Scene scene = new Scene(pane);
		Stage newStage = (Stage) demoButton.getScene().getWindow();
		newStage.setScene(scene);
		}
		
		catch(IOException ex){
			System.out.println("error" + ex.getMessage());
		}
	}
	
}
