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

    public Competitor searchCompetitorById(int id) throws IOException {
        CompetitorList competitorList = new CompetitorList();
        return competitorList.getCompetitorByNumber(id);
    }

    public void recordScores(CompetitorScores competitorScores, String scoresFileName) {
        CompetitorList competitorList = new CompetitorList();
        CompetitorScoresList competitorScoresList = new CompetitorScoresList(competitorList.getCompetitors());
        competitorScoresList.recordScores(competitorScores, scoresFileName);
    }
}