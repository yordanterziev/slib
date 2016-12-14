package slib.examples.sml.general;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.openrdf.model.URI;

import slib.examples.sml.tree.SrTree;
import slib.graph.algo.utils.SemanticPath;
import slib.graph.model.graph.elements.E;
import slib.utils.ex.SLIB_Ex_Critic;
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
	
	
	//We need a suitable data structure
	/**
	 * Method for walking through the graph a given number of times.
	 * @param hops the depth of the BFSLevel
	 * @return a HashMap of all semantically correct reached URIs and the corresponding path.
	 * @throws SLIB_Exception
	 * @throws CloneNotSupportedException
	 */
	public HashMap<URI, SemanticPath> getSemanticallyCorrectPaths(int hops) throws SLIB_Exception, CloneNotSupportedException;

	
	/**
	 * @param uriA Starting concept of the calculation
	 * @param uriB Finishing concept
	 * @param the number of hops to be considered
	 * @return the Semantic Relatedness if a correct path can be found;
	 *  -1 otherwise
	 * @throws SLIB_Exception 
	 * @throws CloneNotSupportedException 
	 */
	public double getSemanticRelatedness(URI uriA, URI uriB, int hops ) throws SLIB_Exception, CloneNotSupportedException;
	
	/**
	 * Loads the upward, downward and horizontal edges defined in an XML file and 
	 * fills a WalkConstraint with the given edges. This WC is used to initialise the
	 * BFSLevel search.
	 * 
	 * 
	 * @param file XML file
	 * @throws JAXBException
	 * @throws FileNotFoundException
	 * @throws SLIB_Ex_Critic 
	 */
	public void initialiseWalk(String file) throws JAXBException, FileNotFoundException, SLIB_Ex_Critic;
}
