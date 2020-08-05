package com.mw.mwportal.bff.dto;

//import lombok.Getter;

import com.google.gson.annotations.SerializedName;

// @Getter
public class RunResultDetailDTO {

    private String empno;
    private String salary;
    private String fromdate;
    private String todate;
    
    public String getEmpno()	{ return empno; }
    public String getSalary()	{ return salary; }
    public String getFromdate() { return fromdate; }
    public String getTodate()	{ return todate; }

    @Override
    public String toString() {
        return "{" +
                	"\"empno\": \"" + empno + "\", " + 
                	"\"salary\": \"" + salary + "\", " +
                	"\"fromdate\": \"" + fromdate + "\", " +
                	"\"todate\": \"" + todate + "\"" +
                '}';
    }
}