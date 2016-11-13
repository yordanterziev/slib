package slib.sml.sm.core.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.openrdf.model.URI;


import slib.graph.algo.utils.CorrectPaths;
import slib.graph.algo.utils.SemanticPath;
import slib.graph.algo.utils.SemanticPathAdder;
import slib.graph.algo.utils.Target;
import slib.graph.model.graph.G;
import slib.graph.model.graph.elements.E;
import slib.graph.model.graph.utils.WalkConstraint;
import slib.utils.ex.SLIB_Ex_Critic;
import slib.utils.ex.SLIB_Exception;

/**
 *  This class performs a BFS on a given graph. For each iteration a map of {@link Target} nodes and {@link SemanticPath}
 *  to these nodes are provided.
 * @author Florian Jakobs
 */
public class BFSLevel {
	G g;
	private WalkConstraint wc;
	URI source;
	Set<URI> visited;
	HashMap<Integer, ArrayList<Target>> hopMap;
	HashMap<URI, ArrayList<SemanticPath>> pathMap;
	CorrectPaths correctPaths;
	SemanticPathAdder spa;
	InformationCalculator iCalc;

	/**
	 * Creates an instance of BFSLevel
	 * @param g the graph on which the BFS is performed.
	 * @param source the starting node
	 * @param wc the {@link WalkContstraint}
	 * @param spa the {@link SemanticPathAdder}
	 * @throws SLIB_Ex_Critic
	 */
	public BFSLevel(G g, URI source, WalkConstraint wc, SemanticPathAdder spa) throws SLIB_Ex_Critic {
		this.g = g;
		this.wc = wc;
		this.source = source;
		visited = new HashSet<URI>();
		visited.add(source);
		correctPaths  = new CorrectPaths();
		this.spa = spa;
		iCalc = new InformationCalculator(g);
	}

	/**
	 * Method for performing the level search. Returns a {@link HashMap} of {@link URI} and {@link SemanticPath} containing the best Path to each URI
	 * @param hops the amount of levels to be searched
	 * @return the HashMap
	 * @throws SLIB_Exception
	 */
	public HashMap<URI,SemanticPath> LevelSearch(int hops) throws SLIB_Exception {
		pathMap = new HashMap<URI, ArrayList<SemanticPath>>();
		hopMap = new HashMap<Integer, ArrayList<Target>>();
		for (int i = 1; i <= hops; i++) {
			if (i == 1) {
				ArrayList<Target> targetList = new ArrayList<Target>();
				targetList.addAll(this.firstLevelSearch(g.getV(source, wc), g.getE(source, wc), source));
				hopMap.put(1, targetList);
				this.targetPutInMap(targetList);

			} else {
				ArrayList<Target> queue = new ArrayList<Target>();
				queue.addAll(hopMap.get(i-1));
				ArrayList<Target> targetList = new ArrayList<Target>();
				targetList.addAll(this.nextLevelSearch(queue, i));
				hopMap.put(i, targetList);
				this.targetPutInMap(targetList);
			}

		}
		HashMap<URI,SemanticPath> result = this.selectHighestInformationPath();
		return result;

	}

	/**
	 * Method for the first level.
	 * @param vertices
	 * @param edges
	 * @param src
	 * @return an {@link ArrayList} of {@link Target} which are reached after one level
	 * @throws SLIB_Exception
	 */
	private ArrayList<Target> firstLevelSearch(Set<URI> vertices, Set<E> edges, URI src) throws SLIB_Exception {
		ArrayList<Target> result = new ArrayList<Target>();
		for (URI v : vertices) {
			if (!visited.contains(v)) {
				for (E e : edges) {
					if (e.getSource().equals(src)) {
						if (e.getTarget().equals(v)) {
							Target target = new Target(v, e, 1);
							SemanticPath semanticPathTemp = target.getPath();
							semanticPathTemp.setSemanticPath(spa.getSemanticPath(semanticPathTemp));
							double ic = iCalc.calculateInformation(semanticPathTemp);
							semanticPathTemp.setInformationGain(ic);
							result.add(target);
						}
					}
				}
			}
		}
		return result;
	}

	/**
	 * Method for calculating every step after the first.
	 * @param targetList the list of {@link Traget} after the previous search
	 * @param hop the current hop
	 * @return an {@link ArrayList} of {@link Target} which are reached after @b hop level
	 * @throws SLIB_Exception
	 */
	private ArrayList<Target> nextLevelSearch(ArrayList<Target> targetList, int hop) throws SLIB_Exception {
		ArrayList<Target> result = new ArrayList<Target>();
		for (int i = 0; i < targetList.size(); i++) {
			Target target = targetList.get(i);
			URI src = targetList.get(i).getNode();
			Set<URI> vertices = g.getV(src, wc);
			Set<E> edges = g.getE(src, wc);
			for (URI v : vertices) {
				if (!visited.contains(v)) {
					for (E e : edges) {
						if (e.getSource().equals(src)) {
							if (e.getTarget().equals(v)) {
								Target newtarget = new Target(v, target.getPath(), hop);
								newtarget.getPath().addEdge(e);
								SemanticPath semanticPathTemp = newtarget.getPath();
								semanticPathTemp.setSemanticPath(spa.getSemanticPath(semanticPathTemp));
								if(this.isPathCorrect(newtarget.getPath().getSemanticPath())){
									double ic = iCalc.calculateInformation(semanticPathTemp);
									semanticPathTemp.setInformationGain(ic);
									result.add(newtarget);
								}
							}
						}
					}
				}
			}
		}
		return result;
	}

	/**
	 * Puts the targetList in pathMap
	 * @param targetList
	 */
	private void targetPutInMap(ArrayList<Target> targetList) {
		for (int j = 0; j < targetList.size(); j++) {
			URI uri = targetList.get(j).getNode();
			SemanticPath path = targetList.get(j).getPath();
			if (pathMap.containsKey(uri)) {
				ArrayList<SemanticPath> temp = pathMap.get(uri);
				temp.add(path);
			} else {
				ArrayList<SemanticPath> temp = new ArrayList<SemanticPath>();
				temp.add(path);
				pathMap.put(uri, temp);
			}
		}
	}
	
	/**
	 * Checks if a {@link SemanticPath} is correct
	 * @param path the path to be checked
	 * @return
	 */
	private boolean isPathCorrect(String path){
		boolean result = false;
		if(correctPaths.isSemanticCorrect(path)){
			result = true;
		}
		return result;
	}
	
	/**
	 * Selects the best Path for the search
	 * @return the map containg the best path for each URI
	 */
	private HashMap<URI,SemanticPath> selectHighestInformationPath(){

		HashMap<URI,SemanticPath> result = new HashMap<URI,SemanticPath>();
		
		for(HashMap.Entry<URI,ArrayList<SemanticPath>> entry: pathMap.entrySet()){
			ArrayList<SemanticPath> value = entry.getValue();
			URI key = entry.getKey();
			double min = 0.0;
			int position =0;
			min = value.get(0).getInformationGain();
			for(int i = 1; i<value.size();i++){
				if(min>value.get(i).getInformationGain()){
					min = value.get(i).getInformationGain();
					position = i;
				}
			}
			result.put(key, value.get(position));
		}
		return result;
	}

	
}
