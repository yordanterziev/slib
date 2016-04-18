package slib.examples.sml.general;

import java.util.HashMap;
import java.util.Set;

import org.openrdf.model.URI;
import org.openrdf.model.vocabulary.OWL;

import slib.graph.algo.traversal.classical.BFS;
import slib.graph.io.conf.GDataConf;
import slib.graph.io.loader.GraphLoaderGeneric;
import slib.graph.io.util.GFormat;
import slib.graph.model.graph.G;
import slib.graph.model.graph.elements.E;
import slib.graph.model.graph.utils.Direction;
import slib.graph.model.graph.utils.WalkConstraint;
import slib.graph.model.impl.graph.memory.GraphMemory;
import slib.graph.model.impl.repo.URIFactoryMemory;
import slib.graph.model.repo.URIFactory;
import slib.graph.utils.WalkConstraintGeneric;
import slib.sml.sm.core.engine.SM_Engine;
import slib.utils.ex.SLIB_Exception;

public class Swrc_Example {

	public static void main(String[] args) throws SLIB_Exception {
		
		String swrcOntology = "http://swrc.ontoware.org/ontology";
		
		URIFactory factory = URIFactoryMemory.getSingleton();
        
        URI graph_uri = factory.getURI(swrcOntology);
        
        G graph = new GraphMemory(graph_uri);
        
        String fpath = System.getProperty("user.dir")+"/src/main/resources/swrc_updated_v0.7.1.owl";
        GDataConf graphconf = new GDataConf(GFormat.RDF_XML, fpath);
        GraphLoaderGeneric.populate(graphconf, graph);
        
        SM_Engine engine = new SM_Engine(graph);
        
         
//      Set<E> e = graph.getE();
//        
//        for (E e2 : e) {
//			System.out.println(e2);
//		}
       
        Set<E> e = graph.getE(factory.getURI(swrcOntology+"#Person"), Direction.OUT);
        
        for (E e2 : e) {
			System.out.println(e2);
		}
        
        
        
        
        //WalkConstraint wc = new WalkConstraintGeneric(RDFS.SUBCLASSOF, Direction.IN);
        HashMap<URI, Direction> map = new HashMap<URI, Direction>();
        
        map.put(factory.getURI("http://swrc.ontoware.org/ontology#worksAtProject"), Direction.BOTH);
        
        WalkConstraint wc = new WalkConstraintGeneric(map);
        // The BFS will be performed from the root according to the walk constraint specified above
        // In this case, only rdfs:SubClassOf triplets (edges) will be considered and the traversal 
        // will always be made from the target to the source of the SubClassOf relationship
        URI employeeUri = factory.getURI(swrcOntology+"#AssistantProfessor");
        BFS bfs = new BFS(graph, employeeUri, wc); // you can do the same with the DFS class
        
        while(bfs.hasNext()){
        
            URI n = bfs.next();
            System.out.println("-"+n);
        }
        System.out.println("BFS done");
        
        //Set<URI> taxonomicLeaves = engine.getTaxonomicLeaves();
        
        
        // First we define the information content (IC) we will use
        //ICconf icConf = new IC_Conf_Topo("RESNIK", SMConstants.FLAG_ICI_RESNIK_1995);

//        System.out.println(engine.getIC(icConf, factory.getURI("http://swrc.ontoware.org/ontology#Event")));
//        
//        System.out.println(engine.getIC(icConf, factory.getURI("http://swrc.ontoware.org/ontology#Meeting")));
//        
//        System.out.println(engine.getIC(icConf, factory.getURI("http://swrc.ontoware.org/ontology#ProjectMeeting")));
        
//        for (URI uri : taxonomicLeaves) {
//			System.out.println(uri);
//		}
        
        // General information about the graph
        //System.out.println(graph.toString());
	
	}
}
