package com.yanhuahe.assignment4;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/register")
	public String showRegisterPage() {
		return "register";
	}
	
	@PostMapping("/register")
	public String registerUser(@RequestParam String username, @RequestParam String password, @RequestParam String typeOfUser, Model model) {
		if( (userService.findUserByUsername(username).isPresent())) {
            model.addAttribute("error", "User already exists");
            return "register";
        }
		User newUser = new User(username, password, typeOfUser);
		userService.registerUser(newUser);
		return "redirect:/users/login";
	}
	
	@GetMapping("/login")
	public String showLoginPage() {
		return "login";
	}
	
	@PostMapping("/login")
	public String loginUser(@RequestParam String username, @RequestParam String password, Model model) {
		Optional<User> user = userService.findUserByUsername(username);
		if (user.isPresent() && user.get().getPassword().equals(password)) {
            return "redirect:http://localhost:8085/orders/placeOrder";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
	}

}
