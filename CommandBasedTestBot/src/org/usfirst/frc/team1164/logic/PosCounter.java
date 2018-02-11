package org.usfirst.frc.team1164.logic;
//import edu.wpi.first.wpilibj.PIDController;
public class PosCounter {

	private int endPoint;
	private int curPosition;
	private int itr_num;
	private int lastItrPos;
	private int lastItrNeg;
	private int gap;
	
	
	public PosCounter(int Gap) {
		// TODO Auto-generated constructor stub
		gap = Gap;
	}
	
	public void setPoint(int EndPoint){
		endPoint = EndPoint;
	}
	
	public double PIDGet(){
		itr_num++;
		if (curPosition < endPoint) {						//if we have to go foward
			if (itr_num - lastItrNeg >= gap) {  //this will check how long it has been since we went back
				curPosition++;					//if it was less than the allotted gap, it will reset the curPosition and lastitrpos and return 1
				lastItrPos = itr_num;
				return 1;
			} else {
				return 0;
			}
		}
		else if (curPosition == endPoint){				//if we are at the endPoint
			return 0;
		}
		else {											// having to go backwards
			if (itr_num-lastItrPos >= gap) {			//same as above, but for negative instead of positive
				curPosition--;
				lastItrNeg = itr_num;
				return -1;
			} else {
				return 0;
			}
		}
	}


}