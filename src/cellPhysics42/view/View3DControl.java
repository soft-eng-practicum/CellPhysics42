package cellPhysics42.view;

import java.util.ArrayList;

import Controller.ControlClass;
import cellPhysics42.MainApp;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
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
import javafx.stage.Stage;

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
	private ChoiceBox<Integer> ruleSelectionCB;
	@FXML
	private ChoiceBox<Integer> startLayerCB;
	@FXML
	private ChoiceBox<Integer> endLayerCB;
	@FXML
	private ChoiceBox<Integer> layerNumCB;
	private ArrayList<String> cubeTranslations;
	private ControlClass control;
	private int numLayers, ruleNum, startLayer, endLayer, factor;
	private Scene mainScene;
	private double mousePosX, mousePosY, mouseOldX, mouseOldY, mouseDeltaX, mouseDeltaY;
	private Rotate rotateX, rotateY, rotateZ;

	public void initialize(){
		control = new ControlClass();
		setLayerNumbers();
		setRuleChoices();
		factor = 7;
		rotateX = new Rotate(0, Rotate.X_AXIS);
		rotateY = new Rotate(0, Rotate.Y_AXIS);
		rotateZ = new Rotate(0, Rotate.Z_AXIS);
		ruleSelectionCB.setValue(451);
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
		showBt.setVisible(false);
		//				Button exitModelButton = new Button("Exit Model");
		ruleNum = ruleSelectionCB.getValue();
		buildCubes();
		setLights();
		Stage currentStage = (Stage) showBt.getScene().getWindow();
		//				exitModelButton.setOnAction(e->{
		//					currentStage.setScene(mainScene);
		//				});
		//				exitModelButton.setLayoutX(0);
		//				exitModelButton.setLayoutY(-100);
		//				cubeGroup.getChildren().add(exitModelButton);
		SubScene subScene= new SubScene(cubeGroup, currentStage.getWidth(), currentStage.getHeight(), true, SceneAntialiasing.DISABLED);
		subScene.setCamera(getCamara());
		handleMouseEvents(subScene);
		handleKeyEvents(subScene);
		mainGridPane.add(subScene, 0, 0);
		//		newStage.setScene(scene);
		//		newStage.setTitle("3D Model");
		//		newStage.show();
		//		currentStage.setScene(scene);
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
			//center object first
			//superimpose xyz axis over view and do rotations based on movement in the direction of the axis
			//use epsilon for bounds and offer option to turn off(radio button)
			//get linear velocity from mouse and convert to omega base on radius at start click
			//use omega for rotation about correct axis
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
				startLayer = startLayerCB.getValue();
				endLayerCB.setVisible(true);
			}
		});
	}

	private void setEndChoices(Integer firstLayer){
		ObservableList<Integer> ends = FXCollections.observableArrayList();
		for(int i = firstLayer; i < numLayers; i++) {
			ends.add(i);
		}
		endLayerCB.setItems(ends);
		endLayerCB.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {

			@Override
			public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
				showBt.setVisible(true);
				endLayer = endLayerCB.getValue();
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
				numLayers = layerNumCB.getValue();
			}
		});
	}

	private void setRuleChoices(){
		ObservableList<Integer> rules = FXCollections.observableArrayList(control.get2DRules());
		ruleSelectionCB.setItems(rules);
	}

	private void buildCubes(){
		cubeTranslations = control.getCubeTranslations(ruleNum, numLayers, startLayer, endLayer, factor);
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
		//cubeGroup.getChildren().add(amLight1);
		amLight1.getScope().addAll(cubeGroup.getChildren());
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
