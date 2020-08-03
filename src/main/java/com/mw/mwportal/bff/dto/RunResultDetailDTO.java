package com.mw.mwportal.bff.dto;

import lombok.Getter;

import com.google.gson.annotations.SerializedName;

@Getter
public class RunResultDetailDTO {

	@SerializedName("empno")
    private String empno;
	
	@SerializedName("salary")
    private String salary;
	
	@SerializedName("fromdate")
    private String fromdate;
	
	@SerializedName("todate")
    private String todate;

    @Override
    public String toString() {
        return "{" +
                	"\"empno\": \"" + empno + "\", " + 
                	"\"salary\": \"" + salary + "\", " +
                	"\"fromdate\": \"" + fromdate + "\"" +
                	"\"todate\": \"" + todate + "\"" +
                	// "\"decodestr\": \"" + decodestr + "\"" +
                '}';
    }
}