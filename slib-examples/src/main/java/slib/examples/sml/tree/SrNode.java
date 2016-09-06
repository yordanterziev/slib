package slib.examples.sml.tree;

import java.util.ArrayList;

import org.openrdf.model.URI;

public class SrNode {

	private URI data;
	private SrNode parent;
	private ArrayList<SrNode> children;
	private ArrayList<URI> childrenEdges;

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

	public void instertChildAt(int index, SrNode child) {
		if (!this.hasChildren()) {
			this.addChild(child);
		} else if ((this.hasChildren()) & (index >= this.getNumberOfChildren())) {
			children.get(children.size() - 1).addChild(child);
		} else if ((this.hasChildren()) & (index < this.getNumberOfChildren())) {
			children.get(index).addChild(child);
		}
	}

	public void removeChildAt(int index) {
		if (!this.hasChildren()) {
			System.out.println("Has no children");
		} else if ((this.hasChildren()) & (index >= this.getNumberOfChildren())) {
			children.remove(children.size() - 1);
		} else if ((this.hasChildren()) & (index < this.getNumberOfChildren())) {
			children.remove(index);
		}
	}
}
