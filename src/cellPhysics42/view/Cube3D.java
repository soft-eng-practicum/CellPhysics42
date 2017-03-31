package cellPhysics42.view;

import java.util.Scanner;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

public class Cube3D extends Box {
	private static final double defaultSize = 5.0;
	private static final Color defaultDiffuseColor = Color.MEDIUMSPRINGGREEN;
	private static final Color defaultSpecularColor = Color.AQUA;
	private PhongMaterial material;
	
	public Cube3D(){
		this(defaultSize, defaultDiffuseColor, defaultSpecularColor);
	}
	
	public Cube3D(double size, Color diffuseColor, Color specularColor){
		material = new PhongMaterial(diffuseColor);
		//material.setSpecularColor(specularColor);
		super.setWidth(size);
		super.setHeight(size);
		super.setDepth(size);
		super.setMaterial(material);
	}
	
	public void setMaterial(Color diffuseColor){
		material = new PhongMaterial(diffuseColor);
	}
	
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
