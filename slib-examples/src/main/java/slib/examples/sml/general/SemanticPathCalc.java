package slib.examples.sml.general;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.openrdf.model.URI;
import org.openrdf.model.vocabulary.RDFS;

import slib.examples.sml.general.xml.edges.Edges;
import slib.graph.algo.traversal.classical.BFS;
import slib.graph.algo.traversal.classical.BFSLevel;
import slib.graph.algo.utils.SemanticPath;
import slib.graph.algo.utils.SemanticPathAdder;
import slib.graph.model.graph.G;
import slib.graph.model.graph.elements.E;
import slib.graph.model.graph.utils.Direction;
import slib.graph.model.graph.utils.WalkConstraint;
import slib.graph.model.repo.URIFactory;
import slib.graph.utils.WalkConstraintGeneric;
import slib.sml.sm.core.engine.SM_Engine;
import slib.sml.sm.core.metrics.ic.utils.IC_Conf_Topo;
import slib.sml.sm.core.metrics.ic.utils.ICconf;
import slib.sml.sm.core.utils.SMConstants;
import slib.sml.sm.core.utils.SMconf;
import slib.utils.ex.SLIB_Exception;

/**
 * The SemanticPathCalc implements {@link SemanticRelatednes}. It provides an
 * implementation for calculating semantic correct path in the specified
 * ontology. It uses a {@link BFS} to traverse the graph.
 * 
 * @author Florian Jakobs
 * 
 */
public class SemanticPathCalc implements SemanticRelatednes {

	private OntologyCreator oc;
	private URI graph_uri;
	private G graph;
	private SM_Engine engine;
	private URIFactory uriFactory;
	private URI origin;
	private JAXBContext edgeCategory;
	private static ArrayList<String> hEdges;
	private static ArrayList<String> dEdges;
	private static ArrayList<String> uEdges;
	private SemanticPathAdder spa;
	private String ontology;
	private BFSLevel bfs;

	// Method for calculating the IC
	private ICconf icConf = new IC_Conf_Topo("SANCHEZ", SMConstants.FLAG_ICI_SANCHEZ_2011);
	private SMconf measureConf = new SMconf(SMConstants.FLAG_SIM_PAIRWISE_DAG_EDGE_RESNIK_1995);

	/**
	 * This constructor uses {@link OntologyCreator} in order to create a graph
	 * filled with an ontology.
	 * 
	 * @param ontology
	 *            a String describing the ontology's URI
	 * @param filepath
	 *            relative location of the file containing the ontology
	 * @throws SLIB_Exception
	 */
	public SemanticPathCalc(String ontology, String filepath) throws SLIB_Exception {
		this.ontology = ontology;
		oc = new OntologyCreator(ontology, filepath);
		graph_uri = oc.getUriOnto();
		graph = oc.getGraph();
		engine = oc.getEngine();
		uriFactory = oc.getFactory();

	}

	public G getGraph() {
		return graph;
	}

	@Override
	public void setOrigin(URI uri) {
		// TODO Auto-generated method stub
		this.origin = uri;
	}

	@Override
	public double getSemanticRelatedness(URI uriA, URI uriB) throws SLIB_Exception {
		// TODO Auto-generated method stub
		// double ic1 = engine.getIC(icConf, uriA);
		// double ic2 = engine.getIC(icConf, uriB);
		measureConf.setICconf(icConf);
		System.out.println(engine.getIC(icConf, uriA));
		System.out.println(engine.getIC(icConf, uriB));
		System.out.println(engine.getIC(icConf, uriFactory.getURI(ontology + "#ResearchGroup")));
		System.out.println(engine.getIC(icConf, uriFactory.getURI(ontology + "#Student")));
		System.out.println(engine.getIC(icConf, uriFactory.getURI(ontology + "#Graduate")));
		System.out.println(engine.getIC(icConf, uriFactory.getURI(ontology + "#University")));
		System.out.println(engine.getIC(icConf, uriFactory.getURI(ontology + "#Employee")));
		System.out.println(engine.getIC(icConf, uriFactory.getURI(ontology + "#Publication")));
		System.out.println(engine.getIC(icConf, uriFactory.getURI(ontology + "#Misc")));
		// System.out.println(engine.getIC(icConf,
		// uriFactory.getURI(ontology+"#Thing")));
		return engine.compare(measureConf, uriA, uriB);
	}

	/**
	 * Reads the XML File containing the Edges.
	 * 
	 * @param file
	 *            The XML file containing the Edges
	 * @throws JAXBException
	 * @throws FileNotFoundException
	 */
	private void loadEdges(String file) throws JAXBException, FileNotFoundException {
		edgeCategory = JAXBContext.newInstance("slib.examples.sml.general.xml.edges");
		Unmarshaller u = edgeCategory.createUnmarshaller();
		Edges edges = (Edges) ((JAXBElement) u.unmarshal(new FileInputStream(file))).getValue();
		hEdges = (ArrayList<String>) edges.getHorizontalEdges().getName();
		dEdges = (ArrayList<String>) edges.getDownwardEdges().getName();
		uEdges = (ArrayList<String>) edges.getUpwardEdges().getName();
		spa = new SemanticPathAdder(uEdges,dEdges,hEdges);
	}

	/**
	 * Loads the upward downward and horizontal Edges into the WC and creates a
	 * BFS
	 */
	private void loadBFS() {
		HashMap<URI, Direction> map = new HashMap<URI, Direction>();
		for (String s : hEdges) {
			URI temp = uriFactory.getURI(ontology + "#" + s);
			map.put(temp, Direction.BOTH);
		}
		for (String s : dEdges) {
			URI temp = uriFactory.getURI(ontology + "#" + s);
			map.put(temp, Direction.BOTH);
		}
		for (String s : uEdges) {
			URI temp = uriFactory.getURI(ontology + "#" + s);
			map.put(temp, Direction.BOTH);
		}
		WalkConstraint wc = new WalkConstraintGeneric(RDFS.SUBCLASSOF, Direction.BOTH);
		wc.addWalkconstraints(new WalkConstraintGeneric(map));
		bfs = new BFSLevel(graph, origin, wc,spa);
	}

	@Override
	public void initialiseWalk(String file) throws JAXBException, FileNotFoundException {
		// TODO Auto-generated method stub
		this.loadEdges(file);
		this.loadBFS();
	}

	@Override
	public HashMap<URI,SemanticPath> getSemanticallyCorrectPaths(int hops) throws SLIB_Exception {
		// TODO Auto-generated method stub
		HashMap<URI,SemanticPath> temp = bfs.LevelSearch(hops);


		return temp;
	}





}
