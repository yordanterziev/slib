package slib.examples.sml.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openrdf.model.URI;

import slib.graph.model.graph.elements.E;

public class SrTree {

	
	private int lvl;
	private SrNode root;
	private HashMap<URI,SrNode> mapNodes = new HashMap<URI,SrNode>();
	
	public SrTree(URI root){
		this.root = new SrNode(root);
		lvl=1;
		mapNodes.put(this.root.getData(), this.root);
	}
	
	public void setRoot(SrNode root){
		this.root = root;
	}
	
	public SrNode getRoot(){
		return root;
	}
	
	public List<URI> toArrayList(){
		List<URI> list = new ArrayList<URI>();
		
		return list;
	}
	
	private ArrayList<SrNode> getAllNodeAt(int level){
		ArrayList<SrNode> result = new ArrayList<SrNode>();
		if(level==1){
			result.add(root);
			
		}
		else if (level == 2){
			result.addAll(root.getChildren());
		}else if (level <= lvl){
			ArrayList<SrNode> temp = new ArrayList<SrNode>();
			temp.addAll(this.getAllNodeAt(level-1));
			for(int i=0; i<temp.size();i++){
				result.addAll(temp.get(i).getChildren());
			}
		}
		return result;
	}
	public void addElementatLevel(ArrayList<E> list, int level){
//		if(level==2){
//			for (int i =0;i<list.size();i++){
//				root.addChild(new SrNode(list.get(i).getTarget()));
//				root.addEdges(list.get(i).getURI());
//			}
//		}
//		else{
//			SrNode current;
//			ArrayList<SrNode> nodeList = this.getAllNodeAt(level-1);
//			for(int i=0; i<nodeList.size();i++){
//				current = nodeList.get(i);
//				
//			}
//		}
		
		if (level == 2){
			for (int i = 0; i<list.size(); i++){
				SrNode temp = new SrNode(list.get(i).getTarget());
				root.addChild(temp);
				mapNodes.put(temp.getData(), temp);
			}
		}else
		{
			for (int i = 0; i<list.size(); i++){
				SrNode temp = new SrNode(list.get(i).getTarget());
				SrNode parent = mapNodes.get(list.get(i).getSource());
				parent.addChild(temp);
				mapNodes.put(temp.getData(), temp);
			}
		}
	}
	
}
