
package com.wuerth.phoenix.cis.university.example2.test.lemon;

import java.util.HashMap;
import java.util.Locale;

import com.wuerth.phoenix.cis.university.example2.adapters.DigitGroupingSymbol;
import com.wuerth.phoenix.cis.university.example2.util.CombinationItem;
import com.wuerth.phoenix.cis.university.example2.util.Settings;


public class CombinationValues {
	HashMap<String, CombinationItem<?>> correct = new HashMap<String, CombinationItem<?>>();
	HashMap<String, CombinationItem<?>> incorrect = new HashMap<String, CombinationItem<?>>();
	HashMap<String, CombinationItem<?>> empty = new HashMap<String, CombinationItem<?>>();
	int counter;
	Settings settings;
	public CombinationValues() {
		settings = getSettings();
		counter = 0;
		FillHashMap(correct, "Abc",1523743200000L,1.2,"8999");
		String tooLong = "uydnvsynbuqfdfhwbsprrtxkejojlncwktgvbcvjuyaezozloahltdosptxtexxfalvreqkiihiefuokmgaxnlnexdgnlwaxmupavgupuuqdbktuffgqbvuzghvveekpzhzygfmiaalemcuefjtwcpuyilpyezzqwmbffumpyiflnaupdztjqbuwhqosplodvqbfnvphbzsyrxcowvckojxghiwuiqdkeocksvhbhafkjcpzughqewjxhdvhoxadftazduddnibpleivzxumqrzjwqpzwolrgzeaapojgdknuwizfisqrwaoevapndhwpajvwosyxigjqofrazeabyzahivznhxmzqcxpdgrddoirsiwleeazmvyfnvksmehuggwspvvetsgvfultaytzwbutmqyojlyagwnozccumrzamypvhaekqpxbgfngthadqazvlcfkazlefsctyjrzubgygvnhlhlghvefadggpniimwwdwma";
		FillHashMap(incorrect, tooLong,null,null,"100");
		FillHashMap(empty,"",null,null,"");

	}
	private Settings getSettings() {
		Locale currentLocale = new Locale("de");
		DigitGroupingSymbol digitGroupingSymbol = DigitGroupingSymbol.COMMA;
		DigitGroupingSymbol decimalSeparator = DigitGroupingSymbol.POINT;
		String dateFormat = "dd-MM-yyyy";
		Settings settings = new Settings(currentLocale, digitGroupingSymbol, decimalSeparator, dateFormat);
		settings.setLongVersion(true);
		settings.setAcceptOrders(true);
		return settings;
	}
	private void FillHashMap(HashMap<String, CombinationItem<?>> hmap, String genericString, Long genericDate, Double genericDouble, String companyCode) {
		CombinationItem<String> iContractNumber = CombinationItem.getNewCombinationItem(genericString);
		hmap.put("ContractNumber", iContractNumber);
		CombinationItem<String> iCreditorName = CombinationItem.getNewCombinationItem(genericString);
		hmap.put("CreditorName", iCreditorName);
		CombinationItem<String> iCreditorNumber = CombinationItem.getNewCombinationItem(genericString);
		hmap.put("CreditorNumber", iCreditorNumber);
		CombinationItem<String> iDesignatedLeaseObject = CombinationItem.getNewCombinationItem(genericString);
		hmap.put("DesignatedLeaseObject", iDesignatedLeaseObject);
		CombinationItem<Long> iEndOfContract = CombinationItem.getNewCombinationItem(genericDate,settings);
		hmap.put("EndDateOfContract", iEndOfContract);
		CombinationItem<String> iPartnerCompany = CombinationItem.getNewCombinationItem(companyCode);
		hmap.put("PartnerCompany", iPartnerCompany);
		CombinationItem<Long> iStartOfContract = CombinationItem.getNewCombinationItem(genericDate,settings);
		hmap.put("StartOfContract", iStartOfContract);
		CombinationItem<Double> iInterestRate = CombinationItem.getNewCombinationItem(genericDouble,settings);
		hmap.put("InterestRate", iInterestRate);		
		CombinationItem<Long> iProbableEndOfContract = CombinationItem.getNewCombinationItem(genericDate,settings);
		hmap.put("ProbableEndOfContract", iProbableEndOfContract);
		
		
		CombinationItem<Long> iFromDate = CombinationItem.getNewCombinationItem(genericDate,settings);
		hmap.put("FromDate", iFromDate);
		CombinationItem<String> iCostCenter = CombinationItem.getNewCombinationItem(genericString);
		hmap.put("CostCenter", iCostCenter);
		
		CombinationItem<String> iOrder = CombinationItem.getNewCombinationItem(genericString);
		hmap.put("Order", iOrder);
		CombinationItem<String> iCostCenterOrOrder = CombinationItem.getNewCombinationItem(genericString);
		hmap.put("CostCenterOrOrder", iCostCenterOrOrder);
		
	
		CombinationItem<Double> iAmountWithoutValueAddedTax = CombinationItem.getNewCombinationItem(genericDouble,settings);
		hmap.put("AmountWithoutValueAddedTax", iAmountWithoutValueAddedTax);
		
		CombinationItem<Long> iUntilDate = CombinationItem.getNewCombinationItem(genericDate,settings);
		hmap.put("UntilDate", iUntilDate);
		
	
	}

	public HashMap<String, CombinationItem<?>> getCorrectValues() {
		return (HashMap<String, CombinationItem<?>>) correct.clone();
	}
	
	public HashMap<String, CombinationItem<?>> getInCorrectValues(String key) {
		HashMap<String, CombinationItem<?>> result = (HashMap<String, CombinationItem<?>>) correct.clone();
		CombinationItem<?> swapped = incorrect.get(key);
		result.put(key,swapped);
		return result;
	} 
	public HashMap<String, CombinationItem<?>> getEmptyValues(String key) {
		HashMap<String, CombinationItem<?>> result = (HashMap<String, CombinationItem<?>>) correct.clone();
		CombinationItem<?> swapped = empty.get(key);
		result.put(key,swapped);
		return result;
	} 
	public CombinationItem<?> getIncorrectValue(String key){
		return incorrect.get(key);
	}
	public CombinationItem<?> getEmptyValue(String key){
		return empty.get(key);
	}
	public CombinationItem<?> getCorrectValue(String key){
		return correct.get(key);
	}
	public CombinationItem<?> getCorrectContractNumber(){
		CombinationItem<String> iContractNumber = CombinationItem.getNewCombinationItem("Abc"+(++counter));
		return iContractNumber;
	} 

}

