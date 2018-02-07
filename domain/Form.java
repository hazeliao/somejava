package com.sp17.domain;

import java.util.List;

import com.sp17.domain.IndustryField;
import com.sp17.domain.ServiceLevel;
import com.sp17.domain.Term;

public class Form {
	
	private long id;
	private String name;
	
	private List<IndustryField> industryFields;
	private List<String> industryFieldIds;
	
	private List<Term> terms1;
	private List<String> terms1Ids;	
	
	private List<Term> terms2;
	private List<String> terms2Ids;
	
	private  List<ServiceLevel> serviceLevels;

	public Form(){}

	public Form(long id, String name, List<IndustryField> industryFields, List<Term> terms1, List<Term> terms2) {
		super();
		this.id = id;
		this.name = name;
		this.industryFields = industryFields;
		this.terms1 = terms1;
		this.terms2 = terms2;
		this.serviceLevels = null;
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
	
	public List<IndustryField> getIndustryFields() {
		return industryFields;
	}
	public void setIndustryFields(List<IndustryField> industryFields) {
		this.industryFields = industryFields;
	}
	public List<String> getIndustryFieldIds() {
		return industryFieldIds;
	}
	public void setIndustryFieldIds(List<String> industryFieldIds) {
		this.industryFieldIds = industryFieldIds;
	}
	public List<Term> getTerms1() {
		return terms1;
	}

	public void setTerms1(List<Term> terms1) {
		this.terms1 = terms1;
	}

	public List<Term> getTerms2() {
		return terms2;
	}

	public void setTerms2(List<Term> terms2) {
		this.terms2 = terms2;
	}
	
	public List<ServiceLevel> getServiceLevels() {
		return serviceLevels;
	}

	public void setServiceLevels(List<ServiceLevel> list) {
		this.serviceLevels = list;
	}
	
	public List<String> getTerms1Ids() {
		return terms1Ids;
	}
	public void setTerms1Ids(List<String> terms1Ids) {
		this.terms1Ids = terms1Ids;
	}
	public List<String> getTerms2Ids() {
		return terms2Ids;
	}
	public void setTerms2Ids(List<String> terms2Ids) {
		this.terms2Ids = terms2Ids;
	}
	@Override
	public String toString() {
		return "Form [id=" + id + ", name=" + name + ", industryFields=" + industryFields + ", terms1=" + terms1
				+ ", terms2=" + terms2 + "]";
	}

	
	
	

}
