package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.model.User;
import web.service.UserService;
import web.util.ControllerResponseMessage;
import web.util.InputDataFilter;

import java.util.Date;
import java.util.Optional;

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

	@GetMapping(value = "add", params = {"name!=", "birthdate!=", "gender!=", "phone!="})
	public String addUser(String name,  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date birthdate, String gender, String phone, ModelMap model, ControllerResponseMessage responseMessage, InputDataFilter dataFilter) {
		boolean valid = dataFilter.setGender(gender).setPhone(phone).validate();
		model = responseMessage.setModel(model)
				.setMessages("New user added", "Error: append failed")
				.selectMessage(valid && userService.addUser(new User(name, birthdate, dataFilter.getGender(), phone)));
		return getUsers(model);
	}

	@GetMapping(value = "update", params = {"id!=", "name!=", "birthdate!=", "gender!=", "phone!="})
	public String updateUser(long id, String name, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date birthdate, String gender, String phone, ModelMap model, ControllerResponseMessage responseMessage, InputDataFilter dataFilter) {
		boolean valid = dataFilter.setGender(gender).setPhone(phone).validate();
		model = responseMessage.setModel(model)
				.setMessages("User updated", "User not updated")
				.selectMessage(valid && userService.updateUser(new User(id, name, birthdate, dataFilter.getGender(), phone)));
		return getUsers(model);
	}

	@GetMapping("delete")
	public String removeUser(long id, ModelMap model, ControllerResponseMessage responseMessage) {
		model = responseMessage.setModel(model)
				.setMessages("User deleted", "User not found")
				.selectMessage(userService.deleteUser(id));
		return getUsers(model);
	}
}