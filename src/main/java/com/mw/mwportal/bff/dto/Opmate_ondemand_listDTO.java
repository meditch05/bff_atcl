package com.mw.mwportal.bff.dto;

import lombok.Getter;

@Getter
public class Opmate_ondemand_listDTO {
	private String hostname;
	private String account;
	private String task_id;
	private String script;
	private String domain;
	private String service;
	
	@Override
	public String toString() {
		return "Opmate_ondemand_list [" +
				"DOMAIN=" + domain +
				", SERVICE=" + service +
				", HOSTNAME=" + hostname +
				", ACCOUNT=" + account +
				", TASK_ID=" + task_id +
				", SCRIPT=" + script +				 
				"]";
	}
}