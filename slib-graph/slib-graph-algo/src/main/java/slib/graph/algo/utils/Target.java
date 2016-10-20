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
public class Target {
	
	URI node;
	SemanticPath path;
	int hops;
	
	public Target(URI node, E edge, int hops) {
		super();
		this.node = node;
		path = new SemanticPath(edge);

		this.hops = hops;
	}
	
	public Target(URI node, SemanticPath path, int hops) {
		super();
		this.node = node;
		this.path = path;

		this.hops = hops;
	}

	public URI getNode() {
		return node;
	}

	public SemanticPath getPath() {
		return path;
	}

	public int getHops() {
		return hops;
	}
	
	
	
}
