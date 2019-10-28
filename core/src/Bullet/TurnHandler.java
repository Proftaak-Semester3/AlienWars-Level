package Bullet;

import Objects.Player;
import Render.AlienDemo;

public class TurnHandler {

    private Player player1;
    private Player player2;

    public TurnHandler()
    {
        player1 = new Player("alien.png",AlienDemo.WIDTH / 15, AlienDemo.HEIGHT / 20, 0);
        player2 = new Player("alien.png", AlienDemo.WIDTH - (AlienDemo.WIDTH / 15), AlienDemo.HEIGHT / 20, 1);
        currentPlayer = player1;
    }

    private Player currentPlayer;

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

    public boolean player1turn(){
        return currentPlayer.getPlayerNumber() == 0;
    }

}
