import java.io.*;
import java.util.ArrayList;

public class Officials extends Staff {
    public Officials(int ID, Name name, int accessLevel) {
        super(ID, name, accessLevel);
    }

    public void amendCompetitorDetails(Competitor competitor, Competitor modifiedCompetition,
            String competitorsFileName) throws IOException {
        // Assuming Competitor class has appropriate setters for modification
        competitor.setName(modifiedCompetition.getName());
        competitor.setDateOfBirth(modifiedCompetition.getDateOfBirth());
        competitor.setCategory(modifiedCompetition.getCategory());
        competitor.setAge(modifiedCompetition.getAge());
        competitor.setEmail(modifiedCompetition.getEmail());

        // Update the details in the Competitors.csv file
        updateCompetitorDetailsInCSV(competitorsFileName, competitor);
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
                    data[3] = competitor.getDateOfBirth();
                    data[4] = competitor.getCategory();
                    data[5] = Integer.toString(competitor.getAge());
                    data[6] = competitor.getEmail();
                }

                Competitor updatedCompetitor = new Competitor(number, new Name(data[1], data[2]), data[3], data[4],
                        Integer.parseInt(data[5]), data[6]);
                competitors.add(updatedCompetitor);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        // Write the updated list of competitors back to the file
        writeCompetitorsToCSV(fileName, competitors);
    }

    private void writeCompetitorsToCSV(String fileName, ArrayList<Competitor> competitors) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
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

    public void registerCompetitorForCompetition(Competitor competitor,
            Competition competition) {
        competition.addCompetitor(competitor);
    }

    public void removeCompetitorFromCompetition(Competitor competitor,
            Competition competition) {
        competition.removeCompetitor(competitor.getNumber());
    }

}
