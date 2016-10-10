package slib.graph.algo.utils;

import java.util.ArrayList;
import java.util.List;

import slib.graph.model.graph.elements.E;


/**
 * @author Florian Jakobs
 * 
 * This class provides a list of edges (path). Furthermore it provides a semantic path in the form of "UDH".
 * It also stores the information gain the path provides.
 */
public class SemanticPath {
	ArrayList<E> path;
	String semanticPath;
	double informationGain;
	
	
	
	public SemanticPath(E edge) {
		super();
		path = new ArrayList<E>();
		path.add(edge);
	}
	
	public SemanticPath(List<E> list){
		super();
		path = new ArrayList<E>();
		path.addAll(list);
	}

	public void addEdge(E edge){
		path.add(edge);
	}
	
	public ArrayList<E> getPath() {
		return path;
	}
	
	public void setSemanticPath(String semanticPath) {
		this.semanticPath = semanticPath;
	}

	public String getSemanticPath() {
		return semanticPath;
	}

	public double getInformationGain() {
		return informationGain;
	}

	public void setInformationGain(double informationGain) {
		this.informationGain = informationGain;
	}

	
	
}
