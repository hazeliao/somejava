package com.sp17.domain;

import java.util.ArrayList;


public class IndustryField {
	
	private int id;
	private String name;
	private int priority;
	private String iconFilePath;
	
	private ArrayList<Form> forms;
	
	
	public IndustryField() {}
	
	
	public IndustryField(int id, String name, int priority, String iconFilePath, ArrayList<Form> forms) {
		super();
		this.id = id;
		this.name = name;
		this.priority = priority;
		this.iconFilePath = iconFilePath;
		this.forms = forms;
	}

	public String getIconFilePath() {
		return iconFilePath;
	}

	public void setIconFilePath(String iconFilePath) {
		this.iconFilePath = iconFilePath;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public ArrayList<Form> getForms() {
		return forms;
	}

	public void setForms(ArrayList<Form> forms) {
		this.forms = forms;
	}

	@Override
	public String toString() {
		return "IndustryField [industryId=" + id + ", Name=" + name + ", priority="
				+ priority + ", forms=" + forms + "]";
	}	
	
}
	
	

