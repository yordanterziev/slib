package slib.examples.sml.test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.JAXBException;

import org.junit.*;


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
	SrNode organization = new SrNode(factory.getURI(swrcOntology+"#Organization"));
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

		SrTree tree = treeList.get(treeList.size()-1);
		SrNode treeRoot = tree.getRoot();
		boolean correct = false;
		int counter = 0;
		HashSet<SrNode> setTest = new HashSet<SrNode>();
		HashSet<SrNode> setCheck = new HashSet<SrNode>();
		setTest.addAll(treeRoot.getChildren());
		setCheck.addAll(result.getRoot().getChildren());
		System.out.println(setTest.size());
		System.out.println(setCheck.size());
		for(SrNode nodeTest : setTest){
			for(SrNode nodeCheck : setCheck){
				if (nodeCheck.getData().getLocalName().equals(nodeTest.getData().getLocalName())){
					counter++;
				}
			}
		}
		for(SrNode nodeCheck : setCheck){
			System.out.println(nodeCheck.getData().getLocalName());
		}
		for(SrNode nodeCheck : setTest){
			System.out.println(nodeCheck.getData().getLocalName());
		}
		if (counter == 5){
			correct = true;
		}
		assertTrue("Tree is not correct", correct);
		
		}
	

}
