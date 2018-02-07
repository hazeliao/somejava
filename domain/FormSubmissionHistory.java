package com.sp17.domain;

import java.util.*;

public class FormSubmissionHistory {
	private long id;
	private String dateAndTime;
	private String empName;
	private long formSubmissionId;
	private String description;
	private int eventId;
	private int contacted;
	
	public FormSubmissionHistory(){}



	public FormSubmissionHistory(long id, String dateAndTime, String empName, long formSubmissionId, String description,
			int eventId, int contacted) {
		super();
		this.id = id;
		this.dateAndTime = dateAndTime;
		this.empName = empName;
		this.formSubmissionId = formSubmissionId;
		this.description = description;
		this.eventId = eventId;
		this.contacted = contacted;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(String dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public long getFormSubmissionId() {
		return formSubmissionId;
	}

	public void setFormSubmissionId(long formSubmissionId) {
		this.formSubmissionId = formSubmissionId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public int getContacted() {
		return contacted;
	}

	public void setContacted(int contacted) {
		this.contacted = contacted;
	}

	@Override
	public String toString() {
		return "FormSubmissionHistory [id=" + id + ", dateAndTime=" + dateAndTime + ", empName=" + empName
				+ ", formSubmissionId=" + formSubmissionId + ", description=" + description + ", eventType=" + eventId
				+ "]";
	}



	

		
}
