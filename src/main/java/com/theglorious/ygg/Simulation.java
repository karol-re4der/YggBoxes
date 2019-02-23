package com.theglorious.ygg;

/**
 *
 * @author Karol "re4der" Kozak
 */

public class Simulation implements Solution{

    private int simulationRounds = 10000;
    
    public Simulation(int rounds){
	simulationRounds = rounds;
    }
    
    public void setRounds(int rounds){
	simulationRounds = rounds;
    }
    
    @Override
    public int solve(Boxes boxes) {
	long startTime = System.nanoTime();
	int totalReward = 0;
	
	for(int i=0; i<simulationRounds; i++){
	    int oneups = 0;
	    while(true){
		int content = boxes.openBox();
		if(content>=0){
		    totalReward+=content;
		}
		else if(content==-2){
		    if(oneups>0){
			oneups--;
		    }
		    else{
			int extraReward = boxes.openExtraBox();
			if(extraReward>=0){
			    totalReward+=extraReward;
			    boxes.reset();
			    boxes.shuffle();
			    System.out.println("Round "+i+" simulated.");
			    break;
			}
		    }
		}
		else{
		    oneups+=1;
		}
	    }
	}
	
	System.out.println("Simulation finished in "+((System.nanoTime()-startTime)/1000000000.)+"ns");
	return totalReward/simulationRounds;
    }
}
