/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bowling;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;


public class MultiPlayerTest {
	private MultiPlayer multiGame;
	
	@Before
	public void setUp() {
		multiGame = new MultiPlayer();
	}
	
        
	@Test (expected = Exception.class)
	public void needOnePlayer() throws Exception {
		String[] playerNames = {};
                // Pour commencer une partie, il faut renseigner au moins un joueur
		String res = multiGame.startNewGame(playerNames);
	}

	@Test
	public void goodStartMessage() throws Exception {
		String[] playerNames = {"Goku", "Vegeta"};
		String res = multiGame.startNewGame(playerNames);
                //Verifie si le premier message affiché est correcte
		assertEquals("Prochain tir : joueur Goku, tour n° 1, boule n° 1", res);
	}
	@Test( expected = Exception.class )
        public void goodEndMessage() throws Exception {
            String[] playerNames = {"Goku","Vegeta"};
            multiGame.startNewGame(playerNames);
            // Chaque joueur font 10 tours sans marquer de points (10 tours * 2 boules * 0);
            String mess = rollMany(playerNames.length * 10 * 2, 0);
            // Verifie si le message de fin de partie est affiché lorsque toutes les boules ont été lancées
            assertEquals("Partie terminée", mess);
        }
	
	@Test( expected = Exception.class )
	public void scoreJoueurInconnu() throws Exception {
		String[] playerNames = {"Goku", "Vegeta"};
		multiGame.startNewGame(playerNames);
		int score = multiGame.scoreFor("Unknown");
	}

	// Quelques methodes utilitaires pour faciliter l'écriture des tests
	private String rollMany(int n, int pins) throws Exception {
		String result = "";
		for (int i = 0; i < n; i++) {
			result= multiGame.lancer(pins);
		}
		return result;
	}

	private String rollSpare() throws Exception {
		multiGame.lancer(7);
		return multiGame.lancer(3);
	}

	private String rollStrike() throws Exception {
		return multiGame.lancer(10);
	}	


}
