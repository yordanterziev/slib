package slib.graph.algo.utils;

import java.util.ArrayList;

public class SemanticPathValidator {
	
	public SemanticPathValidator(){
	}
	
	public ArrayList<String> dividePath(SemanticPath path){
		ArrayList<String> result = new ArrayList<String>();
		String temp = path.getSemanticPath();
		char lastChar = temp.charAt(0);
		String split = ""+lastChar;
		for(int i =0;i<temp.length();i++){
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
