package com.example.snakes_and_ladders;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static Group root;
    @Override
    public void start(Stage stage) throws IOException {

        root = new Group();
        GamePage page = new GamePage();
        root.getChildren().add(page.root);
        Scene scene = new Scene(root, 800, 500);
        stage.setTitle("Snake and Ladder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}