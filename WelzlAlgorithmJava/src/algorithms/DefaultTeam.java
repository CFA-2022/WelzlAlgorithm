package algorithms;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/***************************************************************
 * TME 1: calcul de diamètre et de cercle couvrant minimum.    *
 *   - Trouver deux points les plus éloignés d'un ensemble de  *
 *     points donné en entrée.                                 *
 *   - Couvrir l'ensemble de poitns donné en entrée par un     *
 *     cercle de rayon minimum.                                *
 *                                                             *
 * class Circle:                                               *
 *   - Circle(Point c, int r) constructs a new circle          *
 *     centered at c with radius r.                            *
 *   - Point getCenter() returns the center point.             *
 *   - int getRadius() returns the circle radius.              *
 *                                                             *
 * class Line:                                                 *
 *   - Line(Point p, Point q) constructs a new line            *
 *     starting at p ending at q.                              *
 *   - Point getP() returns one of the two end points.         *
 *   - Point getQ() returns the other end point.               *
 ***************************************************************/
import supportGUI.Circle;
import supportGUI.Line;

public class DefaultTeam {

  // calculDiametre: ArrayList<Point> --> Line
  //   renvoie une pair de points de la liste, de distance maximum.
  public Line calculDiametre(ArrayList<Point> points) {
    if (points.size()<3) {
      return null;
    }

    Point p=points.get(0);
    Point q=points.get(1);

    /*******************
     * PARTIE A ECRIRE *
     *******************/
    return new Line(p,q);
  }

  // calculCercleMin: ArrayList<Point> --> Circle
  //   renvoie un cercle couvrant tout point de la liste, de rayon minimum.
  public Circle calculCercleMin(ArrayList<Point> inputPoints) {
    //return NaifAlgorithm.naifAlgorithm(inputPoints);
    //return WelzlAlgorithm.welzlAlgorithm(inputPoints);

    //Circle C = NaifAlgorithm.naifAlgorithm(inputPoints);
    Circle C = WelzlAlgorithm.welzlAlgorithm(inputPoints);
    System.out.println(C.getCenter());
    System.out.println(C.getRadius());
    return C;
  }

  private ArrayList<Point> filtragePoints(ArrayList<Point> points){
    if (points.size()<4) return points;
    int maxX=points.get(0).x;
    for (Point p: points) if (p.x>maxX) maxX=p.x;
    Point[] maxY = new Point[maxX+1];
    Point[] minY = new Point[maxX+1];
    for (Point p: points) {
      if (maxY[p.x]==null||p.y>maxY[p.x].y) maxY[p.x]=p;
      if (minY[p.x]==null||p.y<minY[p.x].y) minY[p.x]=p;
    }
    ArrayList<Point> result = new ArrayList<Point>();
    for (int i=0;i<maxX+1;i++) if (maxY[i]!=null) result.add(maxY[i]);
    for (int i=maxX;i>=0;i--) if (minY[i]!=null && !result.get(result.size()-1).equals(minY[i])) result.add(minY[i]);

    if (result.get(result.size()-1).equals(result.get(0))) result.remove(result.size()-1);

    return result;
  }

}
