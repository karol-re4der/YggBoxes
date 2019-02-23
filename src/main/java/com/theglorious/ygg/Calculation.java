package com.theglorious.ygg;

import java.util.LinkedList;
import javafx.util.Pair;

/**
 *
 * @author Karol "re4der" Kozak
 */

public class Calculation implements Solution{

    private LinkedList<Double> cases = new LinkedList();
    private double result = 0;
    
    private int globalWins = 0;
    private int globalDeaths = 0;
    private int globalOneups = 0;
    private double avgWin = 0;
    
    @Override
    public int solve(Boxes boxes) {
	//prepare
	globalWins = 0;
	globalDeaths = 0;
	globalOneups = 0;
	avgWin = 0;
	boxes.getClosed().forEach((k, v)->{
	    if(k>=0){
		globalWins+=v;
		avgWin+=k*v;
	    }
	    else if(k==-2){
		globalDeaths+=v;
	    }
	    else{
		globalOneups+=v;
	    }
	});
	avgWin/=globalWins;
	
	//calculate the cases
	recure(globalWins, globalDeaths, globalOneups, 0, 0, 1);
	
	//summ the cases
	cases.forEach((k)->result+=k);
	
	//extra try
	result+=(1./boxes.getExtra().size())*result;
	
	//extra reward
	boxes.getExtra().forEach((k, v)->{
	    if(k>=0){
		result+=(double)k*(1./(boxes.getExtra().size()-1));
	    }
	});
	
	return (int)result;
    }
    
    private void recure(int winBoxes, int deathBoxes, int oneupBoxes, int currOneups, double currReward, double chance){
	if(winBoxes>0){
	    double newChance = (double) winBoxes/(winBoxes+deathBoxes+oneupBoxes);
	    recure(winBoxes-1, deathBoxes, oneupBoxes, currOneups, currReward+avgWin, chance*newChance);
	}
	if(oneupBoxes>0){
	    double newChance = (double) oneupBoxes/(winBoxes+deathBoxes+oneupBoxes);
	    recure(winBoxes, deathBoxes, oneupBoxes-1, currOneups+1, currReward, chance*newChance);
	}
	if(deathBoxes>0){
	    double newChance = (double) deathBoxes/(winBoxes+deathBoxes+oneupBoxes);
	    if(currOneups>0){
		recure(winBoxes, deathBoxes-1, oneupBoxes, currOneups-1, currReward, chance*newChance);
	    }
	    else{
		cases.add(currReward*chance*newChance);
	    }
	}
    }
}
