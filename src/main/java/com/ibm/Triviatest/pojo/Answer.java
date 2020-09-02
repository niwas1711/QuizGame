package com.ibm.Triviatest.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class Answer {

private String _id;
private String answerText;
private Boolean correct;


}