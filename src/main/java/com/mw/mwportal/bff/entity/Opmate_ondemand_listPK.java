package com.mw.mwportal.bff.entity;

import java.io.Serializable;
import lombok.Data;

/*
 * JPA 복합key 
 * https://lng1982.tistory.com/286
 *
 * CREATE TABLE OPMATE_ONDEMAND_LIST (
 * 		HOSTNAME	VARCHAR(100) NOT NULL,
 * 		ACCOUNT	VARCHAR(100) NOT NULL,
 * 		SCRIPT		VARCHAR(100) NOT NULL,
 * 		DOMAIN		VARCHAR(100) NOT NULL,
 * 		SERVICE		VARCHAR(100) NOT NULL,
 * 		CONSTRAINT OPMATE_ONDEMAND_LIST_PK PRIMARY KEY (HOSTNAME, ACCOUNT)
 * );
 */

@Data // PrimaryKey가 2개 이상일때, 중복업이 Select 하는 방법
public class Opmate_ondemand_listPK implements Serializable {

	private String hostname;
	private String account;

}