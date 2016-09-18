package slib.examples.sml.general;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.*;

import slib.examples.sml.tree.SrTree;
import slib.graph.model.impl.repo.URIFactoryMemory;
import slib.graph.model.repo.URIFactory;
import slib.utils.ex.SLIB_Exception;

/**
 * A test implementation for {@link SemanticPathCalc}
 * 
 * @author Florian Jakobs
 * 
 */
public class SRComputation {

	public static void main(String[] args) throws SLIB_Exception, JAXBException, FileNotFoundException {
		// TODO Auto-generated method stub
		
		String swrcOntology = "http://swrc.ontoware.org/ontology";
		String path = "/src/main/resources/swrc_updated_v0.7.1.owl";
		String origin = "#Person";
		URIFactory factory = URIFactoryMemory.getSingleton();
		SemanticRelatednes test = new SemanticPathCalc(swrcOntology,path);
		test.setOrigin(factory.getURI(swrcOntology+origin));
		int hops = 1;
		List<SrTree> treeList = new ArrayList<SrTree>();
		
		test.initialiseWalk("Edge.xml");
		
		while (test.hasNext()){
			treeList.add(test.getSemanticallyCorrectPaths(hops).get(hops-1));
			hops++;
		}
		
		
		System.out.println(test.getSemanticRelatedness(factory.getURI(swrcOntology+origin), factory.getURI(swrcOntology+"#Organization")));
		//commit
		
        
        
       
	}

}
