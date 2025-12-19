/**
 * 
 */

/**
 * 
 */
public class Pixel {
	private int x;
	private int y;
	private double intensity;
	
	
	/**
	 * @param x
	 * @param y
	 * @param intensity
	 */
	
	public Pixel(int x, int y, double intensity) {
	    if (x < 0 || y < 0) {
	        throw new IllegalArgumentException(
	            String.format("Invalid coordinates (%d,%d)", x, y));
	    }
	    if (intensity < 0 || intensity > 255) {
	        throw new IllegalArgumentException(
	            String.format("Invalid intensity %.2f", intensity));
	    }
	    this.x = x;
	    this.y = y;
	    this.intensity = intensity;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * @return the intensity
	 */
	public double getIntensity() {
		return intensity;
	}
	
	/**
	 * @param intensity the intensity to set
	 */
	public void setIntensity(double intensity) {
		this.intensity = intensity;
	}
	
	
}
