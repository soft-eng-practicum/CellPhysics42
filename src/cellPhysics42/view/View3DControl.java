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
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
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
	private Button helpBt;
	@FXML
	private ChoiceBox<Integer> ruleSelectionCB;
	@FXML
	private ChoiceBox<Integer> startLayerCB;
	@FXML
	private ChoiceBox<Integer> endLayerCB;
	private ArrayList<String> cubeTranslations;
	private ControlClass control;
	private int maxLayer, ruleNum, startLayer, endLayer, factor;
	private double mousePosX, mousePosY, mouseDeltaX, mouseDeltaY;
	private Rotate rotateX, rotateY, rotateZ;
	private final Translate t = new Translate(0.0, 0.0, 0.0);
	private SubScene subScene;
	private PerspectiveCamera camera;

	/**
	 * Method name: initialize
	 *
	 * method called by the FXML Loader when the file is loaded
	 */
	public void initialize(){
		ImageView image = new ImageView("questionMark.jpg");
		image.setFitWidth(20);
		image.setFitHeight(20);
		helpBt.setGraphic(image);
		control = new ControlClass();
		maxLayer = 30;
		factor = 10;
		rotateX = new Rotate(0,0,0,0, Rotate.X_AXIS);
		rotateY = new Rotate(0,0,0,0, Rotate.Y_AXIS);
		rotateZ = new Rotate(0,0,0,0, Rotate.Z_AXIS);
		setObservableList();
		ruleSelectionCB.setValue(451);
		startLayerCB.setVisible(false);
		endLayerCB.setVisible(false);
		showBt.setVisible(false);
		saveBt.setVisible(false);
		cubeGroup = new Group();
		cubeGroup.getTransforms().addAll(t, rotateX, rotateY, rotateZ);
		subScene = new SubScene(cubeGroup, 100, 100, true, SceneAntialiasing.DISABLED);
		setRuleChoices();
	}

	/**
	 * Method name: exit3D
	 *
	 * exits the 3D view and returns to the customize view
	 */
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

	/**
	 * Method name: show3DObject
	 *
	 * creates and displays the 3D model created by the selected rule
	 */
	@FXML
	public void show3DObject(){
		showBt.setVisible(false);
		clearGroup();
		mainGridPane.getChildren().remove(subScene);
		ruleNum = ruleSelectionCB.getValue();
		buildCubes();
		setLights();
		Stage currentStage = (Stage) showBt.getScene().getWindow();
		subScene.setHeight(currentStage.getHeight());
		subScene.setWidth(currentStage.getWidth());
		subScene.setCamera(getCamara());
		handleMouseEvents(subScene);
		handleKeyEvents(subScene, currentStage);
		mainGridPane.add(subScene, 0, 0);
	}

	/**
	 * Method name: saveModel
	 *
	 * saves the model as a .scad file in the selected location
	 */
	@FXML
	public void saveModel(){
		FileChooser saveFile = new FileChooser();
		Stage currentStage = (Stage)saveBt.getScene().getWindow();
		File file = saveFile.showSaveDialog(currentStage);
		String path = file.getAbsolutePath();
		control.save2D(path, startLayer, endLayer);
	}

	/**
	 * Method name: loadHelp
	 * @return
	 *
	 * loads the user help screen in the default browser
	 */
	@FXML
	public boolean loadHelp(){
		ControlClass.loadHelpPage();
		return true;
	}

	/**
	 * Method name: setObservableList
	 *
	 * initializes all the choice box lists
	 */
	private void setObservableList(){
		ruleSelectionCB.setItems(FXCollections.observableArrayList());
		startLayerCB.setItems(FXCollections.observableArrayList());
		endLayerCB.setItems(FXCollections.observableArrayList());
	}

	/**
	 * Method name: clearGroup
	 *
	 * removes all the cubes in the group to create a new model
	 */
	private void clearGroup(){
		cubeGroup.getChildren().removeAll(cubeGroup.getChildren());
	}

	/**
	 * Method name: handleKeyEvents
	 * @param subScene
	 *
	 * adds key press actions to the given subscene
	 */
	private void handleKeyEvents(SubScene subScene, Stage stage){
		stage.getScene().setOnKeyPressed((KeyEvent ke) -> {
			double zPos = subScene.getCamera().getTranslateZ();
//			if(ke.getCode() == KeyCode.A){
//				rotateZ.setAngle(2.0);
//				cubeGroup.getTransforms().add(rotateZ);
//			}
//			else if(ke.getText().equalsIgnoreCase("z")){
//				rotateZ.setAngle(rotateZ.getAngle()-0.10);
//				cubeGroup.getTransforms().add(rotateZ);
//			}
//			else if(ke.getText().equalsIgnoreCase("s")){
//				rotateX.setAngle(2.0);
//				cubeGroup.getTransforms().add(rotateZ);
//			}
//			else if(ke.getText().equalsIgnoreCase("x")){
//				rotateX.setAngle(rotateX.getAngle()-0.10);
//				cubeGroup.getTransforms().add(rotateX);
//			}
//			else if(ke.getText().equalsIgnoreCase("y")){
//				rotateY.setAngle(rotateY.getAngle() + 0.1);
//				cubeGroup.getTransforms().add(rotateY);
//			}
//			else if(ke.getText().equalsIgnoreCase("h")){
//				rotateY.setAngle(-2.0);
//				cubeGroup.getTransforms().add(rotateZ);
//			}
			if(ke.getText().equalsIgnoreCase("g")){
				subScene.getCamera().setTranslateZ(zPos+ 10);
			}
			else if(ke.getText().equalsIgnoreCase("f")){
				subScene.getCamera().setTranslateZ(zPos - 10);
			}
		});
	}

	/**
	 * Method name: handleMouseEvents
	 * @param scene
	 *
	 * adds the mouse events to the given scene, rotates the scene on drag
	 */
	private void handleMouseEvents(SubScene scene){
		scene.addEventFilter(MouseEvent.DRAG_DETECTED, (MouseEvent me) -> {
			mousePosX = me.getSceneX();
			mousePosY = me.getSceneY();
		});

		scene.setOnMouseDragged((MouseEvent me) -> {
//			mouseDeltaX = (mousePosX - me.getSceneX()) ;
//			mouseDeltaY = (mousePosY - me.getSceneY());
//			if (me.isPrimaryButtonDown()) {
//				rotateX.setAngle(rotateX.getAngle() -
//						(mouseDeltaY/ (me.getSceneY() + 1000) * 360) * (Math.PI / 180));
//				rotateY.setAngle(rotateY.getAngle() -
//						(mouseDeltaX/ (me.getSceneX() + 1000) * -360) * (Math.PI / 180));
//			}
//			mousePosX = me.getSceneX();
//			mousePosY = me.getSceneY();

            mouseDeltaX = mousePosX - me.getSceneX();
            mouseDeltaY = mousePosY - me.getSceneY();

            if (me.isPrimaryButtonDown()) {
            	rotateX.setAngle(rotateX.getAngle() - mouseDeltaY/200.0);
            	rotateY.setAngle(rotateY.getAngle() + mouseDeltaX/200.0);
            }
			//camera.getTransforms().addAll(rotateY, rotateX);

//			System.out.println(rotateX.getPivotX() + " " + rotateX.getPivotY());
//			System.out.println(rotateY.getPivotX() + " " + rotateY.getPivotY());
//			System.out.println(mouseDeltaX/500.0 + " " + mouseDeltaY/500.0);
//			System.out.println(rotateX.getAngle() + " " + rotateY.getAngle());
		});

		scene.setOnRotate(e->{

		});
	}

	/**
	 * Method name: setStartChoices
	 *
	 * fills the startLayer choice box and displays it
	 */
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
				try{
				startLayer = startLayerCB.getValue();
				}
				catch(NullPointerException ex){

				}
				endLayerCB.setVisible(true);
			}
		});
	}

	/**
	 * Method name: setEndChoices
	 * @param firstLayer
	 *
	 * fills the endLayer choice box starting with the given number
	 */
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
				try{
				endLayer = endLayerCB.getValue();
				}
				catch(NullPointerException ex){

				}
			}
		});
	}

	/**
	 * Method name: setRuleChoices
	 *
	 * sets the ruleSelection choice box with all the valid 2D rules
	 */
	private void setRuleChoices(){
		ObservableList<Integer> rules = ruleSelectionCB.getItems();
		rules.addAll(control.get2DRules());
		ruleSelectionCB.setItems(rules);
		startLayerCB.setVisible(true);
		setStartChoices();
	}

	/**
	 * Method name: buildCubes
	 *
	 * builds and places cubes in the group at the location specified by the translation strings
	 */
	private void buildCubes(){
		cubeTranslations = control.getCubeTranslations(ruleNum, endLayer, startLayer, endLayer, factor);
		for(String translation : cubeTranslations){
			Cube3D cube = new Cube3D(factor);
			cube.translateCube(translation);
			PhongMaterial material = new PhongMaterial();
			material.setDiffuseColor(Color.SKYBLUE);
			material.setSpecularColor(Color.BLUE);
			material.setSpecularPower(60);
			cube.setMaterial(material);
			cubeGroup.getChildren().add(cube);
		}
	}

	/**
	 * Method name: setLights
	 *
	 * creates and adds lights to the cube group for visibility
	 */
	private void setLights(){
//		AmbientLight amLight1 = new AmbientLight(Color.WHITE);
//		amLight1.setTranslateX(200);
//		amLight1.setTranslateY(200);
//		amLight1.setTranslateZ(200);
//		amLight1.getScope().addAll(cubeGroup.getChildren());
//		AmbientLight amLight2 = new AmbientLight(Color.WHITE);
//		amLight2.setTranslateX(-200);
//		amLight2.setTranslateY(-200);
//		amLight2.setTranslateZ(200);
//		amLight2.getScope().addAll(cubeGroup.getChildren());
		PointLight pLight1 = new PointLight(Color.WHITE);
		pLight1.setTranslateX(300);
		pLight1.setTranslateY(200);
		pLight1.setTranslateZ(-500);

		PointLight pLight2 = new PointLight(Color.WHITE);
		pLight2.setTranslateX(-300);
		pLight2.setTranslateY(-200);
		pLight2.setTranslateZ(500);
		
		PointLight pLight3 = new PointLight(Color.WHITE);
		pLight2.setTranslateX(-400);
		pLight2.setTranslateY(-200);
		pLight2.setTranslateZ(100);
		
		PointLight pLight4 = new PointLight(Color.WHITE);
		pLight2.setTranslateX(400);
		pLight2.setTranslateY(-200);
		pLight2.setTranslateZ(100);

		cubeGroup.getChildren().addAll(pLight1, pLight2);
		//pLight1.getScope().addAll(cubeGroup.getChildren());
	}

	/**
	 * Method name: getCamara
	 * @return
	 *
	 * creates and returns a prespective camera
	 */
	private PerspectiveCamera getCamara(){
		camera = new PerspectiveCamera(true);
		camera.setNearClip(0.1);
		camera.setFarClip(10000.0);
		camera.setTranslateZ(-1000);
//		camera.getTransforms().addAll(t, rotateX, rotateY, rotateZ);
//		camera.setOnKeyTyped(e->{
//			if(e.getCode() == KeyCode.Q){
//				camera.setTranslateZ(camera.getTranslateZ() - 50);
//			}
//		});
		return camera;
	}
}
