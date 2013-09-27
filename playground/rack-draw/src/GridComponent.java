import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JComponent;

/*
   A component that draws rectangles.
*/
public class GridComponent extends JComponent {
	private static final long serialVersionUID = -5924855970968763632L;

public void paintComponent(Graphics g) {  
      // Recover Graphics2D
      Graphics2D g2 = (Graphics2D) g;
      // Col
      int m = 15;
      // Rows
      int n = 6;
      for (int i = 1; i <= m; i++) {
	      for (int j = 1; j <= n; j++) {
	    	  Rectangle box = new Rectangle((35*i), 10 + (35*j), 30, 30);
	          g2.draw(box);
	          int number = i*j;
	          g2.setColor(Color.BLACK);
	          g2.drawString(" " + number, (35*i), 40 + (35*j));
	      }
      }
      
/*      g2.setColor(Color.GREEN);
	  Rectangle inout = new Rectangle(0, 20+10+(35*n), 30, 10);
      g2.fill(inout);
      g2.draw(inout);*/
      
      //g2.setColor(Color.BLUE);
      //g2.drawString("Hello, World!", 5, 175);
      
      // Construct a rectangle and draw it
/*      Rectangle box1 = new Rectangle(35, 10, 30, 30);
      g2.draw(box1);

      Rectangle box2 = new Rectangle(35, 45, 30, 30);
      g2.draw(box2);
      
      Rectangle box3 = new Rectangle(35, 80, 30, 30);
      g2.draw(box3);*/
      
      // 
/*      box.translate(0, 35);
      g2.draw(box);

      box.translate(0, 35);
      g2.draw(box);

      box.translate(0, 35);
      g2.draw(box);
      
      box.translate(35, 0);
      g2.draw(box);

      box.translate(35, 0);
      g2.draw(box);
      
      box.translate(35, 0);
      g2.draw(box);*/
   }
}