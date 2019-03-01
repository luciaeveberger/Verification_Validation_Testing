package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import com.wuerth.phoenix.cis.university.example1.Example1Checker;

import com.wuerth.phoenix.cis.university.example1.adapters.IAccount;
import com.wuerth.phoenix.cis.university.example1.adapters.ICRComponent;
import com.wuerth.phoenix.cis.university.example1.adapters.ICompany;
import com.wuerth.phoenix.cis.university.example1.adapters.IProfitCenter;

import com.wuerth.phoenix.cis.university.example1.implementations.CRComponent;
import com.wuerth.phoenix.cis.university.example1.implementations.ProfitCenter;


import com.wuerth.phoenix.cis.university.example1.types.DataScenarioType;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class AccountValidityTest {
	
	 static List<AggregatedTestData> combinedDataList= new ArrayList <AggregatedTestData>();
	 
	

	@Parameters
	    public static Collection<Object[]> data() {
	    	CombinationCreator Creator = new CombinationCreator();
	    	combinedDataList = (List<AggregatedTestData>) Creator.create_test_data();
	    	
	    	Object[][]objArray = new Object[combinedDataList.size()][2];
	    	for (int i=0; i<combinedDataList.size(); i++) {
	    		objArray[i][0]=combinedDataList.toArray()[i];
	    		objArray[i][1] = true;

	    	}
	    		return Arrays.asList(objArray);
	    }
	
	

	@AfterClass
	public static void tearDown() throws Exception {
	}
	
    private AggregatedTestData testDataRow;
	
    private boolean predictedOutcome;
	
	public AccountValidityTest(AggregatedTestData testAccount, boolean predictedOutcome) {
		this.testDataRow = testAccount;
		this.predictedOutcome = predictedOutcome;
	}
	
	@Test
	public void testAccountInputs() {
			try {
				System.out.println(testDataRow.getAccount());
				boolean res = Example1Checker.isValid(testDataRow.getCompany(), testDataRow.getProfitCenter(), testDataRow.getCrComponent(), testDataRow.isExternal(), testDataRow.getScenarioType(),testDataRow.getAccount(), testDataRow.getPartnerCode(), testDataRow.getCurrencyCode());
				assertEquals(res, predictedOutcome);
				}
			catch(Exception e) {
					System.out.println(e);
				}
		}

		

}
