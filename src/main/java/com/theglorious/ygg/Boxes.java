package com.theglorious.ygg;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

/**
 *
 * @author Karol "re4der" Kozak
 */

public class Boxes {
    private Random rand;
    private final LinkedHashMap<Integer, Integer> template;
    private final LinkedHashMap<Integer, Integer> closed;
    private final LinkedHashMap<Integer, Integer> extra;
    
    public Boxes(){
	rand = new Random();
	rand.setSeed(System.currentTimeMillis());
	
	template = new LinkedHashMap<>();
	closed = new LinkedHashMap<>();
	extra = new LinkedHashMap<>();
	
	template.put(100, 1);
	template.put(20, 2);
	template.put(5, 5);
	template.put(-1, 1); //extra life
	template.put(-2, 3); //game over
	
	extra.put(5, 1);
	extra.put(10, 1);
	extra.put(20, 1);
	extra.put(-3, 1); //reshuffle
	
	reset();
    }
    
    public void reset(){
	closed.putAll(template);
	extra.keySet().forEach((k)->extra.put(k, 1));
    }
    
    public void reshuffle(){
	closed.putAll(template);
    }
    
    public int randomBox(){
	int index = 0;
	int size = 0;
	int content = 0;
	for(int i:closed.values()){
	    size+=i;
	}
	index = rand.nextInt(size);
	int pointer = 0;
	for(Entry e:closed.entrySet()){
	    pointer+=(int)e.getValue();
	    if(pointer>index){
		content = (int)e.getKey();
		break;
	    }
	}
	closed.put(content, closed.get(content)-1);
	
	return content;
    }

    public LinkedHashMap<Integer, Integer> getClosed(){
	return closed;
    }
    
    public LinkedHashMap<Integer, Integer> getExtra(){
	return extra;
    }
    
    public int openExtraBox(){
	int index = 0;
	do{
	    index = rand.nextInt(extra.size());
	} while((int)extra.values().toArray()[index]==0);
	
	int content = (int)extra.keySet().toArray()[index];
	extra.put(content, 0);
	
	if(content<0){
	    reshuffle();
	}
	
	return content;
    }
    
}
