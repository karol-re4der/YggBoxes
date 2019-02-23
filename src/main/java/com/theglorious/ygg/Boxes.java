package com.theglorious.ygg;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Karol "re4der" Kozak
 */

public class Boxes {
    private final LinkedList<Integer> closed;
    private final LinkedList<Integer> opened;
    private final LinkedList<Integer> extra;
    
    public Boxes(){
	closed = new LinkedList<>();
	opened = new LinkedList<>();
	extra = new LinkedList<>();
	reset();
	shuffle();
    }
    
    public void reset(){
	closed.clear();
	opened.clear();
	extra.clear();
	closed.add(100);
	closed.add(20);
	closed.add(20);
	for(int i = 0; i<5; i++)
	    closed.add(5);
	closed.add(-1); //extra life
	for(int i = 0; i<3; i++)
	    closed.add(-2); //game over
	extra.add(5);
	extra.add(10);
	extra.add(20);
	extra.add(-3); //reshuffle
    }
    
    public void shuffle(){
	closed.addAll(opened);
	opened.clear();
	Collections.shuffle(closed);
	Collections.shuffle(extra);
    }
    
    public int openBox(){
	opened.add(closed.getLast()); //if there is an exception, then the set of boxes is not valid
	closed.removeLast();
	return opened.getLast();
    }
    
    public int openBox(int i){
	opened.add(closed.get(i));
	closed.remove(i);
	return opened.getLast();
    }
    
    public LinkedList<Integer> getOpened(){
	return opened;
    }
    
    public LinkedList<Integer> getClosed(){
	return closed;
    }
    
    public LinkedList<Integer> getExtra(){
	return extra;
    }
    
    public int openExtraBox(){
	int content = extra.pop();
	if(content<0){
	    shuffle();
	}
	return content;
    }
    
}
