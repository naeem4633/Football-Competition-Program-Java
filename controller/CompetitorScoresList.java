package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import model.Competitor;
import model.CompetitorScores;

public class CompetitorScoresList {

    private ArrayList<CompetitorScores> competitorScoresList;
    private String fileName = "CompetitorScores.csv";
    private static final int EXPECTED_FIELD_COUNT = 6;

    public CompetitorScoresList(ArrayList<Competitor> competitorsList) {
        this.competitorScoresList = readCompetitorScoresFromCSV(fileName, competitorsList);
    }

    private static boolean isValidCompetitorScoresData(String[] data) {
        return data.length == EXPECTED_FIELD_COUNT;
    }

    // Modified readCompetitorScoresFromCSV method
    private ArrayList<CompetitorScores> readCompetitorScoresFromCSV(String scoresFileName,
            ArrayList<Competitor> competitorsList) {
        ArrayList<CompetitorScores> scoresList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(scoresFileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (isValidCompetitorScoresData(data)) {
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
                } else {
                    System.err.println("Invalid data in CSV file: " + line);
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

    public CompetitorScores getCompetitorScoresByNumber(int competitorID) {
        for (CompetitorScores scores : competitorScoresList) {
            if (scores.getCompetitorNumber() == competitorID) {
                return scores;
            }
        }
        return null; // Return null if not found
    }

    public void recordScores(CompetitorScores competitorScores) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
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

    public void amendScores(int competitorID, int competitionID, int[] newScores) {
        CompetitorScores targetScores = null;

        for (CompetitorScores scores : competitorScoresList) {
            if (scores.getCompetitorNumber() == competitorID) {
                // Found the target scores
                targetScores = scores;
                break;
            }
        }

        if (targetScores != null) {
            // Update the scores
            targetScores.setScores(newScores);

            // Update the details in the CompetitorScores.csv file
            updateScoresInCSV(fileName, competitorScoresList);
        } else {
            System.out.println(
                    "Scores not found for Competitor ID " + competitorID + " and Competition ID "
                            + competitionID);
        }
    }

    private void updateScoresInCSV(String fileName, ArrayList<CompetitorScores> scoresList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (CompetitorScores scores : scoresList) {
                writer.write(scores.getCompetitorNumber() + "," +
                        1 + "," +
                        scores.getScores()[0] + "," +
                        scores.getScores()[1] + "," +
                        scores.getScores()[2] + "," +
                        scores.getScores()[3] + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}