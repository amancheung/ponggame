//**********************************************************
//
//Author: Cheung Lap Yan
//
//*********************************************************

public class pongBall {
	// track ball x,y positions
	public float x;
	public float y;
	// variables to track score
	public int p1Score;
	public int p2Score;

	// variables x speed and y speed
	private float xSpeed;
	private float ySpeed;

	// store initial speed so game can reset every turn
	private float xSpeedInitial;
	private float ySpeedInitial;

	// track boundaries
	public static int width;
	public static int height;

	// track restart
	public boolean endloop;

	// constructor
	public pongBall() {
		// store initial position -- CENTER
		this.x = 160;
		this.y = 320;
		// randomize ball direction
		float speed;
		int direction = (int) (1 + Math.random() * 2);
		if (direction == 1) {
			// setup speed
			speed = -4;
		} else {
			speed = 4;
		}
		// store initial speed for reset
		this.xSpeedInitial = speed;
		this.ySpeedInitial = speed;
		// allocate speed for movement
		this.xSpeed = speed;
		this.ySpeed = speed;
	}

	// setup reset speed method for every turn
	public void speedReset() {
		this.xSpeed = this.xSpeedInitial;
		this.ySpeed = this.ySpeedInitial;
	}

	// stop game method
	public void stop() {
		this.xSpeed = 0;
		this.ySpeed = 0;
	}

	// ball behavior method
	public void ballCollision(float xltRacketPos, float yltRacketPos, float xrtRacketPos, float yrtRacketPos) {
		// move ball
		this.x += this.xSpeed;
		this.y += this.ySpeed;
		// on the right side of the game
		if (this.x > width / 2) {
			// if top and bottom boundaries are hit, refract the ball
			if (this.y > height) {
				this.y = height;
				this.ySpeed *= -1;
			}
			if (this.y < 0) {
				this.y = 0;
				this.ySpeed *= -1;
				// if racket is hit, refract the ball
			}
			if (this.x >= xrtRacketPos && (this.y >= yrtRacketPos && this.y <= yrtRacketPos + 50)) {
				this.x = xrtRacketPos;
				this.xSpeed *= -1;
				// if rackets are not hit and ball escapes boundary, add score
				// to opponent and reset
			} else if ((this.x > width)) {
				this.p1Score++;
				endloop = true;
			}
			// on the left side of the game
		} else if (this.x <= width / 2) {
			// if top and bottom boundaries are hit, refract the ball
			if (this.y > height) {
				this.y = height;
				this.ySpeed *= -1;
			}
			if (this.y < 0) {
				this.y = 0;
				this.ySpeed *= -1;
				// if racket is hit, refract the ball
			}
			if (this.x <= 20 && (this.y >= yltRacketPos && this.y <= yltRacketPos + 50)) {
				this.x = 20;
				this.xSpeed *= -1;
				// if rackets are not hit and ball escapes boundary, add score
				// to opponent and reset
			} else if (this.x < 0) {
				this.p2Score++;
				endloop = true;
			}
		}
		// add speed progressively
		if (this.xSpeed < 0 && this.ySpeed < 0) {
			this.xSpeed -= .004;
			this.ySpeed -= .004;
		} else {
			this.xSpeed += .004;
			this.ySpeed += .004;
		}
	}

}
