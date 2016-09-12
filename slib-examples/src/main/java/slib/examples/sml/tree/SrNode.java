package slib.examples.sml.tree;

import java.util.ArrayList;

import org.openrdf.model.URI;

public class SrNode {

	private URI data;
	private SrNode parent;
	private ArrayList<SrNode> children;
	private ArrayList<URI> childrenEdges;
	private String semanticRelatedness;

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

	public ArrayList<SrNode> getChildren() {
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
		return (childrenEdges != null);
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
			children = new ArrayList<SrNode>();
		}
		children.add(child);
	}
	
	public void addEdges(URI edge) {
		if (!this.hasChildrenEdges()) {
			childrenEdges = new ArrayList<URI>();
		}
		childrenEdges.add(edge);
	}

	
}
