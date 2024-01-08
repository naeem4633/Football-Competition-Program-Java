import java.io.*;
import java.util.ArrayList;

public class Officials extends Staff {
    public Officials(int ID, Name name, int accessLevel) {
        super(ID, name, accessLevel);
    }

    public void amendCompetitorDetails(Competitor competitor, Competitor modifiedCompetitor) {
        CompetitorList competitorList = new CompetitorList();
        competitorList.amendCompetitorDetails(competitor, modifiedCompetitor);
    }

    public void registerCompetitorForCompetition(Competitor competitor) {
        Competition competition = new Competition(1, null, null, null, null);
        competition.addCompetitor(competitor);
    }

    public Competitor removeAndReturnCompetitorFromCompetition(Competitor competitor) {
        Competition competition = new Competition(1, null, null, null, null);
        return competition.removeCompetitor(competitor.getNumber());
    }
}
