
//**********************************************************
//
//Author: Cheung Lap Yan
//
//*********************************************************

import processing.core.PApplet;

@SuppressWarnings("serial")
public class MyGame extends PApplet {
	// declare objects and variables
	Racket rR;
	Racket lR;
	pongBall pB;
	boolean lRup;
	boolean lRdown;
	boolean rRup;
	boolean rRdown;

	// game instructions
	String p1Instructions = "Press 'W' key to move up & 'S' key to move down";
	String p2Instructions = "Press 'I' key to move up & 'K' key to move down";

	// winner text
	public String winner;

	// method to determine if keys are pressed to prompt actions
	public void keyPressed() {
		if (key == 'W' || key == 'w') {
			lRup = true;
		}
		if (key == 'S' || key == 's') {
			lRdown = true;
		}
		if (key == 'I' || key == 'i') {
			rRup = true;
		}
		if (key == 'K' || key == 'k') {
			rRdown = true;
		}
	}

	// method to determine if keys are released to end actions
	public void keyReleased() {
		if (key == 'W' || key == 'w') {
			lRup = false;
		}
		if (key == 'S' || key == 's') {
			lRdown = false;
		}
		if (key == 'I' || key == 'i') {
			rRup = false;
		}
		if (key == 'K' || key == 'k') {
			rRdown = false;
		}
	}

	// method to reset game for each turn
	public void reset() {
		pB.x = 320;
		pB.y = 160;
		pB.endloop = false;
	}

	// method to decide winner
	public boolean winner() {
		if (pB.p1Score == 11 || pB.p2Score == 11) {
			if (pB.p1Score > pB.p2Score) {
				winner = "Player 1 wins!";
				return true;
			} else {
				winner = "Player 2 wins!";
				return true;
			}
		}
		return false;
	}

	// setup game environment
	public void setup() {
		// set size of game
		size(640, 320);
		// inform the size of the environment to objects
		Racket.width = 640;
		Racket.height = 320;
		Racket.width = 640;
		Racket.height = 320;
		pongBall.width = 640;
		pongBall.height = 320;
		// set background
		background(0, 0, 0);
		// intialize objects
		this.lR = new Racket(0, 0);
		this.rR = new Racket(620, 0);
		this.pB = new pongBall();
	}

	// implement graphics
	public void draw() {
		// erase background
		background(0);
		smooth();
		// draw dividing line
		stroke(255);
		line(320, 0, 320, 320);
		// create game title
		fill(255);
		rect(276, 10, 70, 40);
		fill(255, 51, 0);
		textSize(25);
		text("Pong!", 278, 40);
		// create player titles
		fill(255);
		text("Player 1", 30, 20);
		text("Player 2", width - 120, 20);
		textSize(15);
		// print game instructions
		text(p1Instructions, 50, 180, 100, 200);
		text(p2Instructions, 480, 180, 100, 400);
		textSize(30);
		rect(215, 160, 235, 50);
		fill(0);
		text("First to 11 wins", 220, 200);

		// prompt slider up/down actions according to keyboard commands
		if (lRup) {
			lR.up();
		}
		if (lRdown) {
			lR.down();
		}
		if (rRup) {
			rR.up();
		}
		if (rRdown) {
			rR.down();
		}

		// get dynamic positions of rackets after movements
		float xLRacketpos = lR.getxPosition();
		float yLRacketpos = lR.getyPosition();
		float xRRacketpos = rR.getxPosition();
		float yRRacketpos = rR.getyPosition();
		// pass variables to ball behavior method
		pB.ballCollision(xLRacketpos, yLRacketpos, xRRacketpos, yRRacketpos);
		// display player scores
		textSize(30);
		fill(255);
		text(pB.p1Score, 235, 40);
		text(pB.p2Score, 365, 40);
		// display rackets
		rect(rR.x, rR.y, 20, 50);
		rect(lR.x, lR.y, 20, 50);
		// display game ball
		noStroke();
		fill(255, 51, 0);
		ellipse(pB.x, pB.y, 30, 30);
		// restart game for every point gained
		if (pB.endloop == true) {
			pB.speedReset();
			reset();
		}
		// stop game and display winner
		if (winner()) {
			stroke(0);
			line(320, 0, 320, 320);
			fill(255);
			pB.stop();
			textSize(50);
			text(winner, 160, 110);
		}
	}

	public static void main(String args[]) {
		PApplet.main(new String[] { "My Game" });
	}
}