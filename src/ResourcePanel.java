package csusmNav;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * The ResourcePanel class displays campus resources and provides links to detailed information.
 * It integrates JavaFX WebView for opening resource links within the application.
 */
public class ResourcePanel {

    /**
     * Creates a JPanel that displays campus resources.
     *
     * @return JPanel - The resource panel for the application.
     */
    public static JPanel createResourcePanel() {
        // Create the main panel with a BorderLayout
        JPanel resourcePanel = new JPanel(new BorderLayout());
        resourcePanel.setBackground(new Color(248, 248, 248)); // Light gray background

        // Title label at the top of the panel
        JLabel titleLabel = new JLabel("Campus Resource Information", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(new Color(0, 45, 114)); // Deep blue color
        resourcePanel.add(titleLabel, BorderLayout.NORTH);

        // Create a scrollable content area for resource details
        JPanel resourceContentPanel = new JPanel();
        resourceContentPanel.setLayout(new BoxLayout(resourceContentPanel, BoxLayout.Y_AXIS));
        resourceContentPanel.setBackground(new Color(248, 248, 248));

        // Add sample resource details
        List<CougarNav.ResourceData> resources = CougarNav.ResourceData.getResources();
        for (CougarNav.ResourceData resource : resources) {
            resourceContentPanel.add(createResourceDetail(resource));
        }

        JScrollPane scrollPane = new JScrollPane(resourceContentPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        resourcePanel.add(scrollPane, BorderLayout.CENTER);

        return resourcePanel;
    }

    /**
     * Creates a detailed panel for a single resource.
     *
     * @param resource The CougarNavApp.ResourceData object containing details about a campus resource.
     * @return JPanel - A panel displaying the resource details.
     */
    private static JPanel createResourceDetail(CougarNav.ResourceData resource) {
        JPanel detailPanel = new JPanel(new BorderLayout());
        detailPanel.setBackground(Color.WHITE);
        detailPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10),
                BorderFactory.createLineBorder(new Color(220, 220, 220))));

        // Resource name
        JLabel nameLabel = new JLabel(resource.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nameLabel.setForeground(new Color(0, 45, 114)); // Deep blue color
        detailPanel.add(nameLabel, BorderLayout.NORTH);

        // Resource description
        JTextArea descriptionArea = new JTextArea(resource.getDescription());
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 14));
        descriptionArea.setEditable(false);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setLineWrap(true);
        descriptionArea.setBackground(Color.WHITE);
        descriptionArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Add padding
        detailPanel.add(descriptionArea, BorderLayout.CENTER);

        // Button to open the resource link in a WebView
        JButton linkButton = new JButton("Learn More");
        linkButton.setFont(new Font("Arial", Font.PLAIN, 14));
        linkButton.setBackground(new Color(220, 220, 220));
        linkButton.addActionListener(e -> showWebPage(resource.getLink()));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(linkButton);
        detailPanel.add(buttonPanel, BorderLayout.SOUTH);

        return detailPanel;
    }

    /**
     * Opens a WebView in a new JFrame to display a resource's webpage.
     *
     * @param url The URL of the webpage to be displayed.
     */
    private static void showWebPage(String url) {
        // Create a JFrame to host the WebView
        JFrame browserFrame = new JFrame("Resource Viewer");
        browserFrame.setSize(800, 600);
        browserFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Initialize JavaFX within a Swing component
        JFXPanel fxPanel = new JFXPanel();
        browserFrame.add(fxPanel);

        // Load the WebView content on the JavaFX Application Thread
        javafx.application.Platform.runLater(() -> {
            WebView webView = new WebView(); // Create a WebView
            WebEngine webEngine = webView.getEngine();
            webEngine.load(url); // Load the resource URL

            Scene scene = new Scene(webView); // Wrap WebView in a Scene
            fxPanel.setScene(scene); // Set the Scene in the JFXPanel
        });

        browserFrame.setVisible(true); // Show the JFrame
    }
}

