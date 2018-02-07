package com.sp17.domain;


//import com.fasterxml.jackson.annotation.JsonIgnore;




public class Term {
	
 	//@Id	
 	//@GeneratedValue(strategy=GenerationType.AUTO)	
	private long id;
	private String name;
	
 	//@ManyToOne
 	//@JsonIgnore
 	//@JoinColumn(name = "termClassId")
	private TermClass termClass;
	
	public Term(){}

	public Term(long id, String name, TermClass termClass) {
		super();
		this.id = id;
		this.name = name;
		this.termClass = termClass;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TermClass getTermClass() {
		return termClass;
	}

	public void setTermClass(TermClass termClass) {
		this.termClass = termClass;
	}

	@Override
	public String toString() {
		return "Term [id=" + id + ", name=" + name + ", termClass=" + termClass.getTermClassId() + "]";
	}
	
	
	
	

}
