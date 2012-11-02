package ie.cit.patrickrobertson.BMI;

import junit.framework.TestCase;

public class PersonTest extends TestCase {

	Person test;
	
	public void testPersonStringString() {
		test = new Person("200","100",true);
		
		assertEquals(29, test.getBMI());
	}

	public void testPersonStringString2() {
		test = new Person("6'6\"","220.462", false);
		assertEquals(29, test.getBMI());
	}

	public void testGetHeight() {
		test = new Person("6'6\"","200", false);
		
		assertEquals(198.12,test.getHeight());
	}

	public void testGetWeight() {
		test = new Person("6'6\"","200", false);
		
		assertEquals(90.7184,test.getWeight());
	}

	
}
