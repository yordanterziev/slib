package slib.examples.sml.general;

import org.openrdf.model.URI;

import slib.graph.model.graph.G;
import slib.graph.model.repo.URIFactory;
import slib.sml.sm.core.engine.SM_Engine;
import slib.utils.ex.SLIB_Exception;

/**
 * @author Florian
 *
 */
public class SemanticPathCalc implements SemanticRelatednes {

	private OntologyCreator oc;
	private URI graph_uri;
	private G graph;
	private  SM_Engine engine;
	private URIFactory uriFactory;
	private URI origin;
	
    /**
     * @param ontology a String describing the ontology's URI
     * @param filepath relative location of the file containing the ontology
     * @throws SLIB_Exception
     */
    public SemanticPathCalc(String ontology,String filepath) throws SLIB_Exception{
    	oc = new OntologyCreator(ontology,filepath);
    	graph_uri = oc.getUriOnto();
        graph = oc.getGraph();
        engine = oc.getEngine();
        uriFactory = oc.getFactory();
    }
    
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void setOrigin(URI uri) {
		// TODO Auto-generated method stub
		this.origin = uri;
	}

	@Override
	public double getSemanticRelatedness(URI uriA, URI uriB) {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
