package slib.graph.algo.traversal.classical;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openrdf.model.URI;

import slib.graph.algo.utils.SemanticPath;
import slib.graph.algo.utils.Target;
import slib.graph.model.graph.G;
import slib.graph.model.graph.elements.E;
import slib.graph.model.graph.utils.WalkConstraint;
import slib.utils.impl.SetUtils;

public class BFSLevel {
	G g;
	private WalkConstraint wc;
	URI current;
	List<URI> queuenextlvl;
	Set<URI> visited;

	public BFSLevel(G g, Set<URI> sources, WalkConstraint wc) {

		this.g = g;
		this.wc = wc;

		this.queuenextlvl = new ArrayList<URI>(sources);

		visited = new HashSet<URI>();
		visited.addAll(sources);

	}

	public BFSLevel(G g, URI source, WalkConstraint wc) {
		this(g, SetUtils.buildSet(source), wc);
	}

	public boolean hasNextLevel() {
		return (queuenextlvl.isEmpty() == false);
	}

	public HashMap<URI, ArrayList<SemanticPath>> LevelSearch(int hops) {
		HashMap<URI, ArrayList<SemanticPath>> result = new HashMap<URI, ArrayList<SemanticPath>>();
		HashMap<Integer, ArrayList<Target>> hopMap = new HashMap<Integer, ArrayList<Target>>();		
		for (int i = 0; i < hops; i++) {
			if (i == 0) {
				while (!queuenextlvl.isEmpty()) {
					URI src = queuenextlvl.get(0);
					queuenextlvl.remove(0);
					ArrayList<Target> targetList = this.firstLevelSearch(g.getV(src, wc), g.getE(src, wc), src);
					hopMap.put(1, targetList);
					for(int j = 0;j<targetList.size();j++){
						URI uri = targetList.get(j).getNode();
						SemanticPath path = targetList.get(j).getPath();
						
						if(result.containsKey(uri)){
							ArrayList<SemanticPath> temp = result.get(uri);
							temp.add(path);
						}else{
							ArrayList<SemanticPath> temp = new ArrayList<SemanticPath>();
							temp.add(path);
							result.put(uri, temp);
						}
						
					}
				}
			} else {
				ArrayList<Target> queue = new ArrayList<Target>();
				ArrayList<Target> targetList = new ArrayList<Target>();
				queue.addAll(hopMap.get(i));
				targetList.addAll(this.nextLevelSearch(queue));
			}

		}
		return result;

	}

	private ArrayList<Target> firstLevelSearch(Set<URI> vertices, Set<E> edges, URI src) {
		ArrayList<Target> result = new ArrayList<Target>();
		for (URI v : vertices) {
			if (!visited.contains(v)) {
				for (E e : edges) {
					if (e.getSource().equals(src)) {
						if (e.getTarget().equals(v)) {
							Target target = new Target(v, e, 0);
							result.add(target);
						}
					}
				}
			}
		}
		return result;
	}

	private ArrayList<Target> nextLevelSearch(ArrayList<Target> targetList) {
		ArrayList<Target> result = new ArrayList<Target>();
		for (int i = 0; i < targetList.size(); i++) {
			URI src = targetList.get(i).getNode();
			Set<URI> vertices = g.getV(src, wc);
			Set<E> edges = g.getE(src, wc);
			for (URI v : vertices) {
				if (!visited.contains(v)) {
					for (E e : edges) {
						if (e.getSource().equals(src)) {
							if (e.getTarget().equals(v)) {
								Target target = new Target(v, e, 0);
								result.add(target);
							}
						}
					}
				}
			}
		}
		return result;
	}

}
