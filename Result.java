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

    public void generateSummaryReport() {
        // Implement summary report generation logic
    }

    public void generateDetailedSummaryReport() {
        // Implement detailed summary report generation logic
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

        // Now, use the winnerID to find the competitor's details in the Competitors
        // list
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

    // Find the average overall score of a single competitor across all competitions
    public double getAverageScore(int competitorNumber, ArrayList<CompetitorScores> competitorScoresList) {
        int totalOverallScore = 0;
        int totalCompetitions = 0;

        for (CompetitorScores competitorScores : competitorScoresList) {
            if (competitorScores.getCompetitor().getNumber() == competitorNumber) {
                int[] scores = competitorScores.getScores();
                int totalScore = calculateTotalScore(scores);
                totalOverallScore += totalScore;
                totalCompetitions++;
            }
        }

        // Avoid division by zero
        if (totalCompetitions == 0) {
            return 0.0;
        }

        return (double) totalOverallScore / totalCompetitions;
    }

    private int calculateTotalScore(int[] scores) {
        int totalScore = 0;
        for (int i = 2; i < scores.length; i++) {
            totalScore += scores[i];
        }
        return totalScore;
    }
}