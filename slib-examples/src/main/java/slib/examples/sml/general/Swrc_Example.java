package slib.examples.sml.general;

import java.util.Set;

import org.openrdf.model.URI;

import slib.graph.io.conf.GDataConf;
import slib.graph.io.loader.GraphLoaderGeneric;
import slib.graph.io.util.GFormat;
import slib.graph.model.graph.G;
import slib.graph.model.graph.elements.E;
import slib.graph.model.impl.graph.memory.GraphMemory;
import slib.graph.model.impl.repo.URIFactoryMemory;
import slib.graph.model.repo.URIFactory;
import slib.utils.ex.SLIB_Exception;

public class Swrc_Example {

	public static void main(String[] args) throws SLIB_Exception {
		
		URIFactory factory = URIFactoryMemory.getSingleton();
        
        URI graph_uri = factory.getURI("http://swrc.ontoware.org/ontology/");
        
        G graph = new GraphMemory(graph_uri);
        
        String fpath = System.getProperty("user.dir")+"/src/main/resources/swrc_v0.3.owl";
        GDataConf graphconf = new GDataConf(GFormat.RDF_XML, fpath);
        GraphLoaderGeneric.populate(graphconf, graph);
        
        
        Set<E> e = graph.getE();
        
        for (E e2 : e) {
			System.out.println(e2);
		}
        
        // General information about the graph
        System.out.println(graph.toString());
	
	}
}
