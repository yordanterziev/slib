/**
 * 
 */
package slib.graph.algo.utils;

import java.util.ArrayList;

/**
 * @author Florian
 *
 */
public class CorrectPaths {
	private static ArrayList<String> correctPaths;
	
	public CorrectPaths(){
		correctPaths = new ArrayList<String>();
		correctPaths.add("U");
		correctPaths.add("UU");
		correctPaths.add("UUU");

		correctPaths.add("UD");
		correctPaths.add("UUD");
		correctPaths.add("UDD");

		correctPaths.add("UH");
		correctPaths.add("UUH");
		correctPaths.add("UHH");

		correctPaths.add("UHD");

		correctPaths.add("D");
		correctPaths.add("DD");
		correctPaths.add("DDD");

		correctPaths.add("DH");
		correctPaths.add("DDH");
		correctPaths.add("DHH");

		correctPaths.add("HD");
		correctPaths.add("HHD");
		correctPaths.add("HDD");

		correctPaths.add("H");
		correctPaths.add("HH");
		correctPaths.add("HHH");
	}
	
	public boolean isSemanticCorrect(String path){
		boolean result = false;
		if (path.length() > 3) {
			path = path.substring(path.length() - 3);
		}
		for (int i = 0; i < correctPaths.size(); i++) {
			result = correctPaths.get(i).equals(path);
			if (result == true) {
				break;
			}
		}
		return result;
	}
	
}
