import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Staff {
    private int ID;
    private Name name;
    private int accessLevel;

    public Staff(int ID, Name name, int accessLevel) {
        this.ID = ID;
        this.name = name;
        this.accessLevel = accessLevel;
    }

    public int getID() {
        return ID;
    }

    public Name getName() {
        return name;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public Competitor searchCompetitorById(int id, CompetitorList competitorList) throws IOException {
        // Implement search logic here
        ArrayList<Competitor> competitors = competitorList.getCompetitors();

        for (Competitor competitor : competitors) {
            if (competitor.getNumber() == id) {
                return competitor;
            }
        }

        return null;
    }

    public void recordScores(CompetitorScores competitorScores, String scoresFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(scoresFileName, true))) {
            // Append new scores to the CSV file
            writer.write(competitorScores.getCompetitor().getNumber() + ",");

            int[] scores = competitorScores.getScores();
            for (int score : scores) {
                writer.write(score + ",");
            }
            writer.write("\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
