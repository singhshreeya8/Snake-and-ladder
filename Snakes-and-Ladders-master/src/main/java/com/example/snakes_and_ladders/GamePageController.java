package com.example.snakes_and_ladders;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class GamePageController {

    @FXML
    public void start(MouseEvent event) throws IOException {
        AnchorPane start = FXMLLoader.load(getClass().getResource("PlayGround.fxml"));
        Main.root.getChildren().setAll(start);
    }
}
