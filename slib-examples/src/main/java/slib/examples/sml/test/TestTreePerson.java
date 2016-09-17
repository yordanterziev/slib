package slib.examples.sml.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import slib.examples.sml.general.SemanticPathCalc;
import slib.graph.model.impl.repo.URIFactoryMemory;
import slib.graph.model.repo.URIFactory;
import slib.utils.ex.SLIB_Exception;
import slib.examples.sml.tree.SrNode;
import slib.examples.sml.tree.SrTree;

public class TestTreePerson  {
	String swrcOntology = "http://swrc.ontoware.org/ontology";
	String path = "/src/main/resources/swrc_updated_v0.7.1.owl";
	String origin = "#Person";
	URIFactory factory = URIFactoryMemory.getSingleton();
	SrTree result = new SrTree(factory.getURI(swrcOntology+origin), null, null, null);
	
	@Before
	public void fillTree(){
		
	}

	@Test
	public void test() throws SLIB_Exception {

		SemanticPathCalc spc = new SemanticPathCalc(swrcOntology,path);
		spc.setOrigin(factory.getURI(swrcOntology+origin));
		ArrayList<SrTree> treeList;


		
		fail("Not yet implemented");
		
		}
	

}
