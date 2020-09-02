package com.ibm.Triviatest.pojo;

import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class Question_ {

private String _id;
private String questionText;
private List<Answer> answers = null;



}