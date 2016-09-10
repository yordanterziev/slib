/* 
 *  Copyright or © or Copr. Ecole des Mines d'Alès (2012-2014) 
 *  
 *  This software is a computer program whose purpose is to provide 
 *  several functionalities for the processing of semantic data 
 *  sources such as ontologies or text corpora.
 *  
 *  This software is governed by the CeCILL  license under French law and
 *  abiding by the rules of distribution of free software.  You can  use, 
 *  modify and/ or redistribute the software under the terms of the CeCILL
 *  license as circulated by CEA, CNRS and INRIA at the following URL
 *  "http://www.cecill.info". 
 * 
 *  As a counterpart to the access to the source code and  rights to copy,
 *  modify and redistribute granted by the license, users are provided only
 *  with a limited warranty  and the software's author,  the holder of the
 *  economic rights,  and the successive licensors  have only  limited
 *  liability. 

 *  In this respect, the user's attention is drawn to the risks associated
 *  with loading,  using,  modifying and/or developing or reproducing the
 *  software by the user in light of its specific status of free software,
 *  that may mean  that it is complicated to manipulate,  and  that  also
 *  therefore means  that it is reserved for developers  and  experienced
 *  professionals having in-depth computer knowledge. Users are therefore
 *  encouraged to load and test the software's suitability as regards their
 *  requirements in conditions enabling the security of their systems and/or 
 *  data to be ensured and,  more generally, to use and operate it in the 
 *  same conditions as regards security. 
 * 
 *  The fact that you are presently reading this means that you have had
 *  knowledge of the CeCILL license and that you accept its terms.
 */
package slib.graph.io.loader.rdf;

import java.util.HashMap;
import java.util.Map.Entry;

import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.Value;
import org.openrdf.rio.RDFHandler;
import org.openrdf.rio.RDFHandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import slib.graph.model.graph.G;
import slib.graph.model.impl.repo.URIFactoryMemory;
import slib.graph.model.repo.URIFactory;

/**
 *
 * @author Sébastien Harispe (sebastien.harispe@gmail.com)
 */
public class SlibRdfHandler implements RDFHandler {
	// HashMaps that save the non RDF Standard Edges
	HashMap<URI,URI> hashRange = new HashMap<URI, URI>();
	HashMap<URI,URI> hashDomain = new HashMap<URI, URI>();
    
	G g;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    int count = 0;
    int countSkipped = 0;
    URIFactory factory;

    /**
     *
     * @param g
     */
    public SlibRdfHandler(G g) {
        this.g = g;
        factory = URIFactoryMemory.getSingleton();
    }

    @Override
    public void startRDF() throws RDFHandlerException {

        logger.info("Start Process");
        count = 0;
    }

    @Override
    public void endRDF() throws RDFHandlerException {
    	// Called to push the non Standard edges
    	this.pushHorizontalEdges();
    	
    	
    	System.out.println("+++++++PushHorizonal+++++++");
        logger.info("Ending Process " + count + " statements loaded ");
        logger.info("vertices: " + g.getV().size());
        logger.info("edges   : " + g.getE().size());
        logger.info("Skipped (statement involving non URI ressources) : " + countSkipped);
    }

    @Override
    // TODO
    public void handleNamespace(String prefix, String uri) throws RDFHandlerException {
    }

    @Override
    public void handleStatement(Statement st) throws RDFHandlerException {
        
        Value s = st.getSubject();
        Value o = st.getObject();
        
//        logger.debug(st.toString());
        
        // Pushing the non Standard edges to the associated HashMaps
        
        if	 (st.getPredicate().toString().contains("#domain")
        		&& !(st.getObject().toString().contains("node"))
        	 ){
        	hashDomain.put( (URI) st.getSubject(),(URI) st.getObject());
        	System.out.println("Domain push");
        	System.out.println("------" +st.toString());
        	
        }
        else if 	 (st.getPredicate().toString().contains("#range")
        			 &&!(st.getObject().toString().contains("node"))
        			// &&!(st.getObject().toString().contains("XMLSchema#"))
        			 ){
        	hashRange.put( (URI) st.getSubject(),(URI) st.getObject());
        	System.out.println("Range push");
        	System.out.println("++++++"+st.toString());
        }
        else{
        	System.out.println("Subject " + st.getSubject().toString());
        	System.out.println("Pred " + st.getPredicate().toString());
        	System.out.println("Object " + st.getObject().toString());
        	System.out.println("Finish statement");
        	System.out.println();
        }

        if (s instanceof URI && o instanceof URI) {
            g.addE((URI) s,st.getPredicate(),(URI) o);
            count++;
        }
        else{
            countSkipped++;
        }
        if(count % 100000 == 0){
            logger.info(count+" statements already loaded");
            logger.info("Number of vertices: "+g.getV().size());
            logger.info("Number of edges   : "+g.getE().size());
        }
    }

    @Override
    public void handleComment(String comment) throws RDFHandlerException {}
    
    
    /**
     * Pushes the non Standard edges to the graph
     * 
     * @author Florian Jakobs
     */
    public void pushHorizontalEdges(){
    	for(Entry<URI, URI> entry : hashDomain.entrySet()) {
    	    URI key = entry.getKey();
    	    URI value = entry.getValue();
    	    if (hashRange.containsKey(key)){
    	    	System.out.println("hashRange contians "+key.toString());
    	    	System.out.println("######### "+value.toString()+key.toString()+hashRange.get(key).toString());
    	    	g.addE(value, key, hashRange.get(key));
    	    }
    	    // do what you have to do here
    	    // In your case, an other loop.
    	}
    }
}
