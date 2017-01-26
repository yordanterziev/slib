package slib.examples.sml.test;

import static org.junit.Assert.*;

import org.junit.Test;

import slib.graph.algo.utils.CorrectPaths;

/**
 * This test works only for strings with length 1 to 3 because it is assumed that paths longer than 3 have already been checked.
 * 
 * @author Florian Jakobs
 *
 */
public class CorrectPathTest {
	
	
	CorrectPaths test = new CorrectPaths();
	@Test
	public void test() {
		
		boolean test1 = test.isSemanticCorrect("UDU"); 
		boolean test2 = test.isSemanticCorrect("HHU");
		boolean test3 = test.isSemanticCorrect("DU");
		boolean test4 = test.isSemanticCorrect("UD");
		boolean test5 = test.isSemanticCorrect("DDU");
		
		assertTrue(!test1 && !test2 && !test3 && test4 && !test5);
	}

}
