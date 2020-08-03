package com.mw.mwportal.bff.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.AbstractView;

import com.mw.mwportal.bff.dto.Opmate_ondemand_listDTO;
import com.mw.mwportal.bff.dto.RunResultDetailDTO;
// import com.mw.mwportal.bff.repository.Opmate_ondemand_listRepository;
import com.mw.mwportal.bff.service.OpmateService;

@RestController
public class BffController {
	
	// @Autowired
    // private Opmate_ondemand_listRepository op_odmRepository;
	
	@GetMapping("/check/daily/list") // READ
    public void check_daily_list() {		

		int one_time_batch_run_count	= 50;
		int excute_interval_secs			= 5;  // 5 초
		
		List<String> resultIDs = new ArrayList<String>();
		
		OpmateService opmate = new OpmateService();
		List<Opmate_ondemand_listDTO> opmate_list = opmate.msa_opmategw_list_get(); // RestAPI 호출해서 List 가져오기
		resultIDs = opmate.msa_opmategw_daily_list_make(opmate_list, one_time_batch_run_count, excute_interval_secs ); // RestAPI 호출해서 List 가져오기
		
        for( String resultid: resultIDs ) {
        	System.out.println("===== TaskInstanceID = " + resultid );
        }
        
        System.out.println("===== END ===== ");
    }
	
	@RequestMapping(value = "/check/result/view", method = RequestMethod.GET)
	public ModelAndView check_result_view(
			@RequestParam(value="no", required=true) String taskInstanceNo
			// @RequestParam(value="nodes", required=true) List<String> nodes
			) {
		
		ModelAndView result = new ModelAndView();
		
		List<String> resultIDs							= new ArrayList<String>();
		List<RunResultDetailDTO> resultDetailList	= new ArrayList<RunResultDetailDTO>();
		int RetryTimes = 10;
		
		resultIDs.add(taskInstanceNo);
		
		OpmateService opmate = new OpmateService();
		
		for(int i=0; i<RetryTimes; i++ ) {
			System.out.println("RetryTimes : " + i + "/" + RetryTimes );
			
			resultDetailList	= opmate.msa_opmategw_result_view(resultIDs);
			
			if ( resultDetailList == null ) {		// null 이 넘어오면 다시 호출해
				resultDetailList	= opmate.msa_opmategw_result_view(resultIDs);				 
			} else {				
				break;  // 결과값이 채워지면 끝
			}
		}

		result.addObject("resultDetailList", resultDetailList);
		result.setViewName("ondemand/check_result");
		
		return result;	
	}
	
	@GetMapping("/check/list") // READ
    public ModelAndView check_list() {
		
		ModelAndView result = new ModelAndView();
		
		OpmateService opmate = new OpmateService();
		List<Opmate_ondemand_listDTO> resultList = opmate.msa_opmategw_list_get(); // RestAPI 호출해서 List 가져오기
		
		result.addObject("resultList", resultList);
		result.setViewName("ondemand/check_list");
		
        return result;
    }
	
	@RequestMapping(value = "/check/result", method = RequestMethod.POST)
	public ModelAndView check_result (
			@RequestParam(value="checked_value", required=true) List<String> reqList     // task_id|hostname
			) {
		
		ModelAndView result = new ModelAndView();
		
		List<String> resultIDs							= new ArrayList<String>();
		List<RunResultDetailDTO> resultDetailList	= new ArrayList<RunResultDetailDTO>();
		int RetryTimes = 10; 
		
		/***********************************************************************************************
		 * 1개 문자열을 "," 으로 분리하면 1개만 들어오면 2개 문자열로 쪼개진다. ( "," => "|" 로 교체 )
		 ***********************************************************************************************/
		OpmateService opmate = new OpmateService();
		
		/*
		for(String str : reqList )
			System.out.println("MAIN - reqList[] :" + str );
		*/
		
		resultIDs			= opmate.msa_opmategw_call(reqList);	// List<String> resultIDs
		
		for(int i=0; i<RetryTimes; i++ ) {
			System.out.println("RetryTimes : " + i + "/" + RetryTimes );
			
			resultDetailList	= opmate.msa_opmategw_result_view(resultIDs);
			
			if ( resultDetailList == null ) {		// null 이 넘어오면 다시 호출해
				resultDetailList	= opmate.msa_opmategw_result_view(resultIDs);				 
			} else {				
				break;  // 결과값이 채워지면 끝
			}
		}

		result.addObject("resultDetailList", resultDetailList);
		result.setViewName("ondemand/check_result");
		
		return result;	
		
		/*
		for( String id : resultIDs )
			System.out.println("result ID = " + id );

		List<Selected_List> opm_task_list = new ArrayList<Selected_List>();

		for(String str : reqList ) {
			opm_task_list.add( new Selected_List( StringUtils.substringBefore(str, ","), StringUtils.substringAfter(str, ",") ) );			
		}
		
		result.addObject("resultList", opm_task_list);
		result.setViewName("ondemand/check_result");
		*/
	}
	
	@RequestMapping(value = "/send/nateonbiz", method = RequestMethod.POST)
	public ModelAndView send_nateonbiz (
			@RequestParam(value="title",					required=true) String title,
			@RequestParam(value="checked_value",	required=true) List<String> addressList,
			@RequestParam(value="message", 			required=true) String message
			) {		
		//////////////////////////////////////////////////////////////////
		// 컨트롤러에서 Page 생성
		//////////////////////////////////////////////////////////////////
		// http://www.mungchung.com/xe/spring/21223
		
		OpmateService opmate = new OpmateService();
		
		opmate.msa_send_nateonbiz(title, addressList, message);
		
		View view = new AbstractView() {
	        @Override
	        protected void renderMergedOutputModel(Map model, HttpServletRequest request, HttpServletResponse response) throws Exception {
	            response.setContentType("text/html; charset=UTF-8");
	            response.setCharacterEncoding("UTF-8");
	            PrintWriter outs = response.getWriter();
	            outs.println("<html>");
	            outs.println("<head>");
	            outs.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
	            outs.println("<script type='text/javascript'>");
	            outs.println("window.close()");
	            outs.println("</script>");
	            outs.println("</head>");
	            outs.println("</body>");
	            outs.println("</html>");
	            outs.flush();
	        }
	    };
	    
	    return new ModelAndView(view);
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