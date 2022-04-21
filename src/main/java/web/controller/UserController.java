package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;
import web.util.Gender;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class UserController {
	@Autowired
	UserService userService;

	@GetMapping
	public String getUsers( ModelMap model ) {
		List<User> users = userService.getUsers();

		model.addAttribute( "users", users );
		return "index";
	}

	@GetMapping("add")
	public String addUser( String name, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date birthdate, String gender, String phone, ModelMap model ) {
		Optional<Gender> genderType = Arrays.stream( Gender.values() ).filter(v -> v.name().contains(gender.toUpperCase()) ).findFirst();
		boolean flag =  genderType.isPresent() && userService.addUser( new User(name, birthdate, genderType.get(), phone) );

		setResponse(flag, model, "New user added", "Error: append failed");
		return getUsers(model);
	}

	@GetMapping("update")
	public String updateUser( long id, String name, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date birthdate, String gender, String phone, ModelMap model ) {
		Optional<Gender> genderType = Arrays.stream( Gender.values() ).filter(v -> v.name().contains(gender.toUpperCase()) ).findFirst();

		setResponse( false, model, "User updated", "User not updated" );

		if( genderType.isPresent() ) {
			boolean flag = userService.updateUser( new User(id, name, birthdate, genderType.get(), phone) );

			setResponse( flag, model, "User updated", "User not updated" );
		}

		return getUsers(model);
	}

	@GetMapping("delete")
	public String removeUser( long id, ModelMap model ) {
		setResponse( userService.deleteUser(id), model, "User deleted", "User not found" );
		return getUsers(model);
	}

	private void setResponse( boolean flag, ModelMap model, String acomplished, String incomlished){
		final String attribute = "response";
		String msg = flag ? acomplished : incomlished;

		model.addAttribute(attribute, msg);
	}
}