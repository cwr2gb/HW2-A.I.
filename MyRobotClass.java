import java.awt.List;
import java.awt.Point;
import java.util.ArrayList;

import world.Robot;
import world.World;

/**
 * You need to extend the Robot class to make your own robot!
 **/
public class MyRobotClass extends Robot {
	private double startx, starty, endx, endy;
	/**
	 * You will need to override and implement the travelToDestination() method.
	 * This method will be your path finding. 
	 **/
	public MyRobotClass(double xx, double yy, double x, double y){
		startx = xx;
		starty = yy;
		endx = x;
		endy = y;
	}
	
	public double getEndX(){
		return endx;
	}
	
	public double getEndY(){
		return endy;
	}
	
	public double getStartX(){
		return startx;
	}
	
	public double getStartY(){
		return starty;
	}
	
	public double Distance(int a, int b, int c, int d){
		return Math.sqrt(Math.pow((a-c),2)+Math.pow((b-d),2));
	}
	
	public Point Best(int x, int y){
		Point best = new Point(x,y);
		return best;
	}
	
	@Override
	public void travelToDestination() {
		//ArrayList that holds the Solution in which to move
		ArrayList solution = new ArrayList();
		
		//ArrayList that holds the Open Points
		ArrayList open = new ArrayList();
		
		//ArrayList that holds the Closed Points
		ArrayList closed = new ArrayList();
		
		//Move Values for Both X and Y
		int XMove = (int)getStartX();
		int YMove = (int)getStartY();		
		int count = 0;
		
		//Prints out the Start Point
		//System.out.println("Start X: " + XMove + " Start Y: " + YMove);
		
		//Start Values for Both X and Y
		int Xend = (int)getEndX();
		int Yend = (int)getEndY();			
		
		//Prints out the End Point
		//System.out.println("End X: " + Xend + " End Y: " + Yend);
		
		//Adds the starting point to the open ArrayList
		open.add(super.getPosition());
		
		//Main while loop that fills the solution ArrayList with valid moves
		while(open.size() > 0){
			
			Point curr = (Point) open.get(0);

			if(pingMap(curr).equals("F")){
				break;
			}

						
			//Diagonal Downward Right Move
			if(!closed.contains(new Point(XMove+1, YMove+1)) && ((Distance(XMove, YMove, Xend, Yend) > Distance(XMove+1, YMove+1, Xend, Yend))) && pingMap(new Point(XMove+1, YMove+1)) != null && (pingMap(new Point(XMove+1, YMove+1)).equals("O") || pingMap(new Point(XMove+1, YMove+1)).equals("F"))){
				closed.add(new Point(XMove, YMove));
				open.remove(0);
				open.add(new Point(XMove+1, YMove+1));
				solution.add(new Point(XMove+1, YMove+1));
				YMove += 1;
				XMove += 1;
				count = 1;			
			}
			//Diagonal Upward Right Move	
			else if(!closed.contains(new Point(XMove-1, YMove+1))&&((Distance(XMove, YMove, Xend, Yend) > Distance(XMove-1, YMove+1, Xend, Yend))) && pingMap(new Point(XMove-1, YMove+1)) != null && (pingMap(new Point(XMove-1, YMove+1)).equals("O") || pingMap(new Point(XMove-1, YMove+1)).equals("F"))){
					closed.add(new Point(XMove, YMove));
					open.remove(0);
					open.add(new Point(XMove-1, YMove+1));
					solution.add(new Point(XMove-1, YMove+1));
					YMove += 1;
					XMove -= 1;
					count = 1;
			}			
			//Diagonal Upward Left Move
			else if(!closed.contains(new Point(XMove-1, YMove-1))&&(Distance(XMove, YMove, Xend, Yend) > Distance(XMove-1, YMove-1, Xend, Yend)) && pingMap(new Point(XMove-1, YMove-1)) != null && (pingMap(new Point(XMove-1, YMove-1)).equals("O") || pingMap(new Point(XMove-1, YMove-1)).equals("F"))){
					closed.add(new Point(XMove, YMove));
					open.remove(0);
					open.add(new Point(XMove-1, YMove-1));
					solution.add(new Point(XMove-1, YMove-1));
					YMove -= 1;
					XMove -= 1;
					count = 1;
			}
			
			//Diagonal Downward Left Move
			else if(!closed.contains(new Point(XMove+1, YMove-1))&&(Distance(XMove, YMove, Xend, Yend) > Distance(XMove+1, YMove-1, Xend, Yend)) && pingMap(new Point(XMove+1, YMove-1)) != null && (pingMap(new Point(XMove+1, YMove-1)).equals("O") || pingMap(new Point(XMove+1, YMove-1)).equals("F"))){
					closed.add(new Point(XMove, YMove));
					open.remove(0);
					open.add(new Point(XMove+1, YMove-1));
					solution.add(new Point(XMove+1, YMove-1));
					YMove -= 1;
					XMove += 1;
					count = 1;
			}
			
			//Horizontal Forward Move
			else if(!closed.contains(new Point(XMove, YMove+1))&&(Distance(XMove, YMove, Xend, Yend) > Distance(XMove, YMove+1, Xend, Yend)) && (pingMap(new Point(XMove, YMove+1)).equals("O") || pingMap(new Point(XMove, YMove+1)).equals("F"))){
					closed.add(new Point(XMove, YMove));
					open.remove(0);
					open.add(new Point(XMove, YMove+1));
					solution.add(new Point(XMove, YMove+1));
					YMove += 1;
					count = 1;
			}
			
			//Horizontal Backward Move
			else if(!closed.contains(new Point(XMove, YMove-1))&&(Distance(XMove, YMove, Xend, Yend) > Distance(XMove, YMove-1, Xend, Yend)) && (pingMap(new Point(XMove, YMove-1)).equals("O") || pingMap(new Point(XMove, YMove-1)).equals("F"))){
					closed.add(new Point(XMove, YMove));
					open.remove(0);
					open.add(new Point(XMove, YMove-1));
					solution.add(new Point(XMove, YMove-1));
					YMove -= 1;
					count = 1;
			}		
			//Vertical Upward Move
			else if(!closed.contains(new Point(XMove-1, YMove))&&pingMap(new Point(XMove-1, YMove)) != null && (Distance(XMove, YMove, Xend, Yend) > Distance(XMove-1, YMove, Xend, Yend)) && (pingMap(new Point(XMove-1, YMove)).equals("O") || pingMap(new Point(XMove-1, YMove)).equals("F"))){
					closed.add(new Point(XMove, YMove));
					open.remove(0);
					open.add(new Point(XMove-1, YMove));
					solution.add(new Point(XMove-1, YMove));
					XMove -= 1;
			}
			//Vertical Downward Move
			else if(!closed.contains(new Point(XMove+1, YMove))&&(Distance(XMove, YMove, Xend, Yend) > Distance(XMove+1, YMove, Xend, Yend)) && (pingMap(new Point(XMove+1, YMove)).equals("O") || pingMap(new Point(XMove+1, YMove)).equals("F"))){
					closed.add(new Point(XMove, YMove));
					open.remove(0);
					open.add(new Point(XMove+1, YMove));
					solution.add(new Point(XMove+1, YMove));
					XMove += 1;
			}
			if(count == 0){
			//Diagonal Downward Right Move
			if(!closed.contains(new Point(XMove+1, YMove+1))&&(Distance(XMove, YMove, Xend, Yend) > Distance(XMove+1, YMove+1, Xend, Yend)) && pingMap(new Point(XMove+1, YMove+1)) != null && (pingMap(new Point(XMove+1, YMove+1)).equals("O") || pingMap(new Point(XMove+1, YMove+1)).equals("F"))){
					closed.add(new Point(XMove, YMove));
					open.remove(0);
					open.add(new Point(XMove+1, YMove+1));
					solution.add(new Point(XMove+1, YMove+1));
					YMove += 1;
					XMove += 1;
			}
			//Diagonal Upward Right Move
			else if(!closed.contains(new Point(XMove-1, YMove+1))&&pingMap(new Point(XMove-1, YMove+1)) != null && (pingMap(new Point(XMove-1, YMove+1)).equals("O") || pingMap(new Point(XMove-1, YMove+1)).equals("F"))){
					closed.add(new Point(XMove, YMove));
					open.remove(0);
					open.add(new Point(XMove-1, YMove+1));
					solution.add(new Point(XMove-1, YMove+1));
					YMove += 1;
					XMove -= 1;
			}
			
			//Diagonal Upward Left Move
			else if(!closed.contains(new Point(XMove-1, YMove-1))&&pingMap(new Point(XMove-1, YMove-1)) != null && (pingMap(new Point(XMove-1, YMove-1)).equals("O") || pingMap(new Point(XMove-1, YMove-1)).equals("F"))){
					closed.add(new Point(XMove, YMove));
					open.remove(0);
					open.add(new Point(XMove-1, YMove-1));
					solution.add(new Point(XMove-1, YMove-1));
					YMove -= 1;
					XMove -= 1;
			}
			
			//Diagonal Downward Left Move
			else if(!closed.contains(new Point(XMove+1, YMove-1))&&pingMap(new Point(XMove+1, YMove-1)) != null && (pingMap(new Point(XMove+1, YMove-1)).equals("O") || pingMap(new Point(XMove+1, YMove-1)).equals("F"))){
					closed.add(new Point(XMove, YMove));
					open.remove(0);
					open.add(new Point(XMove+1, YMove-1));
					solution.add(new Point(XMove+1, YMove-1));
					YMove -= 1;
					XMove += 1;
			}
			
			//Horizontal Forward Move
			else if(!closed.contains(new Point(XMove, YMove+1))&&pingMap(new Point(XMove, YMove+1)) != null && (pingMap(new Point(XMove, YMove+1)).equals("O") || pingMap(new Point(XMove, YMove+1)).equals("F"))){
					closed.add(new Point(XMove, YMove));
					open.remove(0);
					open.add(new Point(XMove, YMove+1));
					solution.add(new Point(XMove, YMove+1));
					YMove += 1;
			}			
			//Horizontal Backward Move
			else if(!closed.contains(new Point(XMove, YMove-1))&&pingMap(new Point(XMove, YMove-1)) != null && (pingMap(new Point(XMove, YMove-1)).equals("O") || pingMap(new Point(XMove, YMove-1)).equals("F"))){
					closed.add(new Point(XMove, YMove));
					open.remove(0);
					open.add(new Point(XMove, YMove-1));
					solution.add(new Point(XMove, YMove-1));
					YMove -= 1;
			}
			//Vertical Upward Move
			else if(!closed.contains(new Point(XMove-1, YMove))&&pingMap(new Point(XMove-1, YMove)) != null && (pingMap(new Point(XMove-1, YMove)).equals("O") || pingMap(new Point(XMove-1, YMove)).equals("F"))){
					closed.add(new Point(XMove, YMove));
					open.remove(0);
					open.add(new Point(XMove-1, YMove));
					solution.add(new Point(XMove-1, YMove));
					XMove -= 1;
			}
			//Vertical Downward Move
			else if(!closed.contains(new Point(XMove+1, YMove))&&pingMap(new Point(XMove+1, YMove)) != null && (pingMap(new Point(XMove+1, YMove)).equals("O") || pingMap(new Point(XMove+1, YMove)).equals("F"))){
					closed.add(new Point(XMove, YMove));
					open.remove(0);
					open.add(new Point(XMove+1, YMove));
					solution.add(new Point(XMove+1, YMove));
					XMove += 1;
			}
		}
			count = 0;
		}
		
		//Moves the robot through the designated path
		for(int x = 0; x < solution.size(); x++){
			super.move((Point)solution.get(x));
		}

		
		if((super.getX() != Xend) && (super.getY() != Yend)){
			travelToDestination();
		}
	}
	
	public static void main(String[] args) {
		try{
			/* Create a world. Pass the input filename first. Second parameter...*/
			/* ...is whether or not the world is uncertain*/
			World myWorld = new World("myInputFile10.txt", false);
			
			/* Create a robot that will run around in the World */
			MyRobotClass myRobot = new MyRobotClass(myWorld.getStartPos().getX(), myWorld.getStartPos().getY(), myWorld.getEndPos().getX(), myWorld.getEndPos().getY());
			myRobot.addToWorld(myWorld);		
			
			/* Tell the robot to travel to the destination */
			/* you will be implementing this method yourself! */
			myRobot.travelToDestination();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}
}
