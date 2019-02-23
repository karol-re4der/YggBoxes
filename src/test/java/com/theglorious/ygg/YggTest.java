package com.theglorious.ygg;

import junit.framework.TestCase;

/**
 *
 * @author Karol "re4der" Kozak
 */

public class YggTest extends TestCase{
    
    public YggTest() {
	
    }

    @org.junit.Test
    public void testResults() {
	double tolerance = 0.05;
	int sim = new Simulation(100).solve(new Boxes());
	int calc = new Calculation().solve(new Boxes());
	double ratio = (double)sim/calc;
	assertTrue(ratio<1+tolerance && ratio>1-tolerance);
    }
    
}
