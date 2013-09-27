import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Iterator;
import java.util.Vector;

/**
   A grid shape that can be positioned anywhere on the screen.
*/
public class Grid {
   private int xLeft;
   private int yTop;
   
   /**
      Create a grid.
      
      @param x x coordinate
      @param y y coordinate
   */
   public Grid(int x, int y)
   {
      xLeft = x;
      yTop = y;
   }

    /**
       Draws the unit.
       @param g2 the graphics context
    */
    public void draw(Graphics2D g2)
    {
       Rectangle body = new Rectangle(xLeft, yTop + 10, 60, 10);
       g2.draw(body);
    }
}