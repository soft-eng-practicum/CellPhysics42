package cellPhysics42;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainApp extends Application {
	

	@Override
	public void start(Stage primaryStage) {
		
		try{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("view/DemoView.fxml"));
		System.out.println(loader.getLocation());
		AnchorPane pane = (AnchorPane)loader.load();
		Scene scene = new Scene(pane);
		primaryStage.setScene(scene);
		primaryStage.show();
		}
		
		catch(IOException ex){
			System.out.println("error" + ex.getLocalizedMessage());
		}
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
