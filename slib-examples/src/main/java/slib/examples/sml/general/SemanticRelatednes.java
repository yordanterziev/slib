package slib.examples.sml.general;

import org.openrdf.model.URI;

/**
 *  The interface defines methods to calculate semantic correct paths based on the work of TODO 
 * 
 * @author Florian Jakobs
 */
public interface SemanticRelatednes {

	
	/**
	 * Called on component initialisation; 
	 * resets the exploration if called again
	 * @param uri The concept from which the exploration starts
	 *  
	 */
	public void setOrigin(URI uri);
	
	/**
	 * 
	 * 
	 * @return true if any node has an successor node
	 */
	public boolean hasNext();
	

	
	//We need a suitable data structure
	//public List<Tree> getSemanticallyCorrectPaths(int hops);

	
	/**
	 * @param uriA Starting concept of the calculation
	 * @param uriB Finishing concept
	 * @return the Semantic Relatedness if a correct path can be found;
	 *  -1 otherwise
	 */
	public double getSemanticRelatedness(URI uriA, URI uriB );
}
