package com.sp17.webcontroller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.sp17.domain.Form;
import com.sp17.domain.FormSubmission;
import com.sp17.domain.FormSubmissionHistory;
import com.sp17.domain.ServiceLevel;
import com.sp17.domain.Term;
import com.sp17.Sp17Application;

@Controller
public class FormController {
	
	private FormSubmission confirmation = null;
	
	@RequestMapping(value="/form/{id}", method = RequestMethod.GET)
	public String formlist(@PathVariable("id") int id, Model model){
		
		System.out.println("Beginning test!");
		if ( confirmation != null )
			System.out.println(confirmation.toString());
		
		FormSubmission formSubmission= new FormSubmission();
		
		Form form = Sp17Application.formDatabase.getForm(id);
		model.addAttribute("form", form);
		formSubmission.setFormId(id);
		model.addAttribute("formSubmission",formSubmission);	
		
		List<Term> terms1 = form.getTerms1();
		Map terms1Map = new HashMap();		
		for (int a = 0; a < terms1.size(); a++){
			int x = (int)terms1.get(a).getTermClass().getTermClassId();
			Map newMap = new LinkedHashMap();
			boolean foundAtleastOneClassIdMatch = false;
			for (int b = 0 ; b < terms1.size(); b++){
				if (x == (int)terms1.get(b).getTermClass().getTermClassId()){
					//newMap.put((""+terms1.get(b).getId()), terms1.get(b));
					newMap.put((""+terms1.get(b).getId()), terms1.get(b).getName()+", " +terms1.get(b).getId());
					foundAtleastOneClassIdMatch = true;
				}
			}
			if ( foundAtleastOneClassIdMatch )
				terms1Map.put(""+x, newMap);
		}		
		//Sort it by classid.
		Map<String, LinkedHashMap> map = new TreeMap<String, LinkedHashMap>(terms1Map);		
		System.out.println(map);
		model.addAttribute("terms1Map", map);
			
		
		List<Term> terms2 = form.getTerms2();
		Map terms2Map = new HashMap();		
		for (int a = 0; a < terms2.size(); a++){
			int x = (int)terms2.get(a).getTermClass().getTermClassId();
			Map newMap = new LinkedHashMap();
			boolean foundAtleastOneClassIdMatch = false;
			for (int b = 0 ; b < terms2.size(); b++){
				if (x == (int)terms2.get(b).getTermClass().getTermClassId()){
					newMap.put((""+terms2.get(b).getId()), terms2.get(b).getName()+", " +terms2.get(b).getId());
					foundAtleastOneClassIdMatch = true;
				}
			}
			if ( foundAtleastOneClassIdMatch )
				terms2Map.put(""+x, newMap);
		}		
		//Sort it by classid.
		Map<String, LinkedHashMap> map2 = new TreeMap<String, LinkedHashMap>(terms2Map);		
		System.out.println(map2);
		model.addAttribute("terms2Map", map2);
				
		List<ServiceLevel> serviceLevels= form.getServiceLevels();
		System.out.println(serviceLevels);
		Map serviceLevelMap = new LinkedHashMap();
		
		for (int i = 0; i < serviceLevels.size(); i++){
			serviceLevelMap.put((""+serviceLevels.get(i).getId()), serviceLevels.get(i).getName()+"  Attorney free "+serviceLevels.get(i).getPrice());
			System.out.println(serviceLevelMap);
		}
		model.addAttribute("serviceLevelMap", serviceLevelMap);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		System.out.println(dateFormat);
		// model.addAttribute("serviceLevels", DemoApplication.database2.listServiceLevels());
		return "form";
	}
	 	
	@RequestMapping(value="/formSubmission", method = RequestMethod.POST)
	public String FormSubmission(@ModelAttribute("formSubmission") FormSubmission formSubmission,BindingResult bindingResult, Model model){
		System.out.println("small stuff");
		System.out.println(formSubmission.toString());
		Form form =  Sp17Application.formDatabase.getForm((int) formSubmission.getFormId());
		List<Term> terms = new ArrayList<Term>();
		terms.addAll(form.getTerms1());
		terms.addAll(form.getTerms2());
		List<Term> parsedTerms = new ArrayList<Term>();
		
		List<String> userChoices = formSubmission.getTermIds();
		for ( int i = 0; i < userChoices.size(); i++ ){
			Term term = new Term();
			term.setId((Integer.parseInt(userChoices.get(i)) ));
			
			for ( int a = 0; a < terms.size(); a++ ){
				if ( terms.get(a).getId() == term.getId() ){
					term = terms.get(a);
				}
			}
			parsedTerms.add(term);
		}
		formSubmission.setTerms(parsedTerms);
		System.out.println(parsedTerms);
		
		System.out.println("After filling the form!! data");
		confirmation = formSubmission;
		System.out.println(confirmation.toString());
		
		long serviceLevelId = formSubmission.getServiceLevelId();
		List<ServiceLevel> serviceLevels= form.getServiceLevels();
		
		ServiceLevel see= new ServiceLevel();
		for (ServiceLevel sl:form.getServiceLevels()){
			if( sl.getId() == serviceLevelId){
				see=sl;
			}
		}
		model.addAttribute("level", see);
		model.addAttribute("form",form);
		model.addAttribute("formSubmission", formSubmission);
		return "formSubmission";
	}
	
	@RequestMapping(value="/confirmation", method = RequestMethod.POST)
	public String submissionConfirmation(Model model) throws ParseException{
		
		Sp17Application.formSubmissionDatabase.createFormSubmission(confirmation);
		
		List<FormSubmission> ls = Sp17Application.formSubmissionDatabase.listFormSubmission();
		confirmation.setId(ls.get(ls.size()-1).getId());
		
		Sp17Application.formSubmissionDatabase.createFormSubmissionTerm(confirmation);
		
		FormSubmissionHistory newfsh= new FormSubmissionHistory();
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();		
		String dat =dateFormat.format(date);		
		System.out.println(dateFormat.format(date));
		newfsh.setDateAndTime(dat);
		newfsh.setEmpName("N/A");
		newfsh.setFormSubmissionId(confirmation.getId());
		newfsh.setEventId(20000);
		newfsh.setDescription("***new submission");
		System.out.println(newfsh);
		
		Sp17Application.formSubmissionHistoryDatabase.createFormSubmissionHistory(newfsh);
				
		return "formSubmissionConfirmed";
	}
	
	    
}
