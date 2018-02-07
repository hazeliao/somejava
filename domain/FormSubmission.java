package com.sp17.domain;

import java.util.List;

public class FormSubmission {
	private long id;
	private long formId;
	private long serviceLevelId;
	private String customerEmail;
	private String textTrademark;
	private String formName;

	private List<Term> terms;
	private List<String> termIds;
	private List<FormSubmissionHistory> history;
	private FormSubmissionHistory latestHistory;
	
	public FormSubmission(){}

	public FormSubmission(long id, long formId, long serviceLevelId, String customerEmail, String textTrademark, List<Term> terms) {
		super();
		this.id = id;
		this.formId = formId;
		this.serviceLevelId = serviceLevelId;
		this.customerEmail = customerEmail;
		this.textTrademark = textTrademark;
		this.terms = terms;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getFormId() {
		return formId;
	}

	public void setFormId(long formId) {
		this.formId = formId;
	}

	public long getServiceLevelId() {
		return serviceLevelId;
	}

	public void setServiceLevelId(long serviceLevelId) {
		this.serviceLevelId = serviceLevelId;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getTextTrademark() {
		return textTrademark;
	}

	public void setTextTrademark(String textTrademark) {
		this.textTrademark = textTrademark;
	}
	
	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public List<Term> getTerms() {
		return terms;
	}

	public void setTerms(List<Term> terms) {
		this.terms = terms;
	}

	public List<String> getTermIds() {
		return termIds;
	}

	public void setTermIds(List<String> termIds) {
		this.termIds = termIds;
	}
	

	public List<FormSubmissionHistory> getHistory() {
		return history;
	}

	public void setHistory(List<FormSubmissionHistory> history) {
		this.history = history;
	}

	
	public FormSubmissionHistory getLatestHistory() {
		return latestHistory;
	}

	public void setLatestHistory(FormSubmissionHistory latestHistory) {
		this.latestHistory = latestHistory;
	}

	@Override
	public String toString() {
		return "FormSubmission [id=" + id + ", formId=" + formId + ", serviceLevelId=" + serviceLevelId
				+ ", customerEmail=" + customerEmail + ", textTrademark=" + textTrademark + ", terms=" + terms
				+ ", termIds=" + termIds + ", history=" + history + ", latestHistory=" + latestHistory + "]";
	}

}
