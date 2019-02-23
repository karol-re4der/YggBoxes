package com.theglorious.ygg;

/**
 *
 * @author Karol "re4der" Kozak
 */

public class Ygg {
    public static void main(String[] args){
	Solution sol = new Simulation();
	System.out.println("Simulated reward per round: "+sol.solve(new Boxes()));
	
	sol = new Calculation();
	System.out.println("Calculated reward per round: "+sol.solve(new Boxes()));
	
	
    }
}
