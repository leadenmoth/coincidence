package en.haagahelia.course.domain;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
//SignupForm class is used to store and validate data entered in sign-up form before it's saved to User class
public class SignupForm {
    @NotEmpty
    @Size(min=5, max=30)
    private String username = "";
    
    @NotEmpty
    @Size(min=5, max=30)
    private String email = "";

    @NotEmpty
    @Size(min=7, max=30)
    private String password = "";

    //Re-typed password is stored separately for later comparison
    @NotEmpty
    @Size(min=7, max=30)
    private String passwordCheck = "";
    
    //Default role is user
    @NotEmpty
    private String role = "USER";

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordCheck() {
		return passwordCheck;
	}

	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
    
    
}
