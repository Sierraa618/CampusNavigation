package csusmNav;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

public class ChatBotPanel {

    private static boolean awaitingConfirmation = false; // Tracks if the chatbot is waiting for a yes/no response

    public static JPanel createEnhancedChatBotPanel() {
        JPanel chatBotPanel = new JPanel();
        chatBotPanel.setLayout(new BorderLayout());
        chatBotPanel.setBackground(new Color(0, 45, 114)); // Deep blue for panel background

        // Title
        JLabel title = new JLabel("CSUSM Chat Bot", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        chatBotPanel.add(title, BorderLayout.NORTH);

        // Chat area
        JTextArea chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setBackground(Color.WHITE);
        chatArea.setForeground(Color.BLACK);
        chatArea.setFont(new Font("Arial", Font.PLAIN, 14));
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        chatArea.append("AI: Hello, welcome to CougarNav! I am here to direct you to all the campus resources you need!\n\n"); // Automatic greeting
        JScrollPane chatScrollPane = new JScrollPane(chatArea);

        // Input area
        JTextField inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.PLAIN, 14));
        JButton sendButton = new JButton("Send");
        sendButton.setBackground(new Color(0, 45, 114));
          sendButton.setForeground(Color.BLACK); // Set text color to black
        sendButton.setFont(new Font("Arial", Font.BOLD, 14)); // Ensure the button text is visible and styled
        sendButton.setFocusPainted(false);

        // Input panel
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        // Button action to handle user queries
        sendButton.addActionListener((ActionEvent e) -> {
            String userQuery = inputField.getText().trim();
            if (!userQuery.isEmpty()) {
                chatArea.append("You: " + userQuery + "\n");
                String response = getChatBotResponse(userQuery);
                chatArea.append("AI: " + response + "\n\n");

                // After responding, ask if the user needs further help
                if (!awaitingConfirmation) {
                    chatArea.append("AI: Is there anything else I can help you with? (yes/no)\n\n");
                    awaitingConfirmation = true; // Waiting for yes/no
                }
                inputField.setText("");
                chatArea.setCaretPosition(chatArea.getDocument().getLength()); // Auto-scroll to the bottom
            }
        });

        // Add components to chatBotPanel
        chatBotPanel.add(chatScrollPane, BorderLayout.CENTER);
        chatBotPanel.add(inputPanel, BorderLayout.SOUTH);

        return chatBotPanel;
    }

    // ** Enhanced ChatBot Response Logic **
    private static String getChatBotResponse(String query) {
        // Predefined responses for common campus resources
        Map<String, String> responses = new HashMap<>();

        // Friendly interactions
        responses.put("hello", "Hello! How can I help you today? 😊");
        
        // Academic Support
        responses.put("academic support", "Academic Support provides tutoring, writing workshops, and study plans. Contact (760) 750-4101 or visit https://www.csusm.edu/students/needs/academic-support/index.html.");
        
        // Library
        responses.put("library", "The CSUSM Library offers access to books, databases, and study rooms. Contact (760) 750-4348 or visit https://biblio.csusm.edu.");
        
        // Career Services
        responses.put("career services", "Career Services helps with resumes, job searches, and networking. Contact (760) 750-4900 or visit https://www.csusm.edu/careers/index.html.");
        
        // Financial Aid
        responses.put("financial aid", "Financial Aid assists with scholarships, grants, and loans. Contact (760) 750-4850 or visit https://www.csusm.edu/finaid/index.html.");
        
        // Parking
        responses.put("parking", "Parking Services offers permits, shuttles, and carpooling info. Contact (760) 750-7500 or visit https://www.csusm.edu/parking.");
        
        // Health and Safety
        responses.put("health and safety", "Health and Safety includes emergency contacts and resources. Contact (760) 750-4567 or visit https://www.csusm.edu/healthsafety.");
        
        // Mental Health
        responses.put("mental health", "Mental Health Services provide free counseling and stress workshops. Contact (760) 750-4915 or visit https://www.csusm.edu/shcs/index.html.");
        
        // Dining
        responses.put("dining", "Dining Services offer various food options, including meal plans. Contact (760) 750-4785 or visit https://www.csusm.edu/dining/index.html.");
        
        // Buildings
        responses.put("buildings", "For building locations, use the CSUSM map or contact (760) 750-4500.");
        
        // Study Spaces
        responses.put("study spaces", "Study rooms and online class spaces are available. Visit https://www.csusm.edu/studyspaces or contact (760) 750-4348.");
        
        // Student Organizations
        responses.put("student organizations", "Join clubs and organizations to enhance your campus experience. Visit https://www.csusm.edu/orgs/index.html or contact (760) 750-4970.");
        
        // Disability Services
        responses.put("disability services", "Disability Support Services provide accommodations and advocacy. Contact (760) 750-4905 or visit https://www.csusm.edu/dss.");

        

        // Default response if no match found
        String defaultResponse = "I'm sorry, I couldn't find information on that. Try asking about campus resources like 'library,' 'academic support,' or 'parking.' Visit the CSUSM website at https://www.csusm.edu for more details.";

        // Find the best-matching response
        query = query.toLowerCase();
        for (Map.Entry<String, String> entry : responses.entrySet()) {
            if (query.contains(entry.getKey())) {
                return entry.getValue();
            }
        }

        return defaultResponse;
    }
}
