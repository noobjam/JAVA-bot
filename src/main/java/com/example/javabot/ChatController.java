package com.example.javabot;

import org.json.JSONObject;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ChatController {

    @FXML
    private TextField messageTextField;

    @FXML
    private ScrollPane chatScrollPane;

    @FXML
    private VBox chatVbox;

    @FXML
    private void initialize() {
        chatScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
    }

    @FXML
    private void sendMessage() {
        String userMessage = messageTextField.getText().trim();
        if (!userMessage.isEmpty()) {
            // Display user message in the chat
            displayUserBubble("You: " + userMessage);

            // Clear the message input field
            messageTextField.clear();

            // Asynchronously send the user message to the server and update UI with server response
            new Thread(() -> sendToFastAPI(userMessage)).start();
        }
    }

    private void displayUserBubble(String message) {
        Bubble bubble = new Bubble(message, null, false);
        //bubble.setAlignment(Pos.TOP_LEFT);
        bubble.setStyle("-fx-background-color: lightgray; -fx-padding: 10px; -fx-background-radius: 10;");
        displayBubble(bubble, true);
    }

    private void displayApiBubble(String message) {
        Bubble bubble = new Bubble(message, null, true);
        //bubble.setAlignment(Pos.TOP_RIGHT);
        bubble.setStyle("-fx-background-color: #9EDBF0; -fx-padding: 10px; -fx-background-radius: 10;");
        displayBubble(bubble, false);
    }

    private void displayBubble(Bubble bubble, boolean isUserBubble) {
        chatVbox.getChildren().add(bubble);

        // Set the vertical position based on the previous HBoxes (Bubbles)
        double verticalPosition = chatVbox.getChildren().stream()
                .filter(node -> node instanceof HBox) // Filter only HBoxes (Bubbles)
                .mapToDouble(node -> ((HBox) node).getBoundsInParent().getMaxY())
                .max()
                .orElse(0);

        // Set the horizontal alignment to CENTER
        bubble.setAlignment(Pos.CENTER);

        bubble.setLayoutY(verticalPosition);

        // Scroll to the bottom
        chatScrollPane.setVvalue(chatScrollPane.getVmax());
    }


    private void sendToFastAPI(String userMessage) {
        try {
            String apiUrl = "http://127.0.0.1:8000/process_query";
            String encodedQuery = URLEncoder.encode(userMessage, StandardCharsets.UTF_8.toString());

            URL url = new URL(apiUrl + "?query=" + encodedQuery);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            // Read the server response asynchronously
            new Thread(() -> {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }

                    // Parse the JSON response
                    JSONObject jsonResponse = new JSONObject(response.toString());
                    String apiResponse = jsonResponse.optString("response", "");

                    // Display the chatbot response in the chat
                    Platform.runLater(() -> displayApiBubble("Java-Bot: " + apiResponse));
                } catch (IOException e) {
                    e.printStackTrace();
                    // Handle the exception as needed
                }
            }).start();

            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }
}
