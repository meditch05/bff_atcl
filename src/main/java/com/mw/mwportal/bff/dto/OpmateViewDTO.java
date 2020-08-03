package com.mw.mwportal.bff.dto;

public class OpmateViewDTO {
	
	private String nodeId;
	private String encbase64out;
	
	@Override
	public String toString() {
		return "View [" +
				"nodeId=" + nodeId +
				", encbase64out=" + encbase64out + "]";
				// "id=" + id + 
				// ", agentguid=" + agentguid + 
				// ", agentver=" + agentver +
				// ", osname=" + osname +
				
				// ", status=" + status +
				// ", heartbeatdt=" + heartbeatdt +
				// ", crtdt=" + crtdt + "]";		
	}

}
