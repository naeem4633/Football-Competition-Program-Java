import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JTextArea;

public class CompetitorList {

    private ArrayList<Competitor> competitors;

    public CompetitorList(String competitorsFileName) {
        this.competitors = readCompetitorsFromCSV(competitorsFileName);
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

    private static ArrayList<Competitor> readCompetitorsFromCSV(String fileName) {
        ArrayList<Competitor> competitors = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
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
                        competitor.getDateOfBirth() + "," +
                        competitor.getCategory() + "," +
                        competitor.getAge() + "," +
                        competitor.getEmail() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}