package model;


import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTest(BuyerModelDMTest.suite());
		suite.addTest(OrderModelDMTest.suite());
		suite.addTest(ProductModelDMTest.suite());
		suite.addTest(SellerModelDMTest.suite());
		suite.addTest(AdminModelDMTest.suite());
		return suite;
	}
	
	public static void main(String args[]) {
		TestRunner.run(suite());
	}
}
