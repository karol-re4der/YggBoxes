package com.theglorious.ygg;

import java.util.ArrayList;
import javafx.util.Pair;

/**
 *
 * @author Karol "re4der" Kozak
 */

public class Simulation implements Solution{

    private final int simulationRounds = 10000;
    
    @Override
    public int solve(Boxes boxes) {
	int totalReward = 0;
	
	for(int i=0; i<simulationRounds; i++){
	    int lifes = 0;
	    while(true){
		int content = boxes.openBox();
		if(content>=0){
		    totalReward+=content;
		}
		else if(content==-2){
		    if(lifes>0){
			lifes--;
		    }
		    else{
			int extraReward = boxes.openExtraBox();
			if(extraReward>=0){
			    totalReward+=extraReward;
			    boxes.reset();
			    System.out.println("Round "+i+" simulated.");
			    break;
			}
		    }
		}
		else{
		    lifes+=1;
		}
	    }
	}
	
	return totalReward/simulationRounds;
    }
}
