package slib.examples.sml.general;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.xml.bind.*;

import org.openrdf.model.URI;
import org.openrdf.model.vocabulary.RDFS;

import slib.examples.sml.general.xml.edges.*;
import slib.graph.algo.traversal.classical.BFS;
import slib.graph.model.graph.G;
import slib.graph.model.graph.utils.Direction;
import slib.graph.model.graph.utils.WalkConstraint;
import slib.graph.model.impl.repo.URIFactoryMemory;
import slib.graph.model.repo.URIFactory;
import slib.graph.utils.WalkConstraintGeneric;
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
		SemanticPathCalc test = new SemanticPathCalc(swrcOntology,path);
		test.setOrigin(factory.getURI(swrcOntology+origin));
		
		JAXBContext jc = JAXBContext.newInstance("slib.examples.sml.general.xml.edges");
		Unmarshaller u = jc.createUnmarshaller();
		Edges edges = (Edges)((JAXBElement) u.unmarshal(new FileInputStream("Edge.xml")) ).getValue();
		List<String> hEdges = edges.getHorizontalEdges().getName();
		List<String>  dEdges = edges.getDownwardEdges().getName();
		List<String>  uEdges = edges.getUpwardEdges().getName();

        HashMap<URI, Direction> map = new HashMap<URI, Direction>();
        for(String s:hEdges){
        	URI temp = factory.getURI(swrcOntology+"#"+s);
        	map.put(temp, Direction.BOTH);
        }
        for(String s:dEdges){
        	URI temp = factory.getURI(swrcOntology+"#"+s);
        	map.put(temp, Direction.BOTH);
        }
        for(String s:uEdges){
        	URI temp = factory.getURI(swrcOntology+"#"+s);
        	map.put(temp, Direction.BOTH);
        }
        
        G g = test.getGraph();
        System.out.println(g.containsVertex(factory.getURI(swrcOntology+"#Document")));
        System.out.println(g.containsVertex(factory.getURI(swrcOntology+"#Product")));
        System.out.println(g.getNumberVertices());
        		
       
        
        WalkConstraint wc = new WalkConstraintGeneric(RDFS.SUBCLASSOF, Direction.BOTH);
        wc.addWalkconstraints(new WalkConstraintGeneric(map));
        BFS bfs = new BFS(test.getGraph(), factory.getURI(swrcOntology+origin), wc);
        while(bfs.hasNext()){
            URI n = bfs.next();
            System.out.println("-"+n);
        }
        System.out.println("BFS done");
        
        
       
	}

}
