package org.usfirst.frc.team1164.logic;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class PosCounter implements PIDSource {
	
	private int endPoint;
	private int curPosition;
	private int itrNum;
	private int lastItrPos;
	private int lastItrNeg;
	private int gap;
	
	public PosCounter(int gap) {
		this.gap = gap;
	}
	
	public void setEndPoint(int endPoint) {
		this.endPoint = endPoint;
	}
	
	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double pidGet() {
		itrNum++;
		if (curPosition < endPoint) {
			if (itrNum - lastItrNeg >= gap) {
				curPosition = itrNum;
				lastItrPos = itrNum;
				return 1;
			}
			else {
				return 0;
			}
		}
		else if (curPosition == endPoint) {
			return 0;
		}
		else {
			if (itrNum - lastItrPos >= gap) {
				curPosition--;
				lastItrNeg = itrNum;
				return -1;
			}
			else {
				return 0;
			}
		}
	}
}
