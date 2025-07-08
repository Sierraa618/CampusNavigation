package csusmNav;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javax.swing.*;

import csusmNav.CougarNav.ResourceData;

import java.awt.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class ResourceDataHelper {

    public static List<ResourceData> getResources() {
        List<ResourceData> resources = new ArrayList<>();

        // Add resources
        resources.add(new ResourceData("Academic Support", "Provides tutoring, workshops, and learning plans.", "(760) 750-4101", "academicsupport@csusm.edu", "https://www.csusm.edu/students/needs/academic-support/index.html"));
        resources.add(new ResourceData("Library Resources", "Access books, research databases, and group study rooms.", "(760) 750-4348", "library@csusm.edu", "https://biblio.csusm.edu"));
        // Add more resources...

        return resources;
    }

    public static void openURL(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to open URL: " + url);
        }
    }

    /**
     * The csusmNav.ResourceDataHelper.MapPanel class provides a panel for displaying an interactive campus map.
     * It integrates JavaFX WebView for modern web-based map rendering within a Swing application.
     */
    public static class MapPanel {

        /**
         * Creates a JPanel that contains a button to open the campus map in a WebView.
         *
         * @return JPanel - The map panel for the application.
         */
        public static JPanel createEmbeddedMapPanel() {
            // Create the main panel with a BorderLayout
            JPanel mapPanel = new JPanel(new BorderLayout());

            // Title label for the panel
            JLabel mapLabel = new JLabel("CSUSM Interactive Map", SwingConstants.CENTER);
            mapLabel.setFont(new Font("Arial", Font.BOLD, 18));
            mapLabel.setForeground(new Color(0, 45, 114)); // Deep blue color
            mapPanel.add(mapLabel, BorderLayout.NORTH);

            // Button to open the map in a WebView
            JButton openMapButton = new JButton("Open Map");
            openMapButton.setFont(new Font("Arial", Font.BOLD, 14));
            openMapButton.setBackground(new Color(200, 200, 200)); // Light gray for contrast
            openMapButton.addActionListener(e -> openWebView("https://www.google.com/maps/d/u/0/embed?mid=1O7Hn5RetxsbdRTnv9vB8MlwW9bHbOSI"));

            // Add the button to the center of the panel
            mapPanel.add(openMapButton, BorderLayout.CENTER);

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

            // Load the WebView content on the JavaFX thread
            javafx.application.Platform.runLater(() -> {
                WebView webView = new WebView(); // Create a WebView
                WebEngine webEngine = webView.getEngine();
                webEngine.load(url); // Load the map URL

                Scene scene = new Scene(webView); // Wrap WebView in a Scene
                fxPanel.setScene(scene); // Set the Scene in the JFXPanel
            });

            webViewFrame.setVisible(true); // Show the JFrame
        }
    }
}
