package Helper;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileService {

    public static ArrayList<Point> parseDataFromFile(String filePath) {
        BufferedReader reader;
        ArrayList<Point> points = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line;
            String[] coord;
            while ((line = reader.readLine()) != null) {
                coord = line.split("\\s+");
                points.add(new Point(Integer.parseInt(coord[0]), Integer.parseInt(coord[1])));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return points;
    }

    public static void writeDataFromList(ArrayList<Long> times, String filePath) {
        FileWriter fw;
        try {
          fw = new FileWriter(filePath);
          for(int i = 0;i < times.size();i++) {
              fw.write(times.get(i).toString()+"\n");
          }
          fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
