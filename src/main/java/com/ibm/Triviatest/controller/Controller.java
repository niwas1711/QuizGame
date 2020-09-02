package com.ibm.Triviatest.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.cloudant.client.api.CloudantClient;
import com.ibm.Triviatest.Service.EmailService;
import com.ibm.Triviatest.Service.GameService;
import com.ibm.Triviatest.Service.SecurityService;
import com.ibm.Triviatest.pojo.Authenticatepojo;
import com.ibm.Triviatest.pojo.CtlUsers;
import com.ibm.Triviatest.pojo.CustomStatus;
import com.ibm.Triviatest.pojo.EmailObject;
import com.ibm.Triviatest.pojo.Game;
import com.ibm.Triviatest.pojo.Question;
import com.ibm.Triviatest.pojo.Score;
import com.ibm.Triviatest.pojo.User;

import ch.qos.logback.core.status.Status;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/game")
@Api(value = "TriviaGame", description = "Api's details for the Games App")
public class Controller {
	@Autowired
	private CloudantClient client;
	@Autowired
	private GameService service;
	@Autowired
	private EmailService email;

	@Autowired
   private SecurityService passwordEncypter;
	
	 final String secretKey = "PleaseEncyptpassword@!!";
	
		@Autowired
	 private HttpServletRequest request;
	 
	
	@PostMapping("/users")
	@ApiOperation(value = "Add User to database", response = ResponseEntity.class)
	public ResponseEntity<CustomStatus> adduser(@RequestBody User user) {
		System.out.println("user data" + user.toString());
		CustomStatus statusobj= new CustomStatus();
		
		user.setPassword(passwordEncypter.encrypt(user.getPassword(), secretKey)) ;
		
		if(service.getuserbyId(user.getEmail(),user.getUsername()).isPresent())
		{
			statusobj.setStatus("User already Exits"+user.toString());	
			return new ResponseEntity<>(statusobj, HttpStatus.OK);
		}else
		{
		
		service.adduser(user);
		//CustomStatus statusobj= new CustomStatus();
		statusobj.setCode(200);
		statusobj.setStatus("User is Added Successfully"+user.toString());
		return new ResponseEntity<>(statusobj, HttpStatus.OK);
		}
	}

	/*
	 * @GetMapping("/users")
	 * 
	 * @ApiOperation(value =
	 * "validate the user you need to pass User Object structure just needed email and Password"
	 * ,response = User.class) public ResponseEntity<User> getuser(@RequestBody User
	 * user) { Optional<User> finduser= service.getuser(user);
	 * if(finduser.isPresent()) return new
	 * ResponseEntity<>(finduser.get(),HttpStatus.OK); else return new
	 * ResponseEntity<>(null,HttpStatus.NOT_FOUND); }
	 */
	@DeleteMapping("/users/{id}")
	@ApiOperation(value = "validate the user you need to pass User Object structure just needed email and Password", response = User.class)
	public ResponseEntity<String> deleteuser(@PathVariable("id") String id) {
		int s = service.deleteuser(id);
		return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
	}

	
	@GetMapping("/users")
	@ApiOperation(value = "get all User from database", response = ResponseEntity.class)
	public ResponseEntity<List<User>> getuser() {
		
	List<User> userdetails=	service.getalluser();
	//userdetails.get(0).setPassword(passwordEncypter.decrypt(userdetails.get(0).getPassword(), secretKey));
		CustomStatus statusobj= new CustomStatus();
		statusobj.setCode(200);
		statusobj.setStatus("User is get Successfully"+userdetails.toString());
		
		return new ResponseEntity<>(userdetails, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/games/{id}")
	@ApiOperation(value = "delete the games ", response = ResponseEntity.class)
	public ResponseEntity<CustomStatus> deletegame(@PathVariable("id") String id) {
	
		
		service.deletegame(id);
		
		CustomStatus statusobj= new CustomStatus();
		statusobj.setCode(200);
		statusobj.setStatus("Successfully deleted");
		return new ResponseEntity<>(statusobj, HttpStatus.OK);
	}
	
	@PostMapping("/question")
	@ApiOperation(value = "add question in the db", response = ResponseEntity.class)
	public ResponseEntity<CustomStatus> addquestion(@RequestBody Question question) {
		System.out.println("question" + question.toString());
		service.addquestion(question);
		CustomStatus statusobj= new CustomStatus();
		statusobj.setCode(200);
		statusobj.setStatus("question Added Successfully");
		return new ResponseEntity<>(statusobj, HttpStatus.OK);
	}

	@PutMapping("/question/{id}")
	@ApiOperation(value = "Update Questions", response = ResponseEntity.class)
	public ResponseEntity<String> getquestion(@PathVariable("id") String id, @RequestBody Question question)
			throws IOException {
		int s = service.updatequestion(id, question);
		return new ResponseEntity<>("Question has updated successfully", HttpStatus.OK);
	}

	@GetMapping("/question")
	@ApiOperation(value = "get Question Questions", response = ResponseEntity.class)
	public ResponseEntity<List<Question>> getquestion(@RequestParam("number") int number,@RequestParam("gameid")String GameId) {
		System.out.println("number" + number);
		List<Question> Questionresponse = service.getquestion(number,GameId);
		return new ResponseEntity<>(Questionresponse, HttpStatus.OK);
	}

	@DeleteMapping("/question/{id}")
	@ApiOperation(value = "delete the questions", responseReference = "List of questions in ResponsEntity class")
	public ResponseEntity<CustomStatus> questiondelete(@PathVariable("id") String id) {
		int s = service.delete(id);
		CustomStatus statusobj= new CustomStatus();
		statusobj.setCode(200);
		statusobj.setStatus("Successfully deleted");
		return new ResponseEntity<>(statusobj, HttpStatus.OK);
	}

	@PostMapping("/games")
	@ApiOperation(value = "add the games ", response = ResponseEntity.class)
	public ResponseEntity<CustomStatus> postgames(@RequestBody Game game) {
		System.out.println("question" + game.toString());
		service.addgame(game);
		CustomStatus statusobj= new CustomStatus();
		statusobj.setCode(200);
		statusobj.setStatus("Game is added Successfully");
		return new ResponseEntity<>(statusobj, HttpStatus.OK);
	}

	
	
	
	
	@GetMapping("/games")
	@ApiOperation(value = "get all the games ", response = ResponseEntity.class)
	public ResponseEntity<List<Game>> getgame() {
		//System.out.println("question" + game.toString());
	List<Game> gamedetails=	service.getallgame();
		return new ResponseEntity<>(gamedetails, HttpStatus.OK);
	}
	
	
	@GetMapping("/games/{id}")
	@ApiOperation(value = "get all the games by id ", response = ResponseEntity.class)
	public ResponseEntity<Game> getgame(@PathVariable("id") String id) {
		System.out.println("question" +id);
	Optional< Game> gamedetails=	service.getgamebyId(id);
	if(gamedetails.isPresent())
		return new ResponseEntity<>(gamedetails.get(), HttpStatus.OK);
	else
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		
	}
	
	@PutMapping("/games/{id}")
	@ApiOperation(value = "update the games ", response = ResponseEntity.class)
	public ResponseEntity<String> changegame(@PathVariable("id") String id,@RequestBody Game game) {
		System.out.println("question" +id);
int s=	service.updategame(id,game);
	
		return new ResponseEntity<>("Game added successfully", HttpStatus.OK);
	
		
	}
	
	
	
	
	
	
	@PostMapping("/score")
	@ApiOperation(value = "add the score of indivisuals", response = ResponseEntity.class)
	public ResponseEntity<CustomStatus> addscore(@RequestBody Score score) {
		System.out.println("question" + score.toString());
		service.addscore(score);
		
		CustomStatus statusobj= new CustomStatus();
		statusobj.setCode(200);
		statusobj.setStatus("Game is added Successfully");
		
		return new ResponseEntity<>(statusobj, HttpStatus.OK);
	}

	@GetMapping(value="/score",params="UserId")
	@ApiOperation(value = "getScore of indivisuals", response = Score.class)
	public ResponseEntity<List<Score>> getscore(@RequestParam("UserId") String UserId) {
		System.out.println("question" + UserId);
		List<Score> score = service.getscore(UserId);
		if (score.size()>0)
			return new ResponseEntity<>(score, HttpStatus.OK);
		else
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value="/score")
	@ApiOperation(value = "getScore of indivisuals", response = Score.class)
	public ResponseEntity<Score> scoreusinggameId(@RequestParam("gameId") String gameId,@RequestParam("email") String email) {
		System.out.println("question" + gameId);
		Optional<Score> score = service.getscoreusingameId(gameId,email);
		if (score.isPresent())
			return new ResponseEntity<>(score.get(), HttpStatus.OK);
		else
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	
	
	
	
	@PostMapping("/savemaster")
	@ApiOperation(value = "Add the completed json which you given", response = ResponseEntity.class)
	public ResponseEntity<String> savemaster(@RequestBody Question masterdata) {
		System.out.println("question" + masterdata.toString());
		int status = service.savemaster(masterdata);
		return new ResponseEntity<>("Complete data has been saved", HttpStatus.OK);
	}

	@PostMapping("/getMaster")
	@ApiOperation(value = "get the  completed Json which you given", response = Question.class)
	public ResponseEntity<List<Question>> getmaster() {
		List<Question> masterdata = service.getmasterdata();
		return new ResponseEntity<>(masterdata, HttpStatus.OK);
	}

	
	
	@PostMapping("/email")
	@ApiOperation(value = "Send the Email", response = String.class)
	public ResponseEntity<String> sendmail(@RequestBody EmailObject reciver) {
		/*String[] reciver = new String[2];
		reciver[0] = "niwas_shashi@in.ibm.com";
		reciver[1] = "niwash1711@gmail.com";
		
		*/
		String sendername = "niwas_shashi@in.ibm.com";
		System.out.println("sendername" +reciver.getFrom());
		
		for (int i = 0; i < reciver.getInvitees().size(); i++) {
			System.out.println("reciver.getInvitees().get(i)" +reciver.getInvitees().get(i));
			Optional<CtlUsers> namefromdb=	service.getname(reciver.getFrom());
		Optional<CtlUsers> emailIdfromdb=	service.getemailId(reciver.getInvitees().get(i));
			
		System.out.println("namefromdb"+namefromdb);
		System.out.println("emailIdfromdb"+emailIdfromdb);
		
			email.sendSimpleMessage(emailIdfromdb.get().getEmailId(), sendername, namefromdb.get().getName()+" has nominated you to play Trivia ",
					"Dear "+reciver.getInvitees().get(i)+ 
					"\r\n" + 
					"\t\t                             "+namefromdb.get().getName()+
				    "  has nominated you to take this trivia. Please go to the following link "+
					"\r\n"  + "1.Signup for a new account."+
				    "\r\n"+" 2.Sign in to play the Trivia."
				     +"\r\n"+
				    "https://ctltriviagame.mybluemix.net   \r\n" + 
					"\r\n" + 
					"Thanks\r\n" + 
					"Trivia");
		}
		return new ResponseEntity<>("email sendt successfully", HttpStatus.OK);
	}


	
	@GetMapping("/topscore")
	@ApiOperation(value = "Get top scorer according to the limit", response = String.class)
	public ResponseEntity<List<Score>> topscrorer(@RequestParam(name="gameId") String gameId,@RequestParam(name="count") int count) {
		System.out.println("in topscorer");
		List<Score> top_scorer = service.gettopscore(gameId,count);
		return new ResponseEntity<>(top_scorer, HttpStatus.OK);
	}

	@PostMapping("/authenticate")
	@ApiOperation(value = "authenticate the user from database", response = String.class)
	public ResponseEntity<User> authenticate(@RequestBody Authenticatepojo authenticate ) {
		String temppass=passwordEncypter.encrypt(authenticate.getPassword(), secretKey) ;
	//	System.out.println("passwor\n"+temppass+"original \n"+authenticate.getPassword());
		
		Optional<User> finduser = service.getuser(authenticate.getEmail(), temppass);
		if (finduser.isPresent())
			return new ResponseEntity<>(finduser.get(), HttpStatus.OK);
		else
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	@GetMapping("/resetpassword")
	@ApiOperation(value = "Api for the forgot Password", response = String.class)
	public ResponseEntity<CustomStatus> reset_password(@RequestParam(name="EmailId") String EmailId ) {
		
		CustomStatus statusobj= new CustomStatus();
		
	Optional<User> userdetails=service.getuserbyEmailId(EmailId);
		
	
		String password=passwordEncypter.decrypt(userdetails.get().getPassword(), secretKey);

		System.out.println("password"+password);
		
		String senderEmailId="niwas_shashi@in.ibm.com";
		
		email.sendSimpleMessage(userdetails.get().getEmail(), senderEmailId, "Trivia Tool Password Reset-", "Dear "+userdetails.get().getUsername()+" ,\r\n" + 
				" Its seems you have Requested the Password Reset from the Tivia tool.\r\n" + 
				"									   \r\n" + 
				"  Your existing password is \t" +password +"\r\n" + 
				"\r\n" + 
				"Thanks\r\n" + 
				"Trivia  \r\n" + 
				"									   ");
		
		if (userdetails.isPresent())
			{statusobj.setStatus("Password has sent to User EmailId");
			statusobj.setCode(200);
			return new ResponseEntity<>(statusobj, HttpStatus.OK);
			}
		else
		{
			statusobj.setStatus("user is Not found in Databse");
			statusobj.setCode(404);
			return new ResponseEntity<>(statusobj, HttpStatus.NOT_FOUND);

		}
	}
	
	
	
	/*
	 * @GetMapping(value = "/getdb") public List
	 * getAll(@RequestParam(required=false) Integer itemId) { // Get all documents
	 * from socialreviewdb List allDocs = null; Database db =
	 * client.database("ctlgamedb",false); try { if (itemId == null) { allDocs =
	 * db.getAllDocsRequestBuilder().includeDocs(true).build().getResponse()
	 * .getDocsAs(Gamepojo.class); } else { // create Index // Create a design doc
	 * named designdoc // A view named querybyitemIdView // and an index named
	 * itemId db.createIndex("querybyitemIdView","designdoc","json", new
	 * IndexField[]{new IndexField("itemId",SortOrder.asc)});
	 * System.out.println("Successfully created index"); allDocs =
	 * db.findByIndex("{\"itemId\" : " + itemId + "}", Gamepojo.class); } } catch
	 * (Exception e) { System.out.println("Exception thrown : " + e.getMessage()); }
	 * return allDocs; }
	 */
	
	
	
	
	@GetMapping("/ctlusers")
	public ResponseEntity<List<CtlUsers>> getctlemployee()
	{
		List<CtlUsers> users = service.getallctluser();
		return new ResponseEntity<>(users, HttpStatus.OK);
		
	}
	
	@PostMapping("/ctlusers")
	public ResponseEntity<String> createctlemployee(@RequestBody List<CtlUsers> ctluser)
	{
		 for(int i=0;i<ctluser.size();i++)
		 {
		   int status= service.addctluser(ctluser.get(i));
		 }
		return new ResponseEntity<>("insert successfully", HttpStatus.OK);
		
	}
	
	
	
	
	
}
