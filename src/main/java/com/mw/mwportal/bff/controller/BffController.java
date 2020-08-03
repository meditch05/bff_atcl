package com.mw.mwportal.bff.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.mw.mwportal.bff.dto.RunResultDetailDTO;
// import com.mw.mwportal.bff.repository.Opmate_ondemand_listRepository;
import com.mw.mwportal.bff.service.OpmateService;

@RestController
public class BffController {
	
	// @Autowired
    // private Opmate_ondemand_listRepository op_odmRepository;
	
	@RequestMapping(value="/view/select/{emp_no}", method=RequestMethod.GET, produces = "application/json;application/text;charset=utf-8")
    public ModelAndView view_select(@PathVariable("emp_no") int emp_no) {		
		ModelAndView result = new ModelAndView();
		
		List<RunResultDetailDTO> resultDetailList	= new ArrayList<RunResultDetailDTO>();
		int RetryTimes = 10;
		
		OpmateService opmate = new OpmateService();
		
		for(int i=0; i<RetryTimes; i++ ) {
			System.out.println("RetryTimes : " + i + "/" + RetryTimes );
			
			resultDetailList	= opmate.msa_get_json(emp_no);
			
			if ( resultDetailList == null ) {		// null 이 넘어오면 다시 호출해
				resultDetailList	= opmate.msa_get_json(emp_no);				 
			} else {				
				break;  // 결과값이 채워지면 끝
			}
		}

		result.addObject("resultDetailList", resultDetailList);
		result.setViewName("main/view_result");
		
		return result;	
	}
	
	/*
	@GetMapping(value = "/hello_1")
	public ModelAndView home() {		
		String version = "1.0";
		
		ModelAndView result = new ModelAndView();

		List<String> resultList = Arrays.asList(new String[] {"BffController", version,"장영오","TEST"});
		result.addObject("result", resultList);
		result.setViewName("ondemand/home");
		
		return result;
	}
	*/

}