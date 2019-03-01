package com.wuerth.phoenix.cis.university.example1.implementations;
import com.wuerth.phoenix.cis.university.example1.adapters.IProfitCenter;

public class ProfitCenter implements IProfitCenter {

	protected String name;
	protected boolean notAllocated;
	public ProfitCenter(String name, boolean notAllocated){
		this.name=name;
		this.notAllocated = notAllocated;
	}
	public ProfitCenter(String name, String notAllocated){
		this.name=name;
		this.notAllocated = Boolean.valueOf(notAllocated);
	}
	@Override
	public String getName() {
		
		return name;
	}

	@Override
	public boolean isNotAllocated() {
		
		return notAllocated;
	}

}
