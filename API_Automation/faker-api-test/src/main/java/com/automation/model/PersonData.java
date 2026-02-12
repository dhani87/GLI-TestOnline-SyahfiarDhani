package com.automation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonData { 
    public String firstname;
    public String gender;
    public String birthday;
}