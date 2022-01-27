package algorithms;

import java.awt.Point;
import java.util.ArrayList;

import Helper.FileService;

public class AlgoService {

  public final int NB_TESTS = 1664;

  public void baseTest_AlgoWelzl() {
    ArrayList<Long> times = new ArrayList<>();
    int count = 1;
    long t;
    while(count <= NB_TESTS) {
      ArrayList<Point> points = FileService.parseDataFromFile("BaseTest/test-" + count + ".txt");

      t = System.currentTimeMillis();
      WelzlAlgorithm.welzlAlgorithm(points);
      t = System.currentTimeMillis() - t;
      times.add(t);
      count++;
    }
    FileService.writeDataFromList(times, "algo_welzl_results.txt");
  }

  public void baseTest_AlgoNaif() {
    ArrayList<Long> times = new ArrayList<>();
    int count = 1;
    long t;
    while(count <= NB_TESTS) {
      ArrayList<Point> points = FileService.parseDataFromFile("BaseTest/test-" + count + ".txt");

      t = System.currentTimeMillis();
      NaifAlgorithm.naifAlgorithm(points);
      t = System.currentTimeMillis() - t;
      times.add(t);
      count++;
    }
    FileService.writeDataFromList(times, "algo_naif_results.txt");
  }
}
