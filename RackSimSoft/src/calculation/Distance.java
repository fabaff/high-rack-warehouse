package calculation;

import location.*;


/**
 * Calculations on the yz area in the grid.
 */

public class Distance {
	private int distance;
	private int yDistance;
	private int zDistance;
	
	/**
	 * Calculates the distance of a bin to the zero point in the grid.
	 * 
	 * @param location
     * @param binID
	 */
	public double zeroDistance(Location location, String binID)
	{
		Bin bin;
		bin = location.getBin(binID);
		double zeroDistance = Math.round(Math.sqrt(Math.pow((bin.getY()), 2) + 
				Math.pow((bin.getZ()), 2)));
		return zeroDistance;
	}
	
	/**
	 * Calculates the y distance of two bins in the grid.
	 * 
	 * @param location
     * @param binID1
	 * @param binID2
	 */
	public int yDistance(Location location, String binID1, String binID2)
	{
		Bin bin1;
		Bin bin2;
		bin1 = location.getBin(binID1);
		bin2 = location.getBin(binID2);
		int yDistance = Math.abs(bin1.getY() - bin2.getY());
		return yDistance;
	}

	/**
	 * Calculates the z distance of two bins in the grid.
	 * 
	 * @param location
     * @param binID1
	 * @param binID2
	 */
	public int zDistance(Location location, String binID1, String binID2)
	{
		Bin bin1;
		Bin bin2;
		bin1 = location.getBin(binID1);
		bin2 = location.getBin(binID2);
		int zDistance = Math.abs(bin1.getZ() - bin2.getZ());
		return zDistance;
	}

	/**
	 * Calculates the x distance of a bin to the middle of the gap.
	 * 
	 * @param location
     * @param binID1
	 * @param binID2
	 */
	public int xDistance(Location location, String gapID, String binID)
	{
		Gap gap;
		gap = location.getGap(gapID);
		Bin bin;
		bin = location.getBin(binID);
		
		int xDistance = (gap.getWidth() / 2) + (bin.getWidth() / 2);
		return xDistance;
	}
	
	/**
	 * Calculates the distance of two bins in the grid which is the traveling
	 * distance for the operating unit.
	 * 
	 * @param location
     * @param binID1
	 * @param binID2
	 */
	public double mDistance(Location location, String binID1, String binID2)
	{
		Bin bin1;
		Bin bin2;
		bin1 = location.getBin(binID1);
		bin2 = location.getBin(binID2);
		// The result is rounded.
		double mDistance = Math.round(Math.sqrt(Math.pow((bin1.getY() - 
				bin2.getY()), 2) + Math.pow((bin1.getZ() - bin2.getZ()), 2)));
		return mDistance;
	}
	
	/**
	 * Returns the distance between two points.
	 * 
	 * @return the distance between two point
	 */
	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getyDistance() {
		return yDistance;
	}

	public void setyDistance(int yDistance) {
		this.yDistance = yDistance;
	}

	public int getzDistance() {
		return zDistance;
	}

	public void setzDistance(int zDistance) {
		this.zDistance = zDistance;
	}
}
