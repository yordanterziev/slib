/**
 * 
 */
package slib.graph.algo.utils;

import java.util.ArrayList;

import slib.graph.model.graph.elements.E;

/**
 * @author Florian
 *
 */
public class SemanticPathAdder {
	
	ArrayList<String> uEdges;
	ArrayList<String> dEdges;
	ArrayList<String> hEdges;
	
	public SemanticPathAdder(ArrayList<String> uEdges, ArrayList<String> dEdges, ArrayList<String> hEdges) {
		this.uEdges = uEdges;
		this.dEdges = dEdges;
		this.hEdges = hEdges;
	}
	
	public String getSemanticPath(SemanticPath semanticPath){
		String temp = "";
		ArrayList<E> path = semanticPath.getPath();
		for (int i = 0; i < path.size(); i++) {
			E edge = path.get(i);
			if (uEdges.contains(edge.getURI().getLocalName())) {
				temp = temp + "U";
			} else if (dEdges.contains(edge.getURI().getLocalName())) {
				temp = temp + "D";
			} else {
				temp = temp + "H";
			}
		}
		return temp;
	}

	
}
