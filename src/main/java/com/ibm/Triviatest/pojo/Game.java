package com.ibm.Triviatest.pojo;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class Game {




private String _id; 
private String  gameName;
private String status;
private Date start; 
private Date end;
private int questionCount;


}