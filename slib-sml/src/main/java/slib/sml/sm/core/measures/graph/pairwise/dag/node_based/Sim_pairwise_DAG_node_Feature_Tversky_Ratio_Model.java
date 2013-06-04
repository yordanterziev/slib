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
package slib.sml.sm.core.measures.graph.pairwise.dag.node_based;

import org.openrdf.model.URI;
import slib.sml.sm.core.engine.SM_Engine;
import slib.sml.sm.core.utils.SMconf;
import slib.utils.ex.SLIB_Exception;
import slib.utils.impl.SetUtils;

/**
 * IC formulation of Tversky Ratio Model
 *
 * @author Sebastien Harispe
 *
 */
public class Sim_pairwise_DAG_node_Feature_Tversky_Ratio_Model extends Sim_pairwise_DAG_node_Tversky_IC_Ratio_Model {

    public Sim_pairwise_DAG_node_Feature_Tversky_Ratio_Model() {}

    /**
     * Create a Tversky measure specifying alpha and beta parameters
     * @param alpha
     * @param beta
     */
    public Sim_pairwise_DAG_node_Feature_Tversky_Ratio_Model(double alpha, double beta) {
        super(alpha,beta);
    }

    @Override
    public double sim(URI a, URI b, SM_Engine c, SMconf conf) throws SLIB_Exception {

        if (conf != null && conf.containsParam(alpha_param_name)) {
            alpha = conf.getParamAsDouble(alpha_param_name);
        }

        if (conf != null && conf.containsParam(beta_param_name)) {
            beta = conf.getParamAsDouble(beta_param_name);
        }

        double ic_a = c.getAncestorsInc(a).size();
        double ic_b = c.getAncestorsInc(b).size();
        
        double ic_MICA = 0;
        
        for(URI sharedAnc : SetUtils.intersection(c.getAncestorsInc(a), c.getAncestorsInc(b))){
            
            double ic_s = c.getAncestorsInc(sharedAnc).size();
            if(ic_s > ic_MICA){
                ic_MICA = ic_s;
            }
        }
        return sim(ic_a, ic_b, ic_MICA, alpha, beta);
    }
}