import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Iterator;
import java.util.Vector;

/**
 * Zeichnet ein Auto an einer gewünschten Stelle in beliebieger Position.
 * Die affinen Transformationen werden in der Reihenfolge ihrer Definition ausgeführt. 
 */
public class Car {
	// Alle aktiven Transformationen
	private Vector<AffineTransform> aff = new Vector<AffineTransform>();

	/**
	 * Kreiert ein Auto. Dabei wird die linke obere Ecke angegeben.
	 * 
	 * @param x   x Koordinate des Autos
	 * @param y   y Koordinate des Autos
	 */
	public Car(int x, int y) {
		aff.add(AffineTransform.getTranslateInstance(x, y));
	}

	/**
	 * Führt eine Translation um x,y aus.
	 * 
	 * @param x   Translation in x-Richtung
	 * @param y   Translation in y-Richtung
	 */
	public void carTranslate(int x, int y) {
		aff.add(AffineTransform.getTranslateInstance(x, y));
	}
	/**
	 * Führt eine Rotation um den Punkt x,y und dem Winkel alpha aus.
	 * 
	 * @param alpha   Rotationswinkel
	 * @param x       x-Koordinate des Drehpunktes
	 * @param y		  y-Koordinate des Drehpunktes
	 */
	public void carRotate(double alpha, int x, int y) {
		aff.add(AffineTransform.getRotateInstance(alpha, x, y));
	}

	/**
	 * Führt eine beliebige affine Transformation durch
	 * 
	 * @param aft   gewünschte affine Transformation
	 */
	public void setTransform(AffineTransform aft) {
		aff.add(aft);
	}

	/**
	 * Entfernt eine affine Transformation wieder
	 * 
	 * @param aft  die affine Transformation die entfernt werden soll.
	 */
	public void removeTransform(AffineTransform aft) {
		aff.remove(aft);
	}

	/**
	 * Alle affinen Transformationen werden entfernt. Das Auto befindet sich wieder
	 * an der Position 0,0 in ursprünglicher Grösse.
	 */
	public void resetTransform() {
		aff.clear();
	}

	/**
	 * Drawing of the elements
	 * 
	 * @param g2 The graphics context
	 */
	public void draw(Graphics2D g2) {
		Rectangle body = new Rectangle(0, 60, 20, 60);

		// Ausführen der vorhandenen Transformationen
		AffineTransform saveAT = g2.getTransform();
		if (aff.size() > 0) {
			Iterator<AffineTransform> ita = aff.iterator();
			while (ita.hasNext()) {
				g2.transform(ita.next());
			}
		}

		// Zeichen der einzelnen Komponenten des Körpers
		g2.draw(body);

		// Zurücksetzen der Transformationen
		if (aff.size() > 0) {
			g2.setTransform(saveAT);
		}
	}
}