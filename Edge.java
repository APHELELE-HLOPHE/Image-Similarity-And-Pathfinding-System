/**
 * 
 */

/**
 * 
 */
public class Edge {

	private Pixel source;
	private Pixel target;
	private double wieght;
	
	/**
	 * @param source
	 * @param target
	 * @param wieght
	 */
	public Edge(Pixel source, Pixel target, double wieght) {
		super();
		this.source = source;
		this.target = target;
		this.wieght = wieght;
	}

	/**
	 * @return the source
	 */
	public Pixel getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(Pixel source) {
		this.source = source;
	}

	/**
	 * @return the target
	 */
	public Pixel getTarget() {
		return target;
	}

	/**
	 * @param target the target to set
	 */
	public void setTarget(Pixel target) {
		this.target = target;
	}

	/**
	 * @return the wieght
	 */
	public double getWieght() {
		return wieght;
	}

	/**
	 * @param wieght the wieght to set
	 */
	public void setWieght(double wieght) {
		this.wieght = wieght;
	}
	
	
	
}
