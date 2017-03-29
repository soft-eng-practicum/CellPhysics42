package cellPhysics42.view;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import Controller.ControlClass;
import javafx.application.Application;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

public class View3DControl {
	private FileReader ruleFileReader;
	private Group root;
	private ArrayList<String> cubeTranslations;
	private ControlClass control;
	
	public void initialize(){
		root = new Group();
		control = new ControlClass();
		//cubeTranslations = new ArrayList<String>();
	}
	
	//get scad as text
//	private void readInFile(String fileName){
//		try{
//			File ruleFile = new File(fileName);
//			ruleFileReader = new FileReader(ruleFile);
//			System.out.println("file read");
//		}
//		catch(Exception ex){
//			System.out.println(ex.getMessage());
//		}
//	}
	
//	private void buildCubeTranslations(){
//		Scanner buildScanner = new Scanner(ruleFileReader);
//		buildScanner.useDelimiter("]"	);
//		while (buildScanner.hasNext()){
//			String cubeTranslate = buildScanner.next();
//			cubeTranslate = cubeTranslate.substring(cubeTranslate.indexOf('[') + 1);
//			cubeTranslations.add(cubeTranslate);
//		}
//		buildScanner.close();
//	}
	
	private void buildCubes(){
		cubeTranslations = control.getCubeTranslations(453);
		for(String translation : cubeTranslations){
			Cube3D cube = new Cube3D();
			cube.translateCube(translation);
			root.getChildren().add(cube);
		}
	}
	
	private void setLights(){
		AmbientLight amLight = new AmbientLight(Color.WHITE);
		amLight.getScope().addAll(root.getChildren());
	}
	
	private PerspectiveCamera getCamara(){
		PerspectiveCamera camera = new PerspectiveCamera(true);
		return camera;
	}
	
	public Scene get3DScene(double width, double height){
		initialize();
		buildCubes();
		setLights();
		root.setTranslateX(0);
		root.setTranslateY(0);
		Scene scene = new Scene(root, width, height);
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
