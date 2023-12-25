package com.example.javabot;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class Bubble extends HBox {

    public Bubble(String message, Image senderImage, boolean incoming) {
        Text text = new Text(message);
        text.setWrappingWidth(400); // Set a maximum width for wrapping text

        ImageView imageView = new ImageView(senderImage);
        imageView.setFitWidth(30); // Set the width of the sender's image
        imageView.setFitHeight(30); // Set the height of the sender's image
        imageView.setPreserveRatio(true);
        this.getChildren().addAll(incoming ? text : imageView, incoming ? imageView : text);
        //setStyle("-fx-background-color: " + (incoming ? "lightgray" : "#9EDBF0") + "; " +
          //      "-fx-padding: 10px; " +
            //    "-fx-background-radius: 10;");

        // Adjust the margin to create an indentation effect
        Insets margin = new Insets(5, incoming ? 5 : 0, 5, incoming ? 0 : 5);
        setMargin(this, margin);
    }
}
