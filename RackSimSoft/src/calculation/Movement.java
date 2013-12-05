package calculation;

import java.util.ArrayList;

import location.RackFeeder;


/**
 * The rack feeder is moving inside the gap in the direction of the 
 * y axis and its beam is only able to move in the direction of the z axis.
 * The super position of both moving vectors let the operating unit move
 * inclined in side the yz area of the grid.
 */

public class Movement
{
	private Distance distance;
	private RackFeeder rackFeeder;
	
	// Beschleunigung
	private double xAccelerationTime = 0;
	private double yAccelerationTime = 0;
	private double zAccelerationTime = 0;
	private double uAccelerationTime = 0;
	private double xAccelerationDistance = 0;
	private double yAccelerationDistance = 0;
	private double zAccelerationDistance = 0;
	private double uAccelerationDistance = 0;
	
	// Fahren mit vMax
	private double xMovingTime = 0;
	private double yMovingTime = 0;
	private double zMovingTime = 0;
	private double uMovingTime = 0;
	private double xMovingDistance = 0;
	private double yMovingDistance = 0;
	private double zMovingDistance = 0;
	private double uMovingDistance = 0;
	
	// Bremsen
	private double xDecelerationTime = 0;
	private double yDecelerationTime = 0;
	private double zDecelerationTime = 0;
	private double uDecelerationTime = 0;
	private double xDecelerationDistance = 0;
	private double yDecelerationDistance = 0;
	private double zDecelerationDistance = 0;
	private double uDecelerationDistance = 0;
	
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
	 * Prepares the rack feeder to move the given distance in the chosen axes.
	 * Calculates the maximum values for acceleration, speed and deceleration.
	 * If the given String is null or empty, the values will be calculated for ALL axes with any difference in distance.
	 * Returns the total time needed to move over all axes.
	 * 
	 * @return the time
	 */
	public int prepareForMove(String direction)
	{
		// Werte zuruecksetzen
		this.xAccelerationTime = 0;
		this.yAccelerationTime = 0;
		this.zAccelerationTime = 0;
		this.uAccelerationTime = 0;
		this.xAccelerationDistance = 0;
		this.yAccelerationDistance = 0;
		this.zAccelerationDistance = 0;
		this.uAccelerationDistance = 0;
		
        this.xMovingTime = 0;
        this.yMovingTime = 0;
        this.zMovingTime = 0;
        this.uMovingTime = 0;
        this.xMovingDistance = 0;
        this.yMovingDistance = 0;
        this.zMovingDistance = 0;
        this.uMovingDistance = 0;
        
        this.xDecelerationTime = 0;
        this.yDecelerationTime = 0;
        this.zDecelerationTime = 0;
        this.uDecelerationTime = 0;
        this.xDecelerationDistance = 0;
		this.yDecelerationDistance = 0;
		this.zDecelerationDistance = 0;
		this.uDecelerationDistance = 0;
        
		int totalTime = 0;
		int axisTime = 0;
		
		// Check the directions
		int xDistance = distance.getXDistance();
		int yDistance = distance.getYDistance();
		int zDistance = distance.getZDistance();
		int uDistance = distance.getUDistance();
		
		System.out.println("Richtung und Distanzen: " + direction + ": " + xDistance + "  " + yDistance + "  " + zDistance + "  " + uDistance);
		
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
		
		// Calculate time for move and set the physical values to the maximum allowed
		if (direction.substring(0, 1).equals("1"))
		{
			axisTime = getOneAxisTime("X");
			if (totalTime < axisTime)
				totalTime = axisTime;
		}
			
		
		if (direction.substring(1, 2).equals("1"))
		{
			axisTime = getOneAxisTime("Y");
			if (totalTime < axisTime)
				totalTime = axisTime;
		}
		
		if (direction.substring(2, 3).equals("1"))
		{
			axisTime = getOneAxisTime("Z");
			if (totalTime < axisTime)
				totalTime = axisTime;
		}
		
		if (direction.substring(3, 4).equals("1"))
		{
			axisTime = getOneAxisTime("U");
			if (totalTime < axisTime)
				totalTime = axisTime;
		}
		
		return totalTime;
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
				s = Math.abs(this.distance.getXDistance());
				break;
				
			case "Y" :
				v = this.rackFeeder.getYSpeed();
				a = this.rackFeeder.getYAcceleration();
				d = this.rackFeeder.getYDeceleration();
				s = Math.abs(this.distance.getYDistance());
				break;
				
			case "Z" :
				v = this.rackFeeder.getZSpeed();
				a = this.rackFeeder.getZAcceleration();
				d = this.rackFeeder.getZDeceleration();
				s = Math.abs(this.distance.getZDistance());
				break;
				
			case "U" :
				v = this.rackFeeder.getUSpeed();
				a = this.rackFeeder.getUAcceleration();
				d = this.rackFeeder.getUDeceleration();
				s = Math.abs(this.distance.getUDistance());
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
        
        // Pruefen, ob Weg ueberschritten wird, dann neue Werte berechnen
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
        
        // Werte auf RBG neu setzen und hier speichern, damit Koordinate nach Zeit berechnet werden kann (z.B. fuer Grafik)
        switch (axis)
		{
			case "X" :
				// RackFeeder
				this.rackFeeder.setXSpeed(v);
				this.rackFeeder.setXAcceleration(a);
				this.rackFeeder.setXDeceleration(d);
				
				// Movement
				this.xAccelerationTime = ta;
				this.xAccelerationDistance = sa;
				this.xMovingTime = tv;
				this.xMovingDistance = sv;
				this.xDecelerationTime = td;
				this.xDecelerationDistance = sd;
				
				break;
				
			case "Y" :
				// RackFeeder
				this.rackFeeder.setYSpeed(v);
				this.rackFeeder.setYAcceleration(a);
				this.rackFeeder.setYDeceleration(d);
				
				// Movement
				this.yAccelerationTime = ta;
				this.yAccelerationDistance = sa;
				this.yMovingTime = tv;
				this.yMovingDistance = sv;
				this.yDecelerationTime = td;
				this.yDecelerationDistance = sd;
				
				break;
				
			case "Z" :
				// RackFeeder
				this.rackFeeder.setZSpeed(v);
				this.rackFeeder.setZAcceleration(a);
				this.rackFeeder.setZDeceleration(d);
				
				// Movement
				this.zAccelerationTime = ta;
				this.zAccelerationDistance = sa;
				this.zMovingTime = tv;
				this.zMovingDistance = sv;
				this.zDecelerationTime = td;
				this.zDecelerationDistance = sd;
				
				break;
				
			case "U" :
				// RackFeeder
				this.rackFeeder.setUSpeed(v);
				this.rackFeeder.setUAcceleration(a);
				this.rackFeeder.setUDeceleration(d);
				
				// Movement
				this.uAccelerationTime = ta;
				this.uAccelerationDistance = sa;
				this.uMovingTime = tv;
				this.uMovingDistance = sv;
				this.uDecelerationTime = td;
				this.uDecelerationDistance = sd;
				
				break;
		}
        
        int time = (int) (ta + tv + td);
		System.out.println("Beschleunigungs- / Fahr- / Bremszeit fuer " + axis + ": " + ta + "/" + tv + "/" + td);
        
        return time;
	}
	
	
	/**
	 * 
	 * 
	 * @return the coordinate
	 */
	public Coordinate getCurrentCoordinate(int time)
	{
		double currentX = this.rackFeeder.getX();
		double currentY = this.rackFeeder.getY();
    	double currentZ = this.rackFeeder.getZ();
    	double currentU = this.rackFeeder.getU();
    	
		// Beschleunigen
    	// -------------
    	// Nur aendern, wenn X noch beschleunigt wird
    	if (time <= this.xAccelerationTime)
    	{
    		currentX = Math.round(Math.abs(0.5 * this.rackFeeder.getXAcceleration() * Math.pow(time, 2)));
    	}
    	
    	// Nur aendern, wenn Y noch beschleunigt wird
    	if (time <= this.yAccelerationTime)
    	{
    		currentY = Math.round(Math.abs(0.5 * this.rackFeeder.getYAcceleration() * Math.pow(time, 2)));
    	}
    	
    	// Nur aendern, wenn Z noch beschleunigt wird
    	if (time <= this.zAccelerationTime)
    	{
    		currentZ = Math.round(Math.abs(0.5 * this.rackFeeder.getZAcceleration() * Math.pow(time, 2)));
    	}
    	
    	// Nur aendern, wenn U noch beschleunigt wird
    	if (time <= this.uAccelerationTime)
    	{
    		currentU = Math.round(Math.abs(0.5 * this.rackFeeder.getUAcceleration() * Math.pow(time, 2)));
    	}
    	// +++++++++++++
    	
    	
    	// Fahren
    	// ------
    	// Nur aendern, wenn X schon / noch fahren muss
    	if ((time <= (int) (this.xAccelerationTime + this.xMovingTime)) && (time > this.xAccelerationTime))
    	{
    		currentX = this.xAccelerationDistance + (this.rackFeeder.getXSpeed() * (time - this.xAccelerationTime));
    	}
    	
    	// Nur aendern, wenn Y schon / noch fahren muss
    	if ((time <= (int) (this.yAccelerationTime + this.yMovingTime)) && (time > this.yAccelerationTime))
    	{
    		currentY = this.yAccelerationDistance + (this.rackFeeder.getYSpeed() * (time - this.yAccelerationTime));
    	}
    	
    	// Nur aendern, wenn Z schon / noch fahren muss
    	if ((time <= (int) (this.zAccelerationTime + this.zMovingTime)) && (time > this.zAccelerationTime))
    	{
    		currentZ = this.zAccelerationDistance + (this.rackFeeder.getZSpeed() * (time - this.zAccelerationTime));
    	}
    	
    	// Nur aendern, wenn U schon / noch fahren muss
    	if ((time <= (int) (this.uAccelerationTime + this.uMovingTime)) && (time > this.uAccelerationTime))
    	{
    		currentU = this.uAccelerationDistance + (this.rackFeeder.getUSpeed() * (time - this.uAccelerationTime));
    	}
    	// ++++++
    	
    	
    	// Bremsen
    	// -------
    	// Nur aendern, wenn X noch gebremst wird
    	if ((time <= (int) (this.xAccelerationTime + this.xMovingTime + this.xDecelerationTime)) && (time > (int) (this.xAccelerationTime + this.xMovingTime)))
    	{
    		currentX = this.xAccelerationDistance + this.xMovingDistance + ((this.rackFeeder.getXSpeed() * (time - this.xAccelerationTime - this.xMovingTime)) + (0.5 * this.rackFeeder.getXDeceleration() * Math.pow((time - this.xAccelerationTime - this.xMovingTime), 2)));
    	}
    	
    	// Nur aendern, wenn Y noch gebremst wird
    	if ((time <= (int) (this.yAccelerationTime + this.yMovingTime + this.yDecelerationTime)) && (time > (int) (this.yAccelerationTime + this.yMovingTime)))
    	{
    		currentY = this.yAccelerationDistance + this.yMovingDistance + ((this.rackFeeder.getYSpeed() * (time - this.yAccelerationTime - this.yMovingTime)) + (0.5 * this.rackFeeder.getYDeceleration() * Math.pow((time - this.yAccelerationTime - this.yMovingTime), 2)));
    	}
    	
    	// Nur aendern, wenn Z noch gebremst wird
    	if ((time <= (int) (this.zAccelerationTime + this.zMovingTime + this.zDecelerationTime)) && (time > (int) (this.zAccelerationTime + this.zMovingTime)))
    	{
    		currentZ = this.zAccelerationDistance + this.zMovingDistance + ((this.rackFeeder.getZSpeed() * (time - this.zAccelerationTime - this.zMovingTime)) + (0.5 * this.rackFeeder.getZDeceleration() * Math.pow((time - this.zAccelerationTime - this.zMovingTime), 2)));
    	}
		
    	// Nur aendern, wenn U noch gebremst wird
    	if ((time <= (int) (this.uAccelerationTime + this.uMovingTime + this.uDecelerationTime)) && (time > (int) (this.uAccelerationTime + this.uMovingTime)))
    	{
    		currentU = this.uAccelerationDistance + this.uMovingDistance + ((this.rackFeeder.getUSpeed() * (time - this.uAccelerationTime - this.uMovingTime)) + (0.5 * this.rackFeeder.getUDeceleration() * Math.pow((time - this.uAccelerationTime - this.uMovingTime), 2)));
    	}
    	
		return new Coordinate((int) currentX, (int) currentY, (int) currentZ, (int) currentU);
	}
	
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
