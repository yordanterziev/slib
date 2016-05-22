package slib.examples.sml.general;

import slib.graph.model.impl.repo.URIFactoryMemory;
import slib.graph.model.repo.URIFactory;
import slib.utils.ex.SLIB_Exception;

public class SRComputation {

	public static void main(String[] args) throws SLIB_Exception {
		// TODO Auto-generated method stub
		
		String swrcOntology = "http://swrc.ontoware.org/ontology";
		String path = "/src/main/resources/swrc_updated_v0.7.1.owl";
		String origin = "#Person";
		URIFactory factory = URIFactoryMemory.getSingleton();
		SemanticRelatednes test = new SemanticPathCalc(swrcOntology,path);
		test.setOrigin(factory.getURI(swrcOntology+origin));

        
        
        
       
	}

}
