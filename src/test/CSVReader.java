package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.wuerth.phoenix.cis.university.example1.adapters.IAccount;
import com.wuerth.phoenix.cis.university.example1.adapters.ICRComponent;
import com.wuerth.phoenix.cis.university.example1.adapters.ICompany;
import com.wuerth.phoenix.cis.university.example1.adapters.IProfitCenter;
import com.wuerth.phoenix.cis.university.example1.implementations.Account;
import com.wuerth.phoenix.cis.university.example1.implementations.CRComponent;
import com.wuerth.phoenix.cis.university.example1.implementations.ProfitCenter;
import com.wuerth.phoenix.cis.university.example1.types.AccountClass;
import com.wuerth.phoenix.cis.university.example1.types.DataScenarioType;

public class CSVReader {
	String ObjectName;
	List<Object> aggregatedData = new ArrayList <Object>();

	public CSVReader() {
	}
	
	public Object create_Object_List(String ObjectName, int columnNumber) {
		Path path = Paths.get(FileSystems.getDefault().getPath("").toAbsolutePath().toString(), "data", ObjectName + ".csv");
		String csvFile = path.toString();
	
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		
		boolean isHeader = true;
		DataScenarioType [] ds = DataScenarioType.values();
		
	
		try {
			
			br = new BufferedReader(new FileReader(csvFile));
			
			while ((line = br.readLine()) != null) {
				if (isHeader) {
					isHeader = false;
					continue;
				}

				String[] splitLine = line.split(cvsSplitBy);
				// just testing => need to be replaced
				Account tempAcc = new Account(splitLine[0], splitLine[1], splitLine[2], splitLine[3]);
				ICompany testComp = new ICompany(splitLine[4]);
				IProfitCenter testprCen = new ProfitCenter(splitLine[5], splitLine[6]);
				ICRComponent testCR = new CRComponent(splitLine[7],splitLine[8],splitLine[9],splitLine[10]);
				boolean isExternal = Boolean.valueOf(splitLine[11]);
				DataScenarioType scenarioType = null;
				for(DataScenarioType d: ds) {		
					if (d.toString().toLowerCase().equals(splitLine[12].toLowerCase())) {
						scenarioType = d;
					}
					
				}
				AggregatedTestData instance = new AggregatedTestData(testComp, testprCen, testCR, isExternal, scenarioType,tempAcc, splitLine[13], splitLine[14]);
				aggregatedData.add(instance);
						

			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return aggregatedData;
	}

}
