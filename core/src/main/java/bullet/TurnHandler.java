package bullet;

import objects.Player;
import render.AlienDemo;

public class TurnHandler {

    private Player player1;
    private Player player2;

    public TurnHandler()
    {
        player1 = new Player((AlienDemo.WIDTH / 10), (AlienDemo.HEIGHT / 7), 0);
        player2 = new Player((AlienDemo.WIDTH - (AlienDemo.WIDTH / 5)), (AlienDemo.HEIGHT / 7), 1);
        player1.setTexture();
        player2.setTexture();
        currentPlayer = player1;
    }

    private Player currentPlayer;

    public Player getCurrentPlayer(){
        return currentPlayer;
    }

    public void switchTurn(){
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

    public boolean player1turn(int playernumber){
        return (currentPlayer.getPlayerNumber() == playernumber);
    }

}
