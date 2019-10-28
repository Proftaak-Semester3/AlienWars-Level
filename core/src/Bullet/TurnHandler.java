package Bullet;

import Objects.Player;
import Render.AlienDemo;

public class TurnHandler {
    private Player player1 = new Player("Bird.png",AlienDemo.WIDTH / 20, AlienDemo.HEIGHT / 20, 0);
    private Player player2 = new Player("Ornstein.jpg", AlienDemo.WIDTH - (AlienDemo.WIDTH / 20), AlienDemo.HEIGHT / 20, 1);

    private Player currentPlayer = player1;

    public Player GetCurrentPlayer(){
        return currentPlayer;
    }

    public void SwitchTurn(){
        if(currentPlayer == player1){
            currentPlayer = player2;
        }
        else {
            currentPlayer = player1;
        }
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

}
