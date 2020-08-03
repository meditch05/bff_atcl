package com.mw.mwportal.bff.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Data;

/*
 * https://medium.com/@dongchimi/%EC%9D%B4%ED%81%B4%EB%A6%BD%EC%8A%A4%EC%97%90-lombok-%EC%84%A4%EC%B9%98-%EB%B0%8F-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0-b3489875780b
 * C:\Users\Administrator\.m2\repository\org\projectlombok\lombok\1.18.8\lombok-1.18.8.jar 더블클릭
 * "Specify Location"에서 사용 Eclipse 선택 => Install/Update
 * 이클립스 재기동

 * JPA 복합key 
 * https://lng1982.tistory.com/286
 *
 */

@Entity
@Data
@IdClass(Opmate_ondemand_listPK.class)   // PrimaryKey가 2개 이상일때, 중복업이 Select 하는 방법
@Table(name = "OPMATE_ONDEMAND_LIST")
public class Opmate_ondemand_list {

	@Id
	@Column(name = "HOSTNAME") // , insertable = true, updatable = true)
	private String hostname;

	@Id
	@Column(name = "ACCOUNT") // , insertable = true, updatable = true)
	private String account;

	@Column(name = "TASK_ID")
	private String task_id;
		
	@Column(name = "SCRIPT")
	private String script;

	@Column(name = "DOMAIN")
	private String domain;

	@Column(name = "SERVICE")
	private String service;
	
	@Override
	public String toString() {
		return "Opmate_ondemand_list [" +
				"HOSTNAME=" + hostname +
				", ACCOUNT=" + account +
				", TASK_ID=" + task_id +
				", SCRIPT=" + script +
				", DOMAIN=" + domain +
				", SERVICE=" + service + 
				"]";
	}
}