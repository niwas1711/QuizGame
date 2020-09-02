/*
 * package com.ibm.Triviatest;
 * 
 * import java.io.IOException;
 * 
 * import javax.servlet.ServletException; import
 * javax.servlet.http.HttpServletRequest; import
 * javax.servlet.http.HttpServletResponse;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Component; import
 * org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
 * 
 * @Component public class ConfigClass extends HandlerInterceptorAdapter {
 * 
 * 
 * @Autowired private HttpServletRequest request;
 * 
 * @Override public boolean preHandle(HttpServletRequest request,
 * HttpServletResponse response, Object handler) throws ServletException,
 * IOException {
 * 
 * System.out.println("I am hererr"); String parameter =
 * request.getHeader("Origin");
 * System.out.println("I am hererr"+request.getHeader("Origin"));
 * 
 * 
 * if (parameter.contains("ctltriviagame.mybluemix.net")) { return true; }else {
 * response.sendError(401, "donot access"); return false; }
 * 
 * }
 * 
 * }
 * 
 */