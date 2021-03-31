package tudelft.mysolution;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DataToCSV {

    private static String path;

    public static void writeToFile(int[] averageOfTrials, String fileName) {
        path = fileName + ".csv";
        File file = new File(path);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try (FileWriter writer = new FileWriter(file); BufferedWriter bw = new BufferedWriter(writer)) {
            String msg = "Id, Value \n";
            bw.write(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < averageOfTrials.length; i++) {
            appendValueToFile(i+1, averageOfTrials[i]);
        }
    }

    private static void appendValueToFile(int i, int averageOfTrial) {
        File file = new File(path);

        try (FileWriter writer = new FileWriter(file, true); BufferedWriter bw = new BufferedWriter(writer)) {
            String msg = i + ", " + averageOfTrial + "\n";
            bw.write(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToFile(Double[] foundRewards, String fileName) {
        path = fileName + ".csv";
        File file = new File(path);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try (FileWriter writer = new FileWriter(file); BufferedWriter bw = new BufferedWriter(writer)) {
            String msg = "Id, Value \n";
            bw.write(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < foundRewards.length; i++) {
            appendValueToFile(i+1, foundRewards[i]);
        }

    }

    private static void appendValueToFile(int i, double reward) {
        File file = new File(path);

        try (FileWriter writer = new FileWriter(file, true); BufferedWriter bw = new BufferedWriter(writer)) {
            String msg = i + ", " + reward + "\n";
            bw.write(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
