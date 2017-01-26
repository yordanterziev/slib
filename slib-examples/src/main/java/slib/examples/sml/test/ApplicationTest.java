package slib.examples.sml.test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.bind.JAXBException;

import org.junit.Test;
import org.openrdf.model.URI;

import slib.examples.sml.general.SemanticPathCalc;
import slib.examples.sml.general.SemanticRelatednes;
import slib.graph.algo.utils.SemanticPath;
import slib.graph.model.impl.repo.URIFactoryMemory;
import slib.graph.model.repo.URIFactory;
import slib.utils.ex.SLIB_Exception;

public class ApplicationTest {
	
	String swrcOntology = "http://swrc.ontoware.org/ontology";
	String path = "/src/main/resources/swrc_updated_v0.7.1.owl";
	String origin = "#Person";
	URIFactory factory = URIFactoryMemory.getSingleton();
	ArrayList<String> keyList = new ArrayList<String>();

	@Test
	public void test() throws SLIB_Exception, FileNotFoundException, JAXBException, CloneNotSupportedException {
		SemanticRelatednes test = new SemanticPathCalc(swrcOntology, path);
		test.setOrigin(factory.getURI(swrcOntology + origin));
		int hops = 3;
		test.initialiseWalk("Edge.xml");
		HashMap<URI,SemanticPath> result =  test.getSemanticallyCorrectPaths(hops);
		
		keyList.add("Event");
		keyList.add("Project");
		keyList.add("Organization");
		keyList.add("Product");
		keyList.add("Document");
		keyList.add("Thing");
		keyList.add("ResearchGroup");
		
		
		boolean resultTestValues = true;
		boolean resultTestKeys = true;
		boolean resultTestSize = true;
		
		
		//Testing the 7 reached Nodes listed in keyList
		for(URI key : result.keySet()){
			boolean temp = false;
			for (int i = 0; i< keyList.size();i++){	
				if (key.getLocalName().equals(keyList.get(i))==true){
					temp =true;
					resultTestKeys = temp;
					break;
				}	
			}
			if (resultTestKeys == false){
					break;
				}
		}
		
		
		// Testing the size of the map
		if (result.size()!=7){
			resultTestSize = false;
		};
		
		// Testing if all calculated path values are > 0
		for(SemanticPath value : result.values()){
			if (value.getInformationGain()<0){
				resultTestValues = false;
			}
		}
		


		
		
		
		assertTrue(resultTestValues && resultTestKeys && resultTestSize);
	}

}
