package com.example.javabot;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class Bubble extends HBox {

    public Bubble(String message, Image senderImage, boolean incoming) {
        Text text = new Text(message);
        text.setWrappingWidth(200); // Set a maximum width for wrapping text

        ImageView imageView = new ImageView(senderImage);
        imageView.setFitWidth(30); // Set the width of the sender's image

        this.getChildren().addAll(incoming ? text : imageView, incoming ? imageView : text);
        this.setAlignment(incoming ? Pos.TOP_LEFT : Pos.TOP_RIGHT);
        this.setStyle("-fx-background-color: " + (incoming ? "lightgray" : "#9EDBF0") + "; " +
                "-fx-padding: 10px; " +
                "-fx-background-radius: 10;");

        // Adjust the margin to create an indentation effect
        this.setMargin(this, new Insets(5, incoming ? 5 : 0, 5, incoming ? 0 : 5));
    }
}


