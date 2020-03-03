package simulator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * the class Main is the start of the simulation where data is read from the input files with
 * the two methods readConfigFile , readDatarequests
 */
public class Main {

    public static int nbfloors;
    public static int nblifts;
    public static int capacity;
    public static int velocity;

    public static ArrayList<int[]> datarequest = new ArrayList<int[]>();

    /**
     * the main method is the first method that run when the app is launched from the command line
      * @param args parameter read from the command line , we might use this list of parameters to get the path of the file conatining the data
     *
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter the name of the file containing the data  ");
        String dataFileName = sc.next();
        readConfigFile();
        readDatarequests(dataFileName);

        Controller c = new Controller(nbfloors,nblifts,capacity,velocity);

    }

    /**
     * this method reads the data from config.txt file and store the values in static variables of the Main class
     */
    public static void readConfigFile() {
        try {
            Scanner input;
            File file = new File("config.txt");
            input = new Scanner(file);

            nbfloors = Integer.parseInt(input.nextLine().replaceAll("[^0-9]", ""));
            nblifts = Integer.parseInt(input.nextLine().replaceAll("[^0-9]", ""));
            capacity = Integer.parseInt(input.nextLine().replaceAll("[^0-9]", ""));
            velocity = Integer.parseInt(input.nextLine().replaceAll("[^0-9]", ""));

            input.close();

        } catch (FileNotFoundException e1) {
            System.out.println("config.txt file does not exist in the project directory");
        }
        catch (NumberFormatException e2)
        {
            System.out.println("the data in the config.txt file does not respect the appropriate format ");

        }
    }

    /**
     * this method reads the data (request) that has the format : id, step, startfloor, endfloor from from a file
     * @param DataFileName  name of the file which contains the data
     */
    public static void readDatarequests(String DataFileName) {
        try {
            System.out.println("reading data");
            Scanner input;
            File file = new File(DataFileName);
            input = new Scanner(file);
            String [] x;
            while (input.hasNext())
            {
                x= input.nextLine().split("\\D+");
                int[] intarray=new int[x.length];
                for(int i=0;i<x.length;i++)
                {
                    intarray[i]=Integer.parseInt(x[i]);
                   // System.out.println(x[i]);
                }
                for(int i = 0 ;i<intarray.length;i++) System.out.print(intarray[i]+" ");
                datarequest.add(intarray);
            }
        }
        catch (FileNotFoundException e1) {
            System.out.println("the file containing the data does not exist in the project directory");
        }
        catch (NumberFormatException e2)
        {
            System.out.println("the data in the data file does not respect the appropriate format ");
        }
    }
}
