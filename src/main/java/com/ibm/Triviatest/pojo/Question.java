package com.ibm.Triviatest.pojo;

import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data

@ToString
public class Question {
/*private String _id;
public List<Question_> questions = null;
public List<User> users = null;
public List<Game> games = null;
public List<Score> scores = null;

*/
	
	private String _id;
private String gameId;
private String questionText;
private List<Answer>answers;
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Question other = (Question) obj;
	if (_id == null) {
		if (other._id != null)
			return false;
	} 
	if (questionText == null) {
		if (other.questionText != null)
			return false;
	} else if (!questionText.equals(other.questionText))
		return false;
	return true;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((_id == null) ? 0 : _id.hashCode());
	result = prime * result + ((answers == null) ? 0 : answers.hashCode());
	result = prime * result + ((gameId == null) ? 0 : gameId.hashCode());
	result = prime * result + ((questionText == null) ? 0 : questionText.hashCode());
	return result;
}





}