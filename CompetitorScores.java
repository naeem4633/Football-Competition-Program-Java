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

}