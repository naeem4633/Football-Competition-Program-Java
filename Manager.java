import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Manager {

    public static void main(String[] args) {
        // generateReport("FinalReport.txt");
        // displayCompetitorDetails();
        // recordSampleScore();
        // amendSampleCompetitorDetails();
        openCompetitorGUI();
    }

    private static void generateReport(String fileName) {
        try {
            CompetitorList competitorList = new CompetitorList();
            CompetitorScoresList competitorScoresList = new CompetitorScoresList(competitorList.getCompetitors());

            // Create an instance of Result
            Result result = new Result(0, null, null, null);

            // Finding the competitor with the highest overall score
            Competitor highestScorer = result.getCompetitorWithHighestScore(
                    competitorScoresList.getCompetitorScoresList(), competitorList.getCompetitors());

            // Calculating summary statistics
            int maxOverallScore = result.getMaxOverallScore(competitorScoresList.getCompetitorScoresList());
            int minOverallScore = result.getMinOverallScore(competitorScoresList.getCompetitorScoresList());
            int totalNumberOfCompetitors = result.getTotalCompetitors(competitorScoresList.getCompetitorScoresList());

            String frequencyReport = result
                    .getFrequencyOfScoresAsString(competitorScoresList.getCompetitorScoresList());

            // Writing the report to a file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                writer.write("Competitors Table:\n");
                writer.write("-------------------------------------------------------\n");
                competitorList.displayCompetitors(writer);

                writer.write(
                        "\nThere are a total of " + totalNumberOfCompetitors + " competitors in the competition.\n");

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

    private static void recordSampleScore() {
        try {
            // Assuming you have a CompetitorList and CompetitorScoresList already
            // initialized
            CompetitorList competitorList = new CompetitorList();

            // Creating a sample Staff object
            Staff staff = new Staff(1, new Name("John", "Doe"), 1);

            // Searching for a competitor by ID (replace 1 with the actual ID)
            Competitor competitor = staff.searchCompetitorById(1);

            if (competitor != null) {
                // Creating a sample CompetitorScores object with competition ID as 1
                CompetitorScores sampleScores = new CompetitorScores(competitor, new int[] { 1, 2, 3, 4, 5 });

                // Recording the sample score
                staff.recordScores(sampleScores);

                System.out.println("Sample score recorded successfully.");
            } else {
                System.out.println("Invalid competitor ID. Please try again.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void displayCompetitorDetails() {
        try {
            CompetitorList competitorList = new CompetitorList();
            CompetitorScoresList competitorScoresList = new CompetitorScoresList(competitorList.getCompetitors());

            // Read competitor number from the user
            int competitorNumber = getCompetitorNumber();

            // Get the competitor based on the entered number
            Competitor competitor = competitorList.getCompetitorByNumber(competitorNumber);

            if (competitor != null) {
                // Display short details about the competitor
                System.out.println("\nShort Details of Competitor " + competitor.getNumber() + ":");
                System.out.println("Name: " + competitor.getName().getFullName());
                System.out.println("Date of Birth: " + competitor.getDateOfBirth());
                System.out.println("Category: " + competitor.getCategory());
                System.out.println("Age: " + competitor.getAge());
                System.out.println("Email: " + competitor.getEmail());

                // Display average score of the competitor
                Result result = new Result(0, null, null, null);
                double averageScore = result.getWeightedAverageScore(competitor.getNumber(),
                        competitorScoresList.getCompetitorScoresList());

                System.out.println("Average Score: " + averageScore);
            } else {
                System.out.println("Invalid competitor number. Please try again.");
            }

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

    private static void openCompetitorGUI() {
        // Assuming you have a CompetitorList already initialized
        CompetitorList competitorList = new CompetitorList();
        CompetitorScoresList competitorScoresList = new CompetitorScoresList(competitorList.getCompetitors());
        Competition competition = new Competition(1, null, null, null, competitorList);

        // Create and display the CompetitorGUI
        CompetitorGUI competitorGUI = new CompetitorGUI(competitorList, competitorScoresList, competition);
        competitorGUI.createAndShowRoleSelection();
    }

    private static void displayCompetitorDetails(Competitor competitor) {
        System.out.println("Name: " + competitor.getName().getFullName());
        System.out.println("Date of Birth: " + competitor.getDateOfBirth());
        System.out.println("Category: " + competitor.getCategory());
        System.out.println("Age: " + competitor.getAge());
        System.out.println("Email: " + competitor.getEmail());
        System.out.println("-------------------------------------------------------");
    }
}