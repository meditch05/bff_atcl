package com.mw.mwportal.bff.dto;

import java.util.Base64;

import com.google.gson.annotations.SerializedName;

public class RunResultDetailDTO {

	@SerializedName("nodeId")
    private String nodeId;
	
	@SerializedName("status")
    private String status;
	
	@SerializedName("encbase64out")
    private String encbase64out;
	
	private String decodestr;
	
    public String getnodeId()					{	return nodeId;			}
    public String getstatus()					{	return status;			}
    public String getencbase64out()			{	return encbase64out;	}
    public String getdecodestr()				{	return decodestr;		}
    
    public void setencbase64out(String in)	{	this.encbase64out = in;	}    
    public void setdecodestr(String in)		{	this.decodestr = in;	}
    
    public void decoding()						{	decodestr = new String(Base64.getDecoder().decode(encbase64out));	}

    @Override
    public String toString() {
        return "{" +
                	"\"nodeId\": \"" + nodeId + "\", " + 
                	"\"status\": \"" + status + "\", " +
                	"\"encbase64out\": \"" + encbase64out + "\"" + 
                	// "\"decodestr\": \"" + decodestr + "\"" +
                '}';
    }
}