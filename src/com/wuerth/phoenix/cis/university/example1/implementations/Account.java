package com.wuerth.phoenix.cis.university.example1.implementations;
import com.wuerth.phoenix.cis.university.example1.adapters.*;
import com.wuerth.phoenix.cis.university.example1.types.AccountClass;
import com.wuerth.phoenix.cis.university.example1.types.AccountType;

public class Account implements IAccount {

	protected String aCode;
	protected AccountClass aClass = null;
	protected AccountType aType = null;
	protected boolean partnerAllowed;
	
	public Account(String aCode, String aClass, String aType, String partnerAllowed) {
		this.aCode=aCode;
		this.partnerAllowed = Boolean.valueOf(partnerAllowed);
		AccountClass[] ac = AccountClass.values();
		
		for(AccountClass a: ac) {		
			if (a.toString().toLowerCase().equals(aClass.toLowerCase())) {
				this.aClass = a;
			}
			
		}
		AccountType[] at = AccountType.values();
		// is this ok?
		this.aType=AccountType.NoValue;
		for(AccountType a: at) {
			
			if (a.toString().toLowerCase().equals(aType.toLowerCase())) {
				this.aType = a;
			}
			
		}
	
	}
	public String toString() {
		String res = "Code: "+aCode+"\tClass: ";
		if(aClass == null) {
			res +="NULL";
		}
		else {
			res += aClass.toString();
		}
		res +="\tType: ";
		if(aType == null) {
			res +="NULL";
		}
		else {
			res += aType.toString();
		}
		res +="\tpartner allowed: "+partnerAllowed;
		return res;
	}
	
	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return aCode;
	}

	@Override
	public AccountClass getAccountClass() {
		// TODO Auto-generated method stub
		return aClass;
	}

	@Override
	public AccountType getAccountType() {
		// TODO Auto-generated method stub
		return aType;
	}

	@Override
	public boolean isPartnerAllowed() {
		// TODO Auto-generated method stub
		return partnerAllowed;
	}

}
