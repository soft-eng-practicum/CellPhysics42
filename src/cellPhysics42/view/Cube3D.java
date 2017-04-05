package cellPhysics42.view;

import java.util.Scanner;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

/**Class: Cube3D.java 
   * @author Bess Burnett 
   * @version 1.0 
   * Course : ITEC  Spring 2017
   * Written: Mar 31, 2017 
  	   * 
   * This class –  extends Box to make a cube
   * 
   * Purpose: –  to create the cubes needed to display the 2D rules and modify their location and color
   */
public class Cube3D extends Box {
	private static final double defaultSize = 5.0;
	private static final Color defaultDiffuseColor = Color.CYAN;
	private static final Color defaultSpecularColor = Color.AQUA;
	private PhongMaterial material;
	
	/**Cube3D
	 * 
	 * no-agr constructor
	 *
	 * creates a Cube3D object with default values
	 */
	public Cube3D(){
		this(defaultSize, defaultDiffuseColor, defaultSpecularColor);
	}
	
	/**Cube3D
	 * @param size
	 * 1- agr constructor
	 *
	 * creates a Cube3D object with the given value and defaults for the ones not given
	 */
	public Cube3D(double size){
		this(size, defaultDiffuseColor, defaultSpecularColor);
	}
	
	/**Cube3D
	 * @param size
	 * @param diffuseColor
	 * @param specularColor
	 * 3-agr constructor
	 *
	 * creates a Cube3D object with the given values
	 */
	public Cube3D(double size, Color diffuseColor, Color specularColor){
		material = new PhongMaterial(diffuseColor);
		material.setSpecularColor(specularColor);
		super.setWidth(size);
		super.setHeight(size);
		super.setDepth(size);
		super.setMaterial(material);
	}
	
	/**
	 * Method name: setMaterial
	 * @param diffuseColor
	 * 
	 * sets the diffuse color of the cube
	 */
	public void setDiffuse(Color diffuseColor){
		material = new PhongMaterial(diffuseColor);
		super.setMaterial(material);
	}
	
	public Color getDiffuse(){
		return material.getDiffuseColor();
	}
	
	/**
	 * Method name: translateCube
	 * @param translation String - x,y,z
	 * 
	 * translates the cube based on the x,y,z cooridinates in the String
	 */
	public void translateCube(String translation){
		Scanner trans = new Scanner(translation);
		trans.useDelimiter(",");
		int x = trans.nextInt();
		int y = trans.nextInt();
		int z = trans.nextInt();
		super.setTranslateX(x);
		super.setTranslateY(y	);
		super.setTranslateZ(z);
		trans.close();
	}
}
