package en.haagahelia.course.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

import en.haagahelia.course.domain.SignupForm;
import en.haagahelia.course.domain.User;
import en.haagahelia.course.domain.UserRepository;

@Controller
public class UserController {
	@Autowired
    private UserRepository repository; 
	
	@RequestMapping(value="/userlist", method=RequestMethod.GET)
	public String userList(Model model) {
		model.addAttribute("userlist", repository.findAll());
		return "userlist";
	}
	
    //TODO method level security @Preauthorize - so only admins can delete
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value="/u/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") Long userId, Model model) {
    	repository.delete(userId);
    	return "redirect:/userlist";
    }
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value="/u/enable/{id}", method = RequestMethod.GET)
    public String enableUser(@PathVariable("id") Long userId, Model model) {
    	User user = repository.findOne(userId);
    	user.flipEnabled();
    	repository.save(user);
    	return "redirect:/userlist";
    }
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value="/u/elevate/{id}", method = RequestMethod.GET)
    public String elevateUser(@PathVariable("id") Long userId, Model model) {
    	User user = repository.findOne(userId);
    	user.flipRole();
    	repository.save(user);
    	return "redirect:/userlist";
    }
    
	//Signup controller adds an empty SignupForm object to model to store form data in
    @RequestMapping(value = "signup")
    public String addUser(Model model){
    	model.addAttribute("signupform", new SignupForm());
        return "signup";
    }	
    //This is javadoc notation that came default with this UserController implementation. It's used to document APIs
    /**
     * Create new user
     * Check if user already exists & form validation
     * 
     * @param signupForm
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "saveuser", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult) {
    	if (!bindingResult.hasErrors()) { // validation errors
    		if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) { // check password match		
	    		//if passwords matched, run them through BCrypt
    			String pwd = signupForm.getPassword();
		    	BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		    	String hashPwd = bc.encode(pwd);
		    	//Fill out a User object with form data
		    	User newUser = new User();
		    	newUser.setPasswordHash(hashPwd);
		    	newUser.setUsername(signupForm.getUsername());
		    	newUser.setRole("USER");
		    	newUser.setEmail(signupForm.getEmail());
		    	//Check if username exists
		    	if (repository.findByUsername(signupForm.getUsername()) == null) { // Check if user exists
		    		repository.save(newUser);
		    	}
		    	else {
	    			bindingResult.rejectValue("username", "err.username", "Username already exists");    	
	    			return "signup";		    		
		    	}
    		}
    		else {
    			bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");    	
    			return "signup";
    		}
    	}
    	else {
    		return "signup";
    	}
    	return "redirect:/index";    	
    }    
    
}
