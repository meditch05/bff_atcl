package com.mw.mwportal.bff.service;
// https://stackoverflow.com/questions/2703161/how-to-ignore-ssl-certificate-errors-in-apache-httpclient-4-0
// https://stackoverflow.com/a/38509015

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.mw.mwportal.bff.dto.RunResultDetailDTO;
import com.google.gson.Gson;

public class OpmateService {

	private static final Logger logger = LoggerFactory.getLogger(OpmateService.class);
	private static final int sleep_time = 5000;
	
	RestTemplate restTemplate;

	/********************************************************************************************************************************
	 * RestAPI 호출  ( http://restapi.test.svc:8080/get/salary/10051 )
	 ********************************************************************************************************************************/
	public List<RunResultDetailDTO> msa_get_json( int emp_no ) {
		
		// 호출하고 바로 값을 가져오면 null 임.
		// Opmate가 끝나서 값이 채워졌을때 가져와야 함.  2019-09-28
		// Sleep 을 걸던지, 호출해서 끝나는 시간 체크해서 바로 땡겨오던지.
		
		String url = "http://restapi.test.svc:8080/get/salary/" + emp_no;
		String jsonResultMSG = "";
		List<RunResultDetailDTO> list = new ArrayList<RunResultDetailDTO>();
		
		MwportalUtils utils = new MwportalUtils();
				
		logger.info("at msa_get_json() : http://restapi.test.svc:8080/get/salary/" + emp_no + " ==== Begin");		
		
		System.out.println("Call URL = " + url);

		try {
			restTemplate = utils.getRestTemplate(url); // encbase64out이 null 로 들어옴
			jsonResultMSG = restTemplate.getForObject(url, String.class);

			// System.out.println(" Json Return String ====================== " );
			// System.out.println(jsonResultMSG);

			for (RunResultDetailDTO item : getRunResultDETAILDTO(jsonResultMSG)) {
				System.out.println("======= RunResultDetailDTO Loop (" + emp_no  + ") =======");
				// System.out.println("Result Status - " + item.getstatus() );
				// System.out.println("Result encout - " + item.getencbase64out() );

				////////////////////////////////////////////////////////////
				// 호출만되고 아직 미완료 "status" : "Ready"
				// 호출만되고 완료 "status" : "Ready"
				// 하나라도 Complete 가 아니면 2초 Sleep 하고 null return 후 Controller 에서 다시 재호출
				////////////////////////////////////////////////////////////
				if (!StringUtils.equals(item.getEmpno(), "10051")) {
					System.out.println("Sleep " + sleep_time + " ms : [" + item.getEmpno() + "]");
					Thread.sleep(sleep_time);
					return null;
				}
				// item.setdecodestr();
				// System.out.println(item.getdecodestr());
				// System.out.println(item.toString());

				list.add(item);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("List<RunResultDetail> list  ==== list.size() = " + list.size());
		logger.info(
				"at msa_opmategw_result_view() : http://opmate-gw.mwportal.svc:8080/view ========================== End");

		return list;
	}

	private List<RunResultDetailDTO> getRunResultDETAILDTO(String jsonMSG) {
		// "[" 으로 시작하는 JsonArray 문자열을 DTO list 로 만들어서 Return
		
		Gson gson = new Gson();
		
		RunResultDetailDTO[] array = gson.fromJson(jsonMSG, RunResultDetailDTO[].class);
		List<RunResultDetailDTO> list = Arrays.asList(array);
		
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
