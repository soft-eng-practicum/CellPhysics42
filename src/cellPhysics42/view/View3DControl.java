package cellPhysics42.view;

import java.util.ArrayList;
import Controller.ControlClass;
import cellPhysics42.MainApp;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class View3DControl extends AnchorPane{
	@FXML
	private Group cubeGroup;
	@FXML
	private Button exitBt;
	@FXML
	private Button showBt;
	@FXML
	private ChoiceBox<Integer> ruleSelectionCB;
	@FXML
	private ChoiceBox<Integer> startLayerCB;
	@FXML
	private ChoiceBox<Integer> endLayerCB;
	@FXML
	private ChoiceBox<Integer> layerNumCB;
	private ArrayList<String> cubeTranslations;
	private ControlClass control;
	private int maxLayers;
	
	public void initialize(){
		control = new ControlClass();
		setLayerNumbers();
		setRuleChoices();
		startLayerCB.setVisible(false);
		endLayerCB.setVisible(false);
		showBt.setVisible(false);
		cubeGroup = new Group();
		//cubeTranslations = new ArrayList<String>();
	}
	
	@FXML
	public void exit3D(){
		try{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("view/CustomizeView.fxml"));
		System.out.println(loader.getLocation());
		AnchorPane pane = (AnchorPane)loader.load();
		Scene scene = new Scene(pane);
		Stage newStage = (Stage) exitBt.getScene().getWindow();
		newStage.setScene(scene);
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	@FXML
	public void show3DObject(){
		buildCubes();
		setLights();
		showBt.getScene().setCamera(getCamara());
	}
	
	private void setStartChoices(Integer lastLayer){
		ObservableList<Integer> starts = FXCollections.observableArrayList();
		for(int i = 1; i < lastLayer; i++){
			starts.add(i);
		}
		startLayerCB.setItems(starts);
		startLayerCB.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {

			@Override
			public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
				setEndChoices(startLayerCB.getValue());
				endLayerCB.setVisible(true);
			}
		});
	}
	
	private void setEndChoices(Integer firstLayer){
		ObservableList<Integer> ends = FXCollections.observableArrayList();
		for(int i = firstLayer; i < maxLayers; i++) {
			ends.add(i);
		}
		endLayerCB.setItems(ends);
		endLayerCB.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {

			@Override
			public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
				showBt.setVisible(true);
			}
		});
	}
	
	private void setLayerNumbers(){
		ObservableList<Integer> layers = FXCollections.observableArrayList();
		for(int i = 2; i < 45; i++){
			layers.add(i);
		}
		layerNumCB.setItems(layers);
		layerNumCB.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {

			@Override
			public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
				setStartChoices(layerNumCB.getValue());
				startLayerCB.setVisible(true);
				maxLayers = layerNumCB.getValue();
			}
		});
	}
	
	private void setRuleChoices(){
		ObservableList<Integer> rules = FXCollections.observableArrayList(control.get2DRules());
		ruleSelectionCB.setItems(rules);
	}
	
	private void buildCubes(){
		cubeTranslations = control.getCubeTranslations(453);
		for(String translation : cubeTranslations){
			Cube3D cube = new Cube3D();
			cube.translateCube(translation);
			cubeGroup.getChildren().add(cube);
		}
	}
	
	private void setLights(){
		AmbientLight amLight = new AmbientLight(Color.WHITE);
		amLight.getScope().addAll(cubeGroup.getChildren());
	}
	
	private PerspectiveCamera getCamara(){
		PerspectiveCamera camera = new PerspectiveCamera(true);
		camera.setNearClip(0.1);
		camera.setFarClip(1000.0);
		camera.setTranslateZ(-500);
		return camera;
	}
	
	public Scene get3DScene(double width, double height){
		initialize();
		buildCubes();
		setLights();
		Scene scene = new Scene(cubeGroup, width, height);
		scene.setCamera(getCamara());
		return scene;
	}
	
	//create a Group to hold the cubes
	
	//create cubes and set position base on file
	//create cube
	//set material
	
	//create light (ambient) and add cubes to its scope
	//add lights to the group
	
	//add cubes to group
	
	//create a scene with the group
	
	//create a camara and set it to the Scene
}
