package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JTextArea;

import model.Competitor;
import model.Name;

public class CompetitorList {

    private ArrayList<Competitor> competitors;
    private String fileName = "Competitors.csv";
    private static final int EXPECTED_FIELD_COUNT = 7;

    public CompetitorList() {
        this.competitors = readCompetitorsFromCSV(this.fileName);
    }

    public void displayCompetitors(BufferedWriter writer) throws IOException {
        for (Competitor competitor : this.competitors) {
            writer.write("Competitor " + competitor.getNumber() + ":\t");
            writer.write("Name: " + competitor.getName().getFullName() + "\t");
            writer.write("Date of Birth: " + competitor.getDateOfBirth() + "\t");
            writer.write("Category: " + competitor.getCategory() + "\t");
            writer.write("Age: " + competitor.getAge() + "\t");
            writer.write("Email: " + competitor.getEmail() + "\n");
            writer.write("-------------------------------------------------------\n");
        }
    }

    public void sortCompetitorsAlphabetically() {
        Collections.sort(competitors, Comparator.comparing(c -> c.getName().getFullName()));
    }

    public void sortCompetitorsById() {
        Collections.sort(competitors, Comparator.comparingInt(Competitor::getNumber));
    }

    public void displaySingleCompetitorInGUI(int competitorNumber, JTextArea textArea) {
        Competitor competitor = getCompetitorByNumber(competitorNumber);

        if (competitor != null) {
            textArea.setText(""); // Clear existing text
            textArea.append("Details of Competitor " + competitor.getNumber() + ":\n");
            textArea.append("Name: " + competitor.getName().getFullName() + "\n");
            textArea.append("Date of Birth: " + competitor.getDateOfBirth() + "\n");
            textArea.append("Category: " + competitor.getCategory() + "\n");
            textArea.append("Age: " + competitor.getAge() + "\n");
            textArea.append("Email: " + competitor.getEmail() + "\n");
            textArea.append("-------------------------------------------------------\n");
        } else {
            textArea.setText("Competitor with number " + competitorNumber + " not found.");
        }
    }

    public void displayCompetitorsInGUI(JTextArea textArea) {
        for (Competitor competitor : this.competitors) {
            textArea.append("Competitor " + competitor.getNumber() + ":\t");
            textArea.append("Name: " + competitor.getName().getFullName() + "\t");
            textArea.append("Date of Birth: " + competitor.getDateOfBirth() + "\t");
            textArea.append("Category: " + competitor.getCategory() + "\t");
            textArea.append("Age: " + competitor.getAge() + "\t");
            textArea.append("Email: " + competitor.getEmail() + "\n");
            textArea.append("-------------------------------------------------------\n");
        }
    }

    private static boolean isValidCompetitorData(String[] data) {
        return data.length == EXPECTED_FIELD_COUNT;
    }

    private static ArrayList<Competitor> readCompetitorsFromCSV(String fileName) {
        ArrayList<Competitor> competitors = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                // input file validation
                if (isValidCompetitorData(data)) {
                    int number = Integer.parseInt(data[0]);
                    String firstName = data[1];
                    String lastName = data[2];
                    String email = data[3];
                    String dateOfBirth = data[4];
                    String category = data[5];
                    int age = Integer.parseInt(data[6]);

                    Competitor competitor = new Competitor(number, new Name(firstName, lastName), dateOfBirth, category,
                            age, email);
                    competitors.add(competitor);
                } else {
                    System.err.println("Invalid data in CSV file: " + line);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return competitors;
    }

    // Getter for the CompetitorList
    public ArrayList<Competitor> getCompetitors() {
        return competitors;
    }

    public void addCompetitor(Competitor newCompetitor) {
        competitors.add(newCompetitor);

        // Update the CSV file after adding the competitor
        writeCompetitorsToCSV();
    }

    public void removeCompetitor(int competitorNumber) {
        Competitor competitorToRemove = null;
        for (Competitor competitor : competitors) {
            if (competitor.getNumber() == competitorNumber) {
                competitorToRemove = competitor;
                break;
            }
        }

        if (competitorToRemove != null) {
            competitors.remove(competitorToRemove);
            writeCompetitorsToCSV();
        }
    }

    private void writeCompetitorsToCSV() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Competitors.csv"))) {
            for (Competitor competitor : competitors) {
                writer.write(competitor.getNumber() + "," +
                        competitor.getName().getFirstName() + "," +
                        competitor.getName().getLastName() + "," +
                        competitor.getEmail() + "," +
                        competitor.getDateOfBirth() + "," +
                        competitor.getCategory() + "," +
                        competitor.getAge() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Competitor getCompetitorByNumber(int number) {
        for (Competitor competitor : competitors) {
            if (competitor.getNumber() == number) {
                return competitor;
            }
        }
        return null; // Return null if no competitor with the specified number is found
    }

    public void amendCompetitorDetails(Competitor competitor, Competitor modifiedCompetition) {
        // Assuming Competitor class has appropriate setters for modification
        competitor.setName(modifiedCompetition.getName());
        competitor.setDateOfBirth(modifiedCompetition.getDateOfBirth());
        competitor.setCategory(modifiedCompetition.getCategory());
        competitor.setAge(modifiedCompetition.getAge());
        competitor.setEmail(modifiedCompetition.getEmail());

        // Update the details in the Competitors.csv file
        updateCompetitorDetailsInCSV(this.fileName, competitor);
    }

    private void updateCompetitorDetailsInCSV(String fileName, Competitor competitor) {
        ArrayList<Competitor> competitors = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int number = Integer.parseInt(data[0]);
                if (number == competitor.getNumber()) {
                    // Update the details for the specific competitor
                    data[1] = competitor.getName().getFirstName();
                    data[2] = competitor.getName().getLastName();
                    data[3] = competitor.getEmail();
                    data[4] = competitor.getDateOfBirth();
                    data[5] = competitor.getCategory();
                    data[6] = Integer.toString(competitor.getAge());
                }

                Competitor updatedCompetitor = new Competitor(number, new Name(data[1],
                        data[2]), data[3], data[4],
                        Integer.parseInt(data[5]), data[6]);
                competitors.add(updatedCompetitor);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        // Write the updated list of competitors back to the file
        writeCompetitorsToCSV();
    }

    public Competitor removeAndReturnCompetitor(int competitorNumber) {
        Competitor removedCompetitor = null;

        for (int i = 0; i < competitors.size(); i++) {
            Competitor competitor = competitors.get(i);
            if (competitor.getNumber() == competitorNumber) {
                removedCompetitor = competitors.remove(i);
                break;
            }
        }

        if (removedCompetitor != null) {
            // Update the CSV file after removing the competitor
            writeCompetitorsToCSV();
        }

        return removedCompetitor;
    }

}