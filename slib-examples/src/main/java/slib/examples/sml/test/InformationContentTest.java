package slib.examples.sml.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.openrdf.model.URI;

import slib.graph.algo.utils.SemanticPath;
import slib.graph.io.conf.GDataConf;
import slib.graph.io.loader.GraphLoaderGeneric;
import slib.graph.io.util.GFormat;
import slib.graph.model.graph.G;
import slib.graph.model.impl.graph.memory.GraphMemory;
import slib.graph.model.impl.repo.URIFactoryMemory;
import slib.graph.model.repo.URIFactory;
import slib.sml.sm.core.engine.SM_Engine;
import slib.sml.sm.core.metrics.ic.utils.IC_Conf_Topo;
import slib.sml.sm.core.utils.SMConstants;
import slib.utils.ex.SLIB_Exception;




/**
 * Testing the calculation of a paths value. It uses imaginary values but the same calculation.
 * This test can not calculate the value of upward and downward sequence with length 1.
 * @author Florian Jakobs
 *
 */
public class InformationContentTest {
	
	double result = 0;
	ArrayList<String> split = new ArrayList<String>();
	ArrayList<Integer> list = new ArrayList<Integer>();

	
	@Test
	public void test() {
		
		split.add("DD");
		
		split.add("HHH");

		list.add(4);
		list.add(3);
		
		list.add(5);
		list.add(5);
		list.add(5);
		
		double tx = 1.0;
		
		for (int i = 0; i < split.size(); i++) {
			double temp = 0;
			int length = split.get(i).length();
			if (split.get(i).contains("U")) {
				if(length>=2){
					int top = list.get(split.get(i).length()-1);
					int bot = list.get(0);
					temp = Math.abs(top - bot);
				}else{
					int top = list.get(0);
					int bot = list.get(0);
					temp = Math.abs(top - bot);	
				}

				list.subList(0, split.get(i).length()).clear();
			}else if (split.get(i).contains("D")) {
				if(length>=2){
					int top = list.get(split.get(i).length()-1);
					int bot = list.get(0);
					temp = Math.abs(top - bot);
				}else{
					int top = list.get(0);
					int bot = list.get(0);
					temp = Math.abs(top - bot);
				}
				list.subList(0, split.get(i).length()).clear();
			}else{
				temp = (split.get(i).length());
				temp = temp / (temp+1);
				temp = temp * tx;
				list.subList(0, split.get(i).length()).clear();
			}
			result += temp;
		}
		
		
		
		double actual = Math.abs(3 - 4) + (3.0/4.0);
		System.out.println(actual);
		System.out.println(result);
		assertTrue(actual==result);
		
	}

}