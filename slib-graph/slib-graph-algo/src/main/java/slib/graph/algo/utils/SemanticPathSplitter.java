package slib.graph.algo.utils;

import java.util.ArrayList;

/**
 * Splits a given {@link Semantic Path} in consecutive upward downard and horizontal edges.
 * 
 * @author Florian Jakobs
 *
 */
public class SemanticPathSplitter {
	
	/**
	 * 
	 */
	public SemanticPathSplitter(){
	}
	
	/**
	 * Splits the path in consecutive segments of the same edges
	 * @param path the path to be split
	 * @return an ArrayList of split paths
	 */
	public ArrayList<String> dividePath(SemanticPath path){
		ArrayList<String> result = new ArrayList<String>();
		String temp = path.getSemanticPath();
		char lastChar = temp.charAt(0);
		String split = ""+lastChar;
		for(int i =1;i<temp.length();i++){
			if(lastChar==temp.charAt(i)){
				split = split + temp.charAt(i);
			}else{
				result.add(split);
				lastChar = temp.charAt(i);
				split = ""+lastChar;
			}
		}
		result.add(split);
		return result;
	}
	
	
}
