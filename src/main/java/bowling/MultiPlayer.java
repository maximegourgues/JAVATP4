/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bowling;

/**
 *
 * @author pedago
 */
public class MultiPlayer implements MultiPlayerGame {
    
    
    public MultiPlayer(){
       
    }
    
    @Override
    public String startNewGame(String[] playerName) throws Exception {
        
        if (playerName.length == 0){
            throw new java.lang.Exception("Tableau vide ou nul");
        }
        return "Prochain tir : joueur"+playerName[0]+", tour n° 1, boule n° 1";
    
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
