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
		String result = multiGame.startNewGame(playerNames);
	}

	@Test
	public void goodStartMessage() throws Exception {
		String[] playerNames = {"Zorglub", "Albator"};
		String result = multiGame.startNewGame(playerNames);
		assertEquals("Prochain tir : joueur Zorglub, tour n° 1, boule n° 1", result);
	}
	
	@Test
	public void testLancerStrike() throws Exception {
		String[] playerNames = {"Zorglub", "Albator"};
		multiGame.startNewGame(playerNames);
		String result = rollStrike();
		assertEquals("Prochain tir : joueur Albator, tour n° 1, boule n° 1", result);
	}

	@Test( expected = Exception.class )
	public void testGameFinishes() throws Exception {
		String[] playerNames = {"Zorglub", "Albator"};
		multiGame.startNewGame(playerNames);
		// Tout dans la rigole : n joueurs * 10 tours * 2 boules par tour
		String message = rollMany(playerNames.length * 10 * 2, 0);
		assertEquals("Partie terminée", message);
		// Un lancer de trop doit lever une exception
		multiGame.lancer(0);
	}
	
	@Test( expected = Exception.class )
	public void scoreForUnknownPlayer() throws Exception {
		String[] playerNames = {"Zorglub", "Albator"};
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
