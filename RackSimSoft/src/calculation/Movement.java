package calculation;

import java.util.ArrayList;

import location.RackFeeder;


/**
 * The operating unit is moving inside the gap in the direction of the 
 * y axis and its beam is only able to move in the direction of the z axis.
 * The super position of both moving vectors let the operating unit move
 * inclined in side the yz area of the grid.
 */

public class Movement
{
	private Distance distance;
	private RackFeeder rackFeeder;
	
	/**
	 * Creates a Movement.
	 * The distance and the rack feeder to move to must be given.
	 * 
	 * @param distance the distance to move
	 * @param rackFeeder the rack feeder which moves the given distance
	 */
	public Movement(Distance distance, RackFeeder rackFeeder)
	{
		this.distance = distance;
		this.rackFeeder = rackFeeder;
	}
	
	/**
	 * Calculates the time, which is needed to move the rack feeder the given distance in the chosen axes.
	 * If the given String is null or empty, the time will be calculated for ALL axes with any difference in distance.
	 * 
	 * This function calculates the time for an linear movement without acceleration and deceleration
	 * 
	 * @return the time
	 */
	public int getLinearTime(String direction)
	{
		int time = -1;
		
		// Check the directions
		int xDistance = distance.getXDistance();
		int yDistance = distance.getYDistance();
		int zDistance = distance.getZDistance();
		int uDistance = distance.getUDistance();
		
		ArrayList<InnerMovement> innerMovementList = new ArrayList<InnerMovement>();
		
		if ((direction == null) || (direction.equals("")))
		{
			direction = getDirectionString(xDistance, yDistance, zDistance, uDistance);
		}
		else
		{
			// Proof, if any distance is zero
			if ((direction.substring(0, 1).equals("1")) && (xDistance == 0))
				direction = "0" + direction.substring(1);
			
			if ((direction.substring(1, 2).equals("1")) && (yDistance == 0))
				direction = direction.substring(0, 1) + "0" + direction.substring(2);
			
			if ((direction.substring(2, 3).equals("1")) && (zDistance == 0))
				direction = direction.substring(0, 2) + "0" + direction.substring(3);
			
			if ((direction.substring(3, 4).equals("1")) && (uDistance == 0))
				direction = direction.substring(0, 3) + "0";
		}
		
		switch (direction)
		{
			case "0000" :
				time = 0;
				break;
			
			case "1000" :
				innerMovementList.add(new InnerMovement("X", xDistance, rackFeeder.getXSpeed(), rackFeeder.getXAcceleration(), rackFeeder.getXDeceleration()));
				break;
				
			case "0100" :
				innerMovementList.add(new InnerMovement("Y", yDistance, rackFeeder.getYSpeed(), rackFeeder.getYAcceleration(), rackFeeder.getYDeceleration()));
				break;
			
			case "1100" :
				innerMovementList.add(new InnerMovement("X", xDistance, rackFeeder.getXSpeed(), rackFeeder.getXAcceleration(), rackFeeder.getXDeceleration()));
				innerMovementList.add(new InnerMovement("Y", yDistance, rackFeeder.getYSpeed(), rackFeeder.getYAcceleration(), rackFeeder.getYDeceleration()));
				break;
				
			case "0010" :
				innerMovementList.add(new InnerMovement("Z", zDistance, rackFeeder.getZSpeed(), rackFeeder.getZAcceleration(), rackFeeder.getZDeceleration()));
				break;
			
			case "1010" :
				innerMovementList.add(new InnerMovement("X", xDistance, rackFeeder.getXSpeed(), rackFeeder.getXAcceleration(), rackFeeder.getXDeceleration()));
				innerMovementList.add(new InnerMovement("Z", zDistance, rackFeeder.getZSpeed(), rackFeeder.getZAcceleration(), rackFeeder.getZDeceleration()));
				break;
				
			case "0110" :
				innerMovementList.add(new InnerMovement("Y", yDistance, rackFeeder.getYSpeed(), rackFeeder.getYAcceleration(), rackFeeder.getYDeceleration()));
				innerMovementList.add(new InnerMovement("Z", zDistance, rackFeeder.getZSpeed(), rackFeeder.getZAcceleration(), rackFeeder.getZDeceleration()));
				break;
				
			case "1110" :
				innerMovementList.add(new InnerMovement("X", xDistance, rackFeeder.getXSpeed(), rackFeeder.getXAcceleration(), rackFeeder.getXDeceleration()));
				innerMovementList.add(new InnerMovement("Y", yDistance, rackFeeder.getYSpeed(), rackFeeder.getYAcceleration(), rackFeeder.getYDeceleration()));
				innerMovementList.add(new InnerMovement("Z", zDistance, rackFeeder.getZSpeed(), rackFeeder.getZAcceleration(), rackFeeder.getZDeceleration()));
				break;
				
			case "0001" :
				innerMovementList.add(new InnerMovement("U", uDistance, rackFeeder.getUSpeed(), rackFeeder.getUAcceleration(), rackFeeder.getUDeceleration()));
				break;
				
			case "1001" :
				innerMovementList.add(new InnerMovement("X", xDistance, rackFeeder.getXSpeed(), rackFeeder.getXAcceleration(), rackFeeder.getXDeceleration()));
				innerMovementList.add(new InnerMovement("U", uDistance, rackFeeder.getUSpeed(), rackFeeder.getUAcceleration(), rackFeeder.getUDeceleration()));
				break;
				
			case "0101" :
				innerMovementList.add(new InnerMovement("Y", yDistance, rackFeeder.getYSpeed(), rackFeeder.getYAcceleration(), rackFeeder.getYDeceleration()));
				innerMovementList.add(new InnerMovement("U", uDistance, rackFeeder.getUSpeed(), rackFeeder.getUAcceleration(), rackFeeder.getUDeceleration()));
				break;
				
			case "1101" :
				innerMovementList.add(new InnerMovement("X", xDistance, rackFeeder.getXSpeed(), rackFeeder.getXAcceleration(), rackFeeder.getXDeceleration()));
				innerMovementList.add(new InnerMovement("Y", yDistance, rackFeeder.getYSpeed(), rackFeeder.getYAcceleration(), rackFeeder.getYDeceleration()));
				innerMovementList.add(new InnerMovement("U", uDistance, rackFeeder.getUSpeed(), rackFeeder.getUAcceleration(), rackFeeder.getUDeceleration()));
				break;
				
			case "0011" :
				innerMovementList.add(new InnerMovement("Z", zDistance, rackFeeder.getZSpeed(), rackFeeder.getZAcceleration(), rackFeeder.getZDeceleration()));
				innerMovementList.add(new InnerMovement("U", uDistance, rackFeeder.getUSpeed(), rackFeeder.getUAcceleration(), rackFeeder.getUDeceleration()));
				break;
				
			case "1011" :
				innerMovementList.add(new InnerMovement("X", xDistance, rackFeeder.getXSpeed(), rackFeeder.getXAcceleration(), rackFeeder.getXDeceleration()));
				innerMovementList.add(new InnerMovement("Z", zDistance, rackFeeder.getZSpeed(), rackFeeder.getZAcceleration(), rackFeeder.getZDeceleration()));
				innerMovementList.add(new InnerMovement("U", uDistance, rackFeeder.getUSpeed(), rackFeeder.getUAcceleration(), rackFeeder.getUDeceleration()));
				break;
				
			case "0111" :
				innerMovementList.add(new InnerMovement("Y", yDistance, rackFeeder.getYSpeed(), rackFeeder.getYAcceleration(), rackFeeder.getYDeceleration()));
				innerMovementList.add(new InnerMovement("Z", zDistance, rackFeeder.getZSpeed(), rackFeeder.getZAcceleration(), rackFeeder.getZDeceleration()));
				innerMovementList.add(new InnerMovement("U", uDistance, rackFeeder.getUSpeed(), rackFeeder.getUAcceleration(), rackFeeder.getUDeceleration()));
				break;
				
			case "1111" :
				innerMovementList.add(new InnerMovement("X", xDistance, rackFeeder.getXSpeed(), rackFeeder.getXAcceleration(), rackFeeder.getXDeceleration()));
				innerMovementList.add(new InnerMovement("Y", yDistance, rackFeeder.getYSpeed(), rackFeeder.getYAcceleration(), rackFeeder.getYDeceleration()));
				innerMovementList.add(new InnerMovement("Z", zDistance, rackFeeder.getZSpeed(), rackFeeder.getZAcceleration(), rackFeeder.getZDeceleration()));
				innerMovementList.add(new InnerMovement("U", uDistance, rackFeeder.getUSpeed(), rackFeeder.getUAcceleration(), rackFeeder.getUDeceleration()));
				break;
		}
		
		switch (innerMovementList.size())
		{
			case 0 :
				time = 0;
				break;
				
			case 1 :
				time = getLinearOneAxisTime(innerMovementList, 0);
				break;
				
			case 2 :
				time = getLinearTwoAxisTime(innerMovementList, 0, 1);
				break;
				
			case 3 :
				// Calc each time for 2 axis. The speed will be changed for the faster axis, if time for both axis is different.
				time = getLinearTwoAxisTime(innerMovementList, 0, 1);  // Time after first 2 axis
				time = getLinearTwoAxisTime(innerMovementList, 0, 2);  // Time after second 2 axis
				time = getLinearTwoAxisTime(innerMovementList, 1, 2);  // Time after third 2 axis (final)
				break;
				
			case 4 :
				// Calc each time for 2 axis. The speed will be changed for the faster axis, if time for both axis is different.
				time = getLinearTwoAxisTime(innerMovementList, 0, 1);  // Time after first 2 axis
				time = getLinearTwoAxisTime(innerMovementList, 0, 2);  // Time after second 2 axis
				time = getLinearTwoAxisTime(innerMovementList, 0, 3);  // Time after third 2 axis
				time = getLinearTwoAxisTime(innerMovementList, 1, 2);  // Time after fourth 2 axis
				time = getLinearTwoAxisTime(innerMovementList, 1, 3);  // Time after fifth 2 axis
				time = getLinearTwoAxisTime(innerMovementList, 2, 3);  // Time after sixth 2 axis (final)
				break;
		}
		
		// Maybe the values speed, acceleration, deceleration have been changed. Set the new values back to the rack feeder here.
		String axis;
		
		for(InnerMovement innerMovement : innerMovementList)
		{
			axis = innerMovement.axis;
			switch (axis)
			{
				case "X" :
					rackFeeder.setXSpeed(innerMovement.speed);
					rackFeeder.setXAcceleration(innerMovement.acceleration);
					rackFeeder.setXDeceleration(innerMovement.deceleration);
					break;
					
				case "Y" :
					rackFeeder.setYSpeed(innerMovement.speed);
					rackFeeder.setYAcceleration(innerMovement.acceleration);
					rackFeeder.setYDeceleration(innerMovement.deceleration);
					break;
					
				case "Z" :
					rackFeeder.setZSpeed(innerMovement.speed);
					rackFeeder.setZAcceleration(innerMovement.acceleration);
					rackFeeder.setZDeceleration(innerMovement.deceleration);
					break;
					
				case "U" :
					rackFeeder.setUSpeed(innerMovement.speed);
					rackFeeder.setUAcceleration(innerMovement.acceleration);
					rackFeeder.setUDeceleration(innerMovement.deceleration);
					break;
			}
		}
				
		return time;
	}
	
	/**
	 * Calculates the time, which is needed to move the rack feeder the given distance in 2 axis.
	 * If the time for both axis were different, the speed will be changed for the faster axis.
	 * 
	 * @return the time
	 */
	private int getLinearTwoAxisTime(ArrayList<InnerMovement> innerMovementList, int which1, int which2)
	{
		double time1 = getLinearOneAxisTime(innerMovementList, which1);
		double time2 = getLinearOneAxisTime(innerMovementList, which2);
		double time = time1;
		
		// If times are not equal, reduce speed of faster axis to prevent the same time for moving
		if (time1 != time2)
		{
			double factor;
			InnerMovement innerMovement1 = innerMovementList.get(0);
			InnerMovement innerMovement2 = innerMovementList.get(1);
			
			if (time1 > time2)
			{
				factor = (time1 / time2);
				innerMovement2.setSpeed(innerMovement2.speed / factor);
				
				time = time1;
			}
			else
			{
				factor = (time2 / time1);
				innerMovement1.setSpeed(innerMovement1.speed / factor);
				
				time = time2;
			}
		}
		
		return (int) time;
	}
	
	/**
	 * Calculates the time, which is needed to move the rack feeder the given distance in 1 axis.
	 * 
	 * @return the time
	 */
	private int getLinearOneAxisTime(ArrayList<InnerMovement> innerMovementList, int which)
	{
		InnerMovement innerMovement = innerMovementList.get(which);
		int time = (int) Math.abs(Math.round(innerMovement.distance / innerMovement.speed));
		
		return time;
	}
	
	/**
	 * Helpermethod
	 * 
	 * @param xDistance
	 * @param yDistance
	 * @param zDistance
	 * @param uDistance
	 * @return
	 */
	private String getDirectionString(int xDistance, int yDistance, int zDistance, int uDistance)
	{
		String direction = "";
		
		if (xDistance != 0)
			direction = direction + "1";
		else
			direction = direction + "0";
		
		if (yDistance != 0)
			direction = direction + "1";
		else
			direction = direction + "0";
		
		if (zDistance != 0)
			direction = direction + "1";
		else
			direction = direction + "0";
		
		if (uDistance != 0)
			direction = direction + "1";
		else
			direction = direction + "0";
		
		// direction: "XYZU"
		return direction;
	}
	
	/**
	 * Calculates the time, which is needed to move the rack feeder the given distance in the chosen axes.
	 * If the given String is null or empty, the time will be calculated for ALL axes with any difference in distance.
	 * 
	 * This function calculates the time for the fastest movement, including acceleration and deceleration
	 * 
	 * This function prepares the given rack feeder for moving and sets the physical values to the maximum allowed.
	 * 
	 * @return the time
	 */
	public int prepareForMove(String direction)
	{
		int time = 0;
		
		// Check the directions
		int xDistance = distance.getXDistance();
		int yDistance = distance.getYDistance();
		int zDistance = distance.getZDistance();
		int uDistance = distance.getUDistance();
		
		System.out.println(direction + ": " + xDistance + "  " + yDistance + "  " + zDistance + "  " + uDistance);
		
		if ((direction == null) || (direction.equals("")))
		{
			direction = getDirectionString(xDistance, yDistance, zDistance, uDistance);
		}
		else
		{
			// Proof, if any distance is zero
			if ((direction.substring(0, 1).equals("1")) && (xDistance == 0))
				direction = "0" + direction.substring(1);
			
			if ((direction.substring(1, 2).equals("1")) && (yDistance == 0))
				direction = direction.substring(0, 1) + "0" + direction.substring(2);
			
			if ((direction.substring(2, 3).equals("1")) && (zDistance == 0))
				direction = direction.substring(0, 2) + "0" + direction.substring(3);
			
			if ((direction.substring(3, 4).equals("1")) && (uDistance == 0))
				direction = direction.substring(0, 3) + "0";
		}
		
		if ((direction.substring(0, 1).equals("1")) && (xDistance == 0))
			time += getOneAxisTime(("X"));
		
		if ((direction.substring(1, 2).equals("1")) && (yDistance == 0))
			time += getOneAxisTime(("Y"));
		
		if ((direction.substring(2, 3).equals("1")) && (zDistance == 0))
			time += getOneAxisTime(("Z"));
		
		if ((direction.substring(3, 4).equals("1")) && (uDistance == 0))
			time += getOneAxisTime(("U"));
		
				
		return time;
	}
	
	/**
	 * Calculates the time, which is needed to move the rack feeder the given distance in 1 axis.
	 * Maybe the values speed, acceleration, deceleration must be changed. Sets the new values to the rack feeder.
	 * 
	 * @return the time
	 */
	private int getOneAxisTime(String axis)
	{
		// Geschwindigkeit
		double v = 0;
		// Beschleunigung
    	double a = 0;
    	// Negativbeschleunigung
    	double d = 0;
    	// Weg
    	double s = 0;
		
		switch (axis)
		{
			case "X" :
				v = this.rackFeeder.getXSpeed();
				a = this.rackFeeder.getXAcceleration();
				d = this.rackFeeder.getXDeceleration();
				s = this.distance.getXDistance();
				break;
				
			case "Y" :
				v = this.rackFeeder.getYSpeed();
				a = this.rackFeeder.getYAcceleration();
				d = this.rackFeeder.getYDeceleration();
				s = this.distance.getYDistance();
				break;
				
			case "Z" :
				v = this.rackFeeder.getZSpeed();
				a = this.rackFeeder.getZAcceleration();
				d = this.rackFeeder.getZDeceleration();
				s = this.distance.getZDistance();
				break;
				
			case "U" :
				v = this.rackFeeder.getUSpeed();
				a = this.rackFeeder.getUAcceleration();
				d = this.rackFeeder.getUDeceleration();
				s = this.distance.getUDistance();
				break;
		}
		
		// Beschleunigungsweg
		double sa = Math.abs(Math.pow(v, 2) / (2 * a));
		// Beschleunigungszeit
		double ta = (v / a);
		// Bremsweg
		double sd = Math.abs(Math.pow(v, 2) / (2 * d));
		// Bremszeit
		double td = Math.abs(v / d);
		// Restweg zum fahren mit max. Geschwindigkeit
        double sv = s - (sa + sd);
        // Zeit zum fahren mit max. Geschwindigkeit
        double tv = sv / v;
        System.out.println("TEST TEST TEST:  " + tv);
        // Prüfen, ob Weg überschritten wird, dann neue Werte berechnen
        if (tv < 0)
        {
        	// v neu berechnen
        	v = Math.sqrt((2 * s * a * Math.abs(d)) / (a + Math.abs(d)));
        	// Alle anderen Werte neu berechnen
        	sa = Math.abs(Math.pow(v, 2) / (2 * a));
        	ta = (v / a);
        	sd = Math.abs(Math.pow(v, 2) / (2 * d));
        	td = Math.abs(v / d);
        	sv = s - (sa + sd);
        	tv = sv / v;
        }
        
        // Werte auf RBG neu setzen
        switch (axis)
		{
			case "X" :
				this.rackFeeder.setXSpeed(v);
				this.rackFeeder.setXAcceleration(a);
				this.rackFeeder.setXDeceleration(d);
				break;
				
			case "Y" :
				this.rackFeeder.setYSpeed(v);
				this.rackFeeder.setYAcceleration(a);
				this.rackFeeder.setYDeceleration(d);
				break;
				
			case "Z" :
				this.rackFeeder.setZSpeed(v);
				this.rackFeeder.setZAcceleration(a);
				this.rackFeeder.setZDeceleration(d);
				break;
				
			case "U" :
				this.rackFeeder.setUSpeed(v);
				this.rackFeeder.setUAcceleration(a);
				this.rackFeeder.setUDeceleration(d);
				break;
		}
        
        int time = (int) (ta + tv + td);
		
        return time;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// TODO cTime ist nur als Test gedacht. Die Zeiten muessen den Events
	// zugeordnet sein und koennen nicht den gesamten Vorgang abdecken.
	/**
	 * Time for a complete loading cycle (from the loading zone to the bin)
	 * and back to standby location.
	 * 
	 * @return the time consumption for a complete loading cycle
	 */
	/*
	public double cTime(Location location, String binID1, String binID2)
	{
		Distance distance = new Distance();
		
		double xDistance = distance.getxDistance();
		double lDistance = distance.getlDistance();
		
		// Loading time
		double ptime = pTime(lSpeed, lDistance);
		// Traveling time
		double mtime = mTime(location, binID1, binID2);
		// Placing time
		double xtime = xTime(xSpeed, xDistance);
		
		double cTime = ptime + mtime + xtime + mtime;
		return cTime;	
	}
	*/
	
	/**
	 * Calculates the moving time between two given positions inside the grid.
	 * 
	 * @param ySpeed 		
	 * @param zSpeed 		
	 * @param acceleration 	
	 * @param deceleration	
	 */
	/*
	public double mTime(Location location, String binID1, String binID2)
	{
		Distance distance = new Distance();

		double track = distance.getMDistance(location, binID1, binID2);
		
		double aTime = aTime(ySpeed, acceleration);
		double dTime = dTime(ySpeed, deceleration);
		double lTime = lTime(ySpeed, zSpeed, track/1000);
		double tTime = aTime + lTime + dTime;	
		return tTime;
	}
	*/
	
	/**
	 * Calculates the time which is needed for the acceleration.
	 * 
	 * @param ySpeed2 		
	 * @param acceleration	
	 */
	/*
	private double aTime(double ySpeed, double acceleration)
	{
		double aTime = ySpeed / acceleration;
		return aTime;	
	}
	*/
	
	/**
	 * Calculates the time which is needed for the deceleration.
	 * 
	 * @param ySpeed 		
	 * @param deceleration	
	 */
	/*
	private double dTime(double ySpeed, double deceleration)
	{
		double dTime = ySpeed / deceleration;
		return dTime;	
	}
	*/
	
	/**
	 * Calculates the time for loading the good on the operating unit beam.
	 * 
	 * @param xSpeed   the traveling speed for the loading process	
	 * @param distance the distance from the bin to the beam (middle of the gap)
	 * 
	 * @return the time for picking or unloading goods in bins
	 */
	/*
	public double xTime(double xSpeed, double distance)
	{
		double xTime = distance / xSpeed;
		return xTime;
	}
	*/
	
	/**
	 * Operating time in the interface area to the loading zone.
	 * 
	 * @param Speed   the traveling speed for the loading process	
	 * @param distance the y distance from the beam to the interface area
	 * 
	 * @return the needed time for operating in the loading zone
	 */
	/*
	public double pTime(double lSpeed, double distance)
	{
		double pTime = distance / lSpeed;
		return pTime;
	}
	*/
	
	/**
	 * @author mschaerer
	 *
	 * The InnerMovement is a helper class to store all relevant informations for calculating time of moving.
	 */
	private class InnerMovement
	{
		private String axis;
		private int distance;
		private double speed;
		private double acceleration;
		private double deceleration;
		
		private InnerMovement(String axis, int distance, double speed, double acceleration, double deceleration)
		{
			this.axis = axis;
			this.distance = distance;
			this.speed = speed;
			this.acceleration = acceleration;
			this.deceleration = deceleration;
		}
		
		private void setSpeed(double speed)
		{
			this.speed = speed;
		}
		
		private void setAcceleration(double acceleration)
		{
			this.acceleration = acceleration;
		}
		
		private void setDeceleration(double deceleration)
		{
			this.deceleration = deceleration;
		}
	}
}
