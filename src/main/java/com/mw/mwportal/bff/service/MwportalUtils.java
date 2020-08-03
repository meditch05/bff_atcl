package com.mw.mwportal.bff.service;
// https://stackoverflow.com/questions/2703161/how-to-ignore-ssl-certificate-errors-in-apache-httpclient-4-0
// https://stackoverflow.com/a/38509015

import org.apache.commons.lang3.StringUtils;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.http.ssl.SSLContextBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class MwportalUtils {

	private static final Logger logger = LoggerFactory.getLogger(MwportalUtils.class);
	
	RestTemplate restTemplate;
	
	/************************************************************************************
	 * http / https --insecure  방식으로 RestTemplate 객체 리턴
	 ************************************************************************************/
	public final RestTemplate getRestTemplate(String URL) {
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
	
	public final Map<String, List<String>> make_hashmap_list_from_StringList( List<String> reqList ) {
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
			
			/*
			System.out.println("list_map.size() : " + list_map.size());
	        for(String key : list_map.keySet()){
	            List<String> values = list_map.get(key);
	            
	            System.out.println("list_map key : " + key + "[" + values.size() + "]");
	        }
	        */
	        
	        return list_map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list_map;
	}
}