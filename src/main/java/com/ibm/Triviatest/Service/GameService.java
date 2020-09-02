package com.ibm.Triviatest.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.cloudant.client.api.model.IndexField;
import com.cloudant.client.api.model.Response;
import com.cloudant.client.api.query.QueryBuilder;
import com.cloudant.client.api.query.QueryResult;
import com.cloudant.client.api.views.AllDocsResponse;
import com.cloudant.client.api.model.IndexField.SortOrder;
import com.ibm.Triviatest.pojo.CtlUsers;
import com.ibm.Triviatest.pojo.Game;
import com.ibm.Triviatest.pojo.Gamepojo;
import com.ibm.Triviatest.pojo.Question;
import com.ibm.Triviatest.pojo.Score;
import com.ibm.Triviatest.pojo.User;
import com.cloudant.client.api.query.*;
@Service
public class GameService {
	@Autowired
	private CloudantClient client;
	
	
	@Autowired
	   private SecurityService passwordEncypter;
		
	 final String secretKey = "PleaseEncyptpassword@!!";
		
		
	
	// Database db = client.database("ctlgamedb",false);
	public int adduser(User user) {
		Database db = client.database("userdetails", false);
		Response rs = db.post(user);
		System.out.println("response" + rs.toString());
		return rs.getStatusCode();
	}
	public int deleteuser(String id) {
		Database db = client.database("userdetails", false);
		HashMap<String, Object> obj = db.find(HashMap.class, id + "");
		Response rs = db.remove(obj);
		System.out.println("remover Successful.");
		return rs.getStatusCode();
	}
	
	public int deletegame(String id) {
		Database db = client.database("game", false);
		System.out.println("I am here in game delete"+id);
		HashMap<String, Object> obj = db.find(HashMap.class, id + "");
		System.out.println("delete objext data"+obj.toString());
		
		Response rs = db.remove(obj);
		System.out.println("remover Successful.");
		return rs.getStatusCode();
	}
	
	
	public Optional<User> getuserbyId(String Emailid,String name) {
		Database db = client.database("userdetails", false);
		List<User> userdata = new ArrayList<>();
		
	//	String temppass=passwordEncypter.decrypt(password, secretKey) ;
	//	System.out.println("temppass"+temppass);
		
		
		try {
			userdata = db.getAllDocsRequestBuilder().includeDocs(true).build().getResponse().getDocsAs(User.class);
		} catch (Exception e) {
			System.out.println("Exception thrown : " + e.getMessage());
		}
		Optional<User> us = userdata.stream().filter(
				e -> e.getEmail().contentEquals(Emailid))
				.findFirst();
		
		
	 
		return us;
	}
	
	public Optional<User> getuserbyEmailId(String Emailid) {
		Database db = client.database("userdetails", false);
		List<User> userdata = new ArrayList<>();
		
	//	String temppass=passwordEncypter.decrypt(password, secretKey) ;
	//	System.out.println("temppass"+temppass);
		
		
		try {
			userdata = db.getAllDocsRequestBuilder().includeDocs(true).build().getResponse().getDocsAs(User.class);
		} catch (Exception e) {
			System.out.println("Exception thrown : " + e.getMessage());
		}
		Optional<User> us = userdata.stream().filter(
				e -> e.getEmail().contentEquals(Emailid))
				.findFirst();
		
		
	 
		return us;
	}
	
	
	
	
	
	public Optional<User> getuser(String Emailid,String password) {
		Database db = client.database("userdetails", false);
		List<User> userdata = new ArrayList<>();
		System.out.println("passwpr"+password);
	//	String temppass=passwordEncypter.decrypt(password, secretKey) ;
	//	System.out.println("temppass"+temppass);
		
		
		try {
			userdata = db.getAllDocsRequestBuilder().includeDocs(true).build().getResponse().getDocsAs(User.class);
		} catch (Exception e) {
			System.out.println("Exception thrown : " + e.getMessage());
		}
		Optional<User> us = userdata.stream().filter(
				e -> e.getEmail().contentEquals(Emailid) && e.getPassword().contentEquals(password))
				.findFirst();
		
		us.get().setPassword("");
		System.out.println("us"+us.get());
		return us;
	}
	
	
	public Optional<CtlUsers> getemailId(String name) {
		Database db = client.database("ctlusers", false);
		List<CtlUsers> userdata = new ArrayList<>();

		try {
			userdata = db.getAllDocsRequestBuilder().includeDocs(true).build().getResponse().getDocsAs(CtlUsers.class);
			
		} catch (Exception e) {
			System.out.println("Exception thrown : " + e.getMessage());
		}
		Optional<CtlUsers> us = userdata.stream().filter(e->e.getName().contentEquals(name)).findFirst();
		
		System.out.println("us"+us.get());
		return us;
	}
	
	public Optional<CtlUsers> getname(String name) {
		Database db = client.database("ctlusers", false);
		List<CtlUsers> userdata = new ArrayList<>();

		try {
			userdata = db.getAllDocsRequestBuilder().includeDocs(true).build().getResponse().getDocsAs(CtlUsers.class);
		} catch (Exception e) {
			System.out.println("Exception thrown : " + e.getMessage());
		}
		Optional<CtlUsers> us = userdata.stream().filter(e->e.getEmailId().contains(name)).findFirst();
		
		System.out.println("us"+us.get());
		return us;
	}
	
	
	
	
	public List<User> getalluser() {
		// TODO Auto-generated method stub
		Database db = client.database("userdetails", false);
		List<User> userdata = new ArrayList<>();
		try {
			userdata = db.getAllDocsRequestBuilder().includeDocs(true).build().getResponse().getDocsAs(User.class);
		} catch (Exception e) {
			System.out.println("Exception thrown : " + e.getMessage());
		}
		
		return userdata;
	}
	
	
	
	
	public List<CtlUsers> getallctluser() {
		// TODO Auto-generated method stub
		Database db = client.database("ctlusers", false);
		List<CtlUsers> userdata = new ArrayList<>();
		try {
			userdata = db.getAllDocsRequestBuilder().includeDocs(true).build().getResponse().getDocsAs(CtlUsers.class);
		} catch (Exception e) {
			System.out.println("Exception thrown : " + e.getMessage());
		}
		
		return userdata;
	}
	
	
	
	
	public int addquestion(Question question) {
		Database db = client.database("questions", false);
		Response rs = db.post(question);
		System.out.println("response" + rs.toString());
		return rs.getStatusCode();
	}
	public int updatequestion(String id, Question question) throws IOException {
		System.out.println("ddd" + id);
		Database db = client.database("questions", false);
		Question ques = db.find(Question.class, id);
		HashMap<String, Object> obj = db.find(HashMap.class, id + "");
		System.out.println("ques" + ques.toString() + "obj" + obj);
		if (question.getQuestionText() != null)
			ques.setQuestionText(question.getQuestionText());
		if (question.getAnswers().size() > 0)
			ques.setAnswers(question.getAnswers());
		obj.put("answers", ques);
		Response rs = db.update(obj);
		System.out.println("Update Successful.");
		return rs.getStatusCode();
	}
	
	
	public int updategame(String id, Game game) {
		// TODO Auto-generated method stub
		System.out.println("ddd" + id);
		Database db = client.database("game", false);
		Game gameupdate = db.find(Game.class, id);
		HashMap<String, Object> obj = db.find(HashMap.class, id + "");
		System.out.println("ques" + gameupdate.toString() + "obj" + obj);
		
		System.out.println("update data"+game.toString());
		System.out.println("game.getGameName().isEmpty()"+game.getGameName().isEmpty());
		
		System.out.println("len gss"+game.getGameName().length());
		
		if (game.getGameName()!= null && game.getGameName().length()>0 )
			
		{	gameupdate.setGameName(game.getGameName());
			
			System.out.println("1");
		   obj.put("gameName", game.getGameName());
		}
		if (game.getQuestionCount()!= 0)
		{
			System.out.println("2");
			gameupdate.setQuestionCount(game.getQuestionCount());
	
			 System.out.println("questionCount");
			 obj.put("questionCount", game.getQuestionCount());
		}
		if(game.getStatus()!=null && game.getStatus().contains(""))
			
		{
			System.out.println("3");
			gameupdate.setStatus(game.getStatus());
			 obj.put("status", game.getStatus());
		}
		if(game.getStart()!=null)
		{	gameupdate.setStart(game.getStart());
			System.out.println("4");
		 obj.put("start", game.getStart());
		}
		if(game.getEnd()!=null)
		{System.out.println("5");
			gameupdate.setEnd(game.getEnd());
			obj.put("end", game.getEnd());
		}
		
		System.out.println("obj"+obj.toString());
		
		//obj.put("Game", gameupdate);
		Response rs = db.update(obj);
		System.out.println("Update Successful.");
		return rs.getStatusCode();
		
		//return 200;
	}
	
	
	
	
	public int delete(String id) {
		System.out.println("ddd" + id);
		Database db = client.database("questions", false);
		HashMap<String, Object> obj = db.find(HashMap.class, id + "");
		Response rs = db.remove(obj);
		System.out.println("remover Successful.");
		return rs.getStatusCode();
	}
	public List<Question> getquestion(int totalItems,String gameid) {
		System.out.println("number here"+totalItems);
		Database db1 = client.database("questions", false);
	//	System.out.println("db"+db1.getAllDocsRequestBuilder().build().getResponse().);
		List<Question> questiondata = new ArrayList<>();
		try {
			questiondata = db1.getAllDocsRequestBuilder().includeDocs(true).build().getResponse()
					.getDocsAs(Question.class);
		} catch (Exception e) {
			System.out.println("Exception thrown : " + e.getMessage());
		}
		if(totalItems==-1)
		{
			List<Question> questiondatawithgameid=questiondata.stream().filter(i->i.getGameId().equalsIgnoreCase(gameid)).collect(Collectors.toList());
		
			return questiondatawithgameid;
		}
		else
		{
		List<Question> questiondatawithgameid=questiondata.stream().filter(i->i.getGameId().equalsIgnoreCase(gameid)).collect(Collectors.toList());
		
		int Item=0;
		if((questiondatawithgameid.size() -totalItems)<1)
		{
			Item=questiondatawithgameid.size();
		}
		else
			{
			Item=totalItems;
			}
		
		System.out.println("Item"+Item);
		
		Random rand = new Random();
System.out.println("questiondata"+questiondatawithgameid.size());
		// int totalItems=3;
		List<Question> newList = new ArrayList<>();
		for (int i = 0; i < Item; i++) {
			// take a raundom index between 0 to size
			// of given List
			int randomIndex = Math.abs(rand.nextInt(questiondatawithgameid.size()));
			// add element in temporary list
			newList.add(questiondatawithgameid.get(randomIndex));
			// Remove selected element from orginal list
			questiondatawithgameid.remove(randomIndex);
		}
		return newList;
		}
	}
	public int addgame(Game game) {
		Database db = client.database("game", false);
		Response rs = db.post(game);
		System.out.println("response" + rs.toString());
		return rs.getStatusCode();
	}
	
	
	
	public List<Game> getallgame() {
		Database db = client.database("game", false);
		List<Game> Score = new ArrayList<>();
		try {
			Score = db.getAllDocsRequestBuilder().includeDocs(true).build().getResponse().getDocsAs(Game.class);
		} catch (Exception e) {
			System.out.println("Exception thrown : " + e.getMessage());
		}
		
		return Score;
	}
	
	
	public Optional<Game> getgamebyId(String id) {
		// TODO Auto-generated method stub
		Database db = client.database("game", false);
		List<Game> Score = new ArrayList<>();
		try {
			Score = db.getAllDocsRequestBuilder().includeDocs(true).build().getResponse().getDocsAs(Game.class);
		} catch (Exception e) {
			System.out.println("Exception thrown : " + e.getMessage());
		}
		Optional<Game> score = Score.stream().filter(e -> e.get_id().contentEquals(id)).findFirst();
		return score;
	}

	
	
	
	public int addscore(Score score) {
		// TODO Auto-generated method stub
		Database db = client.database("scores", false);
		Response rs=null;
		Optional<Score> exitingscore = getscoreusingameId(score.getGameId(),score.getEmail());
		//System.out.println("present or not"+exitingscore.get());
		if(!exitingscore.isPresent())
		{
		  rs = db.post(score);
		System.out.println("response" + rs.toString());
		return rs.getStatusCode();
		}
		else
		{
			System.out.println("not updates");
		return 200;
		}
	}
	public List<Score> getscore(String id) {
		System.out.println(" I am in usedId game acceess");
		Database db = client.database("scores", false);
		List<Score> Score = new ArrayList<>();
		try {
			Score = db.getAllDocsRequestBuilder().includeDocs(true).build().getResponse().getDocsAs(Score.class);
		} catch (Exception e) {
			System.out.println("Exception thrown : " + e.getMessage());
		}
		List<Score> score = Score.stream().filter(e -> e.getEmail().contentEquals(id)).collect(Collectors.toList());
		return score;
	}
	
	
	public Optional<Score>  getscoreusingameId(String gameId,String email) {
		System.out.println(" I am in gameId game acceess");
		Database db = client.database("scores", false);
		List<Score> Score = new ArrayList<>();
		try {
			Score = db.getAllDocsRequestBuilder().includeDocs(true).build().getResponse().getDocsAs(Score.class);
		} catch (Exception e) {
			System.out.println("Exception thrown : " + e.getMessage());
		}
		Optional<Score> score = Score.stream().filter(e -> e.getEmail().contentEquals(email) && e.getGameId().contentEquals(gameId)).findFirst();
		return score;
	}
		
	public List<Score> gettopscore(String gameId,int count) {
		Database db = client.database("scores", false);
		List<Score> Score1 = new ArrayList<>();
		try {
			Score1 = db.getAllDocsRequestBuilder().includeDocs(true).build().getResponse().getDocsAs(Score.class);
		} catch (Exception e) {
			System.out.println("Exception thrown : " + e.getMessage());
		}

		
		//Score1.stream().filter(j->j.getTimeTaken()).map(Float::parseFloat)
	    
		Score1.forEach(System.out::println);
		
		List<Score> scorebasedongameId=Score1.stream().filter(i->i.getGameId().contentEquals(gameId)).collect(Collectors.toList());
		
		Comparator<Score> top10 = Comparator.comparing(Score::getScore).reversed().thenComparing(Score::getTimeTaken);
		if(Score1.size()>count)
		{
	List<Score> top10score=	scorebasedongameId.stream().limit(count).sorted(top10).collect(Collectors.toList());
	System.out.println("top10score"+top10score.toString());
	return top10score;
		}else
			{
			List<Score> toplesscount=	scorebasedongameId.stream().limit(count).sorted(top10).collect(Collectors.toList());
			
			System.out.println("toplesscount"+toplesscount);
			
			
			
	return toplesscount;
			}
	}
	public int savemaster(Question masterdata) {
		// TODO Auto-generated method stub
		Database db = client.database("master", false);
		Response rs = db.post(masterdata);
		System.out.println("response" + rs.toString());
		return rs.getStatusCode();
	}
	public List<Question> getmasterdata() {
		Database db = client.database("scores", false);
		List<Question> masterdata = new ArrayList<>();
		try {
			masterdata = db.getAllDocsRequestBuilder().includeDocs(true).build().getResponse()
					.getDocsAs(Question.class);
		} catch (Exception e) {
			System.out.println("Exception thrown : " + e.getMessage());
		}
		return masterdata;
	}
	public int addctluser(CtlUsers ctluser) {
		// TODO Auto-generated method stub
		
		Database db = client.database("ctlusers", false);
		Response rs = db.post(ctluser);
		System.out.println("response" + rs.toString());
		return rs.getStatusCode();
		
	}
	
	
	
}
