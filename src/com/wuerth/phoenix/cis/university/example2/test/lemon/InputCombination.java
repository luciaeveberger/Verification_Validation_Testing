package com.wuerth.phoenix.cis.university.example2.test.lemon;

import java.util.ArrayList;

import com.wuerth.phoenix.cis.university.example2.util.*;
import com.wuerth.phoenix.cis.university.example2.adapters.*;

public class InputCombination {
	public ArrayList<Company> companies = new ArrayList<Company>();
	public ArrayList<CombinationLine> combinationLines = new ArrayList<CombinationLine>();
	public Settings settings;
	
	public InputCombination(ArrayList<Company> companies,ArrayList<CombinationLine> combinationLines,Settings settings  ) {
		this.companies = companies;
		this.combinationLines = combinationLines;
		this.settings = settings;
	}
}
