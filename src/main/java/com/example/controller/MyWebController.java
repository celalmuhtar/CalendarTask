package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.domain.User;
import com.example.services.UserService;

@Controller
public class MyWebController {
	@Autowired
	private UserService userSer;
	
	@RequestMapping(value="/index", method = RequestMethod.POST)
	public String indexPost(@RequestBody User user){
		String password = user.getPassword();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		System.out.println(hashedPassword);
		user.setPassword(hashedPassword);		
		userSer.insertUser(user);
		return "index";
	}
	
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public String indexGet( @RequestParam("username") String username,
							@RequestParam("password") String pass,
							@RequestParam("email") String email,
							@RequestParam("enabled") int enabled){
		User user = new User();
		String password = pass;
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		System.out.println(hashedPassword);
		user.setUsername(username);
		user.setPassword(hashedPassword);	
		user.setEmail(email);
		user.setEnabled(enabled);
		userSer.insertUser(user);
		return "index";
	}
	
	@RequestMapping(value={"/home", "/"}, method = RequestMethod.POST)
	public String homePost(){
		return "home";
	}
	
	@RequestMapping(value={"/home", "/"}, method = RequestMethod.GET)
	public String homeGet(){
		return "home";
	}
	
	@RequestMapping(value="/hellouser", method = RequestMethod.POST)
	public String hellouserPost(){
		return "hellouser";
	}
	
	@RequestMapping(value="/hellouser", method = RequestMethod.GET)
	public String hellouserGet(){
		return "hellouser";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String loginPost(){
		return "login";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String loginGet(){
       BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
       String hashedPassword = passwordEncoder.encode("pass");
       System.out.println(hashedPassword);
		return "login";
	}
	
	@RequestMapping(value="/hello", method = RequestMethod.POST)
	public String helloPost(){
		return "hello";
	}
	
	@RequestMapping(value="/hello", method = RequestMethod.GET)
	public String helloGet(){
		return "hello";
	}
	
	@RequestMapping(value="/403", method = RequestMethod.POST)
	public String post403(){
		return "err403";
	}
	
	@RequestMapping(value="/403", method = RequestMethod.GET)
	public String get403(){
		return "err403";
	}
	
	@RequestMapping(value="/new-task", method = RequestMethod.GET)
	public String newTask(){
		return "new-task";
	}
	
	
	@RequestMapping(value="/newuser", method = RequestMethod.POST)
    public String newuserPost(@RequestBody User user){
        return "newuser";
    }
    
    @RequestMapping(value="/newuser", method = RequestMethod.GET)
    public String newuserGet( @RequestParam("username") String username,
                            @RequestParam("password") String pass,
                            @RequestParam("email") String email,
                            @RequestParam("enabled") int enabled){
        return "newuser";
    }
    
    @RequestMapping(value="/form", method = RequestMethod.GET)
    public String formPost(){
        return "form";
    }
    
    @RequestMapping("/restricted")
    public String restricted() {
        return "restricted";
    } 
	
    @RequestMapping("/loremIpsum")
    public String loremIpsum() {
        return "loremIpsum";
    } 
    
    @RequestMapping("/registration")
    public String registration(){
    	return "registration";
    }
}
 