import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Result {
    private int ID;
    private Competition competition;
    private Competitor winner;
    private CompetitorScores winnerScores;

    public Result(int ID, Competition competition, Competitor winner, CompetitorScores winnerScores) {
        this.ID = ID;
        this.competition = competition;
        this.winner = winner;
        this.winnerScores = winnerScores;
    }

    public int getID() {
        return ID;
    }

    public Competition getCompetition() {
        return competition;
    }

    public Competitor getWinner() {
        return winner;
    }

    public CompetitorScores getWinnerScores() {
        return winnerScores;
    }

    public void generateSummaryReport(CompetitorList competitorList, CompetitorScoresList competitorScoresList,
            String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            // Finding the competitor with the highest overall score
            Competitor highestScorer = getCompetitorWithHighestScore(
                    competitorScoresList.getCompetitorScoresList(), competitorList.getCompetitors());

            // Calculating summary statistics
            int maxOverallScore = getMaxOverallScore(competitorScoresList.getCompetitorScoresList());
            int minOverallScore = getMinOverallScore(competitorScoresList.getCompetitorScoresList());
            int totalNumberOfCompetitors = getTotalCompetitors(competitorScoresList.getCompetitorScoresList());

            String frequencyReport = getFrequencyOfScoresAsString(competitorScoresList.getCompetitorScoresList());

            writer.write("Competitors Table:\n");
            writer.write("-------------------------------------------------------\n");
            competitorList.displayCompetitors(writer);

            writer.write("\nThere are a total of " + totalNumberOfCompetitors + " competitors in the competition.\n");

            writer.write("\nDetails of the Competitor with the Highest Overall Score: \n"
                    + highestScorer.getName().getFullName() + "\n");

            writer.write("-------------------------------------------------------\n");

            writer.write("\nSummary Statistics:\n");
            writer.write("-------------------------------------------------------\n");
            writer.write("Maximum Overall Score: " + maxOverallScore + "\n");
            writer.write("Minimum Overall Score: " + minOverallScore + "\n");

            writer.write("\nFrequency Report: " + frequencyReport + "\n");

            writer.write("-------------------------------------------------------\n");

            System.out.println("Report generated successfully. Check the file: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Find the competitor with the highest overall score
    public Competitor getCompetitorWithHighestScore(ArrayList<CompetitorScores> competitorScoresList,
            ArrayList<Competitor> competitorsList) {
        int highestScore = Integer.MIN_VALUE;
        int winnerID = -1;

        for (CompetitorScores competitorScores : competitorScoresList) {
            int competitorID = competitorScores.getCompetitor().getNumber();
            int totalScore = calculateTotalScore(competitorScores.getScores());

            if (totalScore > highestScore) {
                highestScore = totalScore;
                winnerID = competitorID;
            }
        }

        for (Competitor competitor : competitorsList) {
            if (competitor.getNumber() == winnerID) {
                // Create and return the Competitor object with the winner's details
                return new Competitor(competitor.getNumber(), competitor.getName(),
                        competitor.getDateOfBirth(), competitor.getCategory(), competitor.getAge(),
                        competitor.getEmail());
            }
        }

        return null;
    }

    // Find the maximum overall score among all competitors
    public int getMaxOverallScore(ArrayList<CompetitorScores> competitorScoresList) {
        int maxOverallScore = Integer.MIN_VALUE;

        for (CompetitorScores competitorScores : competitorScoresList) {
            int totalScore = calculateTotalScore(competitorScores.getScores());
            maxOverallScore = Math.max(maxOverallScore, totalScore);
        }

        return maxOverallScore;
    }

    public int getMinOverallScore(ArrayList<CompetitorScores> competitorScoresList) {
        int minOverallScore = Integer.MAX_VALUE;

        for (CompetitorScores competitorScores : competitorScoresList) {
            int totalScore = calculateTotalScore(competitorScores.getScores());
            minOverallScore = Math.min(minOverallScore, totalScore);
        }

        return minOverallScore;
    }

    public String getFrequencyOfScoresAsString(ArrayList<CompetitorScores> competitorScoresList) {
        Map<Integer, Integer> frequencyMap = getFrequencyOfScores(competitorScoresList);
        StringBuilder result = new StringBuilder();

        result.append("The following individual scores were awarded:\n");
        result.append(String.format("%-10s", "Score:"));

        // Display the scores
        for (int score = 1; score <= 5; score++) {
            result.append(String.format("%-5d", score));
        }

        result.append("\n").append(String.format("%-10s", "Frequency:"));

        // Display the frequency of each score
        for (int score = 1; score <= 5; score++) {
            result.append(String.format("%-5d", frequencyMap.getOrDefault(score, 0)));
        }

        return result.toString();
    }

    // Find the number of times each individual score was given
    public Map<Integer, Integer> getFrequencyOfScores(ArrayList<CompetitorScores> competitorScoresList) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        for (CompetitorScores competitorScores : competitorScoresList) {
            int[] scores = competitorScores.getScores();
            for (int score : scores) {
                frequencyMap.put(score, frequencyMap.getOrDefault(score, 0) + 1);
            }
        }

        return frequencyMap;
    }

    public int getTotalCompetitors(ArrayList<CompetitorScores> competitorScoresList) {
        Map<Competitor, Boolean> competitorsMap = new HashMap<>();

        for (CompetitorScores competitorScores : competitorScoresList) {
            Competitor competitor = competitorScores.getCompetitor();
            competitorsMap.put(competitor, true);
        }

        return competitorsMap.size();
    }

    public double getWeightedAverageScore(int competitorNumber, ArrayList<CompetitorScores> competitorScoresList) {
        int totalOverallScore = 0;
        int totalWeight = 0;

        for (CompetitorScores competitorScores : competitorScoresList) {
            if (competitorScores.getCompetitor().getNumber() == competitorNumber) {
                int[] scores = competitorScores.getScores();
                int totalScore = calculateWeightedScore(scores);
                totalOverallScore += totalScore;
                totalWeight += scores.length; // Assuming each score has a weight
            }
        }

        // Avoid division by zero
        if (totalWeight == 0) {
            return 0.0;
        }

        return (double) totalOverallScore / totalWeight;
    }

    // Helper method to calculate the weighted score
    private int calculateWeightedScore(int[] scores) {
        int totalScore = 0;

        // Define weights for each score
        int[] weights = { 1, 2, 3, 4, 5 };

        // Calculate the weighted score
        for (int i = 0; i < scores.length; i++) {
            totalScore += scores[i] * weights[i];
        }

        return totalScore;
    }

    private int calculateTotalScore(int[] scores) {
        int totalScore = 0;
        for (int i = 2; i < scores.length; i++) {
            totalScore += scores[i];
        }
        return totalScore;
    }
}