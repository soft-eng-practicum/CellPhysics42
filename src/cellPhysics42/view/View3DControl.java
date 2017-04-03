package cellPhysics42.view;

import java.io.File;
import java.util.ArrayList;

import Controller.ControlClass;
import cellPhysics42.MainApp;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**Class: View3DControl.java 
   * @author Bess Burnett 
   * @version 1.0 
   * Course : ITEC  Spring 2017
   * Written: Mar 31, 2017 
  	   * 
   * This class –  controls the View3D
   * 
   * Purpose: –  allows the user to build a 3D model for a given rule and then manipulate it on the screen
   */
public class View3DControl extends AnchorPane{
	@FXML
	private Group cubeGroup;
	@FXML
	private GridPane mainGridPane;
	@FXML
	private Button exitBt;
	@FXML
	private Button showBt;
	@FXML
	private Button saveBt;
	@FXML
	private ChoiceBox<Integer> ruleSelectionCB;
	@FXML
	private ChoiceBox<Integer> startLayerCB;
	@FXML
	private ChoiceBox<Integer> endLayerCB;
	private ArrayList<String> cubeTranslations;
	private ControlClass control;
	private int maxLayer
	, ruleNum, startLayer, endLayer, factor;
	private Scene mainScene;
	private double mousePosX, mousePosY, mouseOldX, mouseOldY, mouseDeltaX, mouseDeltaY;
	private Rotate rotateX, rotateY, rotateZ;

	
	//dont need select num layers, just set end layer to have max
	
	
	public void initialize(){
		control = new ControlClass();
		maxLayer = 30;
		factor = 10;
		rotateX = new Rotate(0, Rotate.X_AXIS);
		rotateY = new Rotate(0, Rotate.Y_AXIS);
		rotateZ = new Rotate(0, Rotate.Z_AXIS);
		setObservableList();
		ruleSelectionCB.setValue(451);
		startLayerCB.setVisible(false);
		endLayerCB.setVisible(false);
		showBt.setVisible(false);
		saveBt.setVisible(false);
		cubeGroup = new Group();
		setRuleChoices();
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
		showBt.setVisible(false);
		clearGroup();
		ruleNum = ruleSelectionCB.getValue();
		buildCubes();
		setLights();
		Stage currentStage = (Stage) showBt.getScene().getWindow();
		SubScene subScene= new SubScene(cubeGroup, currentStage.getWidth(), currentStage.getHeight(), true, SceneAntialiasing.DISABLED);
		subScene.setCamera(getCamara());
		handleMouseEvents(subScene);
		handleKeyEvents(subScene);
		mainGridPane.add(subScene, 0, 0);
	}
	
	@FXML
	public void saveModel(){
		FileChooser saveFile = new FileChooser();
		Stage currentStage = (Stage)saveBt.getScene().getWindow();
		File file = saveFile.showSaveDialog(currentStage);
		String path = file.getAbsolutePath();
		control.save2D(path, startLayer, endLayer);
	}
	
	private void setObservableList(){
		ruleSelectionCB.setItems(FXCollections.observableArrayList());
		startLayerCB.setItems(FXCollections.observableArrayList());
		endLayerCB.setItems(FXCollections.observableArrayList());
	}
	
	private void clearGroup(){
		cubeGroup.getChildren().removeAll(cubeGroup.getChildren());
	}
	
	private void handleKeyEvents(SubScene subScene){
		subScene.setOnKeyPressed(ke->{
			if(ke.getCode() == KeyCode.A){
				rotateZ.setAngle(2.0);
				cubeGroup.getTransforms().add(rotateZ);
			}
			else if(ke.getText().equalsIgnoreCase("z")){
				rotateZ.setAngle(-2.0);
				cubeGroup.getTransforms().add(rotateZ);
			}
			else if(ke.getText().equalsIgnoreCase("s")){
				rotateX.setAngle(2.0);
				cubeGroup.getTransforms().add(rotateZ);
			}
			else if(ke.getText().equalsIgnoreCase("x")){
				rotateX.setAngle(-2.0);
				cubeGroup.getTransforms().add(rotateZ);
			}
			else if(ke.getText().equalsIgnoreCase("y")){
				rotateY.setAngle(2.0);
				cubeGroup.getTransforms().add(rotateZ);
			}
			else{
				rotateY.setAngle(-2.0);
				cubeGroup.getTransforms().add(rotateZ);
			}
		});
	}
	
	// dy/ num and dx/num need to be changed to be the height, width of the 3d object
	private void handleMouseEvents(SubScene scene){
		scene.setOnMousePressed((MouseEvent me) -> {
			mousePosX = me.getSceneX();
			mousePosY = me.getSceneY();
		});

		scene.setOnMouseDragged((MouseEvent me) -> {
			mouseDeltaX = (mousePosX - me.getSceneX()) ;
			mouseDeltaY = (mousePosY - me.getSceneY());
			if (me.isPrimaryButtonDown()) {
				rotateX.setAngle(rotateX.getAngle() - 
						(mouseDeltaY / (me.getSceneY() + 1000) * 360) * (Math.PI / 180));
				rotateY.setAngle(rotateY.getAngle() - 
						(mouseDeltaX / (me.getSceneX() + 1000) * -360) * (Math.PI / 180));
			}
			mousePosX = me.getSceneX();
			mousePosY = me.getSceneY();
			cubeGroup.getTransforms().addAll(rotateX, rotateY);
		});
		
		scene.setOnRotate(e->{
			
		});
	}

	private void setStartChoices(){
		ObservableList<Integer> starts = startLayerCB.getItems();
		starts.removeAll(starts);
		for(int i = 1; i < maxLayer; i++){
			starts.add(i);
		}
		startLayerCB.setItems(starts);
		startLayerCB.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {

			@Override
			public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
				setEndChoices(startLayerCB.getValue());
				startLayer = startLayerCB.getValue();
				endLayerCB.setVisible(true);
			}
		});
	}

	private void setEndChoices(Integer firstLayer){
		
		ObservableList<Integer> ends = endLayerCB.getItems();
		ends.removeAll(ends);
		for(int i = firstLayer; i <= maxLayer; i++) {
			ends.add(i);
		}
		endLayerCB.setItems(ends);
		endLayerCB.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {

			@Override
			public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
				showBt.setVisible(true);
				saveBt.setVisible(true);
				endLayer = endLayerCB.getValue();
			}
		});
	}

	private void setRuleChoices(){
		ObservableList<Integer> rules = ruleSelectionCB.getItems();
		rules.addAll(control.get2DRules());
		ruleSelectionCB.setItems(rules);
		startLayerCB.setVisible(true);
		setStartChoices();
	}

	private void buildCubes(){
		cubeTranslations = control.getCubeTranslations(ruleNum, maxLayer, startLayer, endLayer, factor);
		for(String translation : cubeTranslations){
			Cube3D cube = new Cube3D();
			cube.translateCube(translation);
			cubeGroup.getChildren().add(cube);
		}
	}

	private void setLights(){
		AmbientLight amLight1 = new AmbientLight(Color.WHITE);
		amLight1.setTranslateX(200);
		amLight1.setTranslateY(200);
		amLight1.setTranslateZ(200);
		amLight1.getScope().addAll(cubeGroup.getChildren());
	}

	private PerspectiveCamera getCamara(){
		PerspectiveCamera camera = new PerspectiveCamera(true);
		camera.setNearClip(0.1);
		camera.setFarClip(1000.0);
		camera.setTranslateZ(-500);
		return camera;
	}
}
