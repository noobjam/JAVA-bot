package com.example.javabot;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ChatController {

    @FXML
    private TextField messageTextField;

    @FXML
    private ScrollPane chatScrollPane;

    @FXML
    private VBox chatVbox;  // Use your existing Pane

    @FXML
    private void initialize() {
        chatScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        // Initialization code, if needed
        // For example, you might initialize the chat area with a welcome message.
//        displayBubble("Welcome to Java-Bot!", null, false);
    }

    @FXML
    private void sendMessage() {
        String userMessage = messageTextField.getText().trim();
        if (!userMessage.isEmpty()) {
            // Display user message in the chat
            displayBubble("You: " + userMessage, null, true);

            // Clear the message input field
            messageTextField.clear();

            // Simulate the chatbot response (replace with actual logic)
            //String botResponse = getChatbotResponse(userMessage);

            // Display the chatbot response in the chat
            //displayBubble("Java-Bot: " + botResponse, null, false);
        }
    }

    private String getChatbotResponse(String userMessage) {
        return "I'm a fast chatbot, processing: " + userMessage;
    }

    private void displayBubble(String message, Image senderImage, boolean incoming) {
        Bubble bubble = new Bubble(message, senderImage, incoming);
        chatVbox.getChildren().add(bubble);

        // Set the vertical position based on the previous HBoxes (Bubbles)
        double bubbleHeight = bubble.getHeight();
        double verticalPosition = chatVbox.getChildren().stream()
                .filter(node -> node instanceof HBox) // Filter only HBoxes (Bubbles)
                .mapToDouble(node -> ((HBox) node).getBoundsInParent().getMaxY())
                .max()
                .orElse(0);

        bubble.setLayoutY(verticalPosition);

        // Scroll to the bottom
        chatScrollPane.setVvalue(verticalPosition);

        //Platform.runLater(() -> chatScrollPane.setVvalue(1.0));
    }


}
