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
            CompetitorScoresList competitorScoresList = new CompetitorScoresList("CompetitorScores.csv",
                    competitorList.getCompetitors());

            // Create an instance of Result
            Result result = new Result(0, null, null, null);

            // Finding the competitor with the highest overall score
            Competitor highestScorer = result.getCompetitorWithHighestScore(
                    competitorScoresList.getCompetitorScoresList(), competitorList.getCompetitors());

            // Calculating summary statistics
            int maxOverallScore = result.getMaxOverallScore(competitorScoresList.getCompetitorScoresList());
            int minOverallScore = result.getMinOverallScore(competitorScoresList.getCompetitorScoresList());

            // Generating a frequency report
            Map<Integer, Integer> frequencyReport = result
                    .getFrequencyOfScores(competitorScoresList.getCompetitorScoresList());

            // Writing the report to a file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                writer.write("Competitors Table:\n");
                writer.write("-------------------------------------------------------\n");
                competitorList.displayCompetitors(writer);

                writer.write("\nDetails of the Competitor with the Highest Overall Score: \n"
                        + highestScorer.getName().getFullName() + "\n");

                writer.write("-------------------------------------------------------\n");
                // ... display details of the highest scorer as in the previous example ...

                writer.write("\nSummary Statistics:\n");
                writer.write("-------------------------------------------------------\n");
                writer.write("Maximum Overall Score: " + maxOverallScore + "\n");
                writer.write("Minimum Overall Score: " + minOverallScore + "\n");

                writer.write("\nFrequency Report: " + frequencyReport + "\n");

                writer.write("-------------------------------------------------------\n");

                System.out.println("Report generated successfully. Check the file: " + fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}