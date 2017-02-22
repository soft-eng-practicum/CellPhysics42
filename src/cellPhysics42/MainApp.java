package cellPhysics42;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {
	private Stage primaryStage;

	@Override
	public void start(Stage primaryStage) {
		//this.primaryStage = new Stage();
		try{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("view/CustomizeView.fxml"));
		System.out.println(loader.getLocation());
		AnchorPane pane = (AnchorPane)loader.load();
		Scene scene = new Scene(pane);
		primaryStage.setScene(scene);
		primaryStage.show();
		this.primaryStage = primaryStage;
		}
		
		catch(IOException ex){
			System.out.println("error" + ex.getMessage());
		}
		
	}
	
//	public void changeScene(Scene newScence){
//		primaryStage.setScene(newScence);
//	}
	
	public Stage getPrimaryStage(){
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
