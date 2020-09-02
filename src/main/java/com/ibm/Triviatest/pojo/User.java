package com.ibm.Triviatest.pojo;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class User {

public String _id;
public String username;
public String email;
public String password;
public String role = null;


}
