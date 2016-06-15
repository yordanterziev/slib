package slib.examples.sml.tree;

import java.util.ArrayList;

public class SrTree<T> {

	
	
	private SrNode<T> root;
	
	public SrTree(SrNode<T> root){
		this.root = root;
	}
	
	public void setRoot(SrNode<T> root){
		this.root = root;
	}
	
	public SrNode<T> getRoot(){
		return root;
	}
	
	public ArrayList<T> toArrayList(){
		ArrayList<T> list = new ArrayList<T>();
		
		return list;
	}
	
	private void traverseTree(){
		
	}
	
}
