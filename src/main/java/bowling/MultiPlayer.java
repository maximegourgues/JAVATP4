/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bowling;

import java.util.HashMap;
import java.util.Map;
import bowling.SinglePlayerGame;
import java.util.Iterator;
/**
 *
 * @author pedago
 */
public class MultiPlayer implements MultiPlayerGame {
    private static final String TURN = "Prochain tir : joueur %s, tour n° %d, boule n° %d";
    private final Map<String, SinglePlayerGame> games;
    private Iterator<String> player;
    
    private String currentPlayer;
    
    
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
        if (!player.hasNext()){
            player =games.keySet().iterator();
        }
        currentPlayer=player.next();
        return null;
        
    
    }
    
    @Override
    public String lancer(int nombreDeQuillesAbattues) throws Exception {
        return "1";
    }

    @Override
    public int scoreFor(String playerName) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
