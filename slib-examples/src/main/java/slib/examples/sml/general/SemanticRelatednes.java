package slib.examples.sml.general;

import org.openrdf.model.URI;

public interface SemanticRelatednes {

	
	
	/**
	 * 
	 * 
	 * @return
	 */
	public boolean hasNext();
	
	
	/**
	 * Returns next semantically related node
	 * @return
	 */
	public URI next();
}
