/*

Copyright or © or Copr. Ecole des Mines d'Alès (2012) 

This software is a computer program whose purpose is to 
process semantic graphs.

This software is governed by the CeCILL  license under French law and
abiding by the rules of distribution of free software.  You can  use, 
modify and/ or redistribute the software under the terms of the CeCILL
license as circulated by CEA, CNRS and INRIA at the following URL
"http://www.cecill.info". 

As a counterpart to the access to the source code and  rights to copy,
modify and redistribute granted by the license, users are provided only
with a limited warranty  and the software's author,  the holder of the
economic rights,  and the successive licensors  have only  limited
liability. 

In this respect, the user's attention is drawn to the risks associated
with loading,  using,  modifying and/or developing or reproducing the
software by the user in light of its specific status of free software,
that may mean  that it is complicated to manipulate,  and  that  also
therefore means  that it is reserved for developers  and  experienced
professionals having in-depth computer knowledge. Users are therefore
encouraged to load and test the software's suitability as regards their
requirements in conditions enabling the security of their systems and/or 
data to be ensured and,  more generally, to use and operate it in the 
same conditions as regards security. 

The fact that you are presently reading this means that you have had
knowledge of the CeCILL license and that you accept its terms.

 */


package slib.sglib.algo.traversal.classical;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import slib.sglib.algo.traversal.GraphTraversal;
import slib.sglib.model.graph.G;
import slib.sglib.model.graph.elements.V;
import slib.sglib.model.graph.utils.WalkConstraints;
import slib.utils.impl.SetUtils;

/**
 * Class used to perform traversal on a graph using Breadth First Search Algorithm
 * from a set of vertices and considering particular type of both vertices and relationships.
 * Note that transitivity property of relationship is not considered as a rule governing the
 * traversal.
 * 
 * 
 * <a href="http://en.wikipedia.org/wiki/Breadth-first_search">more about</a>
 * 
 * 
 * @author Sebastien Harispe
 *
 */
public class BFS implements GraphTraversal {


	G g;

	private WalkConstraints wc;


	V current;
	List<V> queue;
	Set<V> visited;


	boolean removePerformed = false;

	/**
	 * Creates an instance of BFS
	 * @param g the graph
	 * @param eTypes the type of relationships to consider
	 * @param sources the set of vertices from which starts the BFS
	 */
	public BFS(G g, Set<V> sources, WalkConstraints wc){

		this.g 		= g;
		this.wc  	= wc; 
		this.queue  = new ArrayList<V>(sources);

		visited 	= new HashSet<V>();

	}


	/**
	 * Creates an instance of BFS
	 * @param g the graph
	 * @param eTypes the type of relationships to consider
	 * @param sources the set of vertices from which starts the BFS
	 */
	public BFS(G g,V source, WalkConstraints wc){
		this(g,SetUtils.buildSet(source),wc);
	}

	public boolean hasNext() {
		return queue.isEmpty() == false;
	}

	public V next() {

		removePerformed = false;

		V src = queue.get(0);
		queue.remove(0);

		Set<V> vertices = g.getV(src,wc);


		for (V v : vertices) {

			if(! visited.contains(v)){
				queue.add(v);
				visited.add(v);
			}
		}
		current = src;
		return src;
	}

}
