package cellPhysics42.view;

import javafx.scene.shape.Polygon;

public class Triangle extends Polygon{
	private double height;
	private double width;
	
	public Triangle() {
	}
	
	public Triangle(double width, double height){
		this.width = width;
		this.height = height;
		buildTriangle();
	}
	
	public void buildTriangle(){
		super.getPoints().addAll(0.0, height, width, height, (width/2), 0.0);
	}

	/**getHeight
	 * @return height: double - 
	 * 
	 * returns the height
	 */
	protected double getHeight() {
		return height;
	}

	/**getWidth
	 * @return width: double - 
	 * 
	 * returns the width
	 */
	protected double getWidth() {
		return width;
	}

	/**setHeight
	 * @param height: double - height
	 *
	 * sets height to the given value
	 */
	protected void setHeight(double height) {
		this.height = height;
	}

	/**setWidth
	 * @param width: double - width
	 *
	 * sets width to the given value
	 */
	protected void setWidth(double width) {
		this.width = width;
	}

	
}
