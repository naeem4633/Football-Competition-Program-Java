import java.io.*;
import java.util.ArrayList;

public class Officials extends Staff {
    public Officials(int ID, Name name, int accessLevel) {
        super(ID, name, accessLevel);
    }

    public void amendCompetitorDetails(Competitor competitor, Competitor modifiedCompetitor) {
        CompetitorList competitorList = new CompetitorList();
        try {
            competitorList.amendCompetitorDetails(competitor, modifiedCompetitor);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void registerCompetitorForCompetition(Competitor competitor,
            Competition competition) {
        competition.addCompetitor(competitor);
    }

    public void removeCompetitorFromCompetition(Competitor competitor,
            Competition competition) {
        competition.removeCompetitor(competitor.getNumber());
    }
}
