package cellPhysics42.view;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXML;

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
	
}
