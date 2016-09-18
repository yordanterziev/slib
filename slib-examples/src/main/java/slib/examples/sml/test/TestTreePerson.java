package slib.examples.sml.test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.xml.bind.JAXBException;

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
	SrTree result = new SrTree(factory.getURI(swrcOntology+origin), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>());
	int hops = 1;
	
	@Before
	public void fillTree(){
	SrNode event = new SrNode(factory.getURI(swrcOntology+"#Event"));
	SrNode organization = new SrNode(factory.getURI(swrcOntology+"#Organiziation"));
	SrNode project = new SrNode(factory.getURI(swrcOntology+"#Project"));
	SrNode thing = new SrNode(factory.getURI("http://www.w3.org/2002/07/owl#Thing"));
	SrNode researchGroup = new SrNode(factory.getURI(swrcOntology+"#ResearchGroup"));
	SrNode document = new SrNode(factory.getURI(swrcOntology+"#Document"));
	SrNode product = new SrNode(factory.getURI(swrcOntology+"#Product"));
	project.addChild(document);
	project.addChild(product);
	result.getRoot().addChild(event);
	result.getRoot().addChild(organization);
	result.getRoot().addChild(project);
	result.getRoot().addChild(thing);
	result.getRoot().addChild(researchGroup);
	}

	@Test
	public void test() throws SLIB_Exception, FileNotFoundException, JAXBException {

		SemanticPathCalc spc = new SemanticPathCalc(swrcOntology,path);
		spc.setOrigin(factory.getURI(swrcOntology+origin));
		spc.initialiseWalk("Edge.xml");
		ArrayList<SrTree> treeList = new ArrayList<SrTree>();
		while (spc.hasNext()){
			treeList.add(spc.getSemanticallyCorrectPaths(hops).get(hops-1));
			hops++;
		}
		SrTree tree = treeList.get(treeList.size()-1);
		boolean correct = tree.getRoot().getChildren().equals(result.getRoot().getChildren());
		assertTrue("Tree is not correct", correct);
		
		}
	

}
