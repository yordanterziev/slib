package slib.examples.sml.general;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.openrdf.model.URI;

import slib.examples.sml.tree.SrTree;
import slib.graph.model.graph.elements.E;
import slib.utils.ex.SLIB_Exception;

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
	public HashMap<URI, ArrayList<E>> getSemanticallyCorrectPaths(int hops);

	
	/**
	 * @param uriA Starting concept of the calculation
	 * @param uriB Finishing concept
	 * @return the Semantic Relatedness if a correct path can be found;
	 *  -1 otherwise
	 * @throws SLIB_Exception 
	 */
	public double getSemanticRelatedness(URI uriA, URI uriB ) throws SLIB_Exception;
	
	/**
	 * Loads the upward, downward and horizontal edges defined in an XML file and 
	 * fills a WalkConstraint with the given edges. This WC is used to initialise the
	 * BFSLevel search.
	 * 
	 * 
	 * @param file XML file
	 * @throws JAXBException
	 * @throws FileNotFoundException
	 */
	public void initialiseWalk(String file) throws JAXBException, FileNotFoundException;
}
