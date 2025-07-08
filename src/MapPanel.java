package csusmNav;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javax.swing.*;
import java.awt.*;

/**
 * The MapPanel class provides a panel for displaying the campus map.
 * It integrates JavaFX WebView into a Swing-based application.
 */
public class MapPanel {

    /**
     * Creates the Map Panel containing a title and a button to open the map in a WebView.
     *
     * @return JPanel - The map panel for the application.
     */
    public static JPanel createEmbeddedMapPanel() {
        JPanel mapPanel = new JPanel(new BorderLayout());

        // Title Label
        JLabel mapLabel = new JLabel("CSUSM Interactive Map", SwingConstants.CENTER);
        mapLabel.setForeground(Color.BLACK);
        mapLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mapPanel.add(mapLabel, BorderLayout.NORTH);

        // Info Panel with Button
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JLabel instructionLabel = new JLabel("<html>To view the map, click the button below:</html>");
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        instructionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton openMapButton = new JButton("Open Map");
        openMapButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        openMapButton.setBackground(new Color(200, 200, 200)); // Light gray for contrast
        openMapButton.setForeground(Color.BLACK); // Set text color to black
        openMapButton.setFont(new Font("Arial", Font.BOLD, 14));

        // Button ActionListener to open the map in a JavaFX WebView
        openMapButton.addActionListener(e -> openWebView("https://www.google.com/maps/d/u/0/embed?mid=1O7Hn5RetxsbdRTnv9vB8MlwW9bHbOSI"));

        infoPanel.add(instructionLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer
        infoPanel.add(openMapButton);

        mapPanel.add(infoPanel, BorderLayout.CENTER);

        return mapPanel;
    }

    /**
     * Opens a WebView in a new JFrame to display the map.
     *
     * @param url The URL of the map to be displayed.
     */
    private static void openWebView(String url) {
        // Create a JFrame to host the WebView
        JFrame webViewFrame = new JFrame("Campus Map Viewer");
        webViewFrame.setSize(800, 600);
        webViewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Initialize JavaFX within a Swing component
        JFXPanel fxPanel = new JFXPanel();
        webViewFrame.add(fxPanel);

        // Load the WebView content on the JavaFX Application Thread
        javafx.application.Platform.runLater(() -> {
            WebView webView = new WebView(); // Create a WebView
            WebEngine webEngine = webView.getEngine();
            webEngine.load(url); // Load the map URL

            Scene scene = new Scene(webView); // Wrap WebView in a Scene
            fxPanel.setScene(scene); // Set the Scene in the JFXPanel
        });

        webViewFrame.setVisible(true); // Display the JFrame
    }
}
