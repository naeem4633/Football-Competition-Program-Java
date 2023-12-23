import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Manager {

    public static void main(String[] args) {
        generateReport("FinalReport.txt");
    }

    private static void generateReport(String fileName) {
        try {
            CompetitorList competitorList = new CompetitorList("Competitors.csv");

            // Writing the report to a file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                writer.write("Competitors Table:\n");
                writer.write("-------------------------------------------------------\n");
                competitorList.displayCompetitors(writer);

                writer.write("-------------------------------------------------------\n");

                System.out.println("Report generated successfully. Check the file: " + fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}