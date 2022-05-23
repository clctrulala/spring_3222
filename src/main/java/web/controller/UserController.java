package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.model.User;
import web.service.UserService;
import web.util.Gender;

import java.util.Date;

@Controller
@RequestMapping("/")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public String getUsers(ModelMap model) {
		model.addAttribute("users", userService.getUsers());
		return "index";
	}

	@PostMapping(value = "add", params = {"name!=", "birthdate!=", "gender!=", "phone!="})
	public String addUser(String name,  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date birthdate, Gender gender, String phone, ModelMap model) {
		userService.addUser(new User(name, birthdate, gender, phone));
		return getUsers(model);
	}

	@PostMapping(value = "update", params = {"id!=", "name!=", "birthdate!=", "gender!=", "phone!="})
	public String updateUser(long id, String name, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date birthdate, Gender gender, String phone, ModelMap model) {
		userService.updateUser(new User(id, name, birthdate, gender, phone));
		return getUsers(model);
	}

	@GetMapping("delete")
	public String removeUser(long id, ModelMap model) {
		userService.deleteUser(id);
		return getUsers(model);
	}
}