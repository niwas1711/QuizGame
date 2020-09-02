package com.ibm.Triviatest.pojo;

import lombok.Data;

@Data
public class Gamepojo {

	private String _id;
private	String question;

private	String answers;
private	String others;



/*
 * id: string;
    gameName: string;
    status: string;
    start: Date;
    end: Date;
    questionCount: number;
    
    
    @Enumerated(EnumType.STRING)
@Column(name = "status")
private StatusEnum status = StatusEnum.DRAFT;

public StatusEnum getStatus() {
    return status;
}

public void setStatus(StatusEnum status) {
    this.status = status;
}
    
    
 */
}
