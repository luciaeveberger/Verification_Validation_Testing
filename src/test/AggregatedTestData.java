package test;

import com.wuerth.phoenix.cis.university.example1.adapters.IAccount;
import com.wuerth.phoenix.cis.university.example1.adapters.ICRComponent;
import com.wuerth.phoenix.cis.university.example1.adapters.ICompany;
import com.wuerth.phoenix.cis.university.example1.adapters.IProfitCenter;
import com.wuerth.phoenix.cis.university.example1.types.DataScenarioType;

public class AggregatedTestData {
	ICompany company;
	IProfitCenter profitCenter;
	ICRComponent crComponent;
	boolean external;
	DataScenarioType scenarioType;
	IAccount account;
	String partnerCode;
	String currencyCode;
	
	
	public AggregatedTestData(ICompany company, 
			IProfitCenter profitCenter, 
			ICRComponent crComponent, 
			boolean external,
			DataScenarioType scenarioType, 
			IAccount account, 
			String partnerCode, 
			String currencyCode) {
		
		
			this.company = company;
			this.profitCenter = profitCenter;
			this.crComponent = crComponent;
			this.external = external;
			this.scenarioType = scenarioType;
			this.account = account;
			this.partnerCode = partnerCode;
			this.currencyCode = currencyCode;
		// TODO Auto-generated constructor stub
	}
	
	public AggregatedTestData() {
		
	}


	public ICompany getCompany() {
		return company;
	}


	public void setCompany(ICompany company) {
		this.company = company;
	}


	public IProfitCenter getProfitCenter() {
		return profitCenter;
	}


	public void setProfitCenter(IProfitCenter profitCenter) {
		this.profitCenter = profitCenter;
	}


	public ICRComponent getCrComponent() {
		return crComponent;
	}


	public void setCrComponent(ICRComponent crComponent) {
		this.crComponent = crComponent;
	}


	public boolean isExternal() {
		return external;
	}


	public void setExternal(boolean external) {
		this.external = external;
	}


	public DataScenarioType getScenarioType() {
		return scenarioType;
	}


	public void setScenarioType(DataScenarioType scenarioType) {
		this.scenarioType = scenarioType;
	}


	public IAccount getAccount() {
		return account;
	}


	public void setAccount(IAccount account) {
		this.account = account;
	}


	public String getPartnerCode() {
		return partnerCode;
	}


	public void setPartnerCode(String partnerCode) {
		this.partnerCode = partnerCode;
	}


	public String getCurrencyCode() {
		return currencyCode;
	}


	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	
}
