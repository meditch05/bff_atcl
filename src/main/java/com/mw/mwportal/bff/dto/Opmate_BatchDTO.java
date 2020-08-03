package com.mw.mwportal.bff.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Opmate_BatchDTO {
	private String task_id;
	private List<String> node_list;
	
	// task_id|hostname
	public List<String> getReqList() {
		List<String> reqList = new ArrayList<String>();
		
		for( String node : node_list ) {
			reqList.add(task_id + "|" + node);
		}
		
		return reqList;
	}
	
	@Override
	public String toString() {
		return "Opmate_BatchDTO [" +
				"TASK_ID=" + task_id +
				", NODE_LIST =" + node_list.toString() + 		 
				"]";
	}
}