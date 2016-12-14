package slib.sml.sm.core.utils;

import java.util.ArrayList;

import slib.graph.algo.utils.SemanticPath;
import slib.graph.algo.utils.SemanticPathSplitter;
import slib.graph.model.graph.G;
import slib.graph.model.graph.elements.E;
import slib.sml.sm.core.engine.SM_Engine;
import slib.sml.sm.core.metrics.ic.utils.IC_Conf_Topo;
import slib.sml.sm.core.metrics.ic.utils.ICconf;
import slib.utils.ex.SLIB_Ex_Critic;
import slib.utils.ex.SLIB_Exception;

/**
 * The class calculates the Information Gain for a given SemanticPath. It does not check if the path is correct.
 * In this implementation it uses "Sanchez" method for calculating the IC of a single node. Different methods can be used.
 * @author Florian Jakobs
 *
 */
public class InformationCalculator {
	private ICconf icConf;
	private SMconf measureConf;
	private SemanticPathSplitter sps;
	private SM_Engine engine;

	/**
	 * Initializes the Calculator with a given graph.
	 * @param g the graph on which the calculations are made
	 * @throws SLIB_Ex_Critic
	 */
	public InformationCalculator(G g) throws SLIB_Ex_Critic {
		// Sanchez method was used. It can be changed to use different methods for calculating IC.
		
		icConf = new IC_Conf_Topo("SANCHEZ", SMConstants.FLAG_ICI_SANCHEZ_2011);
		measureConf = new SMconf(SMConstants.FLAG_SIM_PAIRWISE_DAG_EDGE_RESNIK_1995);
		sps = new SemanticPathSplitter();
		engine = new SM_Engine(g);
	}

	/**
	 * Calculates the Information Gain of a Semantic Path. It returns a {@code double} representing the Information gain.
	 * @param path the path which is to be calculated
	 * @param tx the value assigned to horizontal Edges
	 * @return the Information gain
	 * @throws SLIB_Exception
	 */
	public double calculateInformation(SemanticPath path, double tx) throws SLIB_Exception {
		ArrayList<String> split = sps.dividePath(path);
		ArrayList<E> list = new ArrayList<E>();
		list.addAll(path.getPath());
		double result = 0;
		for (int i = 0; i < split.size(); i++) {
			double temp = 0;
			int length = split.get(i).length();
			if (split.get(i).contains("U")) {
				if(length>=2){
					double top = engine.getIC(icConf, list.get(split.get(i).length()-1).getTarget());
					double bot = engine.getIC(icConf, list.get(0).getSource());
					temp = Math.abs(top - bot);
				}else{
					double top = engine.getIC(icConf, list.get(0).getTarget());
					double bot = engine.getIC(icConf, list.get(0).getSource());
					temp = Math.abs(top - bot);	
				}

				list.subList(0, split.get(i).length()).clear();
			}else if (split.get(i).contains("D")) {
				if(length>=2){
					double top = engine.getIC(icConf, list.get(split.get(i).length()-1).getTarget());
					double bot = engine.getIC(icConf, list.get(0).getSource());
					temp = Math.abs(top - bot);
				}else{
					double top = engine.getIC(icConf, list.get(0).getTarget());
					double bot = engine.getIC(icConf, list.get(0).getSource());
					temp = Math.abs(top - bot);
				}
				list.subList(0, split.get(i).length()).clear();
			}else{
				// tx * (n/n+1)
				temp = (split.get(i).length());
				temp = temp / (temp+1);
				temp *= tx;
				list.subList(0, split.get(i).length()).clear();
			}
			result += temp;
		}
		return result;
	}


}
