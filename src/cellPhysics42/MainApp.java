package cellPhysics42;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**Class: MainApp.java 
   * @author Bess Burnett 
   * @version 1.0 
   * Course : ITEC  Spring 2017
   * Written: Mar 31, 2017 
  	   * 
   * This class –  runs the program
   * 
   * Purpose: –  gives a starting point for the cellular automation program
   */
public class MainApp extends Application {

	@Override
	public void start(Stage primaryStage) {
		try{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("view/CustomizeView.fxml"));
		System.out.println(loader.getLocation());
		AnchorPane pane = (AnchorPane)loader.load();
		Scene scene = new Scene(pane);
		primaryStage.setScene(scene);
		primaryStage.show();
		}

		catch(IOException ex){
			System.out.println("error" + ex.getMessage());
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
