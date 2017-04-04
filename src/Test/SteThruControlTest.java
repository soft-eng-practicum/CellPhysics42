package Test;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import cellPhysics42.MainApp;
import cellPhysics42.view.StepThruViewControl;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SteThruControlTest {
	
	public void start(Stage primaryStage) {
		try{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("view/SteThruView.fxml"));
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
	
	@Test
	public void testStartStepThru() throws IOException {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("view/StepThruView.fxml"));
		loader.load();
		StepThruViewControl test = loader.getController();
		
		
		Assert.assertTrue(test.startStepThru());
		
	}

}
