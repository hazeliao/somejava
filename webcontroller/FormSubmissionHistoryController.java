package com.sp17.webcontroller;

import java.util.ArrayList;
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
import com.sp17.dao.impl.FormSubmissionHistoryImpl;

@Controller
public class FormSubmissionHistoryController {
	
	@RequestMapping(value="/formSubmissionHistory", method = RequestMethod.GET)
	public String FormSubmissionList(Model model){
		List <Form> forms = Sp17Application.formDatabase.listForms();		
		List <FormSubmission> formSubmissions = Sp17Application.formSubmissionDatabase.listFormSubmission();
		System.out.println(formSubmissions.size());
		
			
		for (int i = 0; i < formSubmissions.size(); i++) 	{
			FormSubmission submission = formSubmissions.get(i);
			submission.setFormName(Sp17Application.formDatabase.getForm((int)submission.getFormId()).getName());
			int id = (int) submission.getId();
			FormSubmissionHistory fsh=Sp17Application.formSubmissionHistoryDatabase.getLatestHistory(id);
			formSubmissions.get(i).setLatestHistory(fsh);
			System.out.println("X" +formSubmissions.get(i).getFormName());
		}
		
		model.addAttribute("forms", forms); 
		model.addAttribute("formSubmissions",formSubmissions);
		return "formsubmissionlist";
	}
	
	private int formSubmissionEditedNow = 0;
	
	@RequestMapping(value="/formsubmission/{id}", method = RequestMethod.GET)
	public String formlist(@PathVariable("id") int id, Model model){
		formSubmissionEditedNow = id;
		FormSubmission formSubmission = Sp17Application.formSubmissionDatabase.getFormSubmission(id);
		FormSubmission formsubmission = new FormSubmission();
		List <FormSubmissionHistory> history = Sp17Application.formSubmissionHistoryDatabase.listHistoryBySubmissionId(id);
		Form form = Sp17Application.formDatabase.getForm((int)formSubmission.getFormId());
		model.addAttribute("form", form);
		
		List<Form> forms = Sp17Application.formDatabase.listForms();
		Map formsMap = new LinkedHashMap();
		for (Form fff: forms){
			//System.out.println("xxfff:" + fff.toString());
			formsMap.put(""+fff.getId(), ""+fff.getName()+" "+fff.getId());
			
		}
		//FINAL_DEBUG//System.out.println("--------------------------------");
		//FINAL_DEBUG//System.out.println(formsMap);
		//FINAL_DEBUG//System.out.println("--------------------------------");
		model.addAttribute("formsMap", formsMap);
		
		List<Term> terms = Sp17Application.termDatabase.listTerms();
		
		Map termsMap = new LinkedHashMap();
		for (int j = 0; j < terms.size(); j++){
			termsMap.put(""+terms.get(j).getId(), ""+terms.get(j).getName());
		}
		model.addAttribute("termsMap", termsMap);
		
		Map serviceLevels = new LinkedHashMap();
		for (int j = 0; j < form.getServiceLevels().size(); j++){
			serviceLevels.put(	""+form.getServiceLevels().get(j).getId(),
								""+form.getServiceLevels().get(j).getName()+
								" "+form.getServiceLevels().get(j).getPrice());
		}
		model.addAttribute("serviceLevelsMap", serviceLevels);
		
		model.addAttribute("formSubmission",formSubmission);
		model.addAttribute("formsubmission",formsubmission);
		model.addAttribute("history",history);
		Map formsComplexMap = new HashMap();
		
		for (Form ffff: forms){
			
			Form fff = new Form();
			fff = Sp17Application.formDatabase.getForm((int)ffff.getId());
					
			//System.out.println("fff:" + fff.toString());
			Map actualTerms= new LinkedHashMap();
			for (int a = 0 ; a < fff.getTerms1().size(); a++){
				//debug//actualTerms.put("terms1 IDs "+fff.getTerms1().get(a).getId(), ""+fff.getTerms1().get(a).getName());
				actualTerms.put(""+fff.getTerms1().get(a).getId(), ""+fff.getTerms1().get(a).getName());
			}
			for (int b = 0 ; b < fff.getTerms2().size(); b++){
				//debug//actualTerms.put("terms2 IDs "+fff.getTerms2().get(b).getId(), ""+fff.getTerms2().get(b).getName());
				actualTerms.put(""+fff.getTerms2().get(b).getId(), ""+fff.getTerms2().get(b).getName());
			}
			
			formsComplexMap.put(""+fff.getId(), actualTerms);
			
		}
		Map<String, LinkedHashMap> complexMap = new TreeMap<String, LinkedHashMap>(formsComplexMap);
		//FINAL_DEBUG//System.out.println("complexMap:");
		//FINAL_DEBUG//System.out.println(complexMap);
		model.addAttribute("complexMap",complexMap);

		return "formsubmissionitem";
	}
	
	@RequestMapping(value="/updateFormSubmission", method = RequestMethod.POST)
	public String UpdateFormSubmission(@ModelAttribute("formsubmission") FormSubmission formsubmission, BindingResult bindingResult, Model model){
		System.out.println("test submit");
		System.out.println(formsubmission.toString());
		System.out.println("\n");
		formsubmission.setId(formSubmissionEditedNow);
		Form form =  Sp17Application.formDatabase.getForm((int) formsubmission.getFormId());
		List<Term> terms = new ArrayList<Term>();
		terms.addAll(form.getTerms1());
		terms.addAll(form.getTerms2());
		List<Term> parsedTerms = new ArrayList<Term>();
		
		List<String> userChoices = formsubmission.getTermIds();
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
		formsubmission.setTerms(parsedTerms);
		Sp17Application.formSubmissionDatabase.update(formsubmission);
		Sp17Application.formSubmissionDatabase.deleteFormSubmissionTerms(formsubmission);
		Sp17Application.formSubmissionDatabase.createFormSubmissionTerm(formsubmission);
		
		return "redirect:formsubmission/"+formSubmissionEditedNow;
		
	}
	
	@RequestMapping(value="/createFormSubmissionHistory", method = RequestMethod.POST)
	public String CreateFormSubmissionHistory(@ModelAttribute("history") FormSubmissionHistory history, BindingResult bindingResult, Model model){
		
		
		return "redirect:formsubmission/"+formSubmissionEditedNow;
	}
}