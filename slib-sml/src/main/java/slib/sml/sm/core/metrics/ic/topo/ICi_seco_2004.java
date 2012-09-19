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
 
 
package slib.sml.sm.core.metrics.ic.topo;

import slib.sglib.model.graph.elements.V;
import slib.sml.sm.core.metrics.ic.utils.IC_Conf_Topo;
import slib.sml.sm.core.utils.SM_manager;
import slib.utils.ex.SGL_Exception;
import slib.utils.impl.ResultStack;


/**
 * ﻿1. Seco N, Veale T, Hayes J: 
 * An Intrinsic Information Content Metric for Semantic Similarity in WordNet. 
 * In 16th European Conference on Artificial Intelligence. 
 * IOS Press; 2004, 16:1-5.
 * 
 *  IC range : [0,1]
 *  
 * @author Sebastien Harispe 
 */
public class ICi_seco_2004 implements ICtopo{

	// TODO include current leaf
	public ResultStack<V,Double> compute(ResultStack<V,Long> allNbOfDescendants) throws SGL_Exception{

		ResultStack<V,Double> results = new ResultStack<V,Double>(this.getClass().getSimpleName());

		int nbDesc;
		double x, cur_ic;

		int setSize = allNbOfDescendants.size();

		for ( V v:allNbOfDescendants.keySet() ) {

			nbDesc	= allNbOfDescendants.get(v).intValue();
			
			x =  Math.log( (double) nbDesc)/  Math.log( (double) setSize) ;

			cur_ic = 1 - x ;

			results.add(v, cur_ic);
		}

		return results;
	}
	
	public ResultStack<V,Double> compute(IC_Conf_Topo conf, SM_manager manager)
			throws SGL_Exception {
		return compute(manager.getAllNbDescendants());
	}

}
