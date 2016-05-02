package slib.examples.sml.general;

import org.openrdf.model.URI;

public interface SemanticRelatednes {

	
	/**
	 * Called on component initialisation 
	 * @param uri
	 */
	public void setOrigin(URI uri);
	
	/**
	 * 
	 * 
	 * @return 
	 */
	public boolean hasNext();
	

	
	
	public List<Tree> getSemanticallyCorrectPaths(int hops);

	
	public double getSemanticRelatedness(URI uriA, URI uriB );
}
