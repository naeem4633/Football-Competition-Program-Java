import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Map;

public class CompetitorGUI {

    private CompetitorList competitorList;
    private CompetitorScoresList competitorScoresList;
    private Competition competition;
    private JFrame frame;
    private JTextArea textArea;

    public CompetitorGUI(CompetitorList competitorList, CompetitorScoresList competitorScoresList,
            Competition competition) {
        this.competitorList = competitorList;
        this.competitorScoresList = competitorScoresList;
        this.competition = competition;
        this.frame = new JFrame("Competitor Management");
    }

    public void createAndShowRoleSelection() {
        String[] roles = { "Competitor", "Official", "Staff Member", "Audience" };
        JComboBox<String> roleComboBox = new JComboBox<>(roles);
        System.out.println(competitorList);

        JButton proceedButton = new JButton("Proceed");
        proceedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedRole = (String) roleComboBox.getSelectedItem();
                openRoleWindow(selectedRole);
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        panel.add(new JLabel("I am:"));
        panel.add(roleComboBox);
        panel.add(proceedButton);

        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.setSize(300, 150);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void openRoleWindow(String role) {
        switch (role) {
            case "Competitor":
                openCompetitorWindow();
                break;
            case "Official":
                openOfficialWindow();
                break;
            case "Staff Member":
                openStaffWindow();
                break;
            case "Audience":
                openAudienceWindow();
                break;
        }
    }

    private void openCompetitorWindow() {
        frame.getContentPane().removeAll();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        JButton viewCompetitorsButton = new JButton("View Competitors");
        viewCompetitorsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewCompetitors(textArea);
            }
        });

        JButton generateReportButton = new JButton("Generate Report");
        generateReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateReport(textArea);
            }
        });

        JButton searchCompetitorButton = new JButton("Search Competitor");
        searchCompetitorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchCompetitor(textArea);
            }
        });

        JButton viewScoresButton = new JButton("View Scores");
        viewScoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewScores();
            }
        });

        JButton selfRegistrationButton = new JButton("Self Registration");
        selfRegistrationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selfRegistration();
            }
        });

        JButton sortAlphabeticallyButton = new JButton("Show Competitors List: Sort Alphabetically");
        sortAlphabeticallyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortCompetitorsAlphabetically();
                viewCompetitors(textArea);
            }
        });

        JButton sortByIdButton = new JButton("Show Competitors List: Sort by Competitor Number");
        sortByIdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortCompetitorsById();
                viewCompetitors(textArea);
            }
        });

        frame.setLayout(new FlowLayout());

        frame.add(viewCompetitorsButton);
        frame.add(generateReportButton);
        frame.add(viewScoresButton);
        frame.add(searchCompetitorButton);
        frame.add(selfRegistrationButton);
        frame.add(sortAlphabeticallyButton);
        frame.add(sortByIdButton);

        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void openOfficialWindow() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        JButton generateReportButton = new JButton("Generate Report");
        generateReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateReport(textArea);
            }
        });

        JButton searchCompetitorButton = new JButton("Search Competitor");
        searchCompetitorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchCompetitor(textArea);
            }
        });

        JButton viewScoresButton = new JButton("View Scores");
        viewScoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewScores();
            }
        });

        JButton recordScoresButton = new JButton("Record Scores");
        recordScoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                recordScores();
            }
        });

        JButton addCompetitorButton = new JButton("Add Competitor");
        addCompetitorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCompetitor();
            }
        });

        JButton removeCompetitorButton = new JButton("Remove Competitor");
        removeCompetitorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeCompetitor();
            }
        });

        JButton amendCompetitorButton = new JButton("Amend Competitor Details");
        amendCompetitorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                amendCompetitorDetails();
            }
        });

        JButton sortAlphabeticallyButton = new JButton("Show Competitors List: Sort Alphabetically");
        sortAlphabeticallyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortCompetitorsAlphabetically();
                viewCompetitors(textArea);
            }
        });

        JButton sortByIdButton = new JButton("Show Competitors List: Sort by Competitor Number");
        sortByIdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortCompetitorsById();
                viewCompetitors(textArea);
            }
        });

        frame.setLayout(new FlowLayout());

        frame.add(generateReportButton);
        frame.add(viewScoresButton);
        frame.add(searchCompetitorButton);
        frame.add(recordScoresButton);
        frame.add(addCompetitorButton);
        frame.add(removeCompetitorButton);
        frame.add(amendCompetitorButton);
        frame.add(sortAlphabeticallyButton);
        frame.add(sortByIdButton);

        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void openStaffWindow() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        JButton generateReportButton = new JButton("Generate Report");
        generateReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateReport(textArea);
            }
        });

        JButton searchCompetitorButton = new JButton("Search Competitor");
        searchCompetitorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchCompetitor(textArea);
            }
        });

        JButton viewScoresButton = new JButton("View Scores");
        viewScoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewScores();
            }
        });

        JButton recordScoresButton = new JButton("Record Scores");
        recordScoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                recordScores();
            }
        });

        JButton amendScoresButton = new JButton("Amend Scores");
        amendScoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                amendScores();
            }
        });

        JButton sortAlphabeticallyButton = new JButton("Show Competitors List: Sort Alphabetically");
        sortAlphabeticallyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortCompetitorsAlphabetically();
                viewCompetitors(textArea);
            }
        });

        JButton sortByIdButton = new JButton("Show Competitors List: Sort by Competitor Number");
        sortByIdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortCompetitorsById();
                viewCompetitors(textArea);
            }
        });

        frame.setLayout(new FlowLayout());

        frame.add(generateReportButton);
        frame.add(viewScoresButton);
        frame.add(searchCompetitorButton);
        frame.add(recordScoresButton);
        frame.add(amendScoresButton);
        frame.add(sortAlphabeticallyButton);
        frame.add(sortByIdButton);

        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void openAudienceWindow() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        JButton viewResultsButton = new JButton("View Results");
        viewResultsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateReport(textArea);
            }
        });

        frame.setLayout(new FlowLayout());
        frame.add(viewResultsButton);

        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CompetitorGUI competitorGUI = new CompetitorGUI(null, null, null);
            competitorGUI.createAndShowRoleSelection();
        });
    }

    private void viewScores() {
        try {
            int competitorNumber = getCompetitorNumberFromUser();

            Competitor competitor = competitorList.getCompetitorByNumber(competitorNumber);

            if (competitor != null) {
                displayScores(competitor, textArea);
            } else {
                textArea.setText("Invalid competitor number. Please try again.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayScores(Competitor competitor, JTextArea textArea) {
        textArea.setText("");
        textArea.append("Scores for Competitor " + competitor.getNumber() + ":\n");

        // Retrieve and display scores for the competitor
        // You may need to modify this part based on your data structure
        for (CompetitorScores scores : competitorScoresList.getCompetitorScoresList()) {
            if (scores.getCompetitorNumber() == competitor.getNumber()) {
                // textArea.append("Round: " + scores.getRound() + "\t");
                textArea.append("Score: " + scores.displayScoreReport() + "\n");
            }
        }

        System.out.println("Scores displayed in the GUI.");
    }

    private void recordScores() {
        int competitorID = Integer.parseInt(JOptionPane.showInputDialog("Enter Competitor ID:"));

        int[] scores = new int[4];
        for (int i = 0; i < 4; i++) {
            scores[i] = Integer.parseInt(JOptionPane.showInputDialog("Enter Score " + (i + 1) + ":"));
        }

        // Create CompetitorScores instance
        CompetitorScores competitorScores = new CompetitorScores(
                competitorList.getCompetitorByNumber(competitorID),
                scores);

        // Record scores
        competitorScoresList.recordScores(competitorScores, "CompetitorScores.csv");

        // Show a dialog indicating successful recording
        JOptionPane.showMessageDialog(frame, "Scores recorded successfully!");
    }

    private void amendScores() {
        try {
            int competitorID = Integer.parseInt(JOptionPane.showInputDialog("Enter Competitor ID:"));

            // Check if the competitor ID exists
            if (competitorList.getCompetitorByNumber(competitorID) != null) {

                int[] newScores = new int[4];
                for (int i = 0; i < 4; i++) {
                    newScores[i] = Integer.parseInt(JOptionPane.showInputDialog("Enter New Score " + (i + 1) + ":"));
                }

                // Amend scores in the CompetitorScoresList
                competitorScoresList.amendScores(competitorID, 1, newScores, "CompetitorScores.csv");

                // Show a dialog indicating successful amendment
                JOptionPane.showMessageDialog(frame, "Scores amended successfully!");
            } else {
                JOptionPane.showMessageDialog(frame, "Competitor ID or Competition ID not found.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid input. Please enter valid numbers.");
        }
    }

    private int getCompetitorNumberFromUser() throws IOException {
        String competitorNumberStr = JOptionPane.showInputDialog(frame, "Enter Competitor Number:");

        try {
            if (competitorNumberStr != null) {
                return Integer.parseInt(competitorNumberStr);
            } else {
                throw new NumberFormatException(); // Simulate canceling the input dialog
            }
        } catch (NumberFormatException e) {
            throw new IOException("Invalid input. Please enter a valid competitor number.");
        }
    }

    private void amendCompetitorDetails() {
        try {
            int competitorID = Integer.parseInt(JOptionPane.showInputDialog("Enter Competitor ID to amend:"));

            // Get the existing competitor details
            Competitor existingCompetitor = competitorList.getCompetitorByNumber(competitorID);

            if (existingCompetitor != null) {
                // Gather new details from the user using JOptionPane
                String newFirstName = JOptionPane.showInputDialog("Enter new First Name:");
                String newLastName = JOptionPane.showInputDialog("Enter new Last Name:");
                String newDateOfBirth = JOptionPane.showInputDialog("Enter new Date of Birth:");
                String newCategory = JOptionPane.showInputDialog("Enter new Category:");
                int newAge = Integer.parseInt(JOptionPane.showInputDialog("Enter new Age:"));
                String newEmail = JOptionPane.showInputDialog("Enter new Email:");

                // Create a new Competitor instance with the modified details
                Competitor modifiedCompetitor = new Competitor(
                        competitorID,
                        new Name(newFirstName, newLastName),
                        newDateOfBirth,
                        newCategory,
                        newAge,
                        newEmail);

                // Amend competitor details in the backend
                competitorList.amendCompetitorDetails(existingCompetitor, modifiedCompetitor, "Competitors.csv");

                // Show a dialog indicating successful amendment
                JOptionPane.showMessageDialog(frame, "Competitor details amended successfully!");
            } else {
                JOptionPane.showMessageDialog(frame, "Competitor not found. Please enter a valid competitor ID.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a valid competitor ID and age.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void viewCompetitors(JTextArea textArea) {
        textArea.setText("");
        competitorList.displayCompetitorsInGUI(textArea);
        System.out.println("Competitors displayed in the GUI.");
    }

    private void sortCompetitorsAlphabetically() {
        competitorList.sortCompetitorsAlphabetically();
    }

    private void sortCompetitorsById() {
        competitorList.sortCompetitorsById();
    }

    private void searchCompetitor(JTextArea textArea) {
        try {
            int competitorNumber = getCompetitorNumberFromUser();

            Competitor competitor = competitorList.getCompetitorByNumber(competitorNumber);

            if (competitor != null) {
                // displayCompetitorDetails(competitor, textArea);
                competitorList.displaySingleCompetitorInGUI(competitorNumber, textArea);
            } else {
                textArea.setText("Invalid competitor number. Please try again.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generateReport(JTextArea textArea) {
        CompetitorList competitorList = new CompetitorList("Competitors.csv");
        CompetitorScoresList competitorScoresList = new CompetitorScoresList("CompetitorScores.csv",
                competitorList.getCompetitors());

        // Create an instance of Result
        Result result = new Result(0, null, null, null);

        // Finding the competitor with the highest overall score
        Competitor highestScorer = result.getCompetitorWithHighestScore(
                competitorScoresList.getCompetitorScoresList(), competitorList.getCompetitors());

        // Calculating summary statistics
        int maxOverallScore = result.getMaxOverallScore(competitorScoresList.getCompetitorScoresList());
        int minOverallScore = result.getMinOverallScore(competitorScoresList.getCompetitorScoresList());

        // Generating a frequency report
        Map<Integer, Integer> frequencyReport = result
                .getFrequencyOfScores(competitorScoresList.getCompetitorScoresList());

        // Append the report to the JTextArea
        textArea.append("Competitors Table:\n");
        textArea.append("-------------------------------------------------------\n");
        competitorList.displayCompetitorsInGUI(textArea);

        textArea.append("\nDetails of the Competitor with the Highest Overall Score: \n"
                + highestScorer.getName().getFullName() + "\n");

        textArea.append("-------------------------------------------------------\n");
        // ... append details of the highest scorer as in the previous example ...

        textArea.append("\nSummary Statistics:\n");
        textArea.append("-------------------------------------------------------\n");
        textArea.append("Maximum Overall Score: " + maxOverallScore + "\n");
        textArea.append("Minimum Overall Score: " + minOverallScore + "\n");

        textArea.append("\nFrequency Report: " + frequencyReport + "\n");

        textArea.append("-------------------------------------------------------\n");

        System.out.println("Report generated successfully and displayed in the GUI.");
    }

    private void addCompetitor() {
        // Use JOptionPanes to get user input for adding a competitor
        JTextField numberField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField dobField = new JTextField();
        JTextField categoryField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField emailField = new JTextField();

        Object[] fields = {
                "Number:", numberField,
                "Name:", nameField,
                "Date of Birth:", dobField,
                "Category:", categoryField,
                "Age:", ageField,
                "Email:", emailField
        };

        int option = JOptionPane.showConfirmDialog(frame, fields, "Add Competitor", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            try {
                // Parse user input and create a new Competitor
                int number = Integer.parseInt(numberField.getText());
                Name name = parseName(nameField.getText());
                String dob = dobField.getText();
                String category = categoryField.getText();
                int age = Integer.parseInt(ageField.getText());
                String email = emailField.getText();

                Competitor newCompetitor = new Competitor(number, name, dob, category,
                        age, email);

                // Add the new competitor to the list and update the GUI
                competitorList.addCompetitor(newCompetitor);
                textArea.append("Competitor added:\n" + newCompetitor.toString() + "\n");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a valid age.");
            }
        }
    }

    private void removeCompetitor() {
        // Use JOptionPane to get user input for removing a competitor
        String competitorNumberStr = JOptionPane.showInputDialog(frame, "Enter Competitor Number to Remove:");

        if (competitorNumberStr != null) {
            try {
                int competitorNumber = Integer.parseInt(competitorNumberStr);

                // Remove the competitor from the list and update the GUI
                Competitor removedCompetitor = competitorList.removeAndReturnCompetitor(competitorNumber);

                if (removedCompetitor != null) {
                    textArea.append("Competitor removed:\n" + removedCompetitor.toString() + "\n");
                } else {
                    textArea.append("No competitor found with the specified number.\n");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a valid competitor number.");
            }
        }
    }

    private Name parseName(String name) {
        String[] parts = name.split("\\s");
        if (parts.length == 2) {
            return new Name(parts[0], parts[1]);
        } else {
            // Handle invalid input
            throw new IllegalArgumentException("Invalid name input. Please enter both first and last names.");
        }
    }

    private void selfRegistration() {
        // Create a dialog to input competitor details
        JTextField numberField = new JTextField();
        JTextField firstNameField = new JTextField();
        JTextField lastNameField = new JTextField();
        JTextField dateOfBirthField = new JTextField();
        JTextField categoryField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField emailField = new JTextField();

        Object[] fields = { "Number:", numberField, "First Name:", firstNameField, "Last Name:", lastNameField,
                "Date of Birth:", dateOfBirthField, "Category:", categoryField, "Age:", ageField, "Email:",
                emailField };

        int result = JOptionPane.showConfirmDialog(null, fields, "Self Registration", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try {
                // Extract values from text fields
                int number = Integer.parseInt(numberField.getText());
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String dateOfBirth = dateOfBirthField.getText();
                String category = categoryField.getText();
                int age = Integer.parseInt(ageField.getText());
                String email = emailField.getText();

                // Create a new competitor
                Competitor newCompetitor = new Competitor(number, new Name(firstName, lastName), dateOfBirth, category,
                        age, email);

                // Attempt to register the competitor
                registerCompetitor(newCompetitor);
            } catch (NumberFormatException ex) {
                // Handle invalid number or age format
                JOptionPane.showMessageDialog(null, "Invalid number or age format. Please enter valid numeric values.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void registerCompetitor(Competitor competitor) {
        // Check if the email already exists in the competitor list
        if (!competition.hasCompetitorWithEmailAndCategory(competitor.getEmail(), competitor.getCategory())) {
            // Register the competitor
            // You need to have a reference to the Competition object here
            // and call the registerForCompetition method
            competition.addCompetitor(competitor);
            System.out.println("Competitor registered successfully!");
        } else {
            // Display an error message if the email already exists
            JOptionPane.showMessageDialog(null,
                    "Email " + competitor.getEmail() + " already exists. Register under a different category.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}