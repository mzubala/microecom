package pl.com.bottega.microecom.user.controllers.registration;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

@PasswordsMustMatch
public class CreateAccountRequest {

    @NotBlank
    private String login;

    @NotBlank
    @Size(min = 6)
    private String password;


    private String repeatedPassword;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatedPassword() {
        return repeatedPassword;
    }

    public void setRepeatedPassword(String repeatedPassword) {
        this.repeatedPassword = repeatedPassword;
    }
}
