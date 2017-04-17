package Test;

import org.junit.Test;

import cellPhysics42.view.Cube3D;
import javafx.scene.paint.Color;
import org.junit.Assert;

public class Cube3DTest {
	@Test
	public void setDiffuseColorTest(){
		Cube3D cube = new Cube3D();
		cube.setDiffuse(Color.BLUE);
		Color testColor = cube.getDiffuse();
		Assert.assertEquals(Color.BLUE, testColor);
	}
	
	@Test
	public void translateCubeTest(){
		Cube3D cube = new Cube3D(10, Color.BLACK	, Color.GRAY);
		cube.translateCube("10,10,10");
		double [] coordinates = new double[3];
		coordinates[0] = cube.getTranslateX();
		coordinates[1] = cube.getTranslateY();
		coordinates[2] = cube.getTranslateZ();
		double[] expected = new double[3];
		expected[0] = 10.0;
		expected[1] = 10.0;
		expected[2] = 10.0;
		Assert.assertArrayEquals(expected, coordinates, 0.5);
	}
}
