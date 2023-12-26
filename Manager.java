import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Manager {

    public static void main(String[] args) {
        generateReport("FinalReport.txt");
        amendSampleCompetitorDetails();
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

    private static void amendSampleCompetitorDetails() {
        try {
            // Create a sample Competitor for modification
            Competitor sampleCompetitor = new Competitor(3, new Name("John", "Doe"), "2000-01-01", "Category", 25,
                    "john.doe@example.com");

            // Creating a sample Officials object
            Officials officials = new Officials(1, new Name("Official", "Person"), 2);

            // Create a modified Competitor
            Competitor modifiedCompetitor = new Competitor(3, new Name("Updated", "Person"), "1995-05-05",
                    "New Category", 27, "updated.person@example.com");

            // Amend the details using Officials class
            officials.amendCompetitorDetails(sampleCompetitor, modifiedCompetitor, "Competitors.csv");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getCompetitorNumber() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter Competitor Number: ");
        try {
            return Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid competitor number.");
            return getCompetitorNumber(); // Recursive call until a valid number is entered
        }
    }

}