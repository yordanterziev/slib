package slib.examples.sml.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openrdf.model.URI;

import slib.graph.model.graph.elements.E;

public class SrTree {

	
	private int lvl;
	private SrNode root;
	private HashMap<URI,SrNode> mapNodes = new HashMap<URI,SrNode>();
	private HashMap<SrNode,Integer> mapLevel = new HashMap<SrNode,Integer>();
	private List<String> hEdges = new ArrayList<String>();
	private List<String> dEdges = new ArrayList<String>();
	private List<String> uEdges = new ArrayList<String>();
	
	
	
	public SrTree(URI root,List<String> uEdges,List<String> dEdges,List<String> hEdges){
		this.root = new SrNode(root);
		lvl=1;
		mapNodes.put(this.root.getData(), this.root);
		mapLevel.put( this.root, 1);
		this.uEdges.addAll(uEdges);
		this.dEdges.addAll(dEdges);
		this.hEdges.addAll(hEdges);
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
		for(Map.Entry<SrNode,Integer> entry : mapLevel.entrySet()){
			if(entry.getValue()==level){
				result.add(entry.getKey());
			}
		};
		return result;
	}
	
	public void removeNode(URI data){
		if (data.equals(root.getData())){
			root = null;
			mapNodes.clear();
		}
		SrNode temp = mapNodes.get(data);
		SrNode parent = temp.getParent();
		int position=-1;
		for (int i = 0; i<parent.getChildren().size();i++){
			if (parent.getChildren().get(i).getData().getLocalName().equals(data.getLocalName())){
				position = i;
			}
		};
		if (position!=-1){
		parent.getChildren().remove(position);
		mapNodes.remove(data);}
		else{
			System.out.println("Nicht gefunden");
		}
	}
	
	
	public void addElementatLevel(ArrayList<E> list, int level){
		if (level == 2){
			for (int i = 0; i<list.size(); i++){
				E edge= list.get(i);
				SrNode temp = new SrNode(edge.getTarget());
				root.addChild(temp);
				root.addEdges(edge.getURI());
				
				temp.setParent(root);
				mapNodes.put(temp.getData(), temp);
				mapLevel.put(temp, 2);
				if (uEdges.contains(edge.getURI().getLocalName())){
					temp.setSemanticRelatedness("U");
				}else if (dEdges.contains(edge.getURI().getLocalName())){
					temp.setSemanticRelatedness("D");
				}else{
					temp.setSemanticRelatedness("H");
				}
			}
		}else
		{
			for (int i = 0; i<list.size(); i++){
				URI uriParent = list.get(i).getSource();
				if (mapNodes.containsKey(uriParent)){
					E edge= list.get(i);
					
					SrNode temp = new SrNode(list.get(i).getTarget());
					SrNode parent = mapNodes.get(edge.getSource());
					temp.setParent(parent);
					parent.addChild(temp);
					parent.addEdges(edge.getURI());
					
					mapNodes.put(temp.getData(), temp);
					mapLevel.put(temp, level);
					if (uEdges.contains(edge.getURI().getLocalName())){
						temp.setSemanticRelatedness(parent.getSemanticRelatedness()+ "U");
					}else if (dEdges.contains(edge.getURI().getLocalName())){
						temp.setSemanticRelatedness(parent.getSemanticRelatedness()+"D");
					}else{
						temp.setSemanticRelatedness(parent.getSemanticRelatedness()+"H");
					}
				}
			}
		}
	}
	
	public void addSemanticReladedness(int level){
		
	}
	
}
