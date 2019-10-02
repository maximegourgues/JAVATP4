/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bowling;

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
/**
 *
 * @author pedago
 */
public class MultiPlayer implements MultiPlayerGame {
    private static final String TURN = "Prochain tir : joueur %s, tour n° %d, boule n° %d";
    private static final String END = "Partie terminée";
    private final Map<String, SinglePlayerGame> games;
    private Iterator<String> player;
    
    private String currentPlayer;
    private SinglePlayerGame currentGame;
    private boolean gameFinished;
    
    public MultiPlayer(){
          games = new HashMap<>();
    }
    
    @Override
    public String startNewGame(String[] playerName) throws Exception {
        
        if (playerName.length == 0){
            throw new java.lang.Exception("Aucun joueur n'est enregistré");
        }
        for(String name : playerName){
            games.put(name,new SinglePlayerGame());
        }
        
        player = games.keySet().iterator();
        gameFinished = false;

        joueurSuivant();
        
        
        return Message();
        
    
    }
    
    @Override
    public String lancer(int nombreDeQuillesAbattues) throws Exception {
        if(gameFinished){
            throw new java.lang.Exception(END);
        }
        currentGame.lancer(nombreDeQuillesAbattues);
        
        if (currentGame.hasCompletedFrame() || currentGame.isFinished()){
            joueurSuivant();
        }
        
       return Message();
    }

    @Override
    public int scoreFor(String playerName) throws Exception {
         if(!games.containsKey(playerName)){
            throw new Exception("Aucune partie trouvée pour ce joueur");
        }
        SinglePlayerGame scoredGame = games.get(playerName);
        return scoredGame.score();
             
    }
    
    public String Message(){
        if (gameFinished){
            return(END);
        }
        else {
            SinglePlayerGame curGame=currentGame;            
            return(String.format(TURN,currentPlayer,curGame.getFrameNumber() , curGame.getNextBallNumber()));
        }
    }
    
    public void joueurSuivant(){
        if (!player.hasNext()){
            if (currentGame.isFinished()){
                gameFinished = true;
            }else{
                player = games.keySet().iterator();
            }          
        }
        currentPlayer=player.next();
        currentGame=games.get(currentPlayer);
    }
    
}
