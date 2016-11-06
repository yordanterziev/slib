package slib.graph.algo.traversal.classical;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.openrdf.model.URI;

//import slib.examples.sml.general.InformationCalculator;
import slib.graph.algo.utils.CorrectPaths;
import slib.graph.algo.utils.SemanticPath;
import slib.graph.algo.utils.SemanticPathAdder;
import slib.graph.algo.utils.Target;
import slib.graph.model.graph.G;
import slib.graph.model.graph.elements.E;
import slib.graph.model.graph.utils.WalkConstraint;
import slib.utils.ex.SLIB_Exception;

public class BFSLevel {
	G g;
	private WalkConstraint wc;
	URI source;
	Set<URI> visited;
	HashMap<Integer, ArrayList<Target>> hopMap;
	HashMap<URI, ArrayList<SemanticPath>> pathMap;
	CorrectPaths correctPaths;
	SemanticPathAdder spa;
//	InformationCalculator iCalc;

	public BFSLevel(G g, URI source, WalkConstraint wc, SemanticPathAdder spa) {
		this.g = g;
		this.wc = wc;
		this.source = source;
		visited = new HashSet<URI>();
		visited.add(source);
		correctPaths  = new CorrectPaths();
		this.spa = spa;
	}

	public HashMap<URI,SemanticPath> LevelSearch(int hops) throws SLIB_Exception {
		pathMap = new HashMap<URI, ArrayList<SemanticPath>>();
		hopMap = new HashMap<Integer, ArrayList<Target>>();
		ArrayList<Target> targetList = new ArrayList<Target>();
		for (int i = 1; i <= hops; i++) {
			if (i == 1) {
				targetList.addAll(this.firstLevelSearch(g.getV(source, wc), g.getE(source, wc), source));
				hopMap.put(1, targetList);
				this.targetPutInMap(targetList);

			} else {
				ArrayList<Target> queue = new ArrayList<Target>();
				queue.addAll(hopMap.get(i-1));
				targetList.addAll(this.nextLevelSearch(queue, i));
				hopMap.put(i, targetList);
				this.targetPutInMap(targetList);
			}

		}
		HashMap<URI,SemanticPath> result = this.selectHighestInformationPath();
		return result;

	}

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
						//	double ic = iCalc.calculateInformation(semanticPathTemp);
						//	semanticPathTemp.setInformationGain(ic);
							result.add(target);
						}
					}
				}
			}
		}
		return result;
	}

	private ArrayList<Target> nextLevelSearch(ArrayList<Target> targetList, int hop) throws SLIB_Exception {
		ArrayList<Target> result = new ArrayList<Target>();
		for (int i = 0; i < targetList.size(); i++) {
			Target target = targetList.get(0);
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
								//	double ic = iCalc.calculateInformation(semanticPathTemp);
								//	semanticPathTemp.setInformationGain(ic);
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
	
	private boolean isPathCorrect(String path){
		boolean result = false;
		if(correctPaths.isSemanticCorrect(path)){
			result = true;
		}
		return result;
	}
	
	private HashMap<URI,SemanticPath> selectHighestInformationPath(){
		double min = 0.0;
		int position =0;
		HashMap<URI,SemanticPath> result = new HashMap<URI,SemanticPath>();
		
		for(HashMap.Entry<URI,ArrayList<SemanticPath>> entry: pathMap.entrySet()){
			ArrayList<SemanticPath> value = entry.getValue();
			URI key = entry.getKey();
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
