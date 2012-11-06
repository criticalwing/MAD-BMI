package ie.cit.patrickrobertson.BMI;

import junit.framework.TestCase;

public class ResultTest extends TestCase {

	public void testResult() {
		Result x = new Result(100,100);
		
		assertEquals(100,x.getBMI());
		
	}

	public void testGetHeight() {
		Result x = new Result(100,100);
		
		assertEquals(100.0,x.getHeight());
	}

	public void testGetWeight() {
		Result x = new Result(100,100);
		
		assertEquals(100.0,x.getWeight());
	}

	public void testGetBMI() {
		Result x = new Result(100,100);
		
		assertEquals(100,x.getBMI());
	}

	public void testGetDate() {
		Result x = new Result(100,100);
		
		assertEquals("05/11/2012",x.getDate());
	}

}
