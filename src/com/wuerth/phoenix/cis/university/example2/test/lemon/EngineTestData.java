package com.wuerth.phoenix.cis.university.example2.test.lemon;

import java.nio.file.FileSystems;
import java.nio.file.Path;
//import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import com.wuerth.phoenix.cis.university.example2.test.IEngineTestData;
import com.wuerth.phoenix.cis.university.example2.util.*;
import com.wuerth.phoenix.cis.university.example2.adapters.*;

public abstract class EngineTestData implements IEngineTestData {

	final String dirPath = new String("./data/");

	// Range of allowed company codes
	final String codeStart = "1000";
	final String codeEnd = "8999";
	int position;
	private CombinationValues cv;
	private ArrayList<Scenario> createdScenarios = new ArrayList<Scenario>();
	private CombinationItem<Double> CorrectDouble;
	private CombinationItem<Double> InCorrectDouble;
	private CombinationItem<Long> CorrectDate;
	private CombinationItem<Long> InCorrectDate;
	private final int YEAR 	= 2018;
	private final int MONTH = Calendar.MARCH;

	public EngineTestData(String testCaseName, int position) {
		
		Settings settings = getSettings();
		
		CorrectDouble = CombinationItem.getNewCombinationItem(1.2,settings);
		InCorrectDouble = CombinationItem.getNewCombinationItem((Double)null,settings);
		CorrectDate = CombinationItem.getNewCombinationItem(1523743200000L,settings);
		InCorrectDate = CombinationItem.getNewCombinationItem((Long)null,settings);
		this.position = position;
		cv = new CombinationValues();
		if (position < 0 || position > 5) {
			position = 0;
		}
	}

	private Settings getSettings() {
		Locale currentLocale = new Locale("de");
		DigitGroupingSymbol digitGroupingSymbol = DigitGroupingSymbol.COMMA;
		DigitGroupingSymbol decimalSeparator = DigitGroupingSymbol.POINT;
		String dateFormat = "dd-MM-yyyy";
		Settings settings = new Settings(currentLocale, digitGroupingSymbol, decimalSeparator, dateFormat);
		settings.setLongVersion(true);
		settings.setAcceptOrders(true);
		settings.setYear(YEAR);
		settings.setMonth(MONTH);
		return settings;
	}

	public ArrayList<Company> getDummyCompanies() {

		ArrayList<Company> companyList = new ArrayList<Company>();

		ImplementedCompany implementedCompany1 = new ImplementedCompany();
		implementedCompany1.setType(CompanyType.PRODUCTIVE);

		ImplementedCompany implementedCompany2 = new ImplementedCompany();
		implementedCompany2.setType(CompanyType.CONSOLIDATED);

		ImplementedCompany implementedCompany3 = new ImplementedCompany();
		implementedCompany3.setType(CompanyType.PRODUCTIVE);

		ImplementedCompany implementedCompany4 = new ImplementedCompany();
		implementedCompany4.setType(CompanyType.CONSOLIDATED);

		Company company = new Company();
		company.setCode("1000");
		company.createChildImplementedCompany(implementedCompany1);
		company.createChildImplementedCompany(implementedCompany2);
		companyList.add(company);

		Company company2 = new Company();
		company2.setCode("8999");
		company2.createChildImplementedCompany(implementedCompany3);
		company2.createChildImplementedCompany(implementedCompany4);
		companyList.add(company2);
		return companyList;
	}

	/*
	 * public ArrayList<Scenario> getScenario() { return createdScenarios; }
	 */
	public void AddScenario(ArrayList<CombinationLine> combinationLines, Settings settings,
			ArrayList<Company> companies) {
		Combination combination = new Combination();
		combination.getLineList().addAll(combinationLines);
		settings.setLongVersion(true);
		settings.setAcceptOrders(true);
		Scenario scenario = new Scenario(companies, combination, settings);
		createdScenarios.add(scenario);

	}

	private void addCombination1() {
		HashMap<IFRS16ImportAssignmentType, CombinationItem<?>> currentValues = new HashMap<IFRS16ImportAssignmentType, CombinationItem<?>>();
		CombinationLine combinationLine = new CombinationLine();
		ArrayList<Company> companyList = getDummyCompanies();

		ArrayList<CombinationLine> combinationLines = new ArrayList<CombinationLine>();

		ImplementedCompany impl;
		Company firstCompany = companyList.get(0);

		HashMap<String, CombinationItem<?>> correctValues = cv.getCorrectValues();
		Settings settings1 = getSettings();

		settings1.setName("LemonSettings1");
		settings1.setFilePath("C:\\phxrepository\\cis_dev\\dev\\bin\\lemon_comb1.csv");
		settings1.setLongVersion(true);
		impl = (ImplementedCompany) firstCompany.getAllChildImplementedCompany().toArray()[0];
		settings1.setImplementedCompany(impl);

		settings1.getTypeList().add(IFRS16ImportAssignmentType.CONTRACTNUMBER);
		settings1.getTypeList().add(IFRS16ImportAssignmentType.CREDITORNAME);
		settings1.getTypeList().add(IFRS16ImportAssignmentType.CREDITORNUMBER);
		settings1.getTypeList().add(IFRS16ImportAssignmentType.DESIGNATIONLEASEDOBJECT);
		settings1.getTypeList().add(IFRS16ImportAssignmentType.ENDDATEOFCONTRACT);
		settings1.getTypeList().add(IFRS16ImportAssignmentType.PARTNERCOMPANY);
		settings1.getTypeList().add(IFRS16ImportAssignmentType.STARTDATEOFCONTRACT);
		settings1.getTypeList().add(IFRS16ImportAssignmentType.INTERESTRATE);
		settings1.getTypeList().add(IFRS16ImportAssignmentType.PROBABLEENDOFCONTRACT);

		// For Correct Values in Combination 1

		currentValues.put(IFRS16ImportAssignmentType.CONTRACTNUMBER, cv.getCorrectContractNumber());
		currentValues.put(IFRS16ImportAssignmentType.CREDITORNAME, correctValues.get("CreditorName"));
		currentValues.put(IFRS16ImportAssignmentType.CREDITORNUMBER, correctValues.get("CreditorNumber"));
		currentValues.put(IFRS16ImportAssignmentType.DESIGNATIONLEASEDOBJECT,
				correctValues.get("DesignatedLeaseObject"));
		currentValues.put(IFRS16ImportAssignmentType.ENDDATEOFCONTRACT, correctValues.get("EndDateOfContract"));
		currentValues.put(IFRS16ImportAssignmentType.PARTNERCOMPANY, correctValues.get("PartnerCompany"));
		currentValues.put(IFRS16ImportAssignmentType.STARTDATEOFCONTRACT, correctValues.get("StartOfContract"));
		currentValues.put(IFRS16ImportAssignmentType.INTERESTRATE, correctValues.get("InterestRate"));
		currentValues.put(IFRS16ImportAssignmentType.PROBABLEENDOFCONTRACT, correctValues.get("ProbableEndOfContract"));

		combinationLine.getDataMap().putAll(currentValues);
		combinationLines.add(combinationLine);
		ArrayList<String> combinationVariables = new ArrayList<String>(Arrays.asList("ContractNumber", "CreditorName",
				"CreditorNumber", "DesignatedLeaseObject", "EndDateOfContract",  "StartOfContract",
				"InterestRate", "ProbableEndOfContract","PartnerCompany"));
		
		// for incorrect values. wrong + empty
		for (String parameter : combinationVariables) {
			currentValues.clear();
			combinationLine = new CombinationLine();
			HashMap<String, CombinationItem<?>> inCorrectValues = cv.getInCorrectValues(parameter);
			HashMap<String, CombinationItem<?>> emptyValues = cv.getEmptyValues(parameter);
			// incorrect
			currentValues.put(IFRS16ImportAssignmentType.CONTRACTNUMBER, inCorrectValues.get("ContractNumber"));
			currentValues.put(IFRS16ImportAssignmentType.CREDITORNAME, inCorrectValues.get("CreditorName"));
			currentValues.put(IFRS16ImportAssignmentType.CREDITORNUMBER, inCorrectValues.get("CreditorNumber"));
			currentValues.put(IFRS16ImportAssignmentType.DESIGNATIONLEASEDOBJECT,
					inCorrectValues.get("DesignatedLeaseObject"));
			currentValues.put(IFRS16ImportAssignmentType.ENDDATEOFCONTRACT, inCorrectValues.get("EndDateOfContract"));
			currentValues.put(IFRS16ImportAssignmentType.PARTNERCOMPANY, inCorrectValues.get("PartnerCompany"));
			currentValues.put(IFRS16ImportAssignmentType.STARTDATEOFCONTRACT, inCorrectValues.get("StartOfContract"));
			currentValues.put(IFRS16ImportAssignmentType.INTERESTRATE, inCorrectValues.get("InterestRate"));
			currentValues.put(IFRS16ImportAssignmentType.PROBABLEENDOFCONTRACT,
					inCorrectValues.get("ProbableEndOfContract"));
			combinationLine.getDataMap().putAll(currentValues);
			combinationLines.add(combinationLine);

			currentValues.clear();
			combinationLine = new CombinationLine();
			// empty

			currentValues.put(IFRS16ImportAssignmentType.CONTRACTNUMBER, emptyValues.get("ContractNumber"));
			currentValues.put(IFRS16ImportAssignmentType.CREDITORNAME, emptyValues.get("CreditorName"));
			currentValues.put(IFRS16ImportAssignmentType.CREDITORNUMBER, emptyValues.get("CreditorNumber"));
			currentValues.put(IFRS16ImportAssignmentType.DESIGNATIONLEASEDOBJECT,
					emptyValues.get("DesignatedLeaseObject"));
			currentValues.put(IFRS16ImportAssignmentType.ENDDATEOFCONTRACT, emptyValues.get("EndDateOfContract"));
			currentValues.put(IFRS16ImportAssignmentType.PARTNERCOMPANY, emptyValues.get("PartnerCompany"));
			currentValues.put(IFRS16ImportAssignmentType.STARTDATEOFCONTRACT, emptyValues.get("StartOfContract"));
			currentValues.put(IFRS16ImportAssignmentType.INTERESTRATE, emptyValues.get("InterestRate"));
			currentValues.put(IFRS16ImportAssignmentType.PROBABLEENDOFCONTRACT,
					emptyValues.get("ProbableEndOfContract"));
			combinationLine.getDataMap().putAll(currentValues);
			combinationLines.add(combinationLine);

		}

		AddScenario(combinationLines, settings1, companyList);

	}

	private void addCombination2() {
		HashMap<IFRS16ImportAssignmentType, CombinationItem<?>> currentValues = new HashMap<IFRS16ImportAssignmentType, CombinationItem<?>>();

		CombinationLine combinationLine = new CombinationLine();
		ArrayList<Company> companyList = getDummyCompanies();

		ArrayList<CombinationLine> combinationLines = new ArrayList<CombinationLine>();

		ArrayList<String> allGroupPositionCodes = new ArrayList<String>(
				Arrays.asList(Constants.IFRS16_GROUP_POSITION_ACCOUNT_CODES));
		
		ArrayList<String> allConditionTypeCodes = new ArrayList<String>(Arrays.asList(Constants.CONDITIONTYPE_CODES));
	
		ImplementedCompany impl;
		Company firstCompany = companyList.get(0);
		impl = (ImplementedCompany) firstCompany.getAllChildImplementedCompany().toArray()[0];

		Settings settings2 = getSettings();
		settings2.setName("LemonSettings2");
		settings2.setFilePath("C:\\phxrepository\\cis_dev\\dev\\bin\\lemon_comb2.csv");
		settings2.setLongVersion(true);
		settings2.setImplementedCompany(impl);

		settings2.getTypeList().add(IFRS16ImportAssignmentType.CONTRACTNUMBER);
		settings2.getTypeList().add(IFRS16ImportAssignmentType.GROUPPOSITION);
		settings2.getTypeList().add(IFRS16ImportAssignmentType.CONDITIONTYPE);
		settings2.getTypeList().add(IFRS16ImportAssignmentType.FROMDATE);
		settings2.getTypeList().add(IFRS16ImportAssignmentType.COSTCENTER);

		// Correct Values + Iteration in GroupPosition and ConditionType
		for (String groupCode : allGroupPositionCodes) {
			for (String conditionTypeCode : allConditionTypeCodes) {
				currentValues.clear();
				combinationLine = new CombinationLine();

				// GroupPosition CombinationItem
				ConcreteAccount concrAcc = new ConcreteAccount(groupCode);
				CombinationItem<ConcreteAccount> groupPosition = CombinationItem.getNewCombinationItem(concrAcc,
						settings2);

				// ConditionType CombinationItem
				IFRS16ConditionType iConditionType = new IFRS16ConditionType(conditionTypeCode, true);
				CombinationItem<IFRS16ConditionType> conditionType = CombinationItem
						.getNewCombinationItem(iConditionType, settings2);

				currentValues.put(IFRS16ImportAssignmentType.CONTRACTNUMBER, cv.getCorrectContractNumber());
				currentValues.put(IFRS16ImportAssignmentType.FROMDATE, cv.getCorrectValue("FromDate"));
			//	currentValues.put(IFRS16ImportAssignmentType.FROMDATE,  CorrectDate);
				currentValues.put(IFRS16ImportAssignmentType.COSTCENTER, cv.getCorrectValue("CostCenter"));
				currentValues.put(IFRS16ImportAssignmentType.GROUPPOSITION, groupPosition);
				currentValues.put(IFRS16ImportAssignmentType.CONDITIONTYPE, conditionType);

				combinationLine.getDataMap().putAll(currentValues);
				combinationLines.add(combinationLine);

			}
		}
		// that covered all the combinations of GroupPosition and Condition Type. For
		// the rest, we will use correct values only, I'll create
		// one instance of each. These two are also "right related", if I understood
		// correctly
		ConcreteAccount concrAcc = new ConcreteAccount(Constants.IFRS16_RIGHT_OF_USE_LAND_AND_BUILDINGS);
		CombinationItem<ConcreteAccount> groupPosition = CombinationItem.getNewCombinationItem(concrAcc, settings2);

		IFRS16ConditionType iConditionType = new IFRS16ConditionType(Constants.CONDITIONTYPE_CODE_RentLandAndBuildings,
				true);
		CombinationItem<IFRS16ConditionType> conditionType = CombinationItem.getNewCombinationItem(iConditionType,
				settings2);

		// here we use wrong values for FromData and ConsCenter, Contractnumber,
		// GroupPosition and ConditionType - always correct
		ArrayList<String> combinationVariables = new ArrayList<String>(Arrays.asList("FromDate", "CostCenter"));

		for (String parameter : combinationVariables) {
			currentValues.clear();
			combinationLine = new CombinationLine();
			HashMap<String, CombinationItem<?>> inCorrectValues = cv.getInCorrectValues(parameter);
			HashMap<String, CombinationItem<?>> emptyValues = cv.getEmptyValues(parameter);

			currentValues.put(IFRS16ImportAssignmentType.CONTRACTNUMBER, cv.getCorrectContractNumber());
			currentValues.put(IFRS16ImportAssignmentType.CONDITIONTYPE, conditionType);
			currentValues.put(IFRS16ImportAssignmentType.GROUPPOSITION, groupPosition);
			currentValues.put(IFRS16ImportAssignmentType.FROMDATE, inCorrectValues.get("FromDate"));
			currentValues.put(IFRS16ImportAssignmentType.FROMDATE, CorrectDate);
	//		currentValues.put(IFRS16ImportAssignmentType.COSTCENTER, inCorrectValues.get("CostCenter"));
			combinationLines.add(combinationLine);

			currentValues.clear();
			combinationLine = new CombinationLine();
			// empty

			currentValues.put(IFRS16ImportAssignmentType.CONTRACTNUMBER, cv.getCorrectContractNumber());
			currentValues.put(IFRS16ImportAssignmentType.CONDITIONTYPE, conditionType);
			currentValues.put(IFRS16ImportAssignmentType.GROUPPOSITION, groupPosition);
			currentValues.put(IFRS16ImportAssignmentType.FROMDATE, emptyValues.get("FromDate"));
			currentValues.put(IFRS16ImportAssignmentType.FROMDATE, CorrectDate);
			currentValues.put(IFRS16ImportAssignmentType.COSTCENTER, emptyValues.get("CostCenter"));

			combinationLine.getDataMap().putAll(currentValues);
			combinationLines.add(combinationLine);

		}

		AddScenario(combinationLines, settings2, companyList);
	}

	private void addCombination3() {
		HashMap<IFRS16ImportAssignmentType, CombinationItem<?>> currentValues = new HashMap<IFRS16ImportAssignmentType, CombinationItem<?>>();

		CombinationLine combinationLine = new CombinationLine();
		ArrayList<Company> companyList = getDummyCompanies();

		ArrayList<CombinationLine> combinationLines = new ArrayList<CombinationLine>();

		ImplementedCompany impl;
		Company firstCompany = companyList.get(0);
		impl = (ImplementedCompany) firstCompany.getAllChildImplementedCompany().toArray()[0];

		Settings settings3 = getSettings();
		settings3.setName("LemonSettings3");
		settings3.setLongVersion(true);
		settings3.setImplementedCompany(impl);
		settings3.setFilePath("C:\\phxrepository\\cis_dev\\dev\\bin\\lemon_comb3.csv");

		settings3.getTypeList().add(IFRS16ImportAssignmentType.CONTRACTNUMBER);
		settings3.getTypeList().add(IFRS16ImportAssignmentType.GROUPPOSITION);
		settings3.getTypeList().add(IFRS16ImportAssignmentType.CONDITIONTYPE);
		settings3.getTypeList().add(IFRS16ImportAssignmentType.FROMDATE);
		settings3.getTypeList().add(IFRS16ImportAssignmentType.ORDER);

		ConcreteAccount concrAcc = new ConcreteAccount(Constants.IFRS16_RIGHT_OF_USE_LAND_AND_BUILDINGS);
		CombinationItem<ConcreteAccount> groupPosition = CombinationItem.getNewCombinationItem(concrAcc, settings3);

		IFRS16ConditionType iConditionType = new IFRS16ConditionType(Constants.CONDITIONTYPE_CODE_RentLandAndBuildings,
				true);
		CombinationItem<IFRS16ConditionType> conditionType = CombinationItem.getNewCombinationItem(iConditionType,
				settings3);

		// The only new thing here is order, all the rest are assumed to be
		// correct/already tested. so we test 3 different variatiosn for order.

		// correct

		currentValues.put(IFRS16ImportAssignmentType.CONTRACTNUMBER, cv.getCorrectContractNumber());
		currentValues.put(IFRS16ImportAssignmentType.FROMDATE, cv.getCorrectValue("FromDate"));
		currentValues.put(IFRS16ImportAssignmentType.CONDITIONTYPE, conditionType);
		currentValues.put(IFRS16ImportAssignmentType.GROUPPOSITION, groupPosition);
		currentValues.put(IFRS16ImportAssignmentType.ORDER, cv.getCorrectValue("Order"));

		combinationLine.getDataMap().putAll(currentValues);
		combinationLines.add(combinationLine);

		// incorrect
		currentValues.clear();
		combinationLine = new CombinationLine();

		currentValues.put(IFRS16ImportAssignmentType.CONTRACTNUMBER, cv.getCorrectContractNumber());
		currentValues.put(IFRS16ImportAssignmentType.FROMDATE, cv.getCorrectValue("FromDate"));
		currentValues.put(IFRS16ImportAssignmentType.CONDITIONTYPE, conditionType);
		currentValues.put(IFRS16ImportAssignmentType.GROUPPOSITION, groupPosition);
		currentValues.put(IFRS16ImportAssignmentType.ORDER, cv.getIncorrectValue("Order"));

		combinationLine.getDataMap().putAll(currentValues);
		combinationLines.add(combinationLine);

		// empty

		currentValues.clear();
		combinationLine = new CombinationLine();

		currentValues.put(IFRS16ImportAssignmentType.CONTRACTNUMBER, cv.getCorrectContractNumber());
		currentValues.put(IFRS16ImportAssignmentType.FROMDATE, cv.getCorrectValue("FromDate"));
		currentValues.put(IFRS16ImportAssignmentType.CONDITIONTYPE, conditionType);
		currentValues.put(IFRS16ImportAssignmentType.GROUPPOSITION, groupPosition);
		currentValues.put(IFRS16ImportAssignmentType.ORDER, cv.getEmptyValue("Order"));

		combinationLine.getDataMap().putAll(currentValues);
		combinationLines.add(combinationLine);

		AddScenario(combinationLines, settings3, companyList);
	}

	private void addCombination4() {
		// Combination 4

		HashMap<IFRS16ImportAssignmentType, CombinationItem<?>> currentValues = new HashMap<IFRS16ImportAssignmentType, CombinationItem<?>>();

		CombinationLine combinationLine = new CombinationLine();
		ArrayList<Company> companyList = getDummyCompanies();

		ArrayList<CombinationLine> combinationLines = new ArrayList<CombinationLine>();

		ImplementedCompany impl;
		Company firstCompany = companyList.get(0);
		impl = (ImplementedCompany) firstCompany.getAllChildImplementedCompany().toArray()[0];

		Settings settings4 = getSettings();
		settings4.setName("LemonSettings4");
		settings4.setLongVersion(true);
		settings4.setImplementedCompany(impl);
		settings4.setFilePath("C:\\phxrepository\\cis_dev\\dev\\bin\\lemon_comb4.csv");

		settings4.getTypeList().add(IFRS16ImportAssignmentType.CONTRACTNUMBER);
		settings4.getTypeList().add(IFRS16ImportAssignmentType.GROUPPOSITION);
		settings4.getTypeList().add(IFRS16ImportAssignmentType.CONDITIONTYPE);
		settings4.getTypeList().add(IFRS16ImportAssignmentType.FROMDATE);
		settings4.getTypeList().add(IFRS16ImportAssignmentType.COSTCENTER);
		settings4.getTypeList().add(IFRS16ImportAssignmentType.ORDER);

		ConcreteAccount concrAcc = new ConcreteAccount(Constants.IFRS16_RIGHT_OF_USE_LAND_AND_BUILDINGS);
		CombinationItem<ConcreteAccount> groupPosition = CombinationItem.getNewCombinationItem(concrAcc, settings4);

		IFRS16ConditionType iConditionType = new IFRS16ConditionType(Constants.CONDITIONTYPE_CODE_RentLandAndBuildings,
				true);
		CombinationItem<IFRS16ConditionType> conditionType = CombinationItem.getNewCombinationItem(iConditionType,
				settings4);

		// here we need all combinations for ConstCenter and order, so we have to write
		// all possible Combinations ones.

		// 4.1 - all correct
		currentValues.clear();
		combinationLine = new CombinationLine();

		currentValues.put(IFRS16ImportAssignmentType.CONTRACTNUMBER, cv.getCorrectContractNumber());
		currentValues.put(IFRS16ImportAssignmentType.FROMDATE, cv.getCorrectValue("FromDate"));
		currentValues.put(IFRS16ImportAssignmentType.CONDITIONTYPE, conditionType);
		currentValues.put(IFRS16ImportAssignmentType.GROUPPOSITION, groupPosition);
		currentValues.put(IFRS16ImportAssignmentType.COSTCENTER, cv.getCorrectValue("CostCenter"));
		currentValues.put(IFRS16ImportAssignmentType.ORDER, cv.getCorrectValue("Order"));

		combinationLine.getDataMap().putAll(currentValues);
		combinationLines.add(combinationLine);

		// 4.2 - Constcenter correct, order invalid
		currentValues.clear();
		combinationLine = new CombinationLine();

		currentValues.put(IFRS16ImportAssignmentType.CONTRACTNUMBER, cv.getCorrectContractNumber());
		currentValues.put(IFRS16ImportAssignmentType.FROMDATE, cv.getCorrectValue("FromDate"));
		currentValues.put(IFRS16ImportAssignmentType.CONDITIONTYPE, conditionType);
		currentValues.put(IFRS16ImportAssignmentType.GROUPPOSITION, groupPosition);
		currentValues.put(IFRS16ImportAssignmentType.COSTCENTER, cv.getCorrectValue("CostCenter"));
		currentValues.put(IFRS16ImportAssignmentType.ORDER, cv.getIncorrectValue("Order"));

		combinationLine.getDataMap().putAll(currentValues);
		combinationLines.add(combinationLine);

		// 4.3 - Constcenter correct, order empty
		currentValues.clear();
		combinationLine = new CombinationLine();

		currentValues.put(IFRS16ImportAssignmentType.CONTRACTNUMBER, cv.getCorrectContractNumber());
		currentValues.put(IFRS16ImportAssignmentType.FROMDATE, cv.getCorrectValue("FromDate"));
		currentValues.put(IFRS16ImportAssignmentType.CONDITIONTYPE, conditionType);
		currentValues.put(IFRS16ImportAssignmentType.GROUPPOSITION, groupPosition);
		currentValues.put(IFRS16ImportAssignmentType.COSTCENTER, cv.getCorrectValue("CostCenter"));
		currentValues.put(IFRS16ImportAssignmentType.ORDER, cv.getEmptyValue("Order"));

		combinationLine.getDataMap().putAll(currentValues);
		combinationLines.add(combinationLine);

		// 4.4 Constcenter incorrect, order correct
		currentValues.clear();
		combinationLine = new CombinationLine();

		currentValues.put(IFRS16ImportAssignmentType.CONTRACTNUMBER, cv.getCorrectContractNumber());
		currentValues.put(IFRS16ImportAssignmentType.FROMDATE, cv.getCorrectValue("FromDate"));
		currentValues.put(IFRS16ImportAssignmentType.CONDITIONTYPE, conditionType);
		currentValues.put(IFRS16ImportAssignmentType.GROUPPOSITION, groupPosition);
		currentValues.put(IFRS16ImportAssignmentType.COSTCENTER, cv.getIncorrectValue("CostCenter"));
		currentValues.put(IFRS16ImportAssignmentType.ORDER, cv.getCorrectValue("Order"));

		combinationLine.getDataMap().putAll(currentValues);
		combinationLines.add(combinationLine);

		// 4.5 - Constcenter incorrect, order incorrect
		currentValues.clear();
		combinationLine = new CombinationLine();

		currentValues.put(IFRS16ImportAssignmentType.CONTRACTNUMBER, cv.getCorrectContractNumber());
		currentValues.put(IFRS16ImportAssignmentType.FROMDATE, cv.getCorrectValue("FromDate"));
		currentValues.put(IFRS16ImportAssignmentType.CONDITIONTYPE, conditionType);
		currentValues.put(IFRS16ImportAssignmentType.GROUPPOSITION, groupPosition);
		currentValues.put(IFRS16ImportAssignmentType.COSTCENTER, cv.getIncorrectValue("CostCenter"));
		currentValues.put(IFRS16ImportAssignmentType.ORDER, cv.getIncorrectValue("Order"));

		combinationLine.getDataMap().putAll(currentValues);
		combinationLines.add(combinationLine);

		// 4.6 - Constcenter incorrect, order empty
		currentValues.clear();
		combinationLine = new CombinationLine();

		currentValues.put(IFRS16ImportAssignmentType.CONTRACTNUMBER, cv.getCorrectContractNumber());
		currentValues.put(IFRS16ImportAssignmentType.FROMDATE, cv.getCorrectValue("FromDate"));
		currentValues.put(IFRS16ImportAssignmentType.CONDITIONTYPE, conditionType);
		currentValues.put(IFRS16ImportAssignmentType.GROUPPOSITION, groupPosition);
		currentValues.put(IFRS16ImportAssignmentType.COSTCENTER, cv.getIncorrectValue("CostCenter"));
		currentValues.put(IFRS16ImportAssignmentType.ORDER, cv.getEmptyValue("Order"));

		combinationLine.getDataMap().putAll(currentValues);
		combinationLines.add(combinationLine);

		// 4.7 - Constcenter empty, order correct
		currentValues.clear();
		combinationLine = new CombinationLine();

		currentValues.put(IFRS16ImportAssignmentType.CONTRACTNUMBER, cv.getCorrectContractNumber());
		currentValues.put(IFRS16ImportAssignmentType.FROMDATE, cv.getCorrectValue("FromDate"));
		currentValues.put(IFRS16ImportAssignmentType.CONDITIONTYPE, conditionType);
		currentValues.put(IFRS16ImportAssignmentType.GROUPPOSITION, groupPosition);
		currentValues.put(IFRS16ImportAssignmentType.COSTCENTER, cv.getEmptyValue("CostCenter"));
		currentValues.put(IFRS16ImportAssignmentType.ORDER, cv.getCorrectValue("Order"));

		combinationLine.getDataMap().putAll(currentValues);
		combinationLines.add(combinationLine);

		// 4.8 - Constcenter empty, order incorrect
		currentValues.clear();
		combinationLine = new CombinationLine();

		currentValues.put(IFRS16ImportAssignmentType.CONTRACTNUMBER, cv.getCorrectContractNumber());
		currentValues.put(IFRS16ImportAssignmentType.FROMDATE, cv.getCorrectValue("FromDate"));
		currentValues.put(IFRS16ImportAssignmentType.CONDITIONTYPE, conditionType);
		currentValues.put(IFRS16ImportAssignmentType.GROUPPOSITION, groupPosition);
		currentValues.put(IFRS16ImportAssignmentType.COSTCENTER, cv.getEmptyValue("CostCenter"));
		currentValues.put(IFRS16ImportAssignmentType.ORDER, cv.getIncorrectValue("Order"));

		combinationLine.getDataMap().putAll(currentValues);
		combinationLines.add(combinationLine);

		// 4.9 - Constcenter empty, order empty
		currentValues.clear();
		combinationLine = new CombinationLine();

		currentValues.put(IFRS16ImportAssignmentType.CONTRACTNUMBER, cv.getCorrectContractNumber());
		currentValues.put(IFRS16ImportAssignmentType.FROMDATE, cv.getCorrectValue("FromDate"));
		currentValues.put(IFRS16ImportAssignmentType.CONDITIONTYPE, conditionType);
		currentValues.put(IFRS16ImportAssignmentType.GROUPPOSITION, groupPosition);
		currentValues.put(IFRS16ImportAssignmentType.COSTCENTER, cv.getEmptyValue("CostCenter"));
		currentValues.put(IFRS16ImportAssignmentType.ORDER, cv.getEmptyValue("Order"));

		combinationLine.getDataMap().putAll(currentValues);
		combinationLines.add(combinationLine);

		AddScenario(combinationLines, settings4, companyList);
	}

	public void addCombination5() {
		HashMap<IFRS16ImportAssignmentType, CombinationItem<?>> currentValues = new HashMap<IFRS16ImportAssignmentType, CombinationItem<?>>();

		CombinationLine combinationLine = new CombinationLine();
		ArrayList<Company> companyList = getDummyCompanies();

		ArrayList<CombinationLine> combinationLines = new ArrayList<CombinationLine>();

		ImplementedCompany impl;
		Company firstCompany = companyList.get(0);
		impl = (ImplementedCompany) firstCompany.getAllChildImplementedCompany().toArray()[0];

		Settings settings5 = getSettings();
		settings5.setName("LemonSettings5");
		settings5.setLongVersion(true);
		settings5.setImplementedCompany(impl);
		settings5.setFilePath("C:\\phxrepository\\cis_dev\\dev\\bin\\lemon_comb5.csv");

		settings5.getTypeList().add(IFRS16ImportAssignmentType.CONTRACTNUMBER);
		settings5.getTypeList().add(IFRS16ImportAssignmentType.GROUPPOSITION);
		settings5.getTypeList().add(IFRS16ImportAssignmentType.CONDITIONTYPE);
		settings5.getTypeList().add(IFRS16ImportAssignmentType.FROMDATE);
		settings5.getTypeList().add(IFRS16ImportAssignmentType.COSTCENTERTYPE);
		settings5.getTypeList().add(IFRS16ImportAssignmentType.COSTCENTERORORDER);

		ConcreteAccount concrAcc = new ConcreteAccount(Constants.IFRS16_RIGHT_OF_USE_LAND_AND_BUILDINGS);
		CombinationItem<ConcreteAccount> groupPosition = CombinationItem.getNewCombinationItem(concrAcc, settings5);

		IFRS16ConditionType iConditionType = new IFRS16ConditionType(Constants.CONDITIONTYPE_CODE_RentLandAndBuildings,
				true);
		CombinationItem<IFRS16ConditionType> conditionType = CombinationItem.getNewCombinationItem(iConditionType,
				settings5);

		// here, everything is correct except constecntertypes and costcenterororder.

		// I will iterate through all CostCenterType values (it's an enum) and in each
		// iteration, create a valid, invalid and empty version of ConstCenterOrORder

		for (IFRS16CostCenterType costCenterType : IFRS16CostCenterType.getAllValues()) {
			CombinationItem<IFRS16CostCenterType> iCostCenterType = CombinationItem
					.getNewCombinationItem(costCenterType, settings5);

			// correct
			currentValues.clear();
			combinationLine = new CombinationLine();

			currentValues.put(IFRS16ImportAssignmentType.CONTRACTNUMBER, cv.getCorrectContractNumber());
			currentValues.put(IFRS16ImportAssignmentType.FROMDATE, cv.getCorrectValue("FromDate"));
			currentValues.put(IFRS16ImportAssignmentType.CONDITIONTYPE, conditionType);
			currentValues.put(IFRS16ImportAssignmentType.GROUPPOSITION, groupPosition);
			currentValues.put(IFRS16ImportAssignmentType.COSTCENTERTYPE, iCostCenterType);
			currentValues.put(IFRS16ImportAssignmentType.COSTCENTERORORDER, cv.getCorrectValue("CostCenterOrOrder"));

			combinationLine.getDataMap().putAll(currentValues);
			combinationLines.add(combinationLine);

			// incorrect

			currentValues.clear();
			combinationLine = new CombinationLine();

			currentValues.put(IFRS16ImportAssignmentType.CONTRACTNUMBER, cv.getCorrectContractNumber());
			currentValues.put(IFRS16ImportAssignmentType.FROMDATE, cv.getCorrectValue("FromDate"));
			currentValues.put(IFRS16ImportAssignmentType.CONDITIONTYPE, conditionType);
			currentValues.put(IFRS16ImportAssignmentType.GROUPPOSITION, groupPosition);
			currentValues.put(IFRS16ImportAssignmentType.COSTCENTERTYPE, iCostCenterType);
			currentValues.put(IFRS16ImportAssignmentType.COSTCENTERORORDER, cv.getIncorrectValue("CostCenterOrOrder"));

			combinationLine.getDataMap().putAll(currentValues);
			combinationLines.add(combinationLine);

			// empty

			currentValues.clear();
			combinationLine = new CombinationLine();

			currentValues.put(IFRS16ImportAssignmentType.CONTRACTNUMBER, cv.getCorrectContractNumber());
			currentValues.put(IFRS16ImportAssignmentType.FROMDATE, cv.getCorrectValue("FromDate"));
			currentValues.put(IFRS16ImportAssignmentType.CONDITIONTYPE, conditionType);
			currentValues.put(IFRS16ImportAssignmentType.GROUPPOSITION, groupPosition);
			currentValues.put(IFRS16ImportAssignmentType.COSTCENTERTYPE, iCostCenterType);
			currentValues.put(IFRS16ImportAssignmentType.COSTCENTERORORDER, cv.getEmptyValue("CostCenterOrOrder"));

			combinationLine.getDataMap().putAll(currentValues);
			combinationLines.add(combinationLine);

		}

		AddScenario(combinationLines, settings5, companyList);

	}

	public void addCombination6() {
		HashMap<IFRS16ImportAssignmentType, CombinationItem<?>> currentValues = new HashMap<IFRS16ImportAssignmentType, CombinationItem<?>>();

		CombinationLine combinationLine = new CombinationLine();
		ArrayList<Company> companyList = getDummyCompanies();

		ArrayList<CombinationLine> combinationLines = new ArrayList<CombinationLine>();

		ImplementedCompany impl;
		Company firstCompany = companyList.get(0);
		impl = (ImplementedCompany) firstCompany.getAllChildImplementedCompany().toArray()[0];

		Settings settings6 = getSettings();
		settings6.setName("LemonSettings6");
		settings6.setLongVersion(true);
		settings6.setImplementedCompany(impl);
		settings6.setFilePath("C:\\phxrepository\\cis_dev\\dev\\bin\\lemon_comb6.csv");

		settings6.getTypeList().add(IFRS16ImportAssignmentType.CONTRACTNUMBER);
		settings6.getTypeList().add(IFRS16ImportAssignmentType.GROUPPOSITION);
		settings6.getTypeList().add(IFRS16ImportAssignmentType.CONDITIONTYPE);
		settings6.getTypeList().add(IFRS16ImportAssignmentType.FROMDATE);
		settings6.getTypeList().add(IFRS16ImportAssignmentType.COSTCENTER);
		settings6.getTypeList().add(IFRS16ImportAssignmentType.PAYMENTCYCLE);
		settings6.getTypeList().add(IFRS16ImportAssignmentType.PAYMENTDATETYPE);
		settings6.getTypeList().add(IFRS16ImportAssignmentType.AMOUNTWITHOUTVALUEADDEDTAX);
		settings6.getTypeList().add(IFRS16ImportAssignmentType.UNTILDATE);
		settings6.getTypeList().add(IFRS16ImportAssignmentType.VATRATETYPE);

		ConcreteAccount concrAcc = new ConcreteAccount(Constants.IFRS16_RIGHT_OF_USE_LAND_AND_BUILDINGS);
		CombinationItem<ConcreteAccount> groupPosition = CombinationItem.getNewCombinationItem(concrAcc, settings6);

		IFRS16ConditionType iConditionType = new IFRS16ConditionType(Constants.CONDITIONTYPE_CODE_RentLandAndBuildings,
				true);
		CombinationItem<IFRS16ConditionType> conditionType = CombinationItem.getNewCombinationItem(iConditionType,
				settings6);

		// I don't know what exactly they want us to do here, so I'm going to iterate
		// through all the enums and try different values for
		// input paramaters (vaid, invalid and empty for each parameter. And, with
		// enums, I don't think we can add invalid code - it's not a list.
		// this is going to be a huge multi level loop

		ArrayList<String> combinationVariables = new ArrayList<String>(
				Arrays.asList("AmountWithoutValueAddedTax", "UntilDate"));

		// took one valid value for costcenter
		CombinationItem<IFRS16CostCenterType> CostCenterType = CombinationItem
				.getNewCombinationItem(IFRS16CostCenterType.COSTCENTER, settings6);

		for (IFRS16PaymentCycle paymentCycle : IFRS16PaymentCycle.getAllValues()) {
			CombinationItem<IFRS16PaymentCycle> iPaymentCycle = CombinationItem.getNewCombinationItem(paymentCycle,
					settings6);

			for (IFRS16PaymentDateType paymentDateType : IFRS16PaymentDateType.getAllValues()) {
				CombinationItem<IFRS16PaymentDateType> iPaymentDateType = CombinationItem
						.getNewCombinationItem(paymentDateType, settings6);

				for (IFRS16VATRateType vatRateType : IFRS16VATRateType.getAllValues()) {
					CombinationItem<IFRS16VATRateType> ivatRateType = CombinationItem.getNewCombinationItem(vatRateType,
							settings6);

					// correct
					currentValues.clear();
					combinationLine = new CombinationLine();

					currentValues.put(IFRS16ImportAssignmentType.CONTRACTNUMBER, cv.getCorrectContractNumber());
					currentValues.put(IFRS16ImportAssignmentType.GROUPPOSITION, groupPosition);
					currentValues.put(IFRS16ImportAssignmentType.CONDITIONTYPE, conditionType);
					currentValues.put(IFRS16ImportAssignmentType.FROMDATE, cv.getCorrectValue("FromDate"));
					currentValues.put(IFRS16ImportAssignmentType.COSTCENTERTYPE, CostCenterType);
					currentValues.put(IFRS16ImportAssignmentType.PAYMENTCYCLE, iPaymentCycle);
					currentValues.put(IFRS16ImportAssignmentType.PAYMENTDATETYPE, iPaymentDateType);
					currentValues.put(IFRS16ImportAssignmentType.VATRATETYPE, ivatRateType);
					currentValues.put(IFRS16ImportAssignmentType.UNTILDATE, cv.getCorrectValue("UntilDate"));
					currentValues.put(IFRS16ImportAssignmentType.AMOUNTWITHOUTVALUEADDEDTAX,
							cv.getCorrectValue("AmountWithoutValueAddedTax"));

					combinationLine.getDataMap().putAll(currentValues);
					combinationLines.add(combinationLine);

					for (String parameter : combinationVariables) {

						HashMap<String, CombinationItem<?>> inCorrectValues = cv.getInCorrectValues(parameter);
						HashMap<String, CombinationItem<?>> emptyValues = cv.getEmptyValues(parameter);

						// incorrect
						currentValues.clear();
						combinationLine = new CombinationLine();

						currentValues.put(IFRS16ImportAssignmentType.CONTRACTNUMBER, cv.getCorrectContractNumber());
						currentValues.put(IFRS16ImportAssignmentType.GROUPPOSITION, groupPosition);
						currentValues.put(IFRS16ImportAssignmentType.CONDITIONTYPE, conditionType);
						currentValues.put(IFRS16ImportAssignmentType.FROMDATE, cv.getCorrectValue("FromDate"));
						currentValues.put(IFRS16ImportAssignmentType.COSTCENTERTYPE, CostCenterType);
						currentValues.put(IFRS16ImportAssignmentType.PAYMENTCYCLE, iPaymentCycle);
						currentValues.put(IFRS16ImportAssignmentType.PAYMENTDATETYPE, iPaymentDateType);
						currentValues.put(IFRS16ImportAssignmentType.VATRATETYPE, ivatRateType);
						currentValues.put(IFRS16ImportAssignmentType.UNTILDATE, inCorrectValues.get("UntilDate"));
						currentValues.put(IFRS16ImportAssignmentType.AMOUNTWITHOUTVALUEADDEDTAX,
								inCorrectValues.get("AmountWithoutValueAddedTax"));

						combinationLine.getDataMap().putAll(currentValues);
						combinationLines.add(combinationLine);

						// empty
						currentValues.clear();
						combinationLine = new CombinationLine();

						currentValues.put(IFRS16ImportAssignmentType.CONTRACTNUMBER, cv.getCorrectContractNumber());
						currentValues.put(IFRS16ImportAssignmentType.GROUPPOSITION, groupPosition);
						currentValues.put(IFRS16ImportAssignmentType.CONDITIONTYPE, conditionType);
						currentValues.put(IFRS16ImportAssignmentType.FROMDATE, cv.getCorrectValue("FromDate"));
						currentValues.put(IFRS16ImportAssignmentType.COSTCENTERTYPE, CostCenterType);
						currentValues.put(IFRS16ImportAssignmentType.PAYMENTCYCLE, iPaymentCycle);
						currentValues.put(IFRS16ImportAssignmentType.PAYMENTDATETYPE, iPaymentDateType);
						currentValues.put(IFRS16ImportAssignmentType.VATRATETYPE, ivatRateType);
						currentValues.put(IFRS16ImportAssignmentType.UNTILDATE, emptyValues.get("UntilDate"));
						currentValues.put(IFRS16ImportAssignmentType.AMOUNTWITHOUTVALUEADDEDTAX,
								emptyValues.get("AmountWithoutValueAddedTax"));

						combinationLine.getDataMap().putAll(currentValues);
						combinationLines.add(combinationLine);
					}

				}
			}
		}

		AddScenario(combinationLines, settings6, companyList);
	}

	@Override
	public boolean start() {

		switch (this.position) {
		case 1:
			addCombination1();
			break;
		case 2:
			addCombination2();
			break;
		case 3:
			addCombination3();
			break;
		case 4:
			addCombination4();
			break;
		case 5:
			addCombination5();
			break;
		case 6:
			addCombination6();
			break;
		default:
			addCombination1();
			break;
		}
		try {
			if(check(createdScenarios.get(0))) {
				FileGenerator.generate(createdScenarios.get(0));
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// try {
		// for (int i = 0; i < createdScenarios.size(); i++) {
		// if (check(createdScenarios.get(i))) {
		// FileGenerator.generate(createdScenarios.get(i));
		// }
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		return true;
	}



}
