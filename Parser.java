import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Parser {
  public static void main(String[] args)
  {
    readFile("C:\\Users\\Max\\Documents\\Misc Academics\\CS 446\\sampleconfig.txt", "ads");
  }
  
  public static boolean readFile(String filename, String section)
  {
    boolean startReading = false;
    try {
      File myFile = new File(filename);
      Scanner myConfig = new Scanner(myFile);
      String line;
    while (myConfig.hasNext())
    {
      line = myConfig.nextLine();
      //System.out.println(line);
      if (line.contains("new service: " + section)) {
        startReading = true;
      }
      if (startReading) {
        if (line.contains("new service: ") && !(line.contains("new service: " + section))){
          startReading = false;
        }
        else {
          System.out.println(line);
        }
      }
    }
    myConfig.close();
    return true;
    } 
    catch (IOException ex) {
      System.err.println("IO Exception: " + ex.getMessage());
    }
    return false;
  }
}
