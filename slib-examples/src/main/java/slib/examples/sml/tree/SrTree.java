package slib.examples.sml.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openrdf.model.URI;

import slib.graph.model.graph.elements.E;

/**
 * This class builds a tree structure containing a semantic correct path through an ontology.
 * It contains Nodes of the class {@link SrNode}. The tree maintains the semantic correct path by removing the incorrect Node immediately.
 * All nodes can be accessed instantly via a HashMap, because the tree is likely to be unbalanced.
 * The tree does not take care of duplicate values and cycles.
 * 
 * @author Florian Jakobs
 *
 */
public class SrTree {

	
	private int lvl;
	private SrNode root;
	private HashMap<URI,SrNode> mapNodes = new HashMap<URI,SrNode>();
	private HashMap<SrNode,Integer> mapLevel = new HashMap<SrNode,Integer>();
	private List<String> hEdges = new ArrayList<String>();
	private List<String> dEdges = new ArrayList<String>();
	private List<String> uEdges = new ArrayList<String>();
	
	private static ArrayList<String> correctPaths = new ArrayList<String>();
	
	
	
	/**
	 * Creates the tree by setting a root and the lists of Upward, Downward and Horizontal Edges.
	 * 
	 * @param root root of the tree
	 * @param uEdges list of upward edges.
	 * @param dEdges list of downward edges.
	 * @param hEdges list of horizontal edges.
	 */
	public SrTree(URI root,List<String> uEdges,List<String> dEdges,List<String> hEdges){
		this.root = new SrNode(root);
		lvl=1;
		mapNodes.put(this.root.getData(), this.root);
		mapLevel.put( this.root, 1);
		this.uEdges.addAll(uEdges);
		this.dEdges.addAll(dEdges);
		this.hEdges.addAll(hEdges);
		correctPaths.add("U");
		correctPaths.add("UU");
		correctPaths.add("UUU");
		
		correctPaths.add("UD");
		correctPaths.add("UUD");
		correctPaths.add("UDD");
		
		correctPaths.add("UH");
		correctPaths.add("UUH");
		correctPaths.add("UHH");
		
		correctPaths.add("UHD");
		
		correctPaths.add("D");
		correctPaths.add("DD");
		correctPaths.add("DDD");
		
		correctPaths.add("DH");
		correctPaths.add("DDH");
		correctPaths.add("DHH");
		
		correctPaths.add("HD");
		correctPaths.add("HHD");
		correctPaths.add("HDD");
		
		correctPaths.add("H");
		correctPaths.add("HH");
		correctPaths.add("HHH");
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
	
//	private ArrayList<SrNode> getAllNodeAt(int level){
//		ArrayList<SrNode> result = new ArrayList<SrNode>();
//		for(Map.Entry<SrNode,Integer> entry : mapLevel.entrySet()){
//			if(entry.getValue()==level){
//				result.add(entry.getKey());
//			}
//		};
//		return result;
//	}
	
	
	
	/**
	 * Removes the given Node.
	 * The entire tree will be removed, if the root is given.
	 * @param data URI of the given Node
	 */
	public void removeNode(URI data){
		if (data.equals(root.getData())){
			root = null;
			mapNodes.clear();
		}
		SrNode temp = mapNodes.get(data);
		SrNode parent = temp.getParent();
		parent.getChildren().remove(temp);
		mapNodes.remove(temp);
	}
	
	
	/**
	 * Adds Elements to the tree. The Elements are retrieved from a List of Edges.
	 * Data is set to target.
	 * Parent is set to source.
	 * Maps are maintained
	 * The semantic path is extended.
	 * 
	 * @param list list of Edges 
	 * @param level at which the children are put.
	 */
	public void addElementatLevel(ArrayList<E> list, int level){
		if (level == 2){
			for (int i = 0; i<list.size(); i++){
				E edge= list.get(i);
				SrNode temp = new SrNode(edge.getTarget());
				root.addChild(temp);
				root.addEdges(edge.getURI(),edge.getTarget());
				
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
			lvl++;
		}else
		{
			for (int i = 0; i<list.size(); i++){
				URI uriParent = list.get(i).getSource();
				if (mapNodes.containsKey(uriParent)){
					E edge= list.get(i);
					
					SrNode temp = new SrNode(list.get(i).getTarget());
					SrNode parent = mapNodes.get(edge.getSource());
					temp.setParent(parent);
					if (uEdges.contains(edge.getURI().getLocalName())){
						temp.setSemanticRelatedness(parent.getSemanticRelatedness()+ "U");
					}else if (dEdges.contains(edge.getURI().getLocalName())){
						temp.setSemanticRelatedness(parent.getSemanticRelatedness()+"D");
					}else{
						temp.setSemanticRelatedness(parent.getSemanticRelatedness()+"H");
					}
					
					if (isSemanticCorrect(temp) == true){
						parent.addChild(temp);
						parent.addEdges(edge.getURI(),edge.getTarget());
					
						mapNodes.put(temp.getData(), temp);
						mapLevel.put(temp, level);
					}
				}
			}
			lvl++;
		}
	}
	
	private static boolean isSemanticCorrect(SrNode node){
		boolean result = false;
		String path = node.getSemanticRelatedness();
		if (path.length() > 3) {
			  path = path.substring(path.length() - 3);
		}
		for (int i = 0; i<= correctPaths.size();i++){
			result = correctPaths.get(i).equals(path);
			if (result = true){
				break;
			}
		}
		return result;
	}
	
}
