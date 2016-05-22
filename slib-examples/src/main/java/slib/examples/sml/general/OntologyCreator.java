package slib.examples.sml.general;

import org.openrdf.model.URI;

import slib.graph.io.conf.GDataConf;
import slib.graph.io.loader.GraphLoaderGeneric;
import slib.graph.io.util.GFormat;
import slib.graph.model.graph.G;
import slib.graph.model.impl.graph.memory.GraphMemory;
import slib.graph.model.impl.repo.URIFactoryMemory;
import slib.graph.model.repo.URIFactory;
import slib.sml.sm.core.engine.SM_Engine;
import slib.utils.ex.SLIB_Exception;

public class OntologyCreator {
	
	private URI uriOnto;
	private URIFactory factory = URIFactoryMemory.getSingleton();
	private G graph;
	private GDataConf graphconf;
	private SM_Engine engine;
	
	
	/**
	 * Creating the Graph and the SM_Engine for a given Ontology
	 * 
	 * @param ontology a String describing the ontology's URI
     * @param filepath relative location of the file containing the ontology
	 * @throws SLIB_Exception
	 */
	public OntologyCreator(String ontology,String filepath) throws SLIB_Exception{
		
		uriOnto = factory.getURI(ontology);
		graph = new GraphMemory(uriOnto);
		String fpath = System.getProperty("user.dir")+filepath;
		graphconf = new GDataConf(GFormat.RDF_XML, fpath);
		GraphLoaderGeneric.populate(graphconf, graph);
		engine = new SM_Engine(graph);
		
	}


	public URI getUriOnto() {
		return uriOnto;
	}


	public void setUriOnto(URI uriOnto) {
		this.uriOnto = uriOnto;
	}


	public G getGraph() {
		return graph;
	}


	public void setGraph(G graph) {
		this.graph = graph;
	}


	public GDataConf getGraphconf() {
		return graphconf;
	}


	public void setGraphconf(GDataConf graphconf) {
		this.graphconf = graphconf;
	}


	public SM_Engine getEngine() {
		return engine;
	}


	public void setEngine(SM_Engine engine) {
		this.engine = engine;
	}


	public URIFactory getFactory() {
		return factory;
	}


	public void setFactory(URIFactory factory) {
		this.factory = factory;
	}

	
	
	
}
