import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

/*∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗*
∗ @file: Proj4.java
∗ @description: This code takes two input arguments, a dataset and a number of lines to be analyzed.
    It then inserts the data as Anime objects into an Arraylist which is then used to test hashtable
    runtimes. It will take the Arraylist in sorted, shuffled, and reverse order, and print out runtime
    results for hashtable insert, remove, and search operations for different numbers of lines, incrementing by 100 from 100 to
    the user-specified number of lines. It will then print all runTime values in nanoseconds to analysis.csv.
∗ @author: Alli Swyt
∗ @date: November 19, 2025
∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗*/

public class Proj4 {
    public static void main(String[] args) throws IOException {
        // Use command line arguments to specify the input file
        if (args.length != 2) {
            System.err.println("Usage: java TestAvl <input file> <number of lines>");
            System.exit(1);
        }

        String inputFileName = args[0];//input file of MAL-anime.csv
        //This section just makes sure the number is within bounds for the dataset
        int numLines = 0;
        try {
            numLines = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException e) {
            System.out.println("Argument was not an integer as expected. Exiting program. Please input an integer for the first argument.");
            System.exit(3);
        }
        if (numLines <= 0 || numLines > 12773) {
            System.out.println("Argument value was out of bounds for this data set. Please input a number from 1 to 12773. Exiting program.");
            System.exit(4);
        }

        // For file input
        FileInputStream inputFileNameStream = null;
        Scanner inputFileNameScanner = null;

        // Open the input file
        inputFileNameStream = new FileInputStream(inputFileName);
        inputFileNameScanner = new Scanner(inputFileNameStream);

        // ignore first line
        inputFileNameScanner.nextLine();

        ArrayList<Anime> animeAL = new ArrayList<>();

        //Fills the arraylist (NOTE max lines is 12773)
        for (int k = 0; k < numLines; k++) {
            animeAL.add(new Anime(inputFileNameScanner.nextLine()));
        }

        //the following clears the result file if it has already been in use
        try {
            clearFile("./analysis.csv");
        } catch (IOException e) {
            System.out.println("File does not exist or error in clearing file. If file doesn't exist, it will be created after this is printed.");
        }


        //TEST runtime on Insert, Delete, and Search methods for all elements into a hashTable for different numbers of inputs

        //sortedTest
        Collections.sort(animeAL);
        writeToFile("Sorted======================================================================","./analysis.csv");
        for (int i = 100; i < numLines; i+= 100) {
            SeparateChainingHashTable<Anime> hashTable = new SeparateChainingHashTable<>(i);

            //Test Insert runTime
            long startTime = System.nanoTime();
            for (int j = 0; j < i; j++) {
                hashTable.insert(animeAL.get(j));
            }
            long endTimeInsert = System.nanoTime() - startTime;

            //Test Search runTime
            startTime = System.nanoTime();
            for (int j = 0; j < i; j++) {
                hashTable.contains(animeAL.get(j));
            }
            long endTimeSearch = System.nanoTime() - startTime;

            //Test Delete RunTime
            startTime = System.nanoTime();
            for (int j = 0; j < i; j++) {
                hashTable.remove(animeAL.get(j));
            }
            long endTimeDelete = System.nanoTime() - startTime;

            writeToFile(i + "," + endTimeInsert + "," + i + "," + endTimeSearch + "," + i + "," + endTimeDelete + ",insert,search,delete", "./analysis.csv");
        }
        //Shuffled test
        writeToFile("Shuffled======================================================================","./analysis.csv");
        Collections.shuffle(animeAL);
        for (int i = 100; i < numLines; i+= 100) {
            SeparateChainingHashTable<Anime> hashTable = new SeparateChainingHashTable<>(i);

            //Test Insert runTime
            long startTime = System.nanoTime();
            for (int j = 0; j < i; j++) {
                hashTable.insert(animeAL.get(j));
            }
            long endTimeInsert = System.nanoTime() - startTime;

            //Test Search runTime
            startTime = System.nanoTime();
            for (int j = 0; j < i; j++) {
                hashTable.contains(animeAL.get(j));
            }
            long endTimeSearch = System.nanoTime() - startTime;

            //Test Delete RunTime
            startTime = System.nanoTime();
            for (int j = 0; j < i; j++) {
                hashTable.remove(animeAL.get(j));
            }
            long endTimeDelete = System.nanoTime() - startTime;

            writeToFile(i + "," + endTimeInsert + "," + i + "," + endTimeSearch + "," + i + "," + endTimeDelete + ",insert,search,delete", "./analysis.csv");
        }
        //Reversed test
        writeToFile("Reversed======================================================================","./analysis.csv");
        Collections.sort(animeAL,Collections.reverseOrder());
        for (int i = 100; i < numLines; i+= 100) {
            SeparateChainingHashTable<Anime> hashTable = new SeparateChainingHashTable<>(i);

            //Test Insert runTime
            long startTime = System.nanoTime();
            for (int j = 0; j < i; j++) {
                hashTable.insert(animeAL.get(j));
            }
            long endTimeInsert = System.nanoTime() - startTime;

            //Test Search runTime
            startTime = System.nanoTime();
            for (int j = 0; j < i; j++) {
                hashTable.contains(animeAL.get(j));
            }
            long endTimeSearch = System.nanoTime() - startTime;

            //Test Delete RunTime
            startTime = System.nanoTime();
            for (int j = 0; j < i; j++) {
                hashTable.remove(animeAL.get(j));
            }
            long endTimeDelete = System.nanoTime() - startTime;

            writeToFile(i + "," + endTimeInsert + "," + i + "," + endTimeSearch + "," + i + "," + endTimeDelete + ",insert,search,delete", "./analysis.csv");
        }

    }

    //This method writes the input to a specified file
    public static void writeToFile(String content, String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            }
            catch (IOException e) {
                System.out.println("Error in creating the file.");
            }
        } //If the file doesn't already exist, a new one will be created

        try {
            FileWriter writer = new FileWriter(filePath, true);
            writer.write(content + "\n");
            writer.close();
        } //creates a fileWriter to write to the file and writes to the file
        catch (IOException e) {
            System.out.println("Error in writing to file.");
        } //Exception if the filewriter fails
    }

    //This method clears a specified file to be reused
    public static void clearFile(String filename) throws IOException {
        File file = new File(filename);
        if (file.exists()) {
            try (FileWriter writer = new FileWriter(filename)) {
                writer.close();
            } //This opens and closes the fileWriter to clear the file it is to write to
            catch (IOException e) {
                System.out.println("Error in clearing the file");
            }
        }
    }
}
