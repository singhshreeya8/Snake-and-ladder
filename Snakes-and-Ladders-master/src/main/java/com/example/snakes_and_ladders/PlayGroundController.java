package com.example.snakes_and_ladders;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Pair;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

public class PlayGroundController {

    @FXML
    Text number;
    @FXML
    Text changeturn;
    @FXML
    ImageView player1, player2;
    int turn = 1;

    HashMap<Pair<Double,Double>,Pair<Double,Double>> snakeLadderCoordinateChanges;
    @FXML
    public void roll(MouseEvent event) throws IOException {
        getSnakeLadderCoordinates();
//        System.out.print("Dice is rolling ");
        Random random = new Random();
        int rolling = random.nextInt(6) + 1;

        number.setText("" + rolling);
//        System.out.println(rolling);

        if(turn==1) {
            Pair<Double,Double> moveCoordinates = movement(player1.getTranslateX(),player1.getTranslateY(),rolling);
            player1.setTranslateX(moveCoordinates.getKey());
            player1.setTranslateY(moveCoordinates.getValue());
            if(snakeLadderCoordinateChanges.containsKey(new Pair<>(moveCoordinates.getKey(),moveCoordinates.getValue())))
            {
                player1.setTranslateX(snakeLadderCoordinateChanges.get(new Pair<>(moveCoordinates.getKey(),moveCoordinates.getValue())).getKey());
                player1.setTranslateY(snakeLadderCoordinateChanges.get(new Pair<>(moveCoordinates.getKey(),moveCoordinates.getValue())).getValue());
            }
            checkWin(player1.getTranslateX(),player1.getTranslateY());
        }
        else{
            Pair<Double,Double> moveCoordinates = movement(player2.getTranslateX(),player2.getTranslateY(),rolling);
            player2.setTranslateX(moveCoordinates.getKey());
            player2.setTranslateY(moveCoordinates.getValue());
            if(snakeLadderCoordinateChanges.containsKey(new Pair<>(moveCoordinates.getKey(),moveCoordinates.getValue())))
            {
                player2.setTranslateX(snakeLadderCoordinateChanges.get(new Pair<>(moveCoordinates.getKey(),moveCoordinates.getValue())).getKey());
                player2.setTranslateY(snakeLadderCoordinateChanges.get(new Pair<>(moveCoordinates.getKey(),moveCoordinates.getValue())).getValue());
            }
            checkWin(player2.getTranslateX(),player2.getTranslateY());
        }
        if(rolling != 6) {
            if (turn == 1) {
                turn = 2;
                changeturn.setText("Player 2's turn");
            } else {
                turn = 1;
                changeturn.setText("Player 1's turn");
            }
        }
    }

    Pair<Double,Double> movement(double X, double Y, int rolling)
    {
        double moveX = X;
        double moveY = Y;


        if(moveY % 100 == 0){
            moveX += rolling * 50;
            if(moveX >500)
            {
                moveX = 500 * 2 - moveX + 50;
                moveY -= 50;
            }
        }
        else{
            moveX -= rolling * 50;
            if(moveX<50){
                if(moveY == -450)
                    return (new Pair<>(X,Y));
                moveX = -1 * (moveX - 50);
                moveY -= 50;
            }
        }

        return new Pair<>(moveX,moveY);
    }
    void checkWin(Double X, Double Y) throws IOException {
            if(X==50 && Y == -450)
            {
                Alert winAlert = new Alert(Alert.AlertType.INFORMATION);
                winAlert.setHeaderText("Result");
                if(turn == 1)
                {
                    winAlert.setContentText("Player 1 has won the game!!!");
                }
                else
                {
                    winAlert.setContentText("Player 2 has won the game!!!");
                }
                GamePage page = new GamePage();
                Main.root.getChildren().setAll(page.root);
                winAlert.showAndWait();

            }
    }
    void getSnakeLadderCoordinates(){
        snakeLadderCoordinateChanges = new HashMap<>();
        snakeLadderCoordinateChanges.put(new Pair<>(250.0,0.0), new Pair<>(300.0,-100.0)); //5
        snakeLadderCoordinateChanges.put(new Pair<>(450.0,-50.0), new Pair<>(500.0,-350.0)); //12
        snakeLadderCoordinateChanges.put(new Pair<>(100.0,-50.0), new Pair<>(50.0,-150.0)); //19
        snakeLadderCoordinateChanges.put(new Pair<>(350.0,-100.0), new Pair<>(300.0,0.0)); //27
        snakeLadderCoordinateChanges.put(new Pair<>(400.0,-100.0), new Pair<>(350.0,-250.0)); //28
        snakeLadderCoordinateChanges.put(new Pair<>(250.0,-150.0), new Pair<>(250.0,-350.0)); //36
        snakeLadderCoordinateChanges.put(new Pair<>(150.0,-200.0), new Pair<>(450.0,0.0)); //43
        snakeLadderCoordinateChanges.put(new Pair<>(500.0,-200.0), new Pair<>(500.0,-50.0)); //50
        snakeLadderCoordinateChanges.put(new Pair<>(300.0,-250.0), new Pair<>(350.0,-150.0)); //55
        snakeLadderCoordinateChanges.put(new Pair<>(50.0,-250.0), new Pair<>(100.0,-350.0)); //60
        snakeLadderCoordinateChanges.put(new Pair<>(200.0,-300.0), new Pair<>(100.0,-100.0)); //64
        snakeLadderCoordinateChanges.put(new Pair<>(300.0,-300.0), new Pair<>(350.0,-400.0)); //66
        snakeLadderCoordinateChanges.put(new Pair<>(450.0,-350.0), new Pair<>(500.0,-450.0)); //72
        snakeLadderCoordinateChanges.put(new Pair<>(400.0,-400.0), new Pair<>(500.0,-250.0)); //88
        snakeLadderCoordinateChanges.put(new Pair<>(350.0,-450.0), new Pair<>(400.0,-200.0)); //94
        snakeLadderCoordinateChanges.put(new Pair<>(150.0,-450.0), new Pair<>(150.0,0.0)); //98
    }
}
