package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.wuerth.phoenix.cis.university.example1.adapters.ICRComponent;
import com.wuerth.phoenix.cis.university.example1.adapters.ICompany;
import com.wuerth.phoenix.cis.university.example1.adapters.IProfitCenter;
import com.wuerth.phoenix.cis.university.example1.implementations.Account;
import com.wuerth.phoenix.cis.university.example1.implementations.CRComponent;
import com.wuerth.phoenix.cis.university.example1.implementations.ProfitCenter;
import com.wuerth.phoenix.cis.university.example1.types.DataScenarioType;


	public class CombinationCreator
	{
		static DataScenarioType [] ds = DataScenarioType.values();
		
		public  Object create_test_data() {
			System.out.println("Running!");
			List<Object> aggregatedData = new ArrayList <Object>();
			// maybe these need to be merged together? then combined => quite large
			List<ArrayList<String>> crComponents = createComponentLists("data/CRComponent.csv");
			List<ArrayList<String>> profit_center = createComponentLists("data/ProfitCenter.csv");
			List<ArrayList<String>> accounts = createComponentLists("data/Account.csv");	
			System.out.println("Components size" + crComponents.size() + "Profit" + profit_center.size() + "Accounts" + accounts.size());
			System.out.println(accounts);
			
			// need to combine these better
			List<ArrayList<String>> all_values = new ArrayList<ArrayList<String>>();
			for (ArrayList<String> a : accounts) {
				for (ArrayList<String> c: crComponents) {
					//for (ArrayList<String> p: profit_center) {
						List<String> newList = new ArrayList<String>(a);
						newList.addAll(c);
						//newList.addAll(p);
						all_values.add((ArrayList<String>) newList);
					//}
				}
			}
			aggregatedData = listToInput(all_values);
			return aggregatedData;
			
		}
		
		public static List<Object> listToInput(List<ArrayList<String>> all_values){
			List<Object> aggregatedData = new ArrayList <Object>();
			for (ArrayList<String> a : all_values) {
				Account tempAcc = new Account(a.get(0), a.get(1), a.get(2), a.get(3));
				ICRComponent testCR = new CRComponent(a.get(4), a.get(5), a.get(6), a.get(7));
				boolean isExternal = true;
				ICompany testComp = new ICompany("test");
				IProfitCenter testprCen = new ProfitCenter("test", "FALSE");				
				DataScenarioType scenarioType = null;
				for(DataScenarioType d: ds) {		
					if (d.toString().toLowerCase().equals("Actual")) {
						scenarioType = d;
					}
					
				}
				AggregatedTestData instance = new AggregatedTestData(testComp, testprCen, testCR, isExternal, scenarioType,tempAcc, "test", "EU");
				aggregatedData.add(instance);				
				}
			return aggregatedData;
		}
		
		// adds the combinations of true/false
		static void resursivelyCombine(List<List<String>> results, List<String> current, List<String> in1, List<String> in2, int index) {
		    if (index == in1.size()) {
		        results.add(current);
		    } else {
		        if (in1.get(index).equals(in2.get(index))) {
		           current.add(in1.get(index));
		           resursivelyCombine(results, current, in1, in2, index+1);
		        } else {
		           List<String> temp = new ArrayList<>(current);
		           temp.add("TRUE");
		           resursivelyCombine(results, temp, in1, in2, index+1);

		           temp = new ArrayList<>(current);
		           temp.add("FALSE");
		           resursivelyCombine(results, temp, in1, in2, index+1);
		        }
		    }
		}
		
		
	    public static List<List<String>> combinationCreator(int columnCount, String string_path) {
	    	 	List<List<String>> results = new ArrayList<>();
	    	 	List<String> in1 = new ArrayList<>();
	    	 	List<String> in2 = new ArrayList<>();
	    	 	if (string_path.contains("Account")){
	    	 		columnCount = 2;
	    	 	}
	    	 	for (int i=0; i<columnCount-1;i++) {
	    	 		System.out.println("ColumnCount" + columnCount);
		    	 	in1.add("TRUE");
		    	 	in2.add("FALSE");
	    	 	}
	    	 	resursivelyCombine(results, new ArrayList<String>(), in1, in2, 0);
	    	 	
	    	 	return results;

	    }
	    
	    public static List<ArrayList<String>> generateGenericData(Set<String> col1, int columnCount, String string_path){
		    	List<ArrayList<String>> csvList = new ArrayList<ArrayList<String>>(); 
		    	List<List<String>> booleanCombos = combinationCreator(columnCount, string_path);
		        for (String column: col1) {
		        		// @ Depends if we use this boolean combos , it will be quite large
		        		//for (List<String> b: booleanCombos) {
		        			ArrayList<String> trueCombo = new ArrayList<String>();
			        		trueCombo.add(column);
			        		trueCombo.add("TRUE");
			        		trueCombo.add("FALSE");
			        		csvList.add(trueCombo);
		        		//}
		        				
		        }
		        return csvList;
	    }
	    
	    public static List<ArrayList<String>> generateAccountData(Set<String> col1,  Set<String> col2){
		    List<ArrayList<String>> csvList = new ArrayList<ArrayList<String>>();
				    int count = 0;
					for (String s:col1) {
			    			for (String s2:col2) {
			    			 ArrayList<String> arrayCombo = new ArrayList<String>();
			    			 arrayCombo.add(Integer.toString(count));
			    			 arrayCombo.add(s);
			    			 arrayCombo.add(s2);
			    			 // depends whether we use the boolean accounts
			    			 if (count %2 == 0) {
			        			 arrayCombo.add("True");
			        			 arrayCombo.add("False");
			    			 }
			    			 else {
			        			 arrayCombo.add("False");
			        			 arrayCombo.add("True");
			    			 }
			    			 csvList.add(arrayCombo);
			    			 count++;
			    		}
					}
					return csvList;
	    }
				
		public static List<ArrayList<String>> createComponentLists(String string_path){
	    		Path path = Paths.get(FileSystems.getDefault().getPath("").toAbsolutePath().toString(), string_path);
			String csvFile = path.toString();
			BufferedReader br = null;
			String line = "";
			String cvsSplitBy = ",";
			int columnCount=0;
			boolean isHeader = true;
			List<ArrayList<String>> csvList = new ArrayList<ArrayList<String>>();
			
			try {
				br = new BufferedReader(new FileReader(csvFile));

		        Set<String> column1 = new HashSet<String>();
		        Set<String> column2 = new HashSet<String>();
		      
		        while((line=br.readLine())!=null){
		        {
			        	if (isHeader) {
							isHeader = false;
							continue;
					}
		            String[] linePieces = line.split(cvsSplitBy);
		            columnCount = linePieces.length;
		            // can we do this?
		            if (string_path.contains("Account")) {
			            	column1.add(linePieces[1]);
			            column2.add(linePieces[2]);
		            }
		            
		            else {
		            linePieces[0]=linePieces[0].replaceAll("[*0-9]", "");
		            column1.add(linePieces[0]);
		            }
		        }
		        }
		        
		        if (string_path.contains("Account")) {
		        		csvList=generateAccountData(column1, column2);
		        }
		        else {
		        		csvList = generateGenericData(column1, columnCount, string_path);
			       
		        }
		        br.close();
		        return csvList;
				
				}catch (Exception ex) {
				System.out.println(ex.toString());
			}
			return null;
		    

	}
			}

