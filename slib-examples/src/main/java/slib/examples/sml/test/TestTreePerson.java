package slib.examples.sml.test;

import static org.junit.Assert.*;

import org.junit.Test;

import slib.examples.sml.general.SemanticPathCalc;
import slib.graph.model.impl.repo.URIFactoryMemory;
import slib.graph.model.repo.URIFactory;
import slib.utils.ex.SLIB_Exception;

public class TestTreePerson  {

	String swrcOntology = "http://swrc.ontoware.org/ontology";
	String path = "/src/main/resources/swrc_updated_v0.7.1.owl";
	String origin = "#Person";
	URIFactory factory = URIFactoryMemory.getSingleton();
	SemanticPathCalc spc = new SemanticPathCalc();
	
	
	

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
