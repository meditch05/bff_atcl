package com.mw.mwportal.bff.service;
// https://stackoverflow.com/questions/2703161/how-to-ignore-ssl-certificate-errors-in-apache-httpclient-4-0
// https://stackoverflow.com/a/38509015

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.mw.mwportal.bff.dto.Opmate_BatchDTO;
import com.mw.mwportal.bff.dto.Opmate_ondemand_listDTO;
import com.mw.mwportal.bff.dto.RunResultDetailDTO;
import com.google.common.collect.Lists;
import com.google.gson.Gson;

public class OpmateService {

	private static final Logger logger = LoggerFactory.getLogger(OpmateService.class);
	private static final int sleep_time = 5000;
	
	RestTemplate restTemplate;
	
	/************************************************************************************
	 * 전체 List를 Task 별로 50개씩 쪼갠 String 배열(task_id|hostname) 로 만들어서 msa_opmategw_call( List<String> reqList ) 수행
	 * task_id : hostname = 1 : N 으로 배열을 생성함
	 ************************************************************************************/
	public List<String> msa_opmategw_daily_list_make( List<Opmate_ondemand_listDTO> opmate_list, int one_time_batch_run_count, int excute_interval_secs ) {
		
		List<String> reqList					= new ArrayList<String>();
		Map<String, List<String>> multimap		= new HashMap<>();
		List<Opmate_BatchDTO> opm_batch_list	= new ArrayList<Opmate_BatchDTO>();		
		MwportalUtils utils = new MwportalUtils();
		
		List<String> resultIDs					= new ArrayList<String>();		
				
		logger.info("at msa_opmategw_daily_list_make() ============================ BEGIN");
		try {
			
			// Opmate_ondemand_listDTO List를 가져와서 String List로 만들어 
			for(Opmate_ondemand_listDTO dto: opmate_list) {
				reqList.add(dto.getTask_id() + "|" + dto.getHostname());
			}
			
			// String List로 Map으로 분류하고
			multimap = utils.make_hashmap_list_from_StringList(reqList);   // 16개 task / 339개 account
			
			// Map key / size 확인하고
			System.out.println("Listup multimap Info ======================== ");
			System.out.println("multimap.size() : " + multimap.size());
			for (String key : multimap.keySet()) {
				List<String> values = multimap.get(key);
				System.out.println("multimap key : " + key + "[" + values.size() + "]");
			}

			// Map Key 별로 최대 50개 Opmate_BatchDTO 를 갖는 list로 쪼개
			System.out.println("make Opmate_BatchDTO List from multimap ======================== ");
	        for(String key : multimap.keySet()){
	            List<String> node_list = multimap.get(key);
	            
	            if( node_list.size() <= one_time_batch_run_count ) {	// 50개 미만인 경우
	            	Opmate_BatchDTO opm_dto					= new Opmate_BatchDTO();
	            	
	            	opm_dto.setTask_id(key);
	            	opm_dto.setNode_list(node_list);
	            	
	            	opm_batch_list.add(opm_dto);
	            } else {
	            	List<List<String>>  subLists = Lists.partition(node_list, one_time_batch_run_count);
	            	
	            	for( List<String> divided_list : subLists ) {
	            		Opmate_BatchDTO opm_dto					= new Opmate_BatchDTO();
	            		
	            		opm_dto.setTask_id(key);
		            	opm_dto.setNode_list(divided_list);
		            	
	            		opm_batch_list.add(opm_dto);
	            	}
	            }
	        }
	        
	        // 확인 //  - 10초 단위로 opm_batch_list 를 print ( opm_batch_list = task_id 당 최대 50개짜리 List )
	        System.out.println("Print excute function ======================== ");
	        for( Opmate_BatchDTO dto: opm_batch_list ) {	        	
	        	System.out.println("Check Size = dto.getReqList() : task_id = " + dto.getTask_id() + ", size = " + dto.getNode_list().size() );
	        	
	        	// Thread.sleep(excute_interval_secs * 1000);
	        }
	        
	        System.out.println("Real excute function ======================== ");  // 339개를 task_id 별로 50개씩 쪼개면 21번돈다 
	        for( Opmate_BatchDTO dto: opm_batch_list ) {
	        	List<String> resultid							= new ArrayList<String>();
	        	
	        	if ( StringUtils.equals(dto.getTask_id(), "mw_check_web_webwas") ) {   // webwas 일 경우에만 수행 ( 다른건 잘도는거 확인 했음 )
	        		// 2019-10-17 22:07 : taskInstanceNo = 548 로 전체 339개가 한방에 다 돌았다.  ㅡ_ㅡ 씨바;;;;
	        		
	        		logger.info("Real Excute = dto.getReqList() : task_id = " + dto.getTask_id() + ", size = " + dto.getNode_list().size() );	
		        	List<String> one_time_reqList = dto.getReqList();        	
		        	
		        	/*
		        	System.out.println("one_time_reqList = " + one_time_reqList.toString());
		        	
		        	for( String str: one_time_reqList ) {
		        		System.out.println(str);
		        	}
		        	*/
		        	
		        	//////////////////////////////////////////////////////////////
		        	// TEST
		        	//////////////////////////////////////////////////////////////
		        	/*
		        	Random random = new Random();
		        	String t1 = Integer.toString( Math.abs(random.nextInt()));
		        	logger.info("== Test Random ID = " + t1 );
		        	System.out.println("one_time_reqList = " + one_time_reqList.toString());
		        	
		        	resultIDs.add( t1 );
		        	*/
		        	
		        	//////////////////////////////////////////////////////////////
		        	// 실제 수행
		        	//////////////////////////////////////////////////////////////
		        	resultid = msa_opmategw_call(one_time_reqList);	  // List<String> resultIDs
		        	resultIDs.add(resultid.get(0));
		        	System.out.println("taskInstanceNo = " + resultid.get(0) );

		        	
		        	/* 2019-10-17 22:48:46 569  skt-btwspap2
		        	 * 2019-10-17 22:48:49 570  skt-btwspap2
		        	 * 2019-10-17 22:53:55 572  skt-btwspap2
		        	 * 
		        	 * 웹창 닫으면 돌았던거 한번씩 더도는거 같은데
		        	 * 2019-10-17 22:58:46 573  skt-btwspap2
		        	 * 2019-10-17 22:58:49 574  skt-btwspap2
		        	 * 2019-10-17 22:58:50 575  skt-btwspap2
		        	 */
		        	
		        	Thread.sleep(excute_interval_secs * 1000);
	        	}
	        }
	        
	        logger.info("at msa_opmategw_daily_list_make() ============================ Print taskInstanceNo's ");	        

	        for( String taskInstanceNo : resultIDs ) {
	        	System.out.println("taskInstanceNo = " + taskInstanceNo );
	        }
	        
	        logger.info("at msa_opmategw_daily_list_make() ============================ END");
	        
	        return resultIDs;
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultIDs;
		
		/***********************************************************************
		* "task_id|hostname" 추출해서 reqList 를 만들고 ( 50개 단위로 수행 )
		* resultIDs			= opmate.msa_opmategw_call(reqList); 를 호출하고
		* 아래 처럼 루프돌려서 다 수행될때 까지 retry 한다.
		* 
		* 
		for(int i=0; i<RetryTimes; i++ ) {
			System.out.println("RetryTimes : " + i + "/" + RetryTimes );
			
			resultDetailList	= opmate.msa_opmategw_result_view(resultIDs);
			
			if ( resultDetailList == null ) {		// null 이 넘어오면 다시 호출해
				resultDetailList	= opmate.msa_opmategw_result_view(resultIDs);				 
			} else {
				break;  // 결과값이 채워지면 끝
			}
		}
		***********************************************************************/
	}

	/************************************************************************************
	 * http://opmate-gw.mwportal.svc:8080/run 호출후 TaskinstanceID 배열 가져오기
	 ************************************************************************************/
	public List<String> msa_opmategw_call( List<String> reqList ) {
		
		String url = "http://opmate-gw.mwportal.svc:8080/run";		
		List<String> resultIDs = new ArrayList<String>();
		List<String> nodes = new ArrayList<String>();
		Map<String, List<String>> multimap = new HashMap<>();
		Map<String, String> map = new HashMap<>();
		
		MwportalUtils utils = new MwportalUtils();
		
				
		logger.info("at msa_opmategw_call() : http://opmate-gw.mwportal.svc:8080/run ========================== Begin");
		
		String task_id = "";
		String node = "";
		
		String taskinstandID = "";
		
		for(String str : reqList ) {			
			task_id	= StringUtils.substringBefore(str, "|");
			node		= StringUtils.substringAfter(str, "|");
			
			if( multimap.isEmpty() ) {
				System.out.println("multimap == " + multimap.isEmpty() );
				nodes.add(node);
				multimap.put( task_id, nodes );		
			} else {
				if ( multimap.containsKey(task_id) ) {					
					List<String> tempList = multimap.get(task_id);
					
					tempList.add(node);  // Node 를 추가한 tempList 생성					
					multimap.replace(task_id, tempList ); // value를 tempList 로 교체
				} else {
					List<String> tempList = new ArrayList<String>();
					
					tempList.add(node);					
					multimap.put( task_id, tempList );					
				}
			}			
		}
		
		for(String key : multimap.keySet()) {
			String tmpstr =StringUtils.replace(multimap.get(key).toString(), " ", "");			
			tmpstr =StringUtils.replace(tmpstr, "[", "");
			tmpstr =StringUtils.replace(tmpstr, "]", "");
			
			System.out.println("task_id [" + key + "], values [" + tmpstr + "]");
			
			map.put(key, tmpstr);
		}
		
		try {
			restTemplate = utils.getRestTemplate(url);			
			
			for(String key : map.keySet()) {
				MultiValueMap<String, String> postParameters = new LinkedMultiValueMap<>();
				postParameters.add("taskid"	, key);
				postParameters.add("nodes"	, map.get(key) );
				
				System.out.println("Opmate GW Call = [" + key + "], [" + map.get(key) + "]");
				
				taskinstandID = restTemplate.postForObject(url, postParameters, String.class);
				
				resultIDs.add(taskinstandID);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		logger.info("at msa_opmategw_call() : http://opmate-gw.mwportal.svc:8080/run ========================== End");
		
		return resultIDs;
	}
	
	/********************************************************************************************************************************
	 * TaskinstanceID 배열로 http://opmate-gw.mwportal.svc:8080/view?taskInstanceNo=### 여러번 호출해서 결과값 출력
	 ********************************************************************************************************************************/
	public List<RunResultDetailDTO> msa_opmategw_result_view( List<String> reqList ) {
		
		// 호출하고 바로 값을 가져오면 null 임.
		// Opmate가 끝나서 값이 채워졌을때 가져와야 함.  2019-09-28
		// Sleep 을 걸던지, 호출해서 끝나는 시간 체크해서 바로 땡겨오던지.
		
		String domain = "http://opmate-gw.mwportal.svc:8080/view";
		String url = "";
		String jsonResultMSG = "";
		List<RunResultDetailDTO> list = new ArrayList<RunResultDetailDTO>();
		
		MwportalUtils utils = new MwportalUtils();
				
		logger.info("at msa_opmategw_result_view() : http://opmate-gw.mwportal.svc:8080/view ========================== Begin");
		
		
		for(String taskInstanceNo : reqList ) {
			System.out.println("====================== taskInstanceNo Loop ("+taskInstanceNo+") ====================== " );
			url = domain + "?taskInstanceNo=" + taskInstanceNo ;
			System.out.println("Call URL = " + url);
			
			try {
				restTemplate = utils.getRestTemplate(url);   // encbase64out이 null 로 들어옴
				jsonResultMSG = restTemplate.getForObject(url, String.class );
				 
				// System.out.println(" Json Return String ====================== " );
				// System.out.println(jsonResultMSG);
				
				for(RunResultDetailDTO item : getRunResultDETAILDTO(jsonResultMSG) ) {
					System.out.println("====================== RunResultDetailDTO Loop ("+taskInstanceNo+") ====================== " );
					// System.out.println("Result Status	- " + item.getstatus() );
					// System.out.println("Result encout	- " + item.getencbase64out() );
					
					////////////////////////////////////////////////////////////
					// 호출만되고 아직 미완료	"status" : "Ready"
					// 호출만되고 완료			"status" : "Ready"
					// 하나라도 Complete 가 아니면 2초 Sleep 하고 null return 후 Controller 에서 다시 재호출 
					////////////////////////////////////////////////////////////
					if( !StringUtils.equals(item.getstatus(), "Complete")  ) {
						System.out.println("Sleep " + sleep_time + " ms : [" + item.getstatus() + "]");
						Thread.sleep(sleep_time);
						return null;
					}
					// item.setdecodestr();					
					// System.out.println(item.getdecodestr());
					// System.out.println(item.toString());
					
					list.add(item);	
				}
				
				// 다 완료되면 전체 List를 복호화 / HTML 형식으로 변환
				for(RunResultDetailDTO item : list ) {
					//////////////////////////////////////////////////////////////////////////////////////////////////
					// OS 별로 Clear Screen 문자열이 달라서 암호화 문자열을 자르는 방식은 제외
					//////////////////////////////////////////////////////////////////////////////////////////////////
					// Remove Character "G1TIG1tK" "G1TIG1sy"					
					// String enc = item.getencbase64out();
					// item.setencbase64out(enc.substring(8, enc.length()));   // Remove First 8 Chars

					//////////////////////////////////////////////////////////////////////////////////////////////////
					// Decdoing 이후 맨앞 6개 문자를 제거
					//////////////////////////////////////////////////////////////////////////////////////////////////
					item.decoding();
					String decstr = item.getdecodestr();
					item.setdecodestr(StringUtils.replace(decstr.substring(6, decstr.length()), "\n", "<BR>"));  // <p> => <BR>, Delete "Clear Screen"
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
		
		logger.info("List<RunResultDetail> list  ==== list.size() = " + list.size() );
		logger.info("at msa_opmategw_result_view() : http://opmate-gw.mwportal.svc:8080/view ========================== End");
		
		return list;
	}
	
	/************************************************************************************
	 * http://opmate-gw.mwportal.svc:8080/list 호출해서 Json Get
	 ************************************************************************************/
	public List<Opmate_ondemand_listDTO> msa_opmategw_list_get() {
		
		String url = "http://opmate-gw.mwportal.svc:8080/list";
		String jsonResultMSG = "";
		List<Opmate_ondemand_listDTO> list = new ArrayList<Opmate_ondemand_listDTO>();
		
		MwportalUtils utils = new MwportalUtils();
				
		logger.info("at get_opmate_ondemand_list() : http://opmate-gw.mwportal.svc:8080/list ========================== Begin");

		try {
			restTemplate = utils.getRestTemplate(url);
			jsonResultMSG = restTemplate.getForObject(url, String.class );	 //  [ {}, {}, {}, ... ]

			list = getOpmate_ondemand_listDTO(jsonResultMSG);
			
			/*
			// for (Opmate_ondemand_listDTO dto: getOpmate_ondemand_listDTO(jsonResultMSG) ) {
			for (Opmate_ondemand_listDTO dto: list ) {
				System.out.println(dto.toString());
			}
			*/

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.info("List<RunResultDetail> list  ==== list.size() = " + list.size() );
		logger.info("at msa_opmategw_result_view() : http://opmate-gw.mwportal.svc:8080/view ========================== End");
		
		return list;
	}
	
	/************************************************************************************
	 * NateOnBiz 주소 받아서 보내기
	 ************************************************************************************/
	public int msa_send_nateonbiz(String title, List<String> addressList, String message) {
		
		String url = "http://csp-nateonbiz.cspapi.svc:8080/send_memo";
		String jsonResultMSG = "";
		
		MwportalUtils utils = new MwportalUtils();
		
		/*
		System.out.println("========= Message ======= BEGIN");
		System.out.println(title);
		System.out.println(message);
		System.out.println("========= Message ======= END");
		*/
		
		logger.info("at OpmateService : msa_send_nateonbiz() ========================== Begin");		
		for(String email : addressList ) {
			System.out.println("Address : " + email );
			
			try {
				restTemplate = utils.getRestTemplate(url);			

				MultiValueMap<String, String> postParameters = new LinkedMultiValueMap<>();
				postParameters.add("title"		, title );
				postParameters.add("email"	, email );
				postParameters.add("msg"		, message );
				
				jsonResultMSG = restTemplate.postForObject(url, postParameters, String.class);
					
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// System.out.println("jsonResultMSG : " + jsonResultMSG);
		logger.info("at OpmateService : msa_send_nateonbiz() ========================== End");
		
		return addressList.size();	
	}

	private List<RunResultDetailDTO> getRunResultDETAILDTO(String jsonMSG) {
		// "[" 으로 시작하는 JsonArray 문자열을 DTO list 로 만들어서 Return
		
		Gson gson = new Gson();
		
		RunResultDetailDTO[] array = gson.fromJson(jsonMSG, RunResultDetailDTO[].class);
		List<RunResultDetailDTO> list = Arrays.asList(array);
		
		return list;		
	}
	
	private List<Opmate_ondemand_listDTO> getOpmate_ondemand_listDTO(String jsonMSG) {		
		// "[" 으로 시작하는 JsonArray 문자열을 DTO list 로 만들어서 Return
		
		Gson gson = new Gson();
		
		Opmate_ondemand_listDTO[] array = gson.fromJson(jsonMSG, Opmate_ondemand_listDTO[].class);
		List<Opmate_ondemand_listDTO> list = Arrays.asList(array);
		
		return list;		
	}
	
	/************************************************************************************
	 * http / https --insecure  방식으로 RestTemplate 객체 리턴
	 ************************************************************************************/
	/*
	private RestTemplate getRestTemplate(String URL) {
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		CloseableHttpClient httpClient;
		
		try {
			RestTemplate restTemplate;
			
			logger.info("URL = " + URL );
			
			SSLContext sslContext = new SSLContextBuilder()
					.loadTrustMaterial(null, (x509CertChain, authType) -> true)
			        .build();
					
			///////////////////////////////////////
			// RestTemplate using SSL Insecure
			///////////////////////////////////////
			httpClient = HttpClientBuilder.create()
					.setMaxConnTotal(100) // connection pool 적용. 최대 오픈되는 커넥션수
					.setMaxConnPerRoute(5) // connection pool 적용. IP:PORT 1 쌍에 대해 수행할 연결수
					// .setSSLHostnameVerifier(new NoopHostnameVerifier())			// 1. PKIX Error : insecure 효과 없음. Deprecated
					// .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)	// 2. PKIX Error : insecure 효과 없음. Deprecated
					.setSSLContext(sslContext)
					.setConnectionManager(
			                new PoolingHttpClientConnectionManager(
			                        RegistryBuilder.<ConnectionSocketFactory>create()
			                                .register("http", PlainConnectionSocketFactory.INSTANCE)
			                                .register("https", new SSLConnectionSocketFactory(sslContext,
			                                        NoopHostnameVerifier.INSTANCE))
			                                .build()
			                ))
					.build();
			
			factory.setReadTimeout(5000);  // 읽기시간초과(ms) 
			factory.setConnectTimeout(3000);  // 연결시간초과(ms)
			factory.setHttpClient(httpClient); // 동기실행에 사용될 HttpClient 세팅			
			
			restTemplate = new RestTemplate(factory);
			return restTemplate;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 
		}
		return getRestTemplate(null);		
	}
	*/
	
	/*
	private Map<String, List<String>> make_hashmap_list( List<String> reqList ) {
		// List<String> reqList = new ArrayList<>(Arrays.asList("1|host1", "1|host2", "2|host3"));
		
		List<String> nodes	= new ArrayList<String>();
		Map<String, List<String>> list_map = new HashMap<>();
		
		String task_id	= null;
		String node		= null;

		try {
			
			// String List 로 Map 생성
			for(String str : reqList ) {			
				task_id	= StringUtils.substringBefore(str, "|");
				node		= StringUtils.substringAfter(str, "|");

				if( list_map.isEmpty() ) {  // map 이 비어있으면
					nodes.add(node);
					list_map.put( task_id, nodes );		
				} else {						// map 안에 key 가 있으면
					if ( list_map.containsKey(task_id) ) {					
						List<String> tempList = list_map.get(task_id);

						tempList.add(node);  // Node 를 추가한 tempList 생성					
						list_map.replace(task_id, tempList ); // value를 tempList 로 교체
					} else {					// map 안에 key 가 없으면
						List<String> tempList = new ArrayList<String>();

						tempList.add(node);					
						list_map.put( task_id, tempList );					
					}
				}
			}
			
			System.out.println("list_map.size() : " + list_map.size());
	        for(String key : list_map.keySet()){
	            List<String> values = list_map.get(key);
	            
	            System.out.println("list_map key : " + key + "[" + values.size() + "]");
	            System.out.println("list_map valuse : " + values.toString() );	 
	        }
	        
	        return list_map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list_map;
	}
	*/
	
	
}
