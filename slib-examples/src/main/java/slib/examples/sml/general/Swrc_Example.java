package slib.examples.sml.general;

import java.util.Set;

import org.openrdf.model.URI;

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
import slib.sml.sm.core.engine.SM_Engine;
import slib.sml.sm.core.metrics.ic.utils.IC_Conf_Topo;
import slib.sml.sm.core.metrics.ic.utils.ICconf;
import slib.sml.sm.core.utils.SMConstants;
import slib.utils.ex.SLIB_Exception;

public class Swrc_Example {

	public static void main(String[] args) throws SLIB_Exception {
		
		URIFactory factory = URIFactoryMemory.getSingleton();
        
        URI graph_uri = factory.getURI("http://swrc.ontoware.org/ontology/");
        
        G graph = new GraphMemory(graph_uri);
        
        String fpath = System.getProperty("user.dir")+"/src/main/resources/swrc_v0.3.owl";
        GDataConf graphconf = new GDataConf(GFormat.RDF_XML, fpath);
        GraphLoaderGeneric.populate(graphconf, graph);
        
        SM_Engine engine = new SM_Engine(graph);
        
        Set<URI> taxonomicLeaves = engine.getTaxonomicLeaves();
        
        
        // First we define the information content (IC) we will use
        ICconf icConf = new IC_Conf_Topo("RESNIK", SMConstants.FLAG_ICI_RESNIK_1995);
        
      
        
      
        
        System.out.println(engine.getIC(icConf, factory.getURI("http://swrc.ontoware.org/ontology#Event")));
        
        System.out.println(engine.getIC(icConf, factory.getURI("http://swrc.ontoware.org/ontology#Meeting")));
        
        System.out.println(engine.getIC(icConf, factory.getURI("http://swrc.ontoware.org/ontology#ProjectMeeting")));
        
//        for (URI uri : taxonomicLeaves) {
//			System.out.println(uri);
//		}
        
        // General information about the graph
        //System.out.println(graph.toString());
	
	}
}
