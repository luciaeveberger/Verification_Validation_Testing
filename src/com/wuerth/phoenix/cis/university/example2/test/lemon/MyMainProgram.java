package com.wuerth.phoenix.cis.university.example2.test.lemon;

import com.wuerth.phoenix.cis.university.example2.util.Scenario;
import com.wuerth.phoenix.cis.university.example2.test.lemon.EngineTestData;


public class MyMainProgram {

	public static void main(String[] args) {

		for(int i=1; i<=6; i++) {
			new EngineTestData("test1",i) {
				
				@Override
				public boolean check(Scenario scenario) {
					
					return true;
				}
			}.start();
		}
	

	}

}
