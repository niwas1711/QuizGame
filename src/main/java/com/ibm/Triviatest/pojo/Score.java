package com.ibm.Triviatest.pojo;

import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class Score {

/*public String _id;
public String userId;
public String status;
public List<State> state = null;
public Integer score;
public List<String> recommendations = null;

*/
	
	
private String _id;
private String email;
private String gameId;
private ScoreStatus status;
private int score;
private List<ScoreState> state;
private List<String>recommendations;
private String timeTaken;




@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Score other = (Score) obj;
	if (_id == null) {
		if (other._id != null)
			return false;
	} else if (!_id.equals(other._id))
		return false;
	if (email == null) {
		if (other.email != null)
			return false;
	} else if (!email.equals(other.email))
		return false;
	if (gameId == null) {
		if (other.gameId != null)
			return false;
	} else if (!gameId.equals(other.gameId))
		return false;
	if (recommendations == null) {
		if (other.recommendations != null)
			return false;
	} else if (!recommendations.equals(other.recommendations))
		return false;
	if (score != other.score)
		return false;
	if (state == null) {
		if (other.state != null)
			return false;
	} else if (!state.equals(other.state))
		return false;
	if (status != other.status)
		return false;
	if (timeTaken == null) {
		if (other.timeTaken != null)
			return false;
	} else if (!timeTaken.equals(other.timeTaken))
		return false;
	return true;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((_id == null) ? 0 : _id.hashCode());
	result = prime * result + ((email == null) ? 0 : email.hashCode());
	result = prime * result + ((gameId == null) ? 0 : gameId.hashCode());
	result = prime * result + ((recommendations == null) ? 0 : recommendations.hashCode());
	result = prime * result + score;
	result = prime * result + ((state == null) ? 0 : state.hashCode());
	result = prime * result + ((status == null) ? 0 : status.hashCode());
	result = prime * result + ((timeTaken == null) ? 0 : timeTaken.hashCode());
	return result;
}

	








}