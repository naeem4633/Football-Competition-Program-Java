import java.util.ArrayList;
import java.util.Arrays;

public class CompetitorScores {

    private Competitor competitor;
    private int[] scores;

    public CompetitorScores(Competitor competitor, int[] scores) {
        this.competitor = competitor;
        this.scores = scores;
    }

    public Competitor getCompetitor() {
        return competitor;
    }

    public int[] getScores() {
        return scores;
    }

    public void setScores(int[] scores) {
        this.scores = scores;
    }

    public int getCompetitorNumber() {
        return competitor.getNumber();
    }

    public String displayScoreReport() {
        return "Competitor number " + competitor.getNumber() + ", name " +
                competitor.getName().getFullName() + ".\n" +
                competitor.getName().getFirstName() + " is a " +
                competitor.getCategory() + " and received these scores: " +
                getFormattedScores();
    }

    private String getFormattedScores() {
        StringBuilder formattedScores = new StringBuilder();
        for (int score : scores) {
            formattedScores.append(score).append(", ");
        }
        // Remove the trailing comma and space
        formattedScores.setLength(formattedScores.length() - 2);
        return formattedScores.toString();
    }

    public String getFullDetails() {

        if (competitor != null) {
            return "Full details for " + competitor.getNumber() + ":\n" +
                    "Competitor number " + competitor.getNumber() + ", name " +
                    competitor.getName().getFullName() + ". " +
                    competitor.getName().getFirstName() + " is a " +
                    competitor.getCategory() + " and received these scores: " +
                    Arrays.toString(getScores()) + "\n" +
                    "This gives him an overall score of " + getWeightedAverageScore() + ".";
        } else {
            return "Competitor scores not found.";
        }
    }

    public String getShortDetails() {
        return "CN " + competitor.getNumber() + " (" + getInitials() +
                ") has overall score " + getWeightedAverageScore() + ".";
    }

    private String getInitials() {
        // Get the first letter of each part of the name
        String firstNameInitial = String.valueOf(competitor.getName().getFirstName().charAt(0));
        String lastNameInitial = String.valueOf(competitor.getName().getLastName().charAt(0));
        return firstNameInitial + lastNameInitial;
    }

    public double getWeightedAverageScore() {
        int totalOverallScore = 0;
        int totalWeight = 0;

        int[] scores = this.getScores();
        int[] weights = { 1, 2, 3, 4, 5 };

        for (int i = 0; i < scores.length; i++) {
            totalOverallScore += scores[i] * weights[i];
            totalWeight += weights[i];
        }

        // Avoid division by zero
        if (totalWeight == 0) {
            return 0.0;
        }

        return (double) totalOverallScore / totalWeight;
    }
}