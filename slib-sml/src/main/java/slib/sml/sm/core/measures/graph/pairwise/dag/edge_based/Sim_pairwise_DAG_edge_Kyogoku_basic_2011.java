/*

Copyright or © or Copr. Ecole des Mines d'Alès (2012) 

This software is a computer program whose purpose is to 
process semantic graphs.

This software is governed by the CeCILL  license under French law and
abiding by the rules of distribution of free software.  You can  use, 
modify and/ or redistribute the software under the terms of the CeCILL
license as circulated by CEA, CNRS and INRIA at the following URL
"http://www.cecill.info". 

As a counterpart to the access to the source code and  rights to copy,
modify and redistribute granted by the license, users are provided only
with a limited warranty  and the software's author,  the holder of the
economic rights,  and the successive licensors  have only  limited
liability. 

In this respect, the user's attention is drawn to the risks associated
with loading,  using,  modifying and/or developing or reproducing the
software by the user in light of its specific status of free software,
that may mean  that it is complicated to manipulate,  and  that  also
therefore means  that it is reserved for developers  and  experienced
professionals having in-depth computer knowledge. Users are therefore
encouraged to load and test the software's suitability as regards their
requirements in conditions enabling the security of their systems and/or 
data to be ensured and,  more generally, to use and operate it in the 
same conditions as regards security. 

The fact that you are presently reading this means that you have had
knowledge of the CeCILL license and that you accept its terms.

 */
 
 
package slib.sml.sm.core.measures.graph.pairwise.dag.edge_based;

import slib.sglib.model.graph.elements.V;
import slib.sml.sm.core.utils.SM_manager;
import slib.sml.sm.core.utils.SMconf;
import slib.utils.ex.SGL_Exception;



/**
 * Kyogoku R, Fujimoto R, Ozaki T, Ohkawa T: 
 * A method for supporting retrieval of articles on protein structure analysis considering users ’ intention. 
 * BMC Bioinformatics 2011, 12:S42. p 2
 * 
 * Basic because Kyogoku propose a method to estimate edge weight
 * 
 * @author Sebastien Harispe
 *
 * @param <V>
 */
public class Sim_pairwise_DAG_edge_Kyogoku_basic_2011 extends Sim_DAG_edge_abstract{

	public double sim(V a, V b, SM_manager c, SMconf conf) throws SGL_Exception {

		int maxDepth   = c.getMaxDepth();

		double sp_AtoB = c.getShortestPath(a,b);
		double sp_BtoA = c.getShortestPath(b,a);

		return sim(maxDepth,sp_AtoB,sp_BtoA);
	}

	/**
	 * 
	 * @param maxDepth Max depth
	 * @param sp_AtoB Shortest path from A to B
	 * @param sp_BtoA Shortest path from B to A
	 * 
	 * @return
	 */
	public double sim(double maxDepth,double sp_AtoB, double sp_BtoA){

		double sim = maxDepth * 2 - (sp_AtoB + sp_BtoA);

		return sim;
	}



}
