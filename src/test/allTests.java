package test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
//the SuiteClasses annotation include all the test classes: in our case only one
//@SuiteClasses({ AggregatedTestData.class })
@SuiteClasses({ AccountValidityTest.class })
// can add as many

public class allTests {
	
}
