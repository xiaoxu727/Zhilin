package Map;

public class Bound {
	 double latMin;
	 double latMax;
	 double lonMin;
	 double lonMax;
	
	public double getLatMin() {
		return latMin;
	}
	public void setLatMin(double latMin) {
		this.latMin = latMin;
	}
	public double getLatMax() {
		return latMax;
	}
	public void setLatMax(double latMax) {
		this.latMax = latMax;
	}
	public double getLonMin() {
		return lonMin;
	}
	public void setLonMin(double lonMin) {
		this.lonMin = lonMin;
	}
	public double getLonMax() {
		return lonMax;
	}
	public void setLonMax(double lonMax) {
		this.lonMax = lonMax;
	}
	
	/**
	 * 
	 * @param latMin 
	 * @param lonMin
	 * @param latMax
	 * @param lonMax
	 */
	
	public Bound(double latMin,double lonMin,double latMax,double lonMax)
	{
		this.latMin = latMin;
		this.latMax = latMax;
		this.lonMax = lonMax;
		this.lonMin = lonMin;
	}
	public Bound(){
		
	}
	
	
}
