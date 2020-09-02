package com.ibm.Triviatest.pojo;

import java.util.List;

import lombok.Data;

@Data
public class EmailObject {

	private String from;
	
	private List<String> invitees;
}
