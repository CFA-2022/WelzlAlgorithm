package algorithms;

import supportGUI.Circle;
import supportGUI.DiamRace;
import supportGUI.FramedDiamRace;
import supportGUI.Variables;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class WelzlAlgorithm {

    private static int randomIndex(int minIndex, int maxIndex) {
        return (int)(Math.random() * (maxIndex - minIndex)) + minIndex;
    }

    private static int distanceCarre(Point p, Point q) {
        return (p.x - q.x)*(p.x - q.x) + (p.y-q.y)*(p.y-q.y);
    }

    private static Point getCenterOfCirconscrit(int bx, int by, int cx, int cy) {
        int B = bx*bx + by*by;
        int C = cx*cx + cy*cy;
        int D = bx*cy - by*cx;

        return new Point((cy*B - by*C)/(2*D), (bx*C - cx*B)/(2*D));
    }

    private static Circle getCircle(Point p, Point q) {
        return new Circle(new Point((p.x+q.x)/2, (p.y+q.y)/2), (int)(Math.sqrt(distanceCarre(p,q))/2));
    }

    private static Circle getCircle(Point p, Point q, Point r) {
        Point I = getCenterOfCirconscrit(q.x-p.x, q.y-p.y, r.x-p.x, r.y-p.y);

        I.x += p.x;
        I.y += q.y;
        return new Circle(I, (int)(Math.sqrt(distanceCarre(I, p)/2)));
    }

    private static boolean isCircleValid(Circle C, ArrayList<Point> P) {
        for(Point p: P) {
            if (isPointInsideCircle(C, p))
                return false;
        }
        return true;
    }

    private static Circle trivial(ArrayList<Point> R, ArrayList<Point> P) {
        int lengthOfR = R.size();
        if (lengthOfR == 0) {
            return new Circle(new Point(0,0), 0);
        }
        if (lengthOfR == 1) {
            return new Circle(R.get(0), 0);
        }
        if (lengthOfR == 2) {
            return getCircle(R.get(0), R.get(1));
        }

        for(int i = 0;i < 3;i++) {
            for(int j = i + 1;j < 3;j++) {
                Circle C = getCircle(R.get(i), R.get(j));
                if (isCircleValid(C, P)) {
                    return C;
                }
            }
        }
        return getCircle(R.get(0), R.get(1), R.get(2));
    }

    private static boolean isPointInsideCircle(Circle C, Point p) {
        //return distanceCarre(C.getCenter(), p) <= C.getRadius()*C.getRadius();
        return Math.sqrt(distanceCarre(C.getCenter(), p)) <= C.getRadius();
    }

    public static Circle B_MINIDISK(ArrayList<Point> P, ArrayList<Point> R) {
        System.out.println(R.size());
        if (P.size() == 0 || R.size() == 3) {
            return trivial(R, P);
        }

        int rIndex = randomIndex(0,P.size());
        Point p = P.get(rIndex);
        P.remove(rIndex);
        Circle D = B_MINIDISK(P, R);

        if (isPointInsideCircle(D, p)) {
            return D;
        }

        R.add(p);

        return B_MINIDISK(P, R);
    }

    public static Circle welzlAlgorithm(ArrayList<Point> points) {
        ArrayList<Point> inputPoints = (ArrayList<Point>) points.clone();
        return B_MINIDISK(inputPoints, new ArrayList<Point>());
    }

    // QuickTest
    /*
    public static void main(String[] args) {

    }
    */

}
