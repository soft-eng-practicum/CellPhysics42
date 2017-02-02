package cellPhysics42;


import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

	@Override
	public void start(Stage primaryStage) {
		try{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("view/DemoView.fxml"));
		Scene scene = new Scene(loader.load());
		primaryStage.setScene(scene);
		primaryStage.show();
		}
		catch(IOException ex){
			
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
