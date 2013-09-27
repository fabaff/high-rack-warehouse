import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JComponent;
 
 /**
    This component draw a car (RGB).
 */
 public class CarComponent extends JComponent
 {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Vector<Car> cars;

	 /**
	  * Create a empty frame.
	  */
     public CarComponent() {
    	 cars = new Vector<Car>();
     }
	 
	 /**
	  * Create a frame...
	  * @param car  The car
	  */
     public CarComponent(Car car) {
    	 cars = new Vector<Car>();
    	 //cars.add(car);
     }

     /**
      * Add a car
      * 
      * @param car   The car
      */
     public void addCar(Car car) {
    	 cars.add(car);
     }
     
     /**
      * paintComponent
      */
     public void paintComponent(Graphics g) {  
    	Graphics2D g2 = (Graphics2D) g;

    	Iterator<Car> cit = cars.iterator();
    	while(cit.hasNext()) {
    		cit.next().draw(g2);
    	}
    }
}