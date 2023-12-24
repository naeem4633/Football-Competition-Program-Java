import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CompetitorScoresList {

    private ArrayList<CompetitorScores> competitorScoresList;

    public CompetitorScoresList(String scoresFileName, ArrayList<Competitor> competitorsList) {
        this.competitorScoresList = readCompetitorScoresFromCSV(scoresFileName, competitorsList);
    }

    private ArrayList<CompetitorScores> readCompetitorScoresFromCSV(String scoresFileName,
            ArrayList<Competitor> competitorsList) {
        ArrayList<CompetitorScores> scoresList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(scoresFileName))) {
            String line;
            br.readLine(); // Skip the header line
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int competitorID = Integer.parseInt(data[0]);

                // Find the Competitor object based on ID from the provided list
                Competitor competitor = getCompetitorById(competitorID, competitorsList);

                if (competitor != null) {
                    // Extract scores from the CSV line
                    int[] scores = new int[data.length - 2];
                    for (int i = 2; i < data.length; i++) {
                        scores[i - 2] = Integer.parseInt(data[i]);
                    }

                    // Create CompetitorScores object and add it to the list
                    CompetitorScores competitorScores = new CompetitorScores(competitor, scores);
                    scoresList.add(competitorScores);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return scoresList;
    }

    private Competitor getCompetitorById(int competitorID, ArrayList<Competitor> competitorsList) {
        for (Competitor competitor : competitorsList) {
            if (competitor.getNumber() == competitorID) {
                return competitor;
            }
        }
        return null;
    }

    public ArrayList<CompetitorScores> getCompetitorScoresList() {
        return competitorScoresList;
    }
}