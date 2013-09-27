import java.awt.Rectangle;

import javax.swing.JFrame;

public class RackDrawing {
	   public static void main(String[] args) {
		  int VSIZE = 600;
		  int HSIZE = 400;
	      JFrame frame = new JFrame();
	      frame.setSize(VSIZE, HSIZE);
	      frame.setTitle("This is the frame title...");
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	      GridComponent component = new GridComponent();
	      frame.add(component);

	      Point point = new Point(100, 100);
	      InOut inout = new InOut(200,200);	      
	      Car car1 = new Car(0,0);

	       //CarComponent component1 = new CarComponent(car1);
	       //component1.addCar(car1);
	       //frame.add(component1);
	      
	       frame.setVisible(true);
	   	   int translate = 0;
	      
	     	      
	      frame.setVisible(true);
	       while(true) {
	    	   mySleep(50);
	    	   // Reset the car (RGB) back to the origin
	    	   car1.resetTransform();
	    	   
	    	   translate += 1;
	    	   car1.carTranslate(translate, 80);
	    	   
	    	   // Redraw everything
	    	   frame.repaint();
	       }
	    }
	    
	    private static void mySleep(long ms) {
	  	   try {
	 		   Thread.sleep(ms);
	 	   } catch(InterruptedException e) {}
	     }
}