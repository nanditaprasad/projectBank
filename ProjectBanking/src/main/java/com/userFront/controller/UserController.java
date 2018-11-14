package com.userFront.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.userFront.domain.User;
import com.userFront.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private BCryptPasswordEncoder PasswordEncoder;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profile(Principal principal, Model model) {
		User user = userService.findByUsername(principal.getName());

		model.addAttribute("user", user);

		return "profile";
	}
	
	@RequestMapping(value = "/password2", method = RequestMethod.GET)
	public String password2(Principal principal, Model model) {
		User user = userService.findByUsername(principal.getName());

		model.addAttribute("user", user);

		return "password2";
	}

	@RequestMapping(value = "/changeTransactionPassword", method = RequestMethod.GET)
	public String password3(Principal principal, Model model) {
		User user = userService.findByUsername(principal.getName());

		model.addAttribute("user", user);

		return "changeTransactionPassword";
	}

	
	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public String profilePost(@ModelAttribute("user") User newUser, Model model) {
		User user = userService.findByUsername(newUser.getUsername());
		user.setUsername(newUser.getUsername());
		user.setFirstName(newUser.getFirstName());
		user.setLastName(newUser.getLastName());
		user.setEmail(newUser.getEmail());
		user.setPhone(newUser.getPhone());
		
		model.addAttribute("user", user);

		userService.saveUser(user);

		return "profile";
	}

	@RequestMapping( value = "/password2", method = RequestMethod.POST )
	public String profilePost2(@RequestParam("oldpassword") String oldPassword ,@RequestParam("newpassword") String newPassword ,@RequestParam("retypepassword") String retypeNewPassword,@ModelAttribute("user") User newUser, Model model ) {
		User user = userService.findByUsername(newUser.getUsername());
		String encryptedPassword;
		String encryptOldPassword = PasswordEncoder.encode(oldPassword);
		System.out.println(user.getPassword());
		System.out.println(encryptOldPassword);
		System.out.println(newPassword);
		System.out.println(retypeNewPassword);
		System.out.println(newUser.getUsername());
		user.setUsername(newUser.getUsername());
		
		
		if(user.getPassword().equals(encryptOldPassword) && newPassword.equals(retypeNewPassword)){
		encryptedPassword = PasswordEncoder.encode(newPassword);
		user.setPassword(encryptedPassword);

		model.addAttribute("user", user);

		userService.saveUser(user);
		return "password2";
		}
//		else {
//			encryptedPassword = newUser.getPassword();
//			user.setPassword(encryptedPassword);
//		}
		return "password2";
	}

	@RequestMapping( value = "/changeTransactionPassword", method = RequestMethod.POST )
	public String profilePost3(@RequestParam("oldtransactionpassword") String oldTransactionPassword ,@RequestParam("newtransactionpassword") String newTransactionPassword ,@RequestParam("retypetransactionpassword") String retypeNewTransactionPassword,@ModelAttribute("user") User newUser, Model model ) {
		User user = userService.findByUsername(newUser.getUsername());
		String encryptedTransactionPassword;
		String encryptOldTransactionPassword = PasswordEncoder.encode(oldTransactionPassword);
		System.out.println(user.getPassword());
		System.out.println(encryptOldTransactionPassword);
		System.out.println(newTransactionPassword);
		System.out.println(retypeNewTransactionPassword);
		System.out.println(newUser.getUsername());
		user.setUsername(newUser.getUsername());
		
		
		if(user.getPassword().equals(encryptOldTransactionPassword) && newTransactionPassword.equals(retypeNewTransactionPassword)){
		encryptedTransactionPassword = PasswordEncoder.encode(newTransactionPassword);
		user.setPassword(encryptedTransactionPassword);

		model.addAttribute("user", user);

		userService.saveUser(user);
		return "changeTransactionPassword";
		}
//		else {
//			encryptedPassword = newUser.getPassword();
//			user.setPassword(encryptedPassword);
//		}
		return "changeTransactionPassword";
	}

	
	
	
}
