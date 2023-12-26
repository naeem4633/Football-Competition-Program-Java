import java.util.ArrayList;

public class Competition {
    private int ID;
    private String name;
    private String date;
    private String location;
    private CompetitorList competitorList;

    public Competition(int ID, String name, String date, String location, CompetitorList competitorList) {
        this.ID = ID;
        this.name = name;
        this.date = date;
        this.location = location;
        this.competitorList = competitorList;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public ArrayList<Competitor> getCompetitors() {
        return competitorList.getCompetitors();
    }

    public void displayCompetitionInfo() {
        // Implement logic to display competition information
    }

    public void addCompetitor(Competitor competitor) {
        competitorList.addCompetitor(competitor);
    }

    public void removeCompetitor(int competitorNumber) {
        competitorList.removeCompetitor(competitorNumber);
    }
}
