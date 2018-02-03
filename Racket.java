//**********************************************************
//
//Author: Cheung Lap Yan
//
//*********************************************************

public class Racket{
	
	// store x and y positions as public so they can also be used by something else
	public float x;
	public float y;
	
	// allocate speed for up/down sliders with initiated
	private float ySpeed = 5;
	
	//size of game 
	public static int width;
	public static int height;
	
	// construct racket
	public Racket(float x, float y){
		//with respect to game dimensions
		this.x = x;
		this.y = y;
	}
	
	// getter for racket x position
	public float getxPosition(){
		return this.x;
	}
	// getter for racket y position
	public float getyPosition(){
		return this.y;
	}
	
	// move up method
	public void up(){
		this.y-=this.ySpeed;
		//boundary check
		if (y<=0){
			this.y=0;
		}
	}
	
	//move down method
	public void down(){
		this.y+=this.ySpeed;
		//boundary check
		if (y>=height-50){
			this.y=height-50;
		}
	}
}