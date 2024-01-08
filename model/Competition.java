package model;

import java.util.ArrayList;
import controller.CompetitorList;
import model.Competitor;

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

    public void addCompetitor(Competitor competitor) {
        competitorList.addCompetitor(competitor);
    }

    public Competitor removeCompetitor(int competitorNumber) {
        return competitorList.removeAndReturnCompetitor(competitorNumber);
    }

    public boolean hasCompetitorWithEmailAndCategory(String email, String category) {
        for (Competitor competitor : competitorList.getCompetitors()) {
            if (competitor.getEmail().equals(email) &&
                    competitor.getCategory().equals(category)) {
                return true;
            }
        }
        return false;
    }

    public void displayCompetitionInfo() {
        System.out.println("Competition ID: " + ID);
        System.out.println("Competition Name: " + name);
        System.out.println("Date: " + date);
        System.out.println("Location: " + location);

        System.out.println("\nCompetitors:");
    }

}
