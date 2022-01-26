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

  private int distanceCarre(Point p, Point q) {
    return (p.x - q.x)*(p.x - q.x) + (p.y-q.y)*(p.y-q.y);
  }
  // calculDiametre: ArrayList<Point> --> Line
  //   renvoie une paire de points de la liste, de distance maximum.
  public Line calculDiametre(ArrayList<Point> points) {
    if (points.size()<3) {
      return null;
    }

    Point p=points.get(0);
    Point q=points.get(1);

    /*******************
     * PARTIE A ECRIRE *
     *******************/
    for (int i = 0;i < points.size();i++) {
      for (int j = 0;j < points.size();j++) {
        if (distanceCarre(p,q) < distanceCarre(points.get(i), points.get(j))) {
          p = points.get(i);
          q = points.get(j);
        }
      }
    }

    return new Line(p,q);
  }

  private List<Integer> findCircle(int x1, int y1,
                         int x2, int y2,
                         int x3, int y3)
  {
    int x12 = x1 - x2;
    int x13 = x1 - x3;

    int y12 = y1 - y2;
    int y13 = y1 - y3;

    int y31 = y3 - y1;
    int y21 = y2 - y1;

    int x31 = x3 - x1;
    int x21 = x2 - x1;

    // x1^2 - x3^2
    int sx13 = (int)(Math.pow(x1, 2) -
            Math.pow(x3, 2));

    // y1^2 - y3^2
    int sy13 = (int)(Math.pow(y1, 2) -
            Math.pow(y3, 2));

    int sx21 = (int)(Math.pow(x2, 2) -
            Math.pow(x1, 2));

    int sy21 = (int)(Math.pow(y2, 2) -
            Math.pow(y1, 2));

    int f = ((sx13) * (x12)
            + (sy13) * (x12)
            + (sx21) * (x13)
            + (sy21) * (x13))
            / (2 * ((y31) * (x12) - (y21) * (x13)));
    int g = ((sx13) * (y12)
            + (sy13) * (y12)
            + (sx21) * (y13)
            + (sy21) * (y13))
            / (2 * ((x31) * (y12) - (x21) * (y13)));

    int c = -(int)Math.pow(x1, 2) - (int)Math.pow(y1, 2) -
            2 * g * x1 - 2 * f * y1;

    // eqn of circle be x^2 + y^2 + 2*g*x + 2*f*y + c = 0
    // where centre is (h = -g, k = -f) and radius r
    // as r^2 = h^2 + k^2 - c
    int h = -g;
    int k = -f;
    int sqr_of_r = h * h + k * k - c;

    // r is the radius
    List<Integer> lst = new ArrayList<Integer>();

    lst.add(k);
    lst.add(h);
    lst.add(sqr_of_r);
    return lst;
  }

  // calculCercleMin: ArrayList<Point> --> Circle
  //   renvoie un cercle couvrant tout point de la liste, de rayon minimum.
  public Circle calculCercleMin(ArrayList<Point> inputPoints) {
    ArrayList<Point> points = (ArrayList<Point>) inputPoints.clone();
    //ArrayList<Point> points = exercice2(inputPoints);
    System.out.println(inputPoints.size());
    System.out.println(points.size());
    if (points.size()<1) return null;
    double cX,cY,cRadius,cRadiusSquared;
    for (Point p: points){
      for (Point q: points){
        cX = .5*(p.x+q.x);
        cY = .5*(p.y+q.y);
        cRadiusSquared = 0.25*((p.x-q.x)*(p.x-q.x)+(p.y-q.y)*(p.y-q.y));
        boolean allHit = true;
        for (Point s: points)
          if ((s.x-cX)*(s.x-cX)+(s.y-cY)*(s.y-cY)>cRadiusSquared){
            allHit = false;
            break;
          }
        if (allHit) return new Circle(new Point((int)cX,(int)cY),(int)Math.sqrt(cRadiusSquared));
      }
    }
    double resX=0;
    double resY=0;
    double resRadiusSquared=Double.MAX_VALUE;
    for (int i=0;i<points.size();i++){
      for (int j=i+1;j<points.size();j++){
        for (int k=j+1;k<points.size();k++){
          Point p=points.get(i);
          Point q=points.get(j);
          Point r=points.get(k);
          //si les trois sont colineaires on passe
          if ((q.x-p.x)*(r.y-p.y)-(q.y-p.y)*(r.x-p.x)==0) continue;
          //si p et q sont sur la meme ligne, ou p et r sont sur la meme ligne, on les echange
          if ((p.y==q.y)||(p.y==r.y)) {
            if (p.y==q.y){
              p=points.get(k); //ici on est certain que p n'est sur la meme ligne de ni q ni r
              r=points.get(i); //parce que les trois points sont non-colineaires
            } else {
              p=points.get(j); //ici on est certain que p n'est sur la meme ligne de ni q ni r
              q=points.get(i); //parce que les trois points sont non-colineaires
            }
          }
          //on cherche les coordonnees du cercle circonscrit du triangle pqr
          //soit m=(p+q)/2 et n=(p+r)/2
          double mX=.5*(p.x+q.x);
          double mY=.5*(p.y+q.y);
          double nX=.5*(p.x+r.x);
          double nY=.5*(p.y+r.y);
          //soit y=alpha1*x+beta1 l'equation de la droite passant par m et perpendiculaire a la droite (pq)
          //soit y=alpha2*x+beta2 l'equation de la droite passant par n et perpendiculaire a la droite (pr)
          double alpha1=(q.x-p.x)/(double)(p.y-q.y);
          double beta1=mY-alpha1*mX;
          double alpha2=(r.x-p.x)/(double)(p.y-r.y);
          double beta2=nY-alpha2*nX;
          //le centre c du cercle est alors le point d'intersection des deux droites ci-dessus
          cX=(beta2-beta1)/(double)(alpha1-alpha2);
          cY=alpha1*cX+beta1;
          cRadiusSquared=(p.x-cX)*(p.x-cX)+(p.y-cY)*(p.y-cY);
          if (cRadiusSquared>=resRadiusSquared) continue;
          boolean allHit = true;
          for (Point s: points)
            if ((s.x-cX)*(s.x-cX)+(s.y-cY)*(s.y-cY)>cRadiusSquared){
              allHit = false;
              break;
            }
          if (allHit) {System.out.println("Found r="+Math.sqrt(cRadiusSquared));resX=cX;resY=cY;resRadiusSquared=cRadiusSquared;}
        }
      }
    }
    return new Circle(new Point((int)resX,(int)resY),(int)Math.sqrt(resRadiusSquared));
  }

  private ArrayList<Point> exercice2(ArrayList<Point> points){
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


  public Circle calculCercleMinv2(ArrayList<Point> points) {
    System.out.println(points.size());
    points = (ArrayList<Point>) exercice2(points).clone();
    System.out.println(points.size());
    if (points.isEmpty()) {
      return null;
    }

    Point center=points.get(0), tmp_center;

    int radius=100, tmp_radius;
    boolean estCouvre = true;

    /*******************
     * PARTIE A ECRIRE *
     *******************/
    for (Point p: points) {
      for (Point q: points) {
        if (p.equals(q))
          continue;
        tmp_center = new Point((p.x + q.x) / 2, (p.y + q.y) / 2);
        tmp_radius = distanceCarre(p, q);
        estCouvre = true;
        for (Point k: points) {
          if (distanceCarre(k, center) > radius) {
            estCouvre = false;
            break;
          }
        }
        if (estCouvre && radius > tmp_radius) {
          /*System.out.println(center);
          System.out.println((int) Math.sqrt(radius));
          return new Circle(center, (int) Math.sqrt(radius));*/
          center = tmp_center;
          radius = tmp_radius;
        }
      }
    }
    List<Integer> lst = new ArrayList<Integer>();
    Point pcenter = center;
    int pradius=radius;
    Point p1,p2,p3;
    for (int i = 0;i < points.size();i++) {
      for (int j = i+1; j < points.size(); j++) {
        for (int k = j+1; k < points.size();k++) {
          p1 = points.get(i);
          p2 = points.get(j);
          p3 = points.get(k);

          if ((p2.x-p1.x)*(p3.y-p1.y)-(p2.y-p1.y)*(p3.x-p1.x)==0) continue;
          //si p et q sont sur la meme ligne, ou p et r sont sur la meme ligne, on les echange
          if ((p1.y==p2.y)||(p1.y==p3.y)) {
            if (p1.y==p2.y){
              p1=points.get(k); //ici on est certain que p n'est sur la meme ligne de ni q ni r
              p3=points.get(i); //parce que les trois points sont non-colineaires
            } else {
              p1=points.get(j); //ici on est certain que p n'est sur la meme ligne de ni q ni r
              p2=points.get(i); //parce que les trois points sont non-colineaires
            }
          }

          lst = findCircle(p1.x,p1.y,p2.x,p2.y,p3.x,p3.y);
          if (lst.size() != 3)
            continue;
          pcenter = new Point(lst.get(0), lst.get(1));
          pradius = lst.get(2);
          estCouvre = true;
          for (int h = 0; h < points.size();h++) {
            if (distanceCarre(points.get(h), pcenter) > pradius) {
              estCouvre = false;
              break;
            }
          }
          if (estCouvre && pradius < radius) {
            /*System.out.println(pcenter);
            System.out.println(pradius);
            return new Circle(pcenter, (int) Math.sqrt(pradius));*/
            radius = pradius;
            center = pcenter;
          }
        }
      }
    }
    System.out.println(center);
    System.out.println((int) Math.sqrt(radius));
    return new Circle(center, (int) Math.sqrt(radius));
  }
}
