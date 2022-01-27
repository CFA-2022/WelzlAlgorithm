package algorithms;

import supportGUI.Circle;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class WelzlAlgorithm {

    private static int distanceCarre(Point p, Point q) {
        return (p.x - q.x)*(p.x - q.x) + (p.y-q.y)*(p.y-q.y);
    }

    private static Circle getCircle(Point p, Point q) {
        return new Circle(new Point((p.x+q.x)/2, (p.y+q.y)/2), (int)(Math.sqrt(distanceCarre(p,q))/2));
    }

    private static Circle getCircle(Point p, Point q, Point r) {
        //si les trois sont colineaires on passe
        if ((q.x-p.x)*(r.y-p.y)-(q.y-p.y)*(r.x-p.x)==0) return null;
        Point tmp;
        //si p et q sont sur la meme ligne, ou p et r sont sur la meme ligne, on les echange
        if ((p.y==q.y)||(p.y==r.y)) {
            if (p.y==q.y){
                tmp = p;
                p=r; //ici on est certain que p n'est sur la meme ligne de ni q ni r
                r=tmp; //parce que les trois points sont non-colineaires
            } else {
                tmp = p;
                p=q; //ici on est certain que p n'est sur la meme ligne de ni q ni r
                q=tmp; //parce que les trois points sont non-colineaires
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
        double cX=(beta2-beta1)/(double)(alpha1-alpha2);
        double cY=alpha1*cX+beta1;
        double cRadiusSquared=(p.x-cX)*(p.x-cX)+(p.y-cY)*(p.y-cY);

        return new Circle(new Point((int)cX, (int)cY), (int)Math.sqrt(cRadiusSquared));
    }

    private static boolean isPointInsideCircle(Circle C, Point p) {
        return distanceCarre(C.getCenter(), p) <= C.getRadius()*C.getRadius();
    }

    private static Circle trivial(ArrayList<Point> P, int lenP, ArrayList<Point> R, int lenR) {
        if (lenR == 3) {
            return getCircle(R.get(0), R.get(1), R.get(2));
        } else if (lenP == 1 && lenR == 0) {
            return new Circle(P.get(0), 0);
        } else if (lenP == 0 && lenR == 2) {
            return getCircle(R.get(0), R.get(1));
        } else if (lenP == 1 && lenR == 1) {
            return getCircle(R.get(0), P.get(0));
        }
        return null;
    }

    public static Circle B_MINIDISK(ArrayList<Point> Ps, ArrayList<Point> Rs) {
        Circle D;
        ArrayList<Point> P = (ArrayList<Point>) Ps.clone();
        ArrayList<Point> R = (ArrayList<Point>) Rs.clone();
        int lenR = R.size();
        int lenP = P.size();
        if(lenR == 3 || (lenP == 1 && (lenR == 0 || lenR == 1)) || (lenP == 0 && lenR == 2)) {
            D = trivial(P, lenP, R, lenR);
        } else {
            Point p = P.get(0);
            P.remove(0);
            D = B_MINIDISK(P, R);

            if (D != null && !isPointInsideCircle(D, p)) {
                R.add(p);
                D = B_MINIDISK(P, R);
            }
        }
        return D;
    }

    public static Circle welzlAlgorithm(ArrayList<Point> points) {
        Collections.shuffle(points);
        return B_MINIDISK(points, new ArrayList<Point>());
    }

}
