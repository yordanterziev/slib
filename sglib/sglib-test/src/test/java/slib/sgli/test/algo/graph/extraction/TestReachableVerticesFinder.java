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
 
 
package slib.sgli.test.algo.graph.extraction;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.openrdf.model.URI;
import org.openrdf.model.vocabulary.RDFS;

import slib.sgli.test.algo.graph.SGL_UnitTestValues;
import slib.sgli.test.algo.graph.TestUtils;
import slib.sglib.algo.extraction.rvf.RVF_DAG;
import slib.sglib.algo.extraction.rvf.RVF_TAX;
import slib.sglib.algo.utils.WalkConstraintTax;
import slib.sglib.io.util.GFormat;
import slib.sglib.model.graph.G;
import slib.sglib.model.graph.elements.V;
import slib.sglib.model.graph.utils.WalkConstraints;
import slib.utils.ex.SGL_Ex_Critic;
import slib.utils.ex.SGL_Exception;
import slib.utils.impl.SetUtils;

import com.tinkerpop.blueprints.Direction;

public class TestReachableVerticesFinder{
	
	public G g;
	public RVF_DAG rvf;
	
	SGL_UnitTestValues testValues;
	
	public TestReachableVerticesFinder() throws SGL_Exception{
		
		testValues = new SGL_UnitTestValues();
		
		
		g = TestUtils.loadTestGraph(GFormat.SGL,SGL_UnitTestValues.G_DAG_BASIC);
	}
	
	@Test
	public void test_dag_descendant_1() throws SGL_Ex_Critic{
		
		WalkConstraints wc = new WalkConstraintTax(RDFS.SUBCLASSOF,Direction.IN);
		rvf = new RVF_DAG(g, wc);
		
		V v = g.getV(testValues.G_BASIC_THING);
		
		Set<V> desc= rvf.getRV(v);
		int sizeInter = SetUtils.intersection(desc, g.getV()).size();
		
		System.out.println(sizeInter+"/"+g.getV().size());
		
		assertTrue(sizeInter == g.getV().size());
	}
	

	
	@Test
	public void test_dag_descendant_2() throws SGL_Ex_Critic{
		
		WalkConstraints wc = new WalkConstraintTax(RDFS.SUBCLASSOF,Direction.IN);
		rvf = new RVF_DAG(g, wc);
		
		V v = g.getV(testValues.G_BASIC_SPIDER);
		
		Set<V> desc= rvf.getRV(v);
		System.out.println(desc);
		
		assertTrue(desc.size() == 5);
	}
	
	@Test
	public void test_dag_descendant_3() throws SGL_Ex_Critic{
		
		WalkConstraints wc = new WalkConstraintTax(RDFS.SUBCLASSOF,Direction.IN);
		rvf = new RVF_DAG(g, wc);
		V v = g.getV(testValues.G_BASIC_OBJECT);
		
		Set<V> desc= rvf.getRV(v);
		assertTrue(desc.size() == 7);
	}
	
	@Test
	public void test_dag_descendant_4() throws SGL_Ex_Critic{
		
		WalkConstraints wc = new WalkConstraintTax(RDFS.SUBCLASSOF,Direction.IN);
		rvf = new RVF_DAG(g, wc);
		V v = g.getV(testValues.G_BASIC_SPIDERMAN);
		
		Set<V> desc= rvf.getRV(v);
		assertTrue(desc.size() == 1);
	}
	
	@Test
	public void test_dag_ancestors_1() throws SGL_Ex_Critic{
		
		WalkConstraints wc = new WalkConstraintTax(RDFS.SUBCLASSOF,Direction.OUT);
		rvf = new RVF_DAG(g, wc);
		V v = g.getV(testValues.G_BASIC_OBJECT);
		
		HashSet<V> anc= (HashSet<V>) rvf.getRV(v);
		
		assertTrue(anc.size() == 2);
	}
	
	@Test
	public void test_dag_ancestors_1b() throws SGL_Ex_Critic{
		
		RVF_TAX rvf = new RVF_TAX( g, Direction.OUT);
		Map<V,Set<V>> ancestorsMap = rvf.getAllRVClass();

		V v = g.getV(testValues.G_BASIC_OBJECT);
		
		HashSet<V> anc= (HashSet<V>) ancestorsMap.get(v);
		
		assertTrue(anc.size() == 2);
	}
	
	
	
	@Test
	public void test_dag_ancestors_2() throws SGL_Ex_Critic{
		
		WalkConstraints wc = new WalkConstraintTax(RDFS.SUBCLASSOF,Direction.OUT);
		rvf = new RVF_DAG(g, wc);
		V v = g.getV(testValues.G_BASIC_MEN);
		
		HashSet<V> anc= (HashSet<V>) rvf.getRV(v);
		System.out.println(anc);
		assertTrue(anc.size() == 8);
	}
	
	@Test
	public void test_dag_ancestors_3() throws SGL_Ex_Critic{
		
		WalkConstraints wc = new WalkConstraintTax(RDFS.SUBCLASSOF,Direction.OUT);
		rvf = new RVF_DAG(g, wc);
		V v = g.getV(testValues.G_BASIC_THING);
		
		HashSet<V> anc= (HashSet<V>) rvf.getRV(v);
		assertTrue(anc.size() == 1);
	}
	
	@Test
	public void test_dag_all_1() throws SGL_Ex_Critic{
		
		WalkConstraints wc = new WalkConstraintTax(RDFS.SUBCLASSOF,Direction.BOTH);
		rvf = new RVF_DAG(g, wc);
		V v = g.getV(testValues.G_BASIC_ANIMAL);
		
		HashSet<V> all= (HashSet<V>) rvf.getRV(v);
		
		assertTrue(all.size() == g.getV().size());
	}

	@Test
	public void test_dag_all_2() throws SGL_Ex_Critic{
		
		HashSet<URI> setEdge = new HashSet<URI>();
		setEdge.add(RDFS.SUBCLASSOF);
		
		WalkConstraints wc = new WalkConstraintTax(RDFS.SUBCLASSOF,Direction.BOTH);
		rvf = new RVF_DAG(g, wc);
		V v = g.getV(testValues.G_BASIC_TABLE);
		
		HashSet<V> all= (HashSet<V>) rvf.getRV(v);
		
		assertTrue(all.size() == g.getV().size());
	}

}
