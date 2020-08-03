package com.mw.mwportal.bff.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mw.mwportal.bff.entity.Opmate_ondemand_list;

public interface Opmate_ondemand_listRepository extends JpaRepository<Opmate_ondemand_list, Long>{
	
	@Query(value = "SELECT svc.DOMAIN DOMAIN, svc.SERVICE SERVICE, odm.HOSTNAME HOSTNAME, odm.ACCOUNT ACCOUNT, 'empty' SCRIPT, 'empty' TASK_ID \r\n" + 
			"FROM ONDEMAND_CHECK_TARGET odm,\r\n" + 
			"     OPMATE_NODE_LIST opm,\r\n" + 
			"	  SERVICE_META svc\r\n" + 
			"WHERE odm.HOSTNAME = opm.HOSTNAME\r\n" + 
			"  AND opm.HOSTNAME = svc.HOSTNAME", nativeQuery = true)
	List<Opmate_ondemand_list> selectByJPQL();
	
	@Query(value = "INSERT INTO OPMATE_ONDEMAND_LIST \r\n" +
			"(HOSTNAME, ACCOUNT, TASK_ID, SCRIPT, DOMAIN, SERVICE) \r\n" +
			"VALUES (:hostname, :account, :task_id, :script, :domain, :service) ", nativeQuery = true)
	List<Opmate_ondemand_list> insertByJPQL(
			String hostname,
			String account,
			String task_id,
			String script,
			String domain,
			String service
			/*
			@Param("hostname") String hostname,
			@Param("account") String account,
			@Param("task_id") String task_id,
			@Param("script") String script,
			@Param("domain") String domain,
			@Param("service") String service
			*/	
			);
	
	// List<Opmate_node> findByHostname(String hostname);

	// List<Opmate_node> findByHostnameOrAccount(String hostname,String account);
	// List<Opmate_node> findByHostnameAndAccount(String hostname,String account);

	/*
	@Query(value = "SELECT * FROM ONDEMAND_CHECK_TARGET WHERE HOSTNAME = :hostname", nativeQuery = true)
	List<Customer> findByHostnameEndingWith(String hostname);

	@Query(value = "SELECT * FROM ONDEMAND_CHECK_TARGET WHERE HOSTNAME = :hostname OR ACCOUNT = :account", nativeQuery = true)
	List<Customer> findByHostnameOrAccount(
			@Param("hostname") String hostname,
			@Param("account") String account
			);

	@Query(value = "SELECT * FROM ONDEMAND_CHECK_TARGET WHERE HOSTNAME = :hostname AND ACCOUNT = :account", nativeQuery = true)
	List<Customer> findByHostnameAndAccount(
			@Param("hostname") String hostname,
			@Param("account") String account
			);
	*/
}
