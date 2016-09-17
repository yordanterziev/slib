package slib.examples.sml.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.openrdf.model.URI;

/**
 * This class contains the information of {@link SrTree}.
 * A Node contains a Set of children, thus the children are unique.
 * It contains a parent for faster accessing other children of the same parent.
 * It also stores a mapping of edges to targets.
 * A Node also contains a string of characters containing the semantic Path up to the given node.
 * 
 * @author Florian Jakobs
 *
 */
public class SrNode {

	private URI data;
	private SrNode parent;
	private Set<SrNode> children;
	private ArrayList<URI> childrenEdges;
	private String semanticRelatedness;
	//Hashmap<Edge,Target>
	private HashMap<URI,URI> edgeMap;
	
	public SrNode(URI data) {
		this.setData(data);
	}

	public void setData(URI data) {
		this.data = data;
	}

	public URI getData() {
		return data;
	}

	public SrNode getParent() {
		return parent;
	}

	public void setParent(SrNode parent) {
		this.parent = parent;
	}

	public String getSemanticRelatedness() {
		return semanticRelatedness;
	}

	public void setSemanticRelatedness(String semanticRelatedness) {
		this.semanticRelatedness = semanticRelatedness;
	}

	public HashMap<URI, URI> getEdgeMap() {
		return edgeMap;
	}

	public void setEdgeMap(HashMap<URI, URI> edgeMap) {
		this.edgeMap = edgeMap;
	}

	public Set<SrNode> getChildren() {
		if (this.hasChildren()) {
			return children;
		} else {
			return null;
		}
	}

	public ArrayList<URI> getChildrenEdges() {
		return childrenEdges;
	}

	public boolean hasChildren() {
		return (children != null);
	}

	public boolean hasChildrenEdges() {
		return (edgeMap != null);
	}

	public int getNumberOfChildren() {
		if (this.hasChildren()) {
			return children.size();
		} else {
			return 0;
		}
	}

	public void addChild(SrNode child) {
		if (!this.hasChildren()) {
			children = new HashSet<SrNode>();
		}
		children.add(child);
	}
	
	public void addEdges(URI edge,URI target) {
		if (!this.hasChildrenEdges()) {
			edgeMap = new HashMap<URI,URI>();
		}
		edgeMap.put(edge,target);
	}

	
}
