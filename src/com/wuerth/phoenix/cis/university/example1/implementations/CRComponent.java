package com.wuerth.phoenix.cis.university.example1.implementations;
import com.wuerth.phoenix.cis.university.example1.adapters.ICRComponent;
public class CRComponent implements ICRComponent {

	
	protected String name;
	protected boolean notAllocated;
	protected boolean vkAllowd;
	protected boolean seanAllowed;
	
	
	public CRComponent(String name, boolean notAllocated, boolean vkAllowd, boolean seanAllowed) {
		super();
		this.name = name;
		this.notAllocated = notAllocated;
		this.vkAllowd = vkAllowd;
		this.seanAllowed = seanAllowed;
	}

	public CRComponent(String name, String notAllocated, String vkAllowd, String seanAllowed) {
		super();
		this.name = name;
		this.notAllocated = Boolean.valueOf(notAllocated);
		this.vkAllowd = Boolean.valueOf(vkAllowd);
		this.seanAllowed = Boolean.valueOf(seanAllowed);
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public boolean isNotAllocated() {
		// TODO Auto-generated method stub
		return notAllocated;
	}

	@Override
	public boolean isVKAllowed() {
		// TODO Auto-generated method stub
		return vkAllowd;
	}

	@Override
	public boolean isSEANAllowed() {
		// TODO Auto-generated method stub
		return seanAllowed;
	}

}
