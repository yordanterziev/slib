package slib.examples.sml.tree;

import java.util.ArrayList;
import java.util.List;

import org.openrdf.model.URI;

public class SrTree {

	
	private int lvl;
	private SrNode root;
	
	public SrTree(SrNode root){
		this.root = root;
		lvl=1;
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
	public void addElementatLevel(ArrayList<ArrayList<URI>> list, int level){
		if(level==2){
			for (int i =0;i<list.get(0).size();i++){
				root.addChild(new SrNode(list.get(0).get(i)));
				root.addEdges(list.get(1).get(i));
			}
		}
		else{
			SrNode current;
			ArrayList<SrNode> nodeList = this.getAllNodeAt(level-1);
			for(int i=0; i<nodeList.size();i++){
				current = nodeList.get(i);
				
			}
		}
	}
	
}
