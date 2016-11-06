package slib.examples.sml.general;

import java.util.ArrayList;

import slib.graph.algo.utils.SemanticPath;
import slib.graph.algo.utils.SemanticPathSplitter;
import slib.graph.model.graph.G;
import slib.graph.model.graph.elements.E;
import slib.sml.sm.core.engine.SM_Engine;
import slib.sml.sm.core.metrics.ic.utils.IC_Conf_Topo;
import slib.sml.sm.core.metrics.ic.utils.ICconf;
import slib.sml.sm.core.utils.SMConstants;
import slib.sml.sm.core.utils.SMconf;
import slib.utils.ex.SLIB_Ex_Critic;
import slib.utils.ex.SLIB_Exception;

public class InformationCalculator {
	private ICconf icConf;
	private SMconf measureConf;
	private SemanticPathSplitter sps;
	private SM_Engine engine;

	public InformationCalculator(G g) throws SLIB_Ex_Critic {
		icConf = new IC_Conf_Topo("SANCHEZ", SMConstants.FLAG_ICI_SANCHEZ_2011);
		measureConf = new SMconf(SMConstants.FLAG_SIM_PAIRWISE_DAG_EDGE_RESNIK_1995);
		sps = new SemanticPathSplitter();
		engine = new SM_Engine(g);
	}

	public double calculateInformation(SemanticPath path) throws SLIB_Exception {
		ArrayList<String> split = sps.dividePath(path);
		ArrayList<E> list = new ArrayList<E>();
		list.addAll(path.getPath());
		double result = 0;
		for (int i = 0; i < split.size(); i++) {
			double temp = 0;
			if (split.get(i).contains("U")) {
				double top = engine.getIC(icConf, list.get(split.get(i).length() - 1).getTarget());
				double bot = engine.getIC(icConf, list.get(0).getSource());
				temp = top - bot;
				list.subList(0, split.get(i).length() - 1).clear();
			}else if (split.get(i).contains("D")) {
				double top = engine.getIC(icConf, list.get(split.get(i).length() - 1).getTarget());
				double bot = engine.getIC(icConf, list.get(0).getSource());
				temp = bot-top;
				list.subList(0, split.get(i).length() - 1).clear();
			}else{
				temp = (split.get(i).length()/(1+split.get(i).length()));
				list.subList(0, split.get(i).length() - 1).clear();
			}
			result += temp;
		}
		return result;
	}


}
