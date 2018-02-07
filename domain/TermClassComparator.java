package com.sp17.domain;

import java.util.Comparator;

public class TermClassComparator implements Comparator<Term> {
	
	@Override
	public int compare(Term a, Term b){
		int different = (int) (a.getTermClass().getTermClassId() - b.getTermClass().getTermClassId());
		
		if (different != 0) {
			return different;
		} else {
			return 0;
		}
	}	

}
