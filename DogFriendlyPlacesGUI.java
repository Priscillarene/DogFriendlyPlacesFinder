import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Creates the graphical user interface for the Dog-Friendly Places Finder.
 * The GUI lets the user search, filter, view place details, and add new places.
 */
public class DogFriendlyPlacesGUI extends JFrame {
    private DogFriendlyPlacesFinder system;

    private JTextField searchField;

    private JPanel resultsPanel;
    private JTextArea detailsArea;

    private JTextField nameField;
    private JTextField categoryField;
    private JTextField addressField;
    private JTextField policyField;

    private DogFriendlyPlace selectedPlace;

    // Color palette for the GUI
    private final Color SOFT_BLUE = new Color(214, 232, 255);
    private final Color SAGE_GREEN = new Color(207, 232, 213);
    private final Color WARM_YELLOW = new Color(255, 209, 102);
    private final Color NAVY = new Color(31, 61, 90);
    private final Color LIGHT_GRAY = new Color(244, 246, 248);
    private final Color WHITE = Color.WHITE;
    private final Color TEXT_GRAY = new Color(70, 80, 90);

    // Font choices for headings and regular text
    private final Font MAIN_TITLE_FONT = new Font("Marker Felt", Font.BOLD, 38);
    private final Font HEADER_FONT = new Font("Marker Felt", Font.BOLD, 24);
    private final Font SUBHEADER_FONT = new Font("Marker Felt", Font.BOLD, 19);
    private final Font BODY_FONT = new Font("Avenir Next", Font.PLAIN, 14);
    private final Font BODY_BOLD_FONT = new Font("Avenir Next", Font.BOLD, 14);

    /**
     * Creates the GUI window and loads sample dog-friendly places.
     *
     * Precondition: The program is started.
     * Postcondition: The GUI window is displayed with sample data.
     */
    public DogFriendlyPlacesGUI() {
        system = new DogFriendlyPlacesFinder();
        setupSampleData();

        setTitle("Dog-Friendly Places Finder");
        setSize(1050, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createLayout();

        setVisible(true);
    }

    /**
     * Sets up the main layout of the GUI.
     *
     * Precondition: The GUI object has been created.
     * Postcondition: The main panels are added to the window.
     */
    private void createLayout() {
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(LIGHT_GRAY);
        mainPanel.setBorder(new EmptyBorder(20, 25, 20, 25));

        mainPanel.add(createHeaderPanel(), BorderLayout.NORTH);
        mainPanel.add(createMainContentPanel(), BorderLayout.CENTER);
        mainPanel.add(createAddPlacePanel(), BorderLayout.SOUTH);

        add(mainPanel);
    }

    /**
     * Creates the top section with the title, search bar, and filter buttons.
     *
     * Precondition: The GUI window is being built.
     * Postcondition: The header panel is created and returned.
     *
     * @return the header panel
     */
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout(10, 15));
        headerPanel.setBackground(LIGHT_GRAY);

        JLabel titleLabel = new JLabel("Dog-Friendly Places Finder", JLabel.CENTER);
        titleLabel.setFont(MAIN_TITLE_FONT);
        titleLabel.setForeground(NAVY);

        JPanel searchFilterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        searchFilterPanel.setBackground(LIGHT_GRAY);

        JLabel searchLabel = new JLabel("Search Places:");
        searchLabel.setFont(SUBHEADER_FONT);
        searchLabel.setForeground(NAVY);

        searchField = new JTextField(22);
        styleTextField(searchField, "Search parks, patios, trails...");

        JButton searchButton = new JButton("Go");
        styleButton(searchButton, SOFT_BLUE, NAVY);

        JLabel filterLabel = new JLabel("Filters:");
        filterLabel.setFont(SUBHEADER_FONT);
        filterLabel.setForeground(NAVY);

        JButton waterButton = new JButton("Water Access");
        JButton shadeButton = new JButton("Shade");
        JButton patioButton = new JButton("Outdoor Seating");
        JButton leashButton = new JButton("Off-Leash");
        JButton clearButton = new JButton("Show All");

        styleChipButton(waterButton);
        styleChipButton(shadeButton);
        styleChipButton(patioButton);
        styleChipButton(leashButton);
        styleChipButton(clearButton);

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchPlaces();
            }
        });

        waterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filterPlaces("water access");
            }
        });

        shadeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filterPlaces("shade");
            }
        });

        patioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filterPlaces("outdoor seating");
            }
        });

        leashButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filterPlaces("off-leash area");
            }
        });

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayResults(system.searchPlaces(""));
            }
        });

        searchFilterPanel.add(searchLabel);
        searchFilterPanel.add(searchField);
        searchFilterPanel.add(searchButton);
        searchFilterPanel.add(filterLabel);
        searchFilterPanel.add(waterButton);
        searchFilterPanel.add(shadeButton);
        searchFilterPanel.add(patioButton);
        searchFilterPanel.add(leashButton);
        searchFilterPanel.add(clearButton);

        headerPanel.add(titleLabel, BorderLayout.NORTH);
        headerPanel.add(searchFilterPanel, BorderLayout.SOUTH);

        return headerPanel;
    }

    /**
     * Creates the middle section with results on the left and details on the right.
     *
     * Precondition: The GUI window is being built.
     * Postcondition: The main content panel is created.
     *
     * @return the main content panel
     */
    private JPanel createMainContentPanel() {
        JPanel contentPanel = new JPanel(new GridLayout(1, 2, 15, 0));
        contentPanel.setBackground(LIGHT_GRAY);

        contentPanel.add(createResultsPanel());
        contentPanel.add(createDetailsPanel());

        return contentPanel;
    }

    /**
     * Creates the results section where matching places are displayed.
     *
     * Precondition: The system has place data to display.
     * Postcondition: The results panel is created with place cards.
     *
     * @return the results panel
     */
    private JPanel createResultsPanel() {
        JPanel outerPanel = createCardPanel();
        outerPanel.setLayout(new BorderLayout(10, 10));

        JLabel resultsTitle = new JLabel("Places Near You");
        resultsTitle.setFont(HEADER_FONT);
        resultsTitle.setForeground(NAVY);

        resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        resultsPanel.setBackground(WHITE);

        JScrollPane scrollPane = new JScrollPane(resultsPanel);
        scrollPane.setBorder(null);

        JButton showAllButton = new JButton("Show All Places");
        styleButton(showAllButton, SOFT_BLUE, NAVY);

        showAllButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayResults(system.searchPlaces(""));
            }
        });

        outerPanel.add(resultsTitle, BorderLayout.NORTH);
        outerPanel.add(scrollPane, BorderLayout.CENTER);
        outerPanel.add(showAllButton, BorderLayout.SOUTH);

        displayResults(system.searchPlaces(""));

        return outerPanel;
    }

    /**
     * Creates the section that displays details for the selected place.
     *
     * Precondition: The GUI window is being built.
     * Postcondition: The details panel is created.
     *
     * @return the details panel
     */
    private JPanel createDetailsPanel() {
        JPanel outerPanel = createCardPanel();
        outerPanel.setLayout(new BorderLayout(10, 10));

        JLabel detailsTitle = new JLabel("Selected Place Details");
        detailsTitle.setFont(HEADER_FONT);
        detailsTitle.setForeground(NAVY);

        detailsArea = new JTextArea();
        detailsArea.setEditable(false);
        detailsArea.setFont(new Font("Avenir Next", Font.PLAIN, 15));
        detailsArea.setForeground(TEXT_GRAY);
        detailsArea.setBackground(new Color(250, 253, 250));
        detailsArea.setLineWrap(true);
        detailsArea.setWrapStyleWord(true);
        detailsArea.setBorder(new EmptyBorder(15, 15, 15, 15));

        JScrollPane detailsScroll = new JScrollPane(detailsArea);
        detailsScroll.setBorder(new LineBorder(SAGE_GREEN, 1, true));

        JPanel summaryPanel = new JPanel();
        summaryPanel.setBackground(SOFT_BLUE);
        summaryPanel.setPreferredSize(new Dimension(400, 120));
        summaryPanel.setBorder(new CompoundBorder(
                new LineBorder(new Color(180, 210, 240), 1, true),
                new EmptyBorder(15, 15, 15, 15)
        ));

        JLabel summaryLabel = new JLabel(
                "<html><center>Select a place to view its dog policy, features, rating, and reviews.</center></html>",
                JLabel.CENTER
        );
        summaryLabel.setFont(SUBHEADER_FONT);
        summaryLabel.setForeground(NAVY);

        summaryPanel.setLayout(new BorderLayout());
        summaryPanel.add(summaryLabel, BorderLayout.CENTER);

        outerPanel.add(detailsTitle, BorderLayout.NORTH);
        outerPanel.add(detailsScroll, BorderLayout.CENTER);
        outerPanel.add(summaryPanel, BorderLayout.SOUTH);

        detailsArea.setText("Select a place from the results to view details.");

        return outerPanel;
    }

    /**
     * Creates the form used to add a new dog-friendly place.
     *
     * Precondition: The GUI window is being built.
     * Postcondition: The add-place form is created.
     *
     * @return the add-place panel
     */
    private JPanel createAddPlacePanel() {
        JPanel outerPanel = createCardPanel();
        outerPanel.setBackground(new Color(255, 248, 225));
        outerPanel.setBorder(new CompoundBorder(
                new LineBorder(WARM_YELLOW, 1, true),
                new EmptyBorder(12, 15, 12, 15)
        ));

        outerPanel.setLayout(new BorderLayout(10, 10));

        JLabel addTitle = new JLabel("Add New Dog-Friendly Place");
        addTitle.setFont(HEADER_FONT);
        addTitle.setForeground(NAVY);

        JPanel formPanel = new JPanel(new GridLayout(2, 4, 10, 8));
        formPanel.setBackground(new Color(255, 248, 225));

        nameField = new JTextField();
        categoryField = new JTextField();
        addressField = new JTextField();
        policyField = new JTextField();

        styleTextField(nameField, "Enter place name");
        styleTextField(categoryField, "Park, Restaurant, Trail...");
        styleTextField(addressField, "Enter address");
        styleTextField(policyField, "Enter dog policy");

        formPanel.add(createLabeledField("Name", nameField));
        formPanel.add(createLabeledField("Category", categoryField));
        formPanel.add(createLabeledField("Address", addressField));
        formPanel.add(createLabeledField("Dog Policy", policyField));

        JButton addButton = new JButton("Add Place");
        styleButton(addButton, new Color(245, 176, 0), NAVY);
        addButton.setPreferredSize(new Dimension(160, 45));

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addNewPlace();
            }
        });

        outerPanel.add(addTitle, BorderLayout.NORTH);
        outerPanel.add(formPanel, BorderLayout.CENTER);
        outerPanel.add(addButton, BorderLayout.EAST);

        return outerPanel;
    }

    /**
     * Creates a label and text field together.
     *
     * @param labelText the label text
     * @param field the text field
     * @return a panel with the label and text field
     */
    private JPanel createLabeledField(String labelText, JTextField field) {
        JPanel panel = new JPanel(new BorderLayout(3, 3));
        panel.setBackground(new Color(255, 248, 225));

        JLabel label = new JLabel(labelText);
        label.setFont(BODY_BOLD_FONT);
        label.setForeground(NAVY);

        panel.add(label, BorderLayout.NORTH);
        panel.add(field, BorderLayout.CENTER);

        return panel;
    }

    /**
     * Displays the list of matching dog-friendly places as cards.
     *
     * Precondition: A list of places is provided.
     * Postcondition: The results panel shows the matching places.
     *
     * @param results the places to display
     */
    private void displayResults(ArrayList<DogFriendlyPlace> results) {
        resultsPanel.removeAll();

        if (results.size() == 0) {
            JLabel noResults = new JLabel("No matching places were found.");
            noResults.setFont(BODY_FONT);
            noResults.setForeground(TEXT_GRAY);
            resultsPanel.add(noResults);
        } else {
            for (DogFriendlyPlace place : results) {
                resultsPanel.add(createPlaceCard(place));
                resultsPanel.add(Box.createVerticalStrut(10));
            }
        }

        resultsPanel.revalidate();
        resultsPanel.repaint();
    }

    /**
     * Creates one card for a dog-friendly place.
     *
     * Precondition: A DogFriendlyPlace object is provided.
     * Postcondition: A place card with name, category, rating, and button is created.
     *
     * @param place the place shown on the card
     * @return the place card panel
     */
    private JPanel createPlaceCard(final DogFriendlyPlace place) {
        JPanel card = new JPanel(new BorderLayout(10, 5));
        card.setBackground(new Color(250, 253, 255));
        card.setBorder(new CompoundBorder(
                new LineBorder(new Color(190, 215, 240), 1, true),
                new EmptyBorder(12, 12, 12, 12)
        ));

        JPanel textPanel = new JPanel(new GridLayout(3, 1));
        textPanel.setBackground(new Color(250, 253, 255));

        JLabel nameLabel = new JLabel(place.getName());
        nameLabel.setFont(SUBHEADER_FONT);
        nameLabel.setForeground(NAVY);

        JLabel categoryLabel = new JLabel(place.getCategory());
        categoryLabel.setFont(BODY_FONT);
        categoryLabel.setForeground(new Color(30, 120, 200));

        JLabel ratingLabel = new JLabel("Rating: " + String.format("%.1f", place.calculateAverageRating()) + "/5");
        ratingLabel.setFont(BODY_FONT);
        ratingLabel.setForeground(new Color(120, 100, 20));

        textPanel.add(nameLabel);
        textPanel.add(categoryLabel);
        textPanel.add(ratingLabel);

        JButton detailsButton = new JButton("View");
        styleButton(detailsButton, SAGE_GREEN, NAVY);

        detailsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedPlace = place;
                showPlaceDetails(place);
            }
        });

        card.add(textPanel, BorderLayout.CENTER);
        card.add(detailsButton, BorderLayout.EAST);

        return card;
    }

    /**
     * Shows the details for the selected place.
     *
     * Precondition: The user selected a place.
     * Postcondition: The details area displays the place information.
     *
     * @param place the selected place
     */
    private void showPlaceDetails(DogFriendlyPlace place) {
        String details = "";

        details += place.getName() + "\n";
        details += "Category: " + place.getCategory() + "\n";
        details += "Rating: " + String.format("%.1f", place.calculateAverageRating()) + "/5\n\n";

        details += "DOG POLICY\n";
        details += place.getDogPolicy() + "\n\n";

        details += "FEATURES\n";
        for (PlaceFeature feature : place.getFeatures()) {
            details += "- " + feature.getFeatureName() + " - " + feature.getDescription() + "\n";
        }

        details += "\nREVIEWS\n";
        if (place.getReviews().size() == 0) {
            details += "No reviews yet.\n";
        } else {
            for (Review review : place.getReviews()) {
                details += "- " + review.getReviewDetails() + "\n";
            }
        }

        detailsArea.setText(details);
    }

    /**
     * Searches for places using the keyword typed by the user.
     *
     * Precondition: The user enters a search keyword.
     * Postcondition: Matching places are displayed, or an error message is shown.
     */
    private void searchPlaces() {
        String keyword = searchField.getText().trim();

        if (keyword.equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter a search keyword.");
            return;
        }

        ArrayList<DogFriendlyPlace> results = system.searchPlaces(keyword);
        displayResults(results);
    }

    /**
     * Filters places using the selected feature button.
     *
     * Precondition: The user clicks a feature button.
     * Postcondition: Places with that feature are displayed.
     *
     * @param feature the selected feature
     */
    private void filterPlaces(String feature) {
        ArrayList<DogFriendlyPlace> results = system.filterPlaces(feature);
        displayResults(results);
    }

    /**
     * Adds a new dog-friendly place using the form input.
     *
     * Precondition: The user fills in the name, category, address, and dog policy.
     * Postcondition: A new place is added, or an error message is shown.
     */
    private void addNewPlace() {
        String name = nameField.getText().trim();
        String category = categoryField.getText().trim();
        String address = addressField.getText().trim();
        String policy = policyField.getText().trim();

        if (name.equals("") || category.equals("") || address.equals("") || policy.equals("")) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields before adding a place.");
            return;
        }

        int newId = 200 + (int)(Math.random() * 500);

        DogFriendlyPlace newPlace = new DogFriendlyPlace(
                newId,
                name,
                address,
                category,
                "New dog-friendly place added by a business owner.",
                policy
        );

        system.addPlace(newPlace);

        nameField.setText("");
        categoryField.setText("");
        addressField.setText("");
        policyField.setText("");

        displayResults(system.searchPlaces(""));

        JOptionPane.showMessageDialog(this, "New place added successfully!");
    }

    /**
     * Applies the style used for regular buttons.
     *
     * @param button the button being styled
     * @param bgColor the button background color
     * @param textColor the button text color
     */
    private void styleButton(JButton button, Color bgColor, Color textColor) {
        button.setBackground(bgColor);
        button.setForeground(textColor);
        button.setFont(BODY_BOLD_FONT);
        button.setFocusPainted(false);
        button.setBorder(new EmptyBorder(8, 14, 8, 14));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    /**
     * Applies the style used for filter buttons.
     *
     * @param button the filter button being styled
     */
    private void styleChipButton(JButton button) {
        button.setBackground(new Color(246, 252, 247));
        button.setForeground(NAVY);
        button.setFont(BODY_FONT);
        button.setFocusPainted(false);
        button.setBorder(new CompoundBorder(
                new LineBorder(new Color(165, 205, 170), 1, true),
                new EmptyBorder(7, 10, 7, 10)
        ));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    /**
     * Applies the style used for text fields.
     *
     * @param field the text field being styled
     * @param toolTip the tooltip text
     */
    private void styleTextField(JTextField field, String toolTip) {
        field.setFont(BODY_FONT);
        field.setForeground(TEXT_GRAY);
        field.setBackground(WHITE);
        field.setToolTipText(toolTip);
        field.setBorder(new CompoundBorder(
                new LineBorder(new Color(200, 210, 220), 1, true),
                new EmptyBorder(8, 10, 8, 10)
        ));
    }

    /**
     * Creates a basic white card panel used in the GUI.
     *
     * @return a styled card panel
     */
    private JPanel createCardPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(WHITE);
        panel.setBorder(new CompoundBorder(
                new LineBorder(new Color(220, 230, 235), 1, true),
                new EmptyBorder(15, 15, 15, 15)
        ));

        return panel;
    }

    /**
     * Creates sample places, features, and reviews for testing the program.
     *
     * Precondition: The system object has been created.
     * Postcondition: Sample data is added to the system.
     */
    private void setupSampleData() {
        DogOwner dogOwner = new DogOwner(1, "Priscilla", "priscilla@email.com");
        BusinessOwner businessOwner = new BusinessOwner(1, "Jacob", "jacob@email.com", "Austin Pet Patio");

        PlaceFeature waterAccess = new PlaceFeature(1, "water access", "Has water available for dogs");
        PlaceFeature outdoorSeating = new PlaceFeature(2, "outdoor seating", "Has a patio or outdoor area");
        PlaceFeature shade = new PlaceFeature(3, "shade", "Has shaded areas for dogs and owners");
        PlaceFeature offLeash = new PlaceFeature(4, "off-leash area", "Allows dogs to be off leash");

        DogFriendlyPlace park = new DogFriendlyPlace(
                101,
                "Bastrop Dog Park",
                "100 Park Road, Bastrop, TX",
                "Park",
                "A local park with open space for dogs.",
                "Dogs allowed. Must be leashed outside off-leash areas."
        );

        park.addFeature(waterAccess);
        park.addFeature(shade);
        park.addFeature(offLeash);

        DogFriendlyPlace patio = new DogFriendlyPlace(
                102,
                "Austin Pet Patio",
                "200 Patio Lane, Austin, TX",
                "Restaurant",
                "A restaurant patio that welcomes dogs.",
                "Dogs allowed on patio only."
        );

        patio.addFeature(waterAccess);
        patio.addFeature(outdoorSeating);
        patio.addFeature(shade);

        DogFriendlyPlace trail = new DogFriendlyPlace(
                103,
                "Zilker Trail Stop",
                "300 Trail Road, Austin, TX",
                "Trail",
                "A relaxing trail stop with shade and open space.",
                "Dogs allowed on leash."
        );

        trail.addFeature(shade);
        trail.addFeature(waterAccess);

        businessOwner.addPlace(system, park);
        businessOwner.addPlace(system, patio);
        businessOwner.addPlace(system, trail);

        dogOwner.leaveReview(park, new Review(1, 5, "Great park with plenty of space!", "05/01/2026"));
        dogOwner.leaveReview(patio, new Review(2, 4, "Cute patio and good water access for dogs.", "05/02/2026"));
        dogOwner.leaveReview(trail, new Review(3, 5, "Peaceful trail and lots of shade.", "05/03/2026"));
    }

    /**
     * Starts the GUI application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        new DogFriendlyPlacesGUI();
    }
}