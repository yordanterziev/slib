/**
 * 
 */
package slib.graph.algo.utils;

import java.util.List;

import org.openrdf.model.URI;

import slib.graph.model.graph.elements.E;

/**
 * @author Florian
 * This class represents a target node in a graph search like BFS or DFS.
 */
public class Target implements Cloneable {
	
	private URI node;
	private SemanticPath path;
	private int hops;
	
	public Target(URI node, E edge, int hops) {
		super();
		this.node = node;
		path = new SemanticPath(edge);

		this.hops = hops;
	}
	
	public Target(Target t){
		this.hops = t.hops;
		this.node = t.node;
		this.path = new SemanticPath(t.path.getPath());
		this.path.semanticPath = t.path.semanticPath;
	}
	
	public void setNode(URI node) {
		this.node = node;
	}

	public void setHops(int hops) {
		this.hops = hops;
	}

	public URI getNode() {
		return node;
	}

	public int getHops() {
		return hops;
	}
	
	public SemanticPath getPath() {
		return path;
	}	
	
}
