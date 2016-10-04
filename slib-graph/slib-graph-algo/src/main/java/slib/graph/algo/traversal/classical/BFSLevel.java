package slib.graph.algo.traversal.classical;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openrdf.model.URI;

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
    
    public boolean hasNextLevel(){
    	return (queuenextlvl.isEmpty() == false);
    }
    
    public HashMap<URI,ArrayList<ArrayList<E>>> LevelSearch(int hops){
    	HashMap<URI,ArrayList<ArrayList<E>>> result = new HashMap<URI,ArrayList<ArrayList<E>>>();
    	ArrayList<URI> queue = new ArrayList<URI>(queuenextlvl);
    	//List<ArrayList<URI>> listOfV = new ArrayList<ArrayList<URI>>();
    	int listCounter = 0;
    	queue.addAll(queuenextlvl);
    	queuenextlvl.clear();
    	for(int i = 0;i<hops;i++){
	    	while (!queue.isEmpty()){
				URI src = queue.get(0);
		        queue.remove(0);
		        
		
		        Set<URI> vertices = g.getV(src, wc);
		        Set<E> edges = g.getE(src, wc);
		        
		        //result.addAll(this.getVerteciesFromSource(vertices, edges, src));
		        listCounter++;
		        // Edges add Next level
		        
		        current = src;
		        
		        //result.add(src);
	    	}
    	}
        return result;
		
	}
    
}
