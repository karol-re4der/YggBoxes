package com.theglorious.ygg;

/**
 *
 * @author Karol "re4der" Kozak
 */

public class Ygg {
    public static void main(String[] args){
	Solution sol = new Simulation(10000000);
	//System.out.print("Simulated reward per round: ");
	System.out.println(sol.solve(new Boxes()));
	
	sol = new Calculation();
	//System.out.print("Calculated reward per round: ");
	System.out.println(sol.solve(new Boxes()));
    }
}
