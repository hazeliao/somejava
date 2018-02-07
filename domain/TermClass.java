package com.sp17.domain;

import java.util.List;


//@Entity
public class TermClass {
	//@Id	
	//@GeneratedValue(strategy=GenerationType.AUTO)	
	private long termClassId;
	
	//private String termClass;
	
	//@OneToMany (cascade = CascadeType.ALL, mappedBy = "termClass")
	private List<Term> terms;
	
	public TermClass(){}
	
  	public TermClass(long termClassId) {
		super();
		this.termClassId = termClassId;
	}

	public long getTermClassId() {
 		return termClassId;
 	}
 
 	public void setTermClassId(long termClassId) {
 		this.termClassId = termClassId;
 	}

 	public List<Term> getTerms() {
 		return terms;
 	}
 
 	public void setTerms(List<Term> terms) {
 		this.terms = terms;
 	}
	
	

//	public String getTermClass() {
//		return termClass;
//	}



//	public void setTermClass(String termClass) {
	//	this.termClass = termClass;
//	}




	

}
