
package csusmNav;

// JavaFX imports
import javafx.application.Platform; // For JavaFX thread operations
import javafx.embed.swing.JFXPanel; // For embedding JavaFX in Swing
import javafx.scene.Scene; // For JavaFX scene creation
import javafx.scene.web.WebEngine; // For handling web content
import javafx.scene.web.WebView; // For displaying web pages

// Swing imports
import javax.swing.*;
import java.awt.*;

// Other imports
import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("unused")
public class CougarNav {

    public static void main(String[] args) {
        // Initialize the JavaFX runtime (required for JavaFX components)
        javafx.application.Platform.startup(() -> {});

        SwingUtilities.invokeLater(() -> {
            // Show the login screen first
            JFrame loginFrame = createLoginFrame();
            loginFrame.setVisible(true);
        });
    }

    /**
     * Creates a JFrame for user login.
     * Only proceeds to the main application if login is successful.
     */
    private static JFrame createLoginFrame() {
        JFrame loginFrame = new JFrame("Cougar Nav App - Login");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(400, 300);
        loginFrame.setLayout(new BorderLayout());

        // Title
        JLabel titleLabel = new JLabel("Login to Cougar Nav App", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        loginFrame.add(titleLabel, BorderLayout.NORTH);

        // Login form
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JLabel emailLabel = new JLabel("Student Email:");
        JTextField emailField = new JTextField();
        JButton loginButton = new JButton("Login");

        formPanel.add(emailLabel);
        formPanel.add(emailField);
        formPanel.add(new JLabel()); // Spacer
        formPanel.add(loginButton);

        loginFrame.add(formPanel, BorderLayout.CENTER);

        // Login button logic
        loginButton.addActionListener(e -> {
            String email = emailField.getText().trim();
            if (email.endsWith("@csusm.edu")) { // Validate student email
                loginFrame.dispose(); // Close login screen
                SwingUtilities.invokeLater(() -> showMainApp()); // Launch main app
            } else {
                JOptionPane.showMessageDialog(loginFrame, "Invalid email! Use your student email.", "Login Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return loginFrame;
    }

    /**
     * Launches the main application after successful login.
     */
    private static void showMainApp() {
        JFrame mainFrame = new JFrame("Cougar Nav App");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Welcome", createWelcomePanel());
        tabbedPane.addTab("Chat Bot", ChatBotPanel.createEnhancedChatBotPanel());
        tabbedPane.addTab("Campus Map", MapPanel.createEmbeddedMapPanel());
        tabbedPane.addTab("Resources", ResourcePanel.createResourcePanel());

        mainFrame.add(tabbedPane, BorderLayout.CENTER);
        mainFrame.setVisible(true);
    }

    /**
     * Creates the Welcome Panel that introduces users to the Cougar Nav App.
     *
     * @return JPanel - The Welcome Panel component.
     */
    private static JPanel createWelcomePanel() {
        JPanel welcomePanel = new JPanel(new BorderLayout());
        welcomePanel.setBackground(new Color(230, 240, 255)); // Light blue background

        // Header
        JLabel headerLabel = new JLabel("Welcome to Cougar Nav App", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 30));
        headerLabel.setForeground(new Color(0, 45, 114));
        welcomePanel.add(headerLabel, BorderLayout.NORTH);

        // Features section
        JTextArea featuresArea = new JTextArea();
        featuresArea.setText(
                "Features:\n" +
                        "- Chat Bot: Get quick answers about campus facilities.\n" +
                        "- Campus Map: Find buildings and resources interactively.\n" +
                        "- Resources: Access detailed service information.\n\n" +
                        "Navigate the tabs to explore these features!"
        );
        featuresArea.setFont(new Font("Arial", Font.PLAIN, 16));
        featuresArea.setEditable(false);
        featuresArea.setWrapStyleWord(true);
        featuresArea.setLineWrap(true);
        featuresArea.setMargin(new Insets(15, 15, 15, 15));
        welcomePanel.add(new JScrollPane(featuresArea), BorderLayout.CENTER);

        // Image
        JLabel imageLabel = new JLabel(new ImageIcon("csusm_logo.png")); // Replace with your image path
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomePanel.add(imageLabel, BorderLayout.SOUTH);

        return welcomePanel;
    }

    /**
     * The ResourceData class holds information about campus resources.
     */
    public static class ResourceData {
        private final String name;
        private final String description;
        private final String phone;
        private final String email;
        private final String link;

        public ResourceData(String name, String description, String phone, String email, String link) {
            this.name = name;
            this.description = description;
            this.phone = phone;
            this.email = email;
            this.link = link;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public String getPhone() {
            return phone;
        }

        public String getEmail() {
            return email;
        }

        public String getLink() {
            return link;
        }

        public static List<ResourceData> getResources() {
            List<ResourceData> resources = new ArrayList<>();
            resources.add(new ResourceData(
                    "Academic Support",
                    "Provides tutoring, workshops, and academic counseling.",
                    "(760) 750-4101",
                    "academicsupport@csusm.edu",
                    "https://www.csusm.edu/students/needs/academic-support/index.html"
            ));
            resources.add(new ResourceData(
                    "Library Resources",
                    "Access books, research databases, and study rooms.",
                    "(760) 750-4348",
                    "library@csusm.edu",
                    "https://biblio.csusm.edu"
            ));
            return resources;
        }
    }
}
