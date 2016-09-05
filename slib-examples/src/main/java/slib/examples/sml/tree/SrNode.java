package slib.examples.sml.tree;

import java.util.ArrayList;


public class SrNode<T> {

	private T data;
	private ArrayList<SrNode<T>> children;
	private ArrayList<SrNode<T>> childrenEdges;
		
	public SrNode(T data){
		this.setData(data);
	}
		
	public void setData(T data){
		this.data = data;
	}
		
	public T getData(){
		return data;
	}
		
	public ArrayList<SrNode<T>> getChildren(){
		if (this.hasChildren()){
			return children;
		}else
			return null;
	}
		
	public ArrayList<SrNode<T>> getChildrenEdges() {
		return childrenEdges;
	}

	public boolean hasChildren(){
		return (children!=null);
	}
	
	public boolean hasChildrenEdges(){
		return (childrenEdges!=null);
	}
	
	public int getNumberOfChildren(){
		if (this.hasChildren()){
			return children.size();
		}else
			return 0;
	}
		
	public void addChild(SrNode<T> child){
		if (!this.hasChildren()){
			children = new ArrayList<SrNode<T>>();
		}
			children.add(child);
	}
	
	public void instertChildAt(int index, SrNode<T> child){
		if(!this.hasChildren()){
			this.addChild(child);
		}
		else if((this.hasChildren())&(index>=this.getNumberOfChildren()) ){
			children.get(children.size()-1).addChild(child);
		}
		else if((this.hasChildren())&(index<this.getNumberOfChildren()) ){
			children.get(index).addChild(child);
		}
	}
		
	public void removeChildAt(int index){
		if(!this.hasChildren()){
			System.out.println("Has no children");
		}
		else if((this.hasChildren())&(index>=this.getNumberOfChildren()) ){
			children.remove(children.size()-1);
		}
		else if((this.hasChildren())&(index<this.getNumberOfChildren()) ){
			children.remove(index);
		}
	}
}
